package com.whoopersoft.payat.partner;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class PayatService {
	private String action = "com.whoopersoft.payat.PAYMENT";		//인텐트 액션

    public boolean checkIntentCallable(Context context) {		//페이앳 호출 가능 여부 리턴(true면 호출 가능, false면 호출 불가)
    	PackageManager packageManager = context.getPackageManager();    
    	Intent intent = new Intent(action);    
    	List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);    
    	
    	return (list.size() > 0);
    }        
    
    //상품이 없을시
    //카드결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료)
    public Intent callCardPay(String client_id, String client_secret, 
    												String store_screen_name, String employee_screen_name,
    												String amount, String tax, String fee) {
    	Intent intent = new Intent(action);
		intent.putExtra("type", "card");
		intent.putExtra("client_id", client_id);
		intent.putExtra("client_secret", client_secret);
		intent.putExtra("store_screen_name", store_screen_name);
		intent.putExtra("employee_screen_name", employee_screen_name);
		intent.putExtra("amount", amount);
		intent.putExtra("tax", tax);
		intent.putExtra("fee", fee);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		return intent;
    }
    
    //상품이 있을시
    //카드결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 상품조회 타입, 상품코드 또는 번호, 상품개수)
    public Intent callCardPayItem(String client_id, String client_secret, 
    												String store_screen_name, String employee_screen_name,
    												String search_type, String item_code, String item_count) {
    	Intent intent = new Intent(action);
		intent.putExtra("type", "card");
		intent.putExtra("client_id", client_id);
		intent.putExtra("client_secret", client_secret);
		intent.putExtra("store_screen_name", store_screen_name);
		intent.putExtra("employee_screen_name", employee_screen_name);
		intent.putExtra("search_type", search_type);
		intent.putExtra("item_code", item_code);
		intent.putExtra("item_count", item_count);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		return intent;
    }
    
    //상품이 없을시
    //현금결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 상품코드, 공급가액, 부가세, 봉사료)
    public Intent callCashPay(String client_id, String client_secret, 
													String store_screen_name, String employee_screen_name,
													String amount, String tax, String fee) {
    	Intent intent = new Intent(action);
		intent.putExtra("type", "cash");
		intent.putExtra("client_id", client_id);
		intent.putExtra("client_secret", client_secret);
		intent.putExtra("store_screen_name", store_screen_name);
		intent.putExtra("employee_screen_name", employee_screen_name);
		intent.putExtra("amount", amount);
		intent.putExtra("tax", tax);
		intent.putExtra("fee", fee);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		return intent;
    }
    
    //상품이 있을시 
    //현금결제시 인텐트 리턴 (클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 상품조회 타입, 상품코드 또는 번호, 상품개수)
    public Intent callCashPayItem(String client_id, String client_secret, 
													String store_screen_name, String employee_screen_name,
													String search_type, String item_code, String item_count) {
    	Intent intent = new Intent(action);
		intent.putExtra("type", "cash");
		intent.putExtra("client_id", client_id);
		intent.putExtra("client_secret", client_secret);
		intent.putExtra("store_screen_name", store_screen_name);
		intent.putExtra("employee_screen_name", employee_screen_name);
		intent.putExtra("search_type", search_type);
		intent.putExtra("item_code", item_code);
		intent.putExtra("item_count", item_count);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		return intent;
    }
}
