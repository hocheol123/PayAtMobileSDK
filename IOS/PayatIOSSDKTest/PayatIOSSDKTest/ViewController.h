//
//  ViewController.h
//  PayatIOSSDKTest
//
//  Created by whooper on 12. 12. 18..
//  Copyright (c) 2012년 whooper. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController

{
    
    //총금액 입력
    IBOutlet UITextField *_iptPayment;
    // 카드 결제 버튼
    IBOutlet UIButton *_btnCard;
    // 현금 결제 버튼
    IBOutlet UIButton *_btnCash;
    // 상품이 포함된 현금 결제 버튼
    IBOutlet UIButton *_btnProductCash;
    // 상품이 포함된 카드 결제 버튼
    IBOutlet UIButton *_btnProductCard;
    // 부가세 입력란
    IBOutlet UITextField *_iptTax;
    // 소액 입력란
    IBOutlet UITextField *_iptSubTotal;
    
}

//현금결제 버튼 클릭
- (IBAction)btnActionCash:(id)sender;
//카드결제 버튼 클릭
- (IBAction)btnActionCard:(id)sender;
//상품이 포함된 현금결제 버튼 클릭
- (IBAction)btnActionProductCash:(id)sender;
//상품이 포함된 카드결제 버튼 클릭
- (IBAction)btnActionProductCard:(id)sender;
//부가세 계산 버튼 클릭
- (IBAction)btnActionTax:(id)sender;
@end
