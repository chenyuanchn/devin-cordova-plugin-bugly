#import <Cordova/CDV.h>
#import <Bugly/Bugly.h>

@interface DevinCordovaPluginBugly : CDVPlugin

- (void)setTag:(CDVInvokedUrlCommand*)command;
- (void)putUserData:(CDVInvokedUrlCommand*)command;

@end