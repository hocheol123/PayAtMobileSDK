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
	private static String TAG = "PartnerPayat";		//로그 태그
	
	private TextView txt_callable;				//페이앳 호출 가능 여부 텍스트
	private Button btn_card_payment;		//카드결제 버튼 
	private Button btn_cash_payment;		//현금결제 버튼 
	
	private PayatService payatService;		//페이앳 API
	private String client_id = "aegisep";	//발급받은 클라이언트 아이디
	private String client_secret = "7adb3bdb22db89f220549925e27e53853c5f20dc8976a06d4513016718b9da96";		//발급받은 시크릿
	private String store_screen_name = "111111";			//가맹점 아이디
	private String employee_screen_name = "111111";		//결제 직원 아이디
	
	//상품정보 조회 타입(상품 코드 또는 번호로 조회), 상품코드 또는 번호, 각 상품 개수, 공급가액(최종금액에서 VAT뺀 금액), 부가세, 봉사료, 상품설명
	private String search_type, item_code, item_count, amount, tax, fee, comment;
	//상점관리용 부가데이터 (4096바이트), 고객명, 고객 이메일, 고객 유선 전화번호, 고객 무선 전화번호
	private String additional_data, customer_name, customer_email, customer_phone, customer_mobile;
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
			callCardPayItem();
		}
		else if(v == btn_cash_payment){
			callCashPay();
		}
	}

    public BroadcastReceiver paymentResultReceiver =  new BroadcastReceiver() {	//결제결과 값을 받을 브로드캐스트
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
    
    public void callCardPayItem() {		//상품목록이 있을때, 카드결제창 호출
    	amount = "1090";		//공급가액
    	tax = "110";					//부가세
    	fee = "0";					//봉사료
    	search_type = "no_in";	//상품번호로 상품조회 (no_in - 상품 번호로 조회, code_in - 상품 코드로 조회)
    	item_code = "1947,1859";	//상품 번호로 조회이므로 상품 번호를 콤마(,)로 구분
    	item_count = "2,1";		//각 상품의 개수 콤마(,)로 구분   
    	
    	///옵션 필드 (옵션으로 추가할 수 있습니다)
    	comment = "안드로보이 1개, 소주 2병";		//상품설명(20자 이내)
    	additional_data = "배송 노트=경비실에 맡겨주세요";		//상점관리용 부가데이터 (4096바이트)
    	customer_name = "김후퍼";		//고객명
    	customer_email = "help@whoopersoft.com";		//고객 이메일
    	customer_phone = "0248864885";		//고객 유선 전화번호
    	customer_mobile = "01088880917";		//고객 무선 전화번호

        //상품이 있을시
        //카드결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료, 상품설명,
        //								   상품조회 타입, 상품코드 또는 번호, 상품개수,
        //								   상점관리용 부가데이터 (4096바이트), 고객명, 고객 이메일, 고객 유선 전화번호, 고객 무선 전화번호)
    	Intent intent = payatService.setCardPayItem(client_id, client_secret, store_screen_name, employee_screen_name, 
    			 														 amount, tax, fee, comment,
    																	 search_type, item_code, item_count,
    																	 additional_data, customer_name, customer_email, customer_phone, customer_mobile);		
		startActivity(intent);
    }
    
    public void callCardPay() {		//상품목록이 없을때, 카드결제창 호출
    	amount = "912";		//공급가액
    	tax = "92";					//부가세
    	fee = "0";					//봉사료
    	
    	///옵션 필드 (옵션으로 추가할 수 있습니다)
    	comment = "자일리톨";		//상품설명(20자 이내)
    	additional_data = "배송 노트=경비실에 맡겨주세요";		//상점관리용 부가데이터 (4096바이트)
    	customer_name = "김후퍼";		//고객명
    	customer_email = "help@whoopersoft.com";		//고객 이메일
    	customer_phone = "0248864885";		//고객 유선 전화번호
    	customer_mobile = "01088880917";		//고객 무선 전화번호
		
		//현금결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료, 상품설명,
        //								   상점관리용 부가데이터 (4096바이트), 고객명, 고객 이메일, 고객 유선 전화번호, 고객 무선 전화번호)
    	Intent intent = payatService.setCardPay(client_id, client_secret, store_screen_name, employee_screen_name, amount, tax, fee, comment,
    																additional_data, customer_name, customer_email, customer_phone, customer_mobile);	
		startActivity(intent);
    }

    
    public void callCashPayItem() {		//상품목록이 있을때, 현금결제창 호출
    	amount = "912";		//공급가액
    	tax = "92";					//부가세
    	fee = "0";					//봉사료
    	search_type = "no_in";	//상품번호로 상품조회 (no_in - 상품 번호로 조회, code_in - 상품 코드로 조회)
    	item_code = "1859";	//상품 번호로 조회이므로 상품 번호를 콤마(,)로 구분
    	item_count = "5";		//각 상품의 개수 콤마(,)로 구분   
    	
    	///옵션 필드 (옵션으로 추가할 수 있습니다)
    	comment = "안드로보이 5개";		//상품설명(20자 이내)
    	additional_data = "배송 노트=경비실에 맡겨주세요";		//상점관리용 부가데이터 (4096바이트)
    	customer_name = "김후퍼";		//고객명
    	customer_email = "help@whoopersoft.com";		//고객 이메일
    	customer_phone = "0248864885";		//고객 유선 전화번호
    	customer_mobile = "01088880917";		//고객 무선 전화번호

        //상품이 있을시
        //카드결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료, 상품설명,
        //								   상품조회 타입, 상품코드 또는 번호, 상품개수,
        //								   상점관리용 부가데이터 (4096바이트), 고객명, 고객 이메일, 고객 유선 전화번호, 고객 무선 전화번호)
    	Intent intent = payatService.setCashPayItem(client_id, client_secret, store_screen_name, employee_screen_name, 
    			 														 amount, tax, fee, comment,
    																	 search_type, item_code, item_count,
    																	 additional_data, customer_name, customer_email, customer_phone, customer_mobile);		
		startActivity(intent);
    }
    
    public void callCashPay() {		//상품목록이 없을때, 현금결제창 호출
    	amount = "912";		//공급가액
    	tax = "92";					//부가세
    	fee = "0";					//봉사료
    	
    	///옵션 필드 (옵션으로 추가할 수 있습니다)
    	comment = "초코우유";		//상품설명(20자 이내)
    	additional_data = "배송 노트=경비실에 맡겨주세요";		//상점관리용 부가데이터 (4096바이트)
    	customer_name = "김후퍼";		//고객명
    	customer_email = "help@whoopersoft.com";		//고객 이메일
    	customer_phone = "0248864885";		//고객 유선 전화번호
    	customer_mobile = "01088880917";		//고객 무선 전화번호
		
		//현금결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료, 상품설명,
        //								   상점관리용 부가데이터 (4096바이트), 고객명, 고객 이메일, 고객 유선 전화번호, 고객 무선 전화번호)
    	Intent intent = payatService.setCashPay(client_id, client_secret, store_screen_name, employee_screen_name, amount, tax, fee, comment,
    															  additional_data, customer_name, customer_email, customer_phone, customer_mobile);	
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
