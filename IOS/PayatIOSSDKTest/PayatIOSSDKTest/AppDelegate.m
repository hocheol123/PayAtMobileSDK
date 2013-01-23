//
//  AppDelegate.m
//  PayatIOSSDKTest
//
//  Created by whooper on 12. 12. 18..
//  Copyright (c) 2012년 whooper. All rights reserved.
//

#import "AppDelegate.h"
#import "PayatSDkManager.h"
#import "ViewController.h"

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    self.window = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    // Override point for customization after application launch.
    self.viewController = [[ViewController alloc] initWithNibName:@"ViewController" bundle:nil];
    self.window.rootViewController = self.viewController;
    [self.window makeKeyAndVisible];
    return YES;
}

//다른앱에서 URL SCHEME를 통해 정보를 넘겨받습니다.
- (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation
{
    //Payat에서 넘겨받은 정보를 Dictionary 형식으로 변경합니다.
    NSDictionary *dic= [PayatSDkManager translationDictionary:url];
    
    
    // code 가 200 일시 결제에 성공입니다. 그이외의 코드일경우 에러 메시지가 넘어옵니다. <-- message
    if ( [[dic objectForKey:@"code"] intValue]  == 200)
    {
        //결제 방법에따라 넘어오는 Dictionary는 다를 수 있습니다.
        //결제 성공 메시지를 뿌려줍니다.
        NSLog(@"Dic %@",dic);
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"응답" message:@"결제에 성공하였습니다." delegate:nil cancelButtonTitle:@"취소" otherButtonTitles:nil];
        [alert show];
    }
    else
    {
        //에러 메시지를 뿌려줍니다.
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"응답" message:[ dic objectForKey:@"message"] delegate:nil cancelButtonTitle:@"취소" otherButtonTitles:nil];
        [alert show];
        NSLog(@"dic message %@ ", [ dic objectForKey:@"message"]);
    }
    
    
    return YES
    ;
    
}


- (void)applicationWillResignActive:(UIApplication *)application
{
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

@end
