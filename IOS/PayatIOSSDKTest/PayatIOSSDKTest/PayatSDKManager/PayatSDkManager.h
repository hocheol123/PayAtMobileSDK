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

// 상품목록이 있는 카드 결제를 위한 필요 정보를 입력합니다. ( 총액 소액 부가세 아이템정보 추가할아이템타입 )
+(BOOL)sendPaymentProductCard:(NSInteger)totalPrice andSubtotalPrice:(NSInteger)subprice andTax:(NSInteger)tax andItem_List:(NSMutableDictionary *)product  andItemType:(ITEMP_TYPE)itemp_Type;

// 상품목록이 있는 현금 결제를 위한 필요 정보를 입력합니다.( 총액 소액 부가세 아이템정보 추가할아이템타입 )
+(BOOL)sendPaymentProductCash:(NSInteger)totalPrice andSubtotalPrice:(NSInteger)subprice andTax:(NSInteger)tax andItem_List:(NSMutableDictionary *)product  andItemType:(ITEMP_TYPE)itemp_Type;

// 현금 결제를 위한 필요 정보를 입력합니다.( 총액 소액 부가세 )
+(BOOL)sendPaymentDataCash:(NSInteger)totalPrice andSubtotalPrice:(NSInteger)subprice   andTax:(NSInteger)tax;

// 카드 결제를 위한 필요 정보를 입력합니다.( 총액 소액 부가세  )
+(BOOL)sendPaymentDataCard:(NSInteger)totalPrice andSubtotalPrice:(NSInteger)subprice   andTax:(NSInteger)tax;

//페이앳에서 응답온 값을 Dictionary 형식으로 변환합니다.
//( Appdelegate -> (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation 으로 리턴 된 URL값 입력 )
+(NSDictionary*)translationDictionary:(NSURL*)url;

// 직접 보낼 데이터를 모두 입력합니다 ( 총액  소액  부가세  결제타입  아이템정보  추가할아이템타입 )
+(BOOL) sendPaymentData:(NSInteger)totalPrice andSubtotalPrice:(NSInteger)subPrice  andTax:(NSInteger)tax isCash:(BOOL)iscash andItemListDic:(NSMutableDictionary *)product andItemType:(ITEMP_TYPE)itemp_Type;
//상품 총가격의 10%부가세를 구합니다.( 총액 )
+(NSInteger)obtainTax :(NSInteger) numPrice;
@end
