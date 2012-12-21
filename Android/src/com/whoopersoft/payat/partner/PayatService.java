package com.whoopersoft.payat.partner;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class PayatService {
	private String action = "com.whoopersoft.payat.PAYMENT";		//����Ʈ �׼�

    public boolean checkIntentCallable(Context context) {		//���̾� ȣ�� ���� ���� ����(true�� ȣ�� ����, false�� ȣ�� �Ұ�)
    	PackageManager packageManager = context.getPackageManager();    
    	Intent intent = new Intent(action);    
    	List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);    
    	
    	return (list.size() > 0);
    }        
    
    //��ǰ�� ������
    //ī������� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ���ް���, �ΰ���, �����)
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
    
    //��ǰ�� ������
    //ī������� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ��ǰ��ȸ Ÿ��, ��ǰ�ڵ� �Ǵ� ��ȣ, ��ǰ����)
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
    
    //��ǰ�� ������
    //���ݰ����� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ��ǰ�ڵ�, ���ް���, �ΰ���, �����)
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
    
    //��ǰ�� ������ 
    //���ݰ����� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ��ǰ��ȸ Ÿ��, ��ǰ�ڵ� �Ǵ� ��ȣ, ��ǰ����)
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
