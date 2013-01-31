package com.whoopersoft.payat.partner;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class PayatService {
	private String action = "com.whoopersoft.payat.PAYMENT";		//인텐트 액션

    public boolean checkIntentCallable(Context context) {		//페이앳 호출 가능 여부 리턴(true 호출 가능, false 호출 불가)
    	PackageManager packageManager = context.getPackageManager();    
    	Intent intent = new Intent(action);    
    	List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);    
    	
    	return (list.size() > 0);
    }        
    
    //상품이 없을시
    //카드결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료, 상품설명,
    //								   상점관리용 부가데이터 (4096바이트), 고객명, 고객 이메일, 고객 유선 전화번호, 고객 무선 전화번호, 영수증 발행 여부)
    public Intent setCardPay(String client_id, String client_secret, 
    			     String store_screen_name, String employee_screen_name,
    			     String amount, String tax, String fee, String comment,
			     String additional_data, String customer_name, String customer_email, 
			     String customer_phone, String customer_mobile, boolean receipt_unissued) {
    	Intent intent = new Intent(action);
		intent.putExtra("type", "card");
		intent.putExtra("client_id", client_id);
		intent.putExtra("client_secret", client_secret);
		intent.putExtra("store_screen_name", store_screen_name);
		intent.putExtra("employee_screen_name", employee_screen_name);
		intent.putExtra("amount", amount);
		intent.putExtra("tax", tax);
		intent.putExtra("fee", fee);
		intent.putExtra("comment", comment);
		intent.putExtra("additional_data", additional_data);
		intent.putExtra("customer_name", customer_name);
		intent.putExtra("customer_email", customer_email);
		intent.putExtra("customer_phone", customer_phone);
		intent.putExtra("customer_mobile", customer_mobile);
		intent.putExtra("receipt_unissued", receipt_unissued);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		return intent;
    }
    
    //상품이 있을시
    //카드결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료, 상품설명,
    //								   상품조회 타입, 상품코드 또는 번호, 상품개수,
    //								   상점관리용 부가데이터 (4096바이트), 고객명, 고객 이메일, 고객 유선 전화번호, 고객 무선 전화번호, 영수증 발행 여부)
    public Intent setCardPayItem(String client_id, String client_secret, 
    				 String store_screen_name, String employee_screen_name,
    				 String amount, String tax, String fee, String comment,
    				 String search_type, String item_code, String item_count,
    				 String additional_data, String customer_name, String customer_email, 
    				 String customer_phone, String customer_mobile, boolean receipt_unissued) {
    	Intent intent = new Intent(action);
		intent.putExtra("type", "card");
		intent.putExtra("client_id", client_id);
		intent.putExtra("client_secret", client_secret);
		intent.putExtra("store_screen_name", store_screen_name);
		intent.putExtra("employee_screen_name", employee_screen_name);
		intent.putExtra("amount", amount);
		intent.putExtra("tax", tax);
		intent.putExtra("fee", fee);
		intent.putExtra("comment", comment);
		intent.putExtra("search_type", search_type);
		intent.putExtra("item_code", item_code);
		intent.putExtra("item_count", item_count);
		intent.putExtra("additional_data", additional_data);
		intent.putExtra("customer_name", customer_name);
		intent.putExtra("customer_email", customer_email);
		intent.putExtra("customer_phone", customer_phone);
		intent.putExtra("customer_mobile", customer_mobile);
		intent.putExtra("receipt_unissued", receipt_unissued);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		return intent;
    }
    
    //상품이 없을시
    //현금결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료, 상품설명,
    //								   상점관리용 부가데이터 (4096바이트), 고객명, 고객 이메일, 고객 유선 전화번호, 고객 무선 전화번호, 영수증 발행 여부)
    public Intent setCashPay(String client_id, String client_secret, 
			     String store_screen_name, String employee_screen_name,
			     String amount, String tax, String fee, String comment,
			     String additional_data, String customer_name, String customer_email, 
			     String customer_phone, String customer_mobile, boolean receipt_unissued) {
    	Intent intent = new Intent(action);
		intent.putExtra("type", "cash");
		intent.putExtra("client_id", client_id);
		intent.putExtra("client_secret", client_secret);
		intent.putExtra("store_screen_name", store_screen_name);
		intent.putExtra("employee_screen_name", employee_screen_name);
		intent.putExtra("amount", amount);
		intent.putExtra("tax", tax);
		intent.putExtra("fee", fee);
		intent.putExtra("comment", comment);
		intent.putExtra("additional_data", additional_data);
		intent.putExtra("customer_name", customer_name);
		intent.putExtra("customer_email", customer_email);
		intent.putExtra("customer_phone", customer_phone);
		intent.putExtra("customer_mobile", customer_mobile);
		intent.putExtra("receipt_unissued", receipt_unissued);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		return intent;
    }
    
    //상품이 있을시 
    //현금결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료, 상품설명,
    //								   상품조회 타입, 상품코드 또는 번호, 상품개수,
    //								   상점관리용 부가데이터 (4096바이트), 고객명, 고객 이메일, 고객 유선 전화번호, 고객 무선 전화번호, 영수증 발행 여부)
    public Intent setCashPayItem(String client_id, String client_secret, 
				 String store_screen_name, String employee_screen_name,
				 String amount, String tax, String fee, String comment,
				 String search_type, String item_code, String item_count,
				 String additional_data, String customer_name, String customer_email, 
				 String customer_phone, String customer_mobile, boolean receipt_unissued) {
		Intent intent = new Intent(action);
		intent.putExtra("type", "card");
		intent.putExtra("client_id", client_id);
		intent.putExtra("client_secret", client_secret);
		intent.putExtra("store_screen_name", store_screen_name);
		intent.putExtra("employee_screen_name", employee_screen_name);
		intent.putExtra("amount", amount);
		intent.putExtra("tax", tax);
		intent.putExtra("fee", fee);
		intent.putExtra("comment", comment);
		intent.putExtra("search_type", search_type);
		intent.putExtra("item_code", item_code);
		intent.putExtra("item_count", item_count);
		intent.putExtra("additional_data", additional_data);
		intent.putExtra("customer_name", customer_name);
		intent.putExtra("customer_email", customer_email);
		intent.putExtra("customer_phone", customer_phone);
		intent.putExtra("customer_mobile", customer_mobile);
		intent.putExtra("receipt_unissued", receipt_unissued);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		return intent;
	}
}
