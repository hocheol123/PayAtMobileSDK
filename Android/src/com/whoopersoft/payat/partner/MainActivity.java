package com.whoopersoft.payat.partner;

import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
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
	
	//��ǰ���� ��ȸ Ÿ��(��ǰ �ڵ� �Ǵ� ��ȣ�� ��ȸ), ��ǰ�ڵ� �Ǵ� ��ȣ, �� ��ǰ ����, ���ް���(�����ݾ׿��� VAT�� �ݾ�), �ΰ���, �����, ��ǰ����
	private String search_type, item_code, item_count, amount, tax, fee, comment;
	//���������� �ΰ������� (4096����Ʈ), ����, �� �̸���, �� ���� ��ȭ��ȣ, �� ���� ��ȭ��ȣ
	private String additional_data, customer_name, customer_email, customer_phone, customer_mobile;
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
			callCardPayItem();
		}
		else if(v == btn_cash_payment){
			callCashPay();
		}
	}

    public BroadcastReceiver paymentResultReceiver =  new BroadcastReceiver() {	//������� ���� ���� ��ε�ĳ��Ʈ
        @Override
        public void onReceive(Context context, Intent intent) {
        	try{
				HashMap<String, Object> data = (HashMap<String, Object>)intent.getSerializableExtra("payment_result");
				Iterator<String> iterator = data.keySet().iterator();
	    	    while (iterator.hasNext()) {
			        String key = (String) iterator.next();
			        Log.i(TAG, "key = " + key + ",   value = "+data.get(key));
	    	    }
        	}
        	catch(Exception e){
        		e.printStackTrace();
        	}
        }
    };
    
    public void callCardPayItem() {		//��ǰ����� ������, ī�����â ȣ��
    	amount = "1090";		//���ް���
    	tax = "110";					//�ΰ���
    	fee = "0";					//�����
    	search_type = "no_in";	//��ǰ��ȣ�� ��ǰ��ȸ (no_in - ��ǰ ��ȣ�� ��ȸ, code_in - ��ǰ �ڵ�� ��ȸ)
    	item_code = "1947,1859";	//��ǰ ��ȣ�� ��ȸ�̹Ƿ� ��ǰ ��ȣ�� �޸�(,)�� ����
    	item_count = "2,1";		//�� ��ǰ�� ���� �޸�(,)�� ����   
    	
    	///�ɼ� �ʵ� (�ɼ����� �߰��� �� �ֽ��ϴ�)
    	comment = "�ȵ�κ��� 1��, ���� 2��";		//��ǰ����(20�� �̳�)
    	additional_data = "��� ��Ʈ=���ǿ� �ð��ּ���";		//���������� �ΰ������� (4096����Ʈ)
    	customer_name = "������";		//����
    	customer_email = "help@whoopersoft.com";		//�� �̸���
    	customer_phone = "0248864885";		//�� ���� ��ȭ��ȣ
    	customer_mobile = "01088880917";		//�� ���� ��ȭ��ȣ

        //��ǰ�� ������
        //ī������� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ���ް���, �ΰ���, �����, ��ǰ����,
        //								   ��ǰ��ȸ Ÿ��, ��ǰ�ڵ� �Ǵ� ��ȣ, ��ǰ����,
        //								   ���������� �ΰ������� (4096����Ʈ), ����, �� �̸���, �� ���� ��ȭ��ȣ, �� ���� ��ȭ��ȣ)
    	Intent intent = payatService.setCardPayItem(client_id, client_secret, store_screen_name, employee_screen_name, 
    			 														 amount, tax, fee, comment,
    																	 search_type, item_code, item_count,
    																	 additional_data, customer_name, customer_email, customer_phone, customer_mobile);		
		startActivity(intent);
    }
    
    public void callCardPay() {		//��ǰ����� ������, ī�����â ȣ��
    	amount = "912";		//���ް���
    	tax = "92";					//�ΰ���
    	fee = "0";					//�����
    	
    	///�ɼ� �ʵ� (�ɼ����� �߰��� �� �ֽ��ϴ�)
    	comment = "���ϸ���";		//��ǰ����(20�� �̳�)
    	additional_data = "��� ��Ʈ=���ǿ� �ð��ּ���";		//���������� �ΰ������� (4096����Ʈ)
    	customer_name = "������";		//����
    	customer_email = "help@whoopersoft.com";		//�� �̸���
    	customer_phone = "0248864885";		//�� ���� ��ȭ��ȣ
    	customer_mobile = "01088880917";		//�� ���� ��ȭ��ȣ
		
		//���ݰ����� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ���ް���, �ΰ���, �����, ��ǰ����,
        //								   ���������� �ΰ������� (4096����Ʈ), ����, �� �̸���, �� ���� ��ȭ��ȣ, �� ���� ��ȭ��ȣ)
    	Intent intent = payatService.setCardPay(client_id, client_secret, store_screen_name, employee_screen_name, amount, tax, fee, comment,
    																additional_data, customer_name, customer_email, customer_phone, customer_mobile);	
		startActivity(intent);
    }

    
    public void callCashPayItem() {		//��ǰ����� ������, ���ݰ���â ȣ��
    	amount = "912";		//���ް���
    	tax = "92";					//�ΰ���
    	fee = "0";					//�����
    	search_type = "no_in";	//��ǰ��ȣ�� ��ǰ��ȸ (no_in - ��ǰ ��ȣ�� ��ȸ, code_in - ��ǰ �ڵ�� ��ȸ)
    	item_code = "1859";	//��ǰ ��ȣ�� ��ȸ�̹Ƿ� ��ǰ ��ȣ�� �޸�(,)�� ����
    	item_count = "5";		//�� ��ǰ�� ���� �޸�(,)�� ����   
    	
    	///�ɼ� �ʵ� (�ɼ����� �߰��� �� �ֽ��ϴ�)
    	comment = "�ȵ�κ��� 5��";		//��ǰ����(20�� �̳�)
    	additional_data = "��� ��Ʈ=���ǿ� �ð��ּ���";		//���������� �ΰ������� (4096����Ʈ)
    	customer_name = "������";		//����
    	customer_email = "help@whoopersoft.com";		//�� �̸���
    	customer_phone = "0248864885";		//�� ���� ��ȭ��ȣ
    	customer_mobile = "01088880917";		//�� ���� ��ȭ��ȣ

        //��ǰ�� ������
        //ī������� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ���ް���, �ΰ���, �����, ��ǰ����,
        //								   ��ǰ��ȸ Ÿ��, ��ǰ�ڵ� �Ǵ� ��ȣ, ��ǰ����,
        //								   ���������� �ΰ������� (4096����Ʈ), ����, �� �̸���, �� ���� ��ȭ��ȣ, �� ���� ��ȭ��ȣ)
    	Intent intent = payatService.setCashPayItem(client_id, client_secret, store_screen_name, employee_screen_name, 
    			 														 amount, tax, fee, comment,
    																	 search_type, item_code, item_count,
    																	 additional_data, customer_name, customer_email, customer_phone, customer_mobile);		
		startActivity(intent);
    }
    
    public void callCashPay() {		//��ǰ����� ������, ���ݰ���â ȣ��
    	amount = "912";		//���ް���
    	tax = "92";					//�ΰ���
    	fee = "0";					//�����
    	
    	///�ɼ� �ʵ� (�ɼ����� �߰��� �� �ֽ��ϴ�)
    	comment = "���ڿ���";		//��ǰ����(20�� �̳�)
    	additional_data = "��� ��Ʈ=���ǿ� �ð��ּ���";		//���������� �ΰ������� (4096����Ʈ)
    	customer_name = "������";		//����
    	customer_email = "help@whoopersoft.com";		//�� �̸���
    	customer_phone = "0248864885";		//�� ���� ��ȭ��ȣ
    	customer_mobile = "01088880917";		//�� ���� ��ȭ��ȣ
		
		//���ݰ����� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ���ް���, �ΰ���, �����, ��ǰ����,
        //								   ���������� �ΰ������� (4096����Ʈ), ����, �� �̸���, �� ���� ��ȭ��ȣ, �� ���� ��ȭ��ȣ)
    	Intent intent = payatService.setCashPay(client_id, client_secret, store_screen_name, employee_screen_name, amount, tax, fee, comment,
    															  additional_data, customer_name, customer_email, customer_phone, customer_mobile);	
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
