//
//  ViewController.m
//  PayatIOSSDKTest
//
//  Created by whooper on 12. 12. 18..
//  Copyright (c) 2012년 whooper. All rights reserved./Users/whooper/PayatSDK/IOS/PayatIOSSDKTest/PayatIOSSDKTest.xcodeproj
//
/*
 발급받은 클라이언트키와 시크릿키는 PayatSDkManager.m에 #define에 적어주시면 됩니다.
 사용을 위해 URL SCHEME등록이 필요합니다.
 등록하지 않을시 retrun값이 제대로 넘어오지 않습니다.
 */
#import "PayatSDkManager.h"
#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    //매니저를 초기화합니다.
    [PayatSDkManager initialize];
    
    //가맹점아이디와 직원아이디를 등록합니다.
    [PayatSDkManager initPayatSDKStoreId:@"111111" andEmployee_ID:@"111111"];
    BOOL isOpne =   [PayatSDkManager canOpenPayat];
    if ( isOpne )
    {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"응답" message:@"페이엣 SDK 사용이 가능합니다." delegate:nil cancelButtonTitle:@"취소" otherButtonTitles:nil];
        [alert show];
    }
    else
    {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"응답" message:@"페이엣 SDK를 사용하시려면 페이앳을 최신버전으로 설치해주세요." delegate:nil cancelButtonTitle:@"취소" otherButtonTitles:nil];
        [alert show];
    }
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    
}

- (IBAction)btnActionCash:(id)sender {
    NSInteger price= [_iptPayment.text intValue];
    NSInteger subtotal= [_iptSubTotal.text intValue];
    NSInteger tax= [_iptTax.text intValue];
    if ( subtotal ==0 && tax ==0)
    {
        subtotal = price;
    }
      NSMutableDictionary *customer = [ PayatSDkManager createCustomerDictionary:_iptName.text andEmail:_iptEmail.text andPhone:_iptPhone.text andMobile:_iptMobile.text];
    //리턴값이 NO일경우 페이앳이 최신버전이아니거나 설치가 안되어있을수있습니다.
  
    [PayatSDkManager sendPaymentDataCash:price  andTax:tax andComment:_iptCommnet.text andCustomerInfo:customer andAdditional_data:_iptAddInfo.text];
    
}

- (IBAction)btnActionCard:(id)sender {
    NSInteger price= [_iptPayment.text intValue];
    NSInteger subtotal= [_iptSubTotal.text intValue];
    NSInteger tax= [_iptTax.text intValue];
    if ( subtotal ==0 && tax ==0)
    {
        subtotal = price;
    }
      NSMutableDictionary *customer = [ PayatSDkManager createCustomerDictionary:_iptName.text andEmail:_iptEmail.text andPhone:_iptPhone.text andMobile:_iptMobile.text];
    //리턴값이 NO일경우 페이앳이 최신버전이아니거나 설치가 안되어있을수있습니다.
    
    [PayatSDkManager sendPaymentDataCard:price  andTax:tax andComment:_iptCommnet.text andCustomerInfo:customer andAdditional_data:_iptAddInfo.text];
}

- (IBAction)btnActionProductCash:(id)sender {
    NSInteger price= [_iptPayment.text intValue];
    NSInteger subtotal= [_iptSubTotal.text intValue];
    NSInteger tax= [_iptTax.text intValue];
    ITEMP_TYPE item_type= ITEM_TYPE_NO;
    NSMutableDictionary *product = [[ NSMutableDictionary alloc] init] ;
    // 상품을 추가합니다. object <-- 갯수 forKey <-- 아이템넘버
    [product setObject:@"1" forKey:@"1947"];
    [product setObject:@"3" forKey:@"1956"];
    if ( subtotal ==0 && tax ==0)
    {
        subtotal = price;
    }
    //리턴값이 NO일경우 페이앳이 최신버전이아니거나 설치가 안되어있을수있습니다.
       NSMutableDictionary *customer = [ PayatSDkManager createCustomerDictionary:_iptName.text andEmail:_iptEmail.text andPhone:_iptPhone.text andMobile:_iptMobile.text];
    [PayatSDkManager sendPaymentProductCash:price andTax:tax andItem_List:product andItemType:item_type andComment:_iptCommnet.text andCustomerInfo:customer andAdditional_data:_iptAddInfo.text];
    
}

- (IBAction)btnActionProductCard:(id)sender {
    NSInteger price= [_iptPayment.text intValue];
    NSInteger subtotal= [_iptSubTotal.text intValue];
    NSInteger tax= [_iptTax.text intValue];
    ITEMP_TYPE item_type= ITEM_TYPE_NO;
    NSMutableDictionary *product = [[ NSMutableDictionary alloc] init] ;
    // 상품을 추가합니다. object <-- 갯수 forKey <-- 아이템넘버
    [product setObject:@"2" forKey:@"1947"];
    [product setObject:@"2" forKey:@"1956"];
    
    
    if ( subtotal ==0 && tax ==0)
    {
        subtotal = price;
    }
       NSMutableDictionary *customer = [ PayatSDkManager createCustomerDictionary:_iptName.text andEmail:_iptEmail.text andPhone:_iptPhone.text andMobile:_iptMobile.text];
    //리턴값이 NO일경우 페이앳이 최신버전이아니거나 설치가 안되어있을수있습니다.
    [PayatSDkManager sendPaymentProductCard:price  andTax:tax andItem_List:product andItemType:item_type andComment:_iptCommnet.text andCustomerInfo:customer andAdditional_data:_iptAddInfo.text];
}

- (IBAction)btnActionTax:(id)sender {
    NSInteger price= [_iptPayment.text intValue];
    NSInteger tax= [PayatSDkManager obtainTax:price];
    NSInteger subtotal = price -tax;
    [_iptTax setText:[NSString stringWithFormat:@"%d",tax]];
    [_iptSubTotal setText:[NSString stringWithFormat:@"%d",subtotal]];
    
}
- (void)viewDidUnload {
    _iptTax = nil;
    _iptSubTotal = nil;
    _iptCommnet = nil;
    _iptName = nil;
    _iptEmail = nil;
    _iptPhone = nil;
    _iptMobile = nil;
    _iptAddInfo = nil;
    [super viewDidUnload];
}
@end
