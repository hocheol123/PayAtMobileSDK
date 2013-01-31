페이앳 모바일 SDK for Android
---------------------------------------
필요항목 : 발급받은 클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제직원 아이디

---------------------------------------
페이앳 설치 확인하기

<<<<<<< HEAD
필요항목 : 발급받은 클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제직원 아이디
>       String client_id;  //발급받은 클라이언트 아이디
      String client_secret;  //발급받은 시크릿
      String store_screen_name;  //가맹점 아이디
      String employee_screen_name;  //결제직원 아이디
              

직접결제 : 필요항목과 공급가액, 부가세, 봉사료
>     callCardPay(String client_id, String client_secret, String store_screen_name, String employee_screen_name, String amount, String tax, String fee)


callCardPay()로 Intent를 리턴받아 startActivity()로 페이앳을 불러옵니다.

현금결제시 callCashPay()를 사용합니다.

              
              
상품결제 : 필요항목과 상품조회 타입, 상품 번호나 코드, 각 상품의 개수
>     callCardPayItem(String client_id, String client_secret, String store_screen_name, String employee_screen_name, String search_type, String item_code, String item_count)

callCardPayItem()로 Intent를 리턴 받아 startActivity()로 페이앳을 불러옵니다.

현금결제시 callCashPayItem()를 사용합니다.


              
결제를 진행 후 다시 돌아 왔을때,
결과값을 BroadcastReceiver로 받습니다.
=======
    checkIntentCallable(this.getBaseContext())

>현재 사용하는 기기에 SDK 사용가능한 페이앳이 설치 되어있는지 여부를 판별합니다(가능 true, 불가 false)

---------------------------------------
직접결제

함수 호출시 필요항목과 공급가액, 부가세, 봉사료를 통해 페이앳을 불러옵니다.

현금결제

    setCashPay(String client_id, String client_secret, String store_screen_name, String employee_screen_name,
            String amount, String tax, String fee, String comment, String additional_data, 
            String customer_name, String customer_email, String customer_phone, String customer_mobile, boolean receipt_unissued)

>setCashPay()로 Intent를 return 받아 startActivity()로 페이앳을 실행합니다.

>필요인자 : 클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료, 상품설명,
상점관리용 부가데이터 (4096바이트), 고객명, 고객 이메일, 고객 유선 전화번호, 고객 무선 전화번호,
영수증 발행 여부(페이앳 영수증을 발행할건지 여부 - true일 경우 발행하지 않으며 결제결과를 바로 리턴시킵니다.)


카드결제

    setCardPay(String client_id, String client_secret, String store_screen_name, String employee_screen_name,
            String amount, String tax, String fee, String comment, String additional_data, 
            String customer_name, String customer_email, String customer_phone, String customer_mobile, boolean receipt_unissued)

>setCardPay()로 Intent를 return 받아 startActivity()로 페이앳을 실행합니다.

>필요인자 : 클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료, 상품설명,
상점관리용 부가데이터 (4096바이트), 고객명, 고객 이메일, 고객 유선 전화번호, 고객 무선 전화번호,
영수증 발행 여부(페이앳 영수증을 발행할건지 여부 - true일 경우 발행하지 않으며 결제결과를 바로 리턴시킵니다.)

---------------------------------------

상품결제

함수 호출시 필요항목과 상품조회 타입, 상품 번호나 코드, 각 상품의 개수를 통해 페이앳을 불러옵니다. 
상품번호로 상품조회 (no_in - 상품 번호로 조회, code_in - 상품 코드로 조회)
상품번호나 상품코드를 콤마(,)로 구분
각 상품의 개수 콤마(,)로 구분  

현금결제

      setCashPayItem(String client_id, String client_secret, String store_screen_name, String employee_screen_name,
                String amount, String tax, String fee, String comment,String search_type, String item_code, String item_count,
                String additional_data, String customer_name, String customer_email, String customer_phone, String customer_mobile, boolean receipt_unissued) 


>현금결제를 위한 정보를 이용해 Intent를 return 받아 startActivity()로 페이앳을 실행합니다.

>필요인자 : 클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료, 상품설명,
상품조회 타입, 상품코드 또는 번호, 상품개수, 상점관리용 부가데이터 (4096바이트), 고객명, 고객 이메일, 고객 유선 전화번호, 고객 무선 전화번호,
영수증 발행 여부(페이앳 영수증을 발행할건지 여부 - true일 경우 발행하지 않으며 결제결과를 바로 리턴시킵니다.)

카드결제

      setCardPayItem(String client_id, String client_secret, String store_screen_name, String employee_screen_name,
                String amount, String tax, String fee, String comment,String search_type, String item_code, String item_count,
                String additional_data, String customer_name, String customer_email, String customer_phone, String customer_mobile, boolean receipt_unissued) 


>카드결제를 위한 정보를 이용해 Intent를 return 받아 startActivity()로 페이앳을 실행합니다.

