페이앳 모바일 SDK for IOS
---------------------------------------
필요항목 : 발급받은 클라이언트 아이디, 클라이언트 시크릿, 가맹점 아이디, 결제직원 아이디

발급받은 클라이언트키와 시크릿키는 PayatSDkManager.m에 #define에 CLIENT_SECRET ,CLIENT_ID에 적어주시면 됩니다.

사용을 위해 URL SCHEME등록이 필요합니다.

URL SCHEME 등록이후에 PayatSDkManager.m 에 URL_SCHEMES_NAME에 등록한 URL_SCHEMES_NAME을 적어주셔야 결제 확인 리턴값이 넘어옵니다.

URL SCHEME을 등록하지 않을시 retrun값이 제대로 넘어오지 않습니다.

PayatSDkManager에있는 함수를 통해 페이앳을 이용해 결제가 가능합니다.

---------------------------------------
페이앳 설치 확인하기

    (BOOL)canOpenPayat

>현재 사용하는 기기에 SDK 사용가능한 페이앳이 설치 되어있는지 여부를 판별합니다.

페이앳 사용자정보 입력

    +(void)initPayatSDKStoreId:(NSString *)storeID andEmployee_ID:(NSString *)employeeID

>사용하는 가맹점계정과 직원의 계정을 입력합니다. ( 가맹점 계정과 사용자계정이 동일할경우 둘 다 가맹점 계정을 입력하시면 됩니다.)

부가세 구하기
    +(NSInteger)obtainTax :(NSInteger) numPrice

>총금액에 대한 부가세를 구합니다. (10% 부과세 필요인자 : 총금액 )

고객정보 Dictionary 만들기 
    
    +(NSMutableDictionary *) createCustomerDictionary:(NSString *)name andEmail:(NSString *)email 
    andPhone:(NSString *)phone andMobile:(NSString *)mobile

>고객정보를 Dictionary로만들어 얻어옵니다. (고객이름 고객이메일 고객전화 고객핸드폰) 

---------------------------------------
직접결제

PayatSDkManager에 있는 함수 호출시 필요항목과 공급가액, 부가세, 봉사료를 통해 페이앳을 불러옵니다.

현금결제

    +(BOOL)sendPaymentDataCash:(NSInteger)totalPrice   andTax:(NSInteger)tax andComment:(NSString *)commnet 
    andCustomerInfo:(NSDictionary *)customerInfo andAdditional_data:(NSString *)additional_data 

>현금 결제를 위한 정보를 이용해 페이앳을 실행합니다.( 필요인자 :  총액  부가세 상품설명 고객정보 추가정보  )

>페이앳 호출에 실패하면 NO를 리턴합니다. 성공시 YES 리턴

카드결제

    +(BOOL)sendPaymentDataCard:(NSInteger)totalPrice   andTax:(NSInteger)tax andComment:(NSString *)commnet 
    andCustomerInfo:(NSDictionary *)customerInfo andAdditional_data:(NSString *)additional_data 

>카드 결제를 위한 정보를 이용해 페이앳을 실행합니다.( 필요인자 : 총액 소액 부가세 상품설명 고객정보 추가정보)

>페이앳 호출에 실패하면 NO를 리턴합니다. 성공시 YES 리턴

---------------------------------------

상품결제

PayatSDkManager에 있는 함수 호출시 필요항목과 상품조회 타입, 상품 번호나 코드, 각 상품의 개수를 통해 페이앳을 불러옵니다. 상품결제시 상품정보의 Dictionary는 key값에 상품 번호 , object값에 상품의 갯수가 들어갑니다. ITEM_TYPE에는 상품이 없을경우 ITEM_TYPE_NONE , 코드로된 상품의경우 ITEM_TYPE_CODE , 넘버로된 상품의경우 ITEM_TYPE_NO를 입력하시면 됩니다.

현금결제

    +(BOOL)sendPaymentProductCash:(NSInteger)totalPrice andTax:(NSInteger)tax andItem_List:(NSMutableDictionary *)product
    andItemType:(ITEMP_TYPE)itemp_Type andComment:(NSString *)commnet andCustomerInfo:(NSDictionary *)customerInfo andAdditional_data:(NSString *)additional_data ;


>상품목록이 있는 현금 결제를 위한 정보를 이용해 페이앳을 실행합니다.( 필요인자 : 총액 소액 부가세 아이템정보 추가할아이템타입 상품설명 고객정보 추가정보)

>페이앳 호출에 실패하면 NO를 리턴합니다. 성공시 YES 리턴

카드결제

    +(BOOL)sendPaymentProductCard:(NSInteger)totalPrice  andTax:(NSInteger)tax andItem_List:(NSMutableDictionary *)product
    andItemType:(ITEMP_TYPE)itemp_Type andComment:(NSString *)commnet andCustomerInfo:(NSDictionary *)customerInfo andAdditional_data:(NSString *)additional_data ;


>상품목록이 있는 현금 결제를 위한 정보를 이용해 페이앳을 실행합니다.( 필요인자 : 총액 소액 부가세 아이템정보 추가할아이템타입 상품설명 고객정보 추가정보)

>페이앳 호출에 실패하면 NO를 리턴합니다. 성공시 YES 리턴

카드결제와 현금결제 모두 동일한 방법으로 진행합니다.

---------------------------------------

페이앳에서 넘어온 리턴값 처리 하기

    +(NSDictionary*)translationDictionary:(NSURL*)url

>페이앳에서 응답온 값을 Dictionary 형식으로 변환합니다.( 필요인자 : Payat에서 리턴된 url )

  Appdelegate.m 에 (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation 으로 리턴 된 openURL:(NSURL *)url 을 인자값으로 입력 code 200 의경우 결제 성공, 200을 제외한 값들은 에러코드로 처리하시면됩니다. 200을 제외한 코드의경우는 Dictionary 에 message를 통해 에러 이유를 알려줍니다.
  
  결제 성공시 메시지
  ---------------------------------------
    //현금 결제 성공 
    code = 200;
  
    data =     {
  
    "net_id" = "AEGIS_VISAwhp_111111_20121227162233883058";
    
    "order_no" = 23445;
  
     };
  
     message = "";
  
    status = ok;
  ---------------------------------------
    //현금 영수증 성공
  
    code = 200;

    data =     {
    
    "approval_no" = "000000000";
  
    "net_id" = "AEGIS_VISAwhp_111111_20121227162717881454";
    
    "order_no" = 23450;
  
     };
  
     message = ok;
  
    status = ok;
  
  ---------------------------------------
    //카드결제 성공
  
    code = 200;
  
    data =     {
  
    "approval_no" = 66191351;
  
    "card_name" = BC;
  
    "card_type" = 0100;
  
    "net_id" = "AEGIS_VISAwhp_111111_20121227164522828134";
  
    "order_no" = 23481;
  
    "send_no" = 510084;

    };
    message = "";
    status = ok;
  ---------------------------------------
  결제 실패 메시지

     code = 400;
   
    message = "에러 메시지";

---------------------------------------
API 문의

담당 : kmh@whoopersoft.com - 김명호 연구원 (주)후퍼소프트 psk@whoopersoft.com - 박승근 연구원 (주)후퍼소프트

제휴 문의

담당 : kwk@whoopersoft.com - 김웅겸 CTO (주)후퍼소프트
