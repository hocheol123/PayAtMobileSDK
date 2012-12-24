//
//  PayatSDkManager.m
//  PayatIOSSDKTest
//
//  Created by whooper on 12. 12. 18..
//  Copyright (c) 2012년 whooper. All rights reserved.
//

#import "PayatSDkManager.h"

//등록한 URL SCHEME 이름을 입력합니다. 
#define  URL_SCHEMES_NAME @"PayatIOSSDKTest"

//발급받은 CLIENT_SECRET 값입니다.
#define  CLIENT_SECRET @""

//발급받은 CLIENT_ID 값입니다.
#define  CLIENT_ID @""

@implementation PayatSDkManager

static NSString *_store_screen_name = nil;
static NSString *_employee_screen_name = nil;

//페이앳 설치 상태를 확인합니다. YES일경우만 SDK 사용가능한 페이앳이 설치된 상태입니다.
+ (BOOL)canOpenPayat
{
	return [[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"payat://?"]];
}


// SDK사용을 위해 직원아이디와 가맹점아이디를 입력합니다. ( 직원아이디 가맹점아이디 )
+(void)initPayatSDKStoreId:(NSString *)storeID andEmployee_ID:(NSString *)employeeID
{
    _store_screen_name = storeID ;
    _employee_screen_name = employeeID ;
}

//상품 총가격의 10%부가세를 구합니다.( 총액 )
+(NSInteger)obtainTax :(NSInteger) numPrice
{
    NSInteger numSubtotal = numPrice/1.1f;
    NSInteger numTax = numPrice - numSubtotal;
    return  numTax;
}


// 현금 결제를 위한 필요 정보를 입력합니다.( 총액 소액 부가세 )
+(BOOL)sendPaymentDataCash:(NSInteger)totalPrice andSubtotalPrice:(NSInteger)subprice andTax:(NSInteger)tax
{
    return  [self sendPaymentData:totalPrice andSubtotalPrice:subprice andTax:tax isCash:YES andItemListDic:nil andItemType:ITEM_TYPE_NONE];
    
}

// 카드 결제를 위한 필요 정보를 입력합니다.( 총액 소액 부가세  )
+(BOOL)sendPaymentDataCard:(NSInteger)totalPrice andSubtotalPrice:(NSInteger)subprice andTax:(NSInteger)tax
{
    
    return  [self sendPaymentData:totalPrice andSubtotalPrice:subprice andTax:tax isCash:NO andItemListDic:nil andItemType:ITEM_TYPE_NONE];
    
}

// 상품목록이 있는 현금 결제를 위한 필요 정보를 입력합니다.( 총액 소액 부가세 아이템정보 추가할아이템타입 )
+(BOOL)sendPaymentProductCash:(NSInteger)totalPrice andSubtotalPrice:(NSInteger)subprice andTax:(NSInteger)tax andItem_List:(NSMutableDictionary *)product andItemType:(ITEMP_TYPE)itemp_Type

{
    return  [self sendPaymentData:totalPrice andSubtotalPrice:subprice andTax:tax isCash:YES andItemListDic:product andItemType:itemp_Type];
}
// 상품목록이 있는 카드 결제를 위한 필요 정보를 입력합니다. ( 총액 소액 부가세 아이템정보 추가할아이템타입 )
+(BOOL)sendPaymentProductCard:(NSInteger)totalPrice andSubtotalPrice:(NSInteger)subprice andTax:(NSInteger)tax andItem_List:(NSMutableDictionary *)product  andItemType:(ITEMP_TYPE)itemp_Type

{
    return  [self sendPaymentData:totalPrice andSubtotalPrice:subprice andTax:tax isCash:NO andItemListDic:product andItemType:itemp_Type];
}
// 직접 보낼 데이터를 모두 입력합니다 ( 총액  소액  부가세  결제타입  아이템정보  추가할아이템타입 )
+(BOOL) sendPaymentData:(NSInteger)totalPrice andSubtotalPrice:(NSInteger)subPrice  andTax:(NSInteger)tax isCash:(BOOL)iscash andItemListDic:(NSMutableDictionary *)product andItemType:(ITEMP_TYPE)itemp_Type
{
    NSArray* item_nos = [[NSArray alloc] init] ;
    if ( product != nil)
    {
        item_nos  = [product allKeys];
    }
    else
    {
        product = [[ NSMutableDictionary alloc] init];
    }
    NSMutableDictionary *sendParams = [[NSMutableDictionary alloc] init];
    [sendParams setObject:URL_SCHEMES_NAME forKey:@"project"];
    [sendParams setObject:CLIENT_ID forKey:@"client_id"];
    [sendParams setObject:CLIENT_SECRET forKey:@"client_secret"];
    [sendParams setObject:(iscash ? @"1":@"0") forKey:@"isCash"];
    [sendParams setObject:_store_screen_name forKey:@"store_screen_name"];
    [sendParams setObject:_employee_screen_name forKey:@"employee_screen_name"];
    [sendParams setObject:[NSString stringWithFormat:@"%d",tax] forKey:@"tax"];
    [sendParams setObject:[NSString stringWithFormat:@"%d",subPrice] forKey:@"subtotal"];
    [sendParams setObject:[NSString stringWithFormat:@"%d",totalPrice] forKey:@"amount"];
    if ( itemp_Type !=-1)
    {
        NSString *i_type =( ITEM_TYPE_CODE == itemp_Type ? @"code_in" : @"no_in");
        [sendParams setObject:i_type forKey:@"item_type"];
    }
    [sendParams setObject:item_nos forKey:@"item_nos"];
    [sendParams setObject:product forKey:@"item_counts"];
    NSLog(@"sendParams %@" , sendParams)   ;
    NSString *strUrl =[@"payat://?" stringByAppendingString:[NSString stringWithFormat:@"%@",sendParams]];
    NSURL *url = [NSURL URLWithString:[strUrl stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
    return   [[UIApplication sharedApplication] openURL: url];
    
}
//페이앳에서 응답온 값을 Dictionary 형식으로 변환합니다.
//( Appdelegate -> (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation 으로 리턴 된 URL값 입력 )
+(NSDictionary*)translationDictionary:(NSURL*)url
{
    
    NSString *cutstring = [URL_SCHEMES_NAME stringByAppendingString:@"://?"];
    NSString *decoded = [[url absoluteString] stringByReplacingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    decoded=  [decoded stringByReplacingOccurrencesOfString:cutstring withString:@""];
    NSDictionary *temp = [decoded propertyListFromStringsFileFormat];
    return  temp;
}


@end
