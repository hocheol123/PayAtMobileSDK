페이앳 모바일 SDK for Android

--------------------------------------------------------------------

필요항목 : 발급받은 클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제직원 아이디
>       private String client_id;  //발급받은 클라이언트 아이디
      private String client_secret;  //발급받은 시크릿
      private String store_screen_name;  //가맹점 아이디
      private String employee_screen_name;  //결제직원 아이디
              

직접결제 : 필요항목과 공급가액, 부가세, 봉사료
>     callCardPay(String client_id, String client_secret, String store_screen_name, String employee_screen_name, String amount, String tax, String fee)


callCardPay()로 Intent를 리턴받아 startActivity()로 페이앳을 불러옵니다.

              
              
상품결제 : 필요항목과 상품조회 타입, 상품 번호나 코드, 각 상품의 개수
>     callCardPayItem(String client_id, String client_secret, String store_screen_name, String employee_screen_name, String search_type, String item_code, String item_count)

callCardPayItem()로 Intent를 리턴 받아 startActivity()로 페이앳을 불러옵니다.

              
결제를 진행 후 다시 돌아 왔을때,
결과값을 BroadcastReceiver로 받습니다.

>      public BroadcastReceiver paymentResultReceiver =  new BroadcastReceiver() {
         @Override
         public void onReceive(Context context, Intent intent) {
        	Log.i(TAG, intent.getExtras().getString("payment_result"));
         }
       };

카드결제와 현금결제 모두 동일한 방법으로 진행합니다.



checkIntentCallable()를 통해 페이앳 호출 여부를 알 수 있습니다.
>      callable = payatService.checkIntentCallable(this.getBaseContext());	
       if (callable){
         Log.i(TAG,"페이앳 호출 가능");
       }
       else{
         Log.i(TAG,"페이앳을 불러올 수 없습니다.");
       }

--------------------------------------------------------------------

API 문의

담당 : kmh@whoopersoft.com - 김명호 연구원 (주)후퍼소프트
      psk@whoopersoft.com - 박승근 연구원 (주)후퍼소프트
      
제휴 문의

담당 : kwk@whoopersoft.com - 김웅겸 CTO (주)후퍼소프트
