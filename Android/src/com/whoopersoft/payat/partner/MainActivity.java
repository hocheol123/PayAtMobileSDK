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
	private static String TAG = "PartnerPayat";		//로그 태그
	
	private TextView txt_callable;				//페이앳 호출 가능 여부 텍스트
	private Button btn_card_payment;		//카드결제 버튼 
	private Button btn_cash_payment;		//현금결제 버튼 
	
	private PayatService payatService;		//페이앳 API
	private String client_id = "aegisep";	//발급받은 클라이언트 아이디
	private String client_secret = "7adb3bdb22db89f220549925e27e53853c5f20dc8976a06d4513016718b9da96";		//발급받은 시크릿
	private String store_screen_name = "111111";			//가맹점 아이디
	private String employee_screen_name = "111111";		//결제 직원 아이디
	
	//상품정보 조회 타입(상품 코드 또는 번호로 조회), 상품코드 또는 번호, 각 상품 개수, 공급가액(최종금액에서 VAT뺀 금액), 부가세, 봉사료
	private String search_type, item_code, item_count, amount, tax, fee;       	
	private boolean callable = false;		//페이앳 호출 가능 여부

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        registerReceiver(paymentResultReceiver, new IntentFilter(Intent.ACTION_MAIN));		//브로드캐스트 등록
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
        unregisterReceiver(paymentResultReceiver);		//브로드캐스트 해제
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

    public BroadcastReceiver paymentResultReceiver =  new BroadcastReceiver() {	//결제결과 값을 받을 브로드캐스트
        @Override
        public void onReceive(Context context, Intent intent) {
        	Log.i(TAG, intent.getExtras().getString("payment_result"));
        }
    };
    
    public void callPayat_Card() {		//카드결제창 호출
    	search_type = "no_in";	//상품번호로 상품조회 (no_in - 상품 번호로 조회
    																//    code_in - 상품 코드로 조회)
    	item_code = "1885,1859,1948";	//상품 번호로 조회이므로 상품 번호를 콤마(,)로 구분
    	item_count = "2,4,1";		//각 상품의 개수 콤마(,)로 구분   
		
		//상품이 있을때 카드결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 상품조회 타입, 상품번호, 상품개수)
    	Intent intent = payatService.callCardPayItem(client_id, client_secret, store_screen_name, employee_screen_name, search_type, item_code, item_count);		
		startActivity(intent);
    }
    
    public void callPayat_Cash() {		//현금결제창 호출
    	
    	//등록된 상품이 아닐경우
    	amount = "912";		//공급가액
    	tax = "92";					//부가세
    	fee = "0";					//봉사료
		
		//현금결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료)
    	Intent intent = payatService.callCashPay(client_id, client_secret, store_screen_name, employee_screen_name, amount, tax, fee);	
		startActivity(intent);
    }
    
    public void checkCallable() {
    	callable = payatService.checkIntentCallable(this.getBaseContext());			//페이앳 호출 가능 여부 리턴(가능 true, 불가 false)
    	
    	if (callable){
    		txt_callable.setText("페이앳 호출 가능");
    		btn_card_payment.setEnabled(true);
    		btn_cash_payment.setEnabled(true);
    	}
    	else{
    		txt_callable.setText("페이앳을 불러올 수 없습니다.");
    		btn_card_payment.setEnabled(false);
    		btn_cash_payment.setEnabled(false);
    	}
    }
}