>필요인자 : 클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제 직원 아이디, 공급가액, 부가세, 봉사료, 상품설명,
상품조회 타입, 상품코드 또는 번호, 상품개수, 상점관리용 부가데이터 (4096바이트), 고객명, 고객 이메일, 고객 유선 전화번호, 고객 무선 전화번호,
영수증 발행 여부(페이앳 영수증을 발행할건지 여부 - true일 경우 발행하지 않으며 결제결과를 바로 리턴시킵니다.)
>>>>>>> 17f9edc78544d2a182cd639c9b393921a4c30534

>      public BroadcastReceiver paymentResultReceiver =  new BroadcastReceiver() {
         @Override
         public void onReceive(Context context, Intent intent) {
        	Log.i(TAG, intent.getExtras().getString("payment_result"));
         }
       };

카드결제와 현금결제 모두 동일한 방법으로 진행합니다.

<<<<<<< HEAD


checkIntentCallable()를 통해 페이앳 호출 여부를 알 수 있습니다.
>      callable = payatService.checkIntentCallable(this.getBaseContext());	
       if (callable){
         Log.i(TAG,"페이앳 호출 가능");
       }
       else{
         Log.i(TAG,"페이앳을 불러올 수 없습니다.");
       }

--------------------------------------------------------------------
=======
---------------------------------------

페이앳에서 넘어온 리턴값 처리 하기

BroadcastReceiver를 이용하여 결제 결과를 받습니다.
>>>>>>> 17f9edc78544d2a182cd639c9b393921a4c30534

    public BroadcastReceiver paymentResultReceiver =  new BroadcastReceiver() {      //결제결과 값을 받을 브로드캐스트
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

  
  결제 성공시 메시지
  ---------------------------------------
    //현금 결제 성공 
    
    code = 200;  
    message = "";
    net_id = AEGIS_VISAwhp_111111_20121227162233883058;    
    order_no = 23445;
    store  = {  //가맹점 정보 (Map)
	            "phone":"02-1234-1234",
	            "address":"서울 금천구 가산동 IT캐슬 2동 714호",
	            "bsn":"1111111111",
	            "mobile":"011-1234-1234",
	            "business_name":"페이앳공식테스트주식회사",
	            "zipcode":"153768",
	            "ceo_name":"페이앳"
	};
	employee = { //결제한 직원 아이디 정보 (Map)
	            "name":"슈파이더맨",
	            "email":"support@whoopersoft.com",
	            "mobile":"02-1234-1234",
	            "group_no":"7",
	            "screen_name":"111111",
	            "partner_no":"6",
	            "no":"7"
	};
  ---------------------------------------
    //현금 영수증 성공
    
    code = 200;  
    message = "";
    net_id = AEGIS_VISAwhp_111111_20121227162233883058;    
    order_no = 23445;
    approval_no = 000000000;
    store  = {  //가맹점 정보 (Map)
	            "phone":"02-1234-1234",
	            "address":"서울 금천구 가산동 IT캐슬 2동 714호",
	            "bsn":"1111111111",
	            "mobile":"011-1234-1234",
	            "business_name":"페이앳공식테스트주식회사",
	            "zipcode":"153768",
	            "ceo_name":"페이앳"
	};
	employee = { //결제한 직원 아이디 정보 (Map)
	            "name":"슈파이더맨",
	            "email":"support@whoopersoft.com",
	            "mobile":"02-1234-1234",
	            "group_no":"7",
	            "screen_name":"111111",
	            "partner_no":"6",
	            "no":"7"
	};
    
  ---------------------------------------
    //카드결제 성공
    
    code = 200;   
    message = "";
    net_id = AEGIS_VISAwhp_111111_20121227162233883058;    
    order_no = 23445;
    approval_no = 000000000;
    card_name = BC;
    card_type = 0100;
    send_no = 510084;
    store  = {  //가맹점 정보 (Map)
	            "phone":"02-1234-1234",
	            "address":"서울 금천구 가산동 IT캐슬 2동 714호",
	            "bsn":"1111111111",
	            "mobile":"011-1234-1234",
	            "business_name":"페이앳공식테스트주식회사",
	            "zipcode":"153768",
	            "ceo_name":"페이앳"
	};
	employee = { //결제한 직원 아이디 정보 (Map)
	            "name":"슈파이더맨",
	            "email":"support@whoopersoft.com",
	            "mobile":"02-1234-1234",
	            "group_no":"7",
	            "screen_name":"111111",
	            "partner_no":"6",
	            "no":"7"
	};
	
  ---------------------------------------
    //결제 실패 메시지

    code = 400;
    message = "에러 메시지";

---------------------------------------
API 문의

담당 : kmh@whoopersoft.com - 김명호 연구원 (주)후퍼소프트 psk@whoopersoft.com - 박승근 연구원 (주)후퍼소프트

제휴 문의

담당 : kwk@whoopersoft.com - 김웅겸 CTO (주)후퍼소프트
