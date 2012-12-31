//
//  PayatSDkManager.h
//  PayatIOSSDKTest
//
//  Created by whooper on 12. 12. 18..
//  Copyright (c) 2012년 whooper. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef enum
{
    ITEM_TYPE_NONE = -1,
    ITEM_TYPE_NO = 0,
    ITEM_TYPE_CODE =1
}ITEMP_TYPE;
@interface PayatSDkManager : NSObject

// SDK사용을 위해 직원아이디와 가맹점아이디를 입력합니다. ( 직원아이디 가맹점아이디 )
+(void)initPayatSDKStoreId:(NSString *)storeID andEmployee_ID:(NSString *)employeeID;
//페이앳 설치 상태를 확인합니다. YES일경우만 SDK 사용가능한 페이앳이 설치된 상태입니다.
+ (BOOL)canOpenPayat;

// 상품목록이 있는 카드 결제를 위한 필요 정보를 입력합니다. ( 총액 봉사료 부가세 아이템정보 추가할아이템타입 상품설명 고객정보 추가정보)
+(BOOL)sendPaymentProductCard:(NSInteger)totalPrice andFee:(NSInteger)fee andTax:(NSInteger)tax andItem_List:(NSMutableDictionary *)product  andItemType:(ITEMP_TYPE)itemp_Type andComment:(NSString *)commnet andCustomerInfo:(NSDictionary *)customerInfo andAdditional_data:(NSString *)additional_data ;

// 상품목록이 있는 현금 결제를 위한 필요 정보를 입력합니다.( 총액 봉사료 부가세 아이템정보 추가할아이템타입 상품설명 고객정보 추가정보)
+(BOOL)sendPaymentProductCash:(NSInteger)totalPrice andFee:(NSInteger)fee andTax:(NSInteger)tax andItem_List:(NSMutableDictionary *)product  andItemType:(ITEMP_TYPE)itemp_Type andComment:(NSString *)commnet andCustomerInfo:(NSDictionary *)customerInfo andAdditional_data:(NSString *)additional_data ;

// 현금 결제를 위한 필요 정보를 입력합니다.( 총액 봉사료 부가세 상품설명 고객정보 추가정보) 
+(BOOL)sendPaymentDataCash:(NSInteger)totalPrice  andFee:(NSInteger)fee andTax:(NSInteger)tax andComment:(NSString *)commnet andCustomerInfo:(NSDictionary *)customerInfo andAdditional_data:(NSString *)additional_data ;

// 카드 결제를 위한 필요 정보를 입력합니다.( 총액 봉사료 부가세  상품설명 고객정보 추가정보)
+(BOOL)sendPaymentDataCard:(NSInteger)totalPrice  andFee:(NSInteger)fee andTax:(NSInteger)tax andComment:(NSString *)commnet andCustomerInfo:(NSDictionary *)customerInfo andAdditional_data:(NSString *)additional_data ;

//페이앳에서 응답온 값을 Dictionary 형식으로 변환합니다.
//( Appdelegate -> (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation 으로 리턴 된 URL값 입력 )
+(NSDictionary*)translationDictionary:(NSURL*)url;

// 직접 보낼 데이터를 모두 입력합니다 ( 총액  소액 봉사료 부가세  결제타입  아이템정보  추가할아이템타입 상품설명 고객정보 추가정보)
+(BOOL) sendPaymentData:(NSInteger)totalPrice andFee:(NSInteger)fee andTax:(NSInteger)tax isCash:(BOOL)iscash andItemListDic:(NSMutableDictionary *)product andItemType:(ITEMP_TYPE)itemp_Type andComment:(NSString *)commnet andCustomerInfo:(NSDictionary *)customerInfo andAdditional_data:(NSString *)additional_data ;
//상품 총가격의 10%부가세를 구합니다.( 총액 )
+(NSInteger)obtainTax :(NSInteger) numPrice;


//고객정보를 담은 Dictionary를 만듭니다.
+(NSMutableDictionary *) createCustomerDictionary:(NSString *)name andEmail:(NSString *)email andPhone:(NSString *)phone andMobile:(NSString *)mobile;
@end
