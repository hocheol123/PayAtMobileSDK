package com.whoopersoft.payat.partner;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class PayatService {
	private String action = "com.whoopersoft.payat.PAYMENT";		//����Ʈ �׼�

    public boolean checkIntentCallable(Context context) {		//���̾� ȣ�� ���� ���� ����(true ȣ�� ����, false ȣ�� �Ұ�)
    	PackageManager packageManager = context.getPackageManager();    
    	Intent intent = new Intent(action);    
    	List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);    
    	
    	return (list.size() > 0);
    }        
    
    //��ǰ�� ������
    //ī������� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ���ް���, �ΰ���, �����, ��ǰ����,
    //								   ���������� �ΰ������� (4096����Ʈ), ����, �� �̸���, �� ���� ��ȭ��ȣ, �� ���� ��ȭ��ȣ)
    public Intent setCardPay(String client_id, String client_secret, 
    										String store_screen_name, String employee_screen_name,
    										String amount, String tax, String fee, String comment,
											String additional_data, String customer_name, String customer_email, String customer_phone, String customer_mobile) {
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
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		return intent;
    }
    
    //��ǰ�� ������
    //ī������� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ���ް���, �ΰ���, �����, ��ǰ����,
    //								   ��ǰ��ȸ Ÿ��, ��ǰ�ڵ� �Ǵ� ��ȣ, ��ǰ����,
    //								   ���������� �ΰ������� (4096����Ʈ), ����, �� �̸���, �� ���� ��ȭ��ȣ, �� ���� ��ȭ��ȣ)
    public Intent setCardPayItem(String client_id, String client_secret, 
    												String store_screen_name, String employee_screen_name,
    												String amount, String tax, String fee, String comment,
    												String search_type, String item_code, String item_count,
    												String additional_data, String customer_name, String customer_email, String customer_phone, String customer_mobile) {
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
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		return intent;
    }
    
    //��ǰ�� ������
    //���ݰ����� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ���ް���, �ΰ���, �����, ��ǰ����,
    //								   ���������� �ΰ������� (4096����Ʈ), ����, �� �̸���, �� ���� ��ȭ��ȣ, �� ���� ��ȭ��ȣ)
    public Intent setCashPay(String client_id, String client_secret, 
											String store_screen_name, String employee_screen_name,
											String amount, String tax, String fee, String comment,
											String additional_data, String customer_name, String customer_email, String customer_phone, String customer_mobile) {
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
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		return intent;
    }
    
    //��ǰ�� ������ 
    //���ݰ����� ����Ʈ ���� (Ŭ���̾�Ʈ ���̵�, Ŭ���̾�Ʈ ��ũ��, ������ ���̵�, ���� ���� ���̵�, ���ް���, �ΰ���, �����, ��ǰ����,
    //								   ��ǰ��ȸ Ÿ��, ��ǰ�ڵ� �Ǵ� ��ȣ, ��ǰ����,
    //								   ���������� �ΰ������� (4096����Ʈ), ����, �� �̸���, �� ���� ��ȭ��ȣ, �� ���� ��ȭ��ȣ)
    public Intent setCashPayItem(String client_id, String client_secret, 
													String store_screen_name, String employee_screen_name,
													String amount, String tax, String fee, String comment,
													String search_type, String item_code, String item_count,
													String additional_data, String customer_name, String customer_email, String customer_phone, String customer_mobile) {
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
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		return intent;
	}
}
