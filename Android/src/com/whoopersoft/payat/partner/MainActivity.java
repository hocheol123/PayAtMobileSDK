package com.whoopersoft.payat.partner;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	private static String TAG = "PartnerPayat";		//�α� �±�
	
	private TextView txt_callable;				//���̾� ȣ�� ���� ���� �ؽ�Ʈ
	private Button btn_card_payment;		//ī����� ��ư 
	private Button btn_cash_payment;		//���ݰ��� ��ư 
	
	private PayatService payatService;		//���̾� API
	private String client_id = "aegisep";	//�߱޹��� Ŭ���̾�Ʈ ���̵�
	private String client_secret = "7adb3bdb22db89f220549925e27e53853c5f20dc8976a06d4513016718b9da96";		//�߱޹��� ��ũ��
	private String store_screen_name = "111111";			//������ ���̵�
	private String employee_screen_name = "111111";		//���� ���� ���̵�
	
	//��ǰ���� ��ȸ Ÿ��(��ǰ �ڵ� �Ǵ� ��ȣ�� ��ȸ), ��ǰ�ڵ� �Ǵ� ��ȣ, �� ��ǰ ����, ���ް���(�����ݾ׿��� VAT�� �ݾ�), �ΰ���, �����
	private String search_type, item_code, item_count, amount, tax, fee;       	
	private boolean callable = false;		//���̾� ȣ�� ���� ����

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        registerReceiver(paymentResultReceiver, new IntentFilter(Intent.ACTION_MAIN));		//��ε�ĳ��Ʈ ���
        txt_callable = (TextView) findViewById (R.id.txt_callable);
        btn_card_payment = (Button) findViewById (R.id.btn_card_payment);
        btn_cash_payment = (Button) findViewById (R.id.btn_cash_payment);
        
        btn_card_payment.setOnClickListener(this);
        btn_cash_payment.setOnClickListener(this);
        payatService = new PayatService();
        checkCallable();
    }
    
    @Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume()");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
        unregisterReceiver(paymentResultReceiver);		//��ε�ĳ��Ʈ ����
	}

	@Override
	public void onClick(View v) {
		if(v == btn_card_payment){
			callPayat_Card();
		}
		else if(v == btn_cash_payment){
			callPayat_Cash();
		}
	}

    public BroadcastReceiver paymentResultReceiver =  new BroadcastReceiver() {	//������� ���� ���� ��ε�ĳ��Ʈ
        @Override
        public void onReceive(Context context, Intent intent) {
        	Log.i(TAG, intent.getExtras().getString("payment_result"));
        }
    };
    
    public void callPayat_Card() {		//ī�����â ȣ��
    	search_type = "no_in";	//��ǰ��ȣ�� ��ǰ��ȸ (no_in - ��ǰ ��ȣ�� ��ȸ
    																//    code_in - ��ǰ �ڵ�� ��ȸ)
    	item_code = "1885,1859,1948";	//��ǰ ��ȣ�� ��ȸ�̹Ƿ� ��ǰ ��ȣ�� �޸�(,)�� ����
    	item_count = "2,4,1";		//�� ��ǰ�� ���� �޸�(,)�� ����   
		
		//��ǰ�� ������ ī������� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ��ǰ��ȸ Ÿ��, ��ǰ��ȣ, ��ǰ����)
    	Intent intent = payatService.callCardPayItem(client_id, client_secret, store_screen_name, employee_screen_name, search_type, item_code, item_count);		
		startActivity(intent);
    }
    
    public void callPayat_Cash() {		//���ݰ���â ȣ��
    	
    	//��ϵ� ��ǰ�� �ƴҰ��
    	amount = "912";		//���ް���
    	tax = "92";					//�ΰ���
    	fee = "0";					//�����
		
		//���ݰ����� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ���ް���, �ΰ���, �����)
    	Intent intent = payatService.callCashPay(client_id, client_secret, store_screen_name, employee_screen_name, amount, tax, fee);	
		startActivity(intent);
    }
    
    public void checkCallable() {
    	callable = payatService.checkIntentCallable(this.getBaseContext());			//���̾� ȣ�� ���� ���� ����(���� true, �Ұ� false)
    	
    	if (callable){
    		txt_callable.setText("���̾� ȣ�� ����");
    		btn_card_payment.setEnabled(true);
    		btn_cash_payment.setEnabled(true);
    	}
    	else{
    		txt_callable.setText("���̾��� �ҷ��� �� �����ϴ�.");
    		btn_card_payment.setEnabled(false);
    		btn_cash_payment.setEnabled(false);
    	}
    }
}
