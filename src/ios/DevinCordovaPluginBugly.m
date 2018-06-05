/********* DevinCordovaPluginBugly.m Cordova Plugin Implementation *******/

#import "DevinCordovaPluginBugly.h"

@implementation DevinCordovaPluginBugly 

- (void)setTag:(CDVInvokedUrlCommand *)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* echo = [command.arguments objectAtIndex:0];

    if (echo != nil && [echo length] > 0) {
        [Bugly setTag:[echo integerValue]];
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

-(void)putUserData:(CDVInvokedUrlCommand *)command
{
    CDVPluginResult* pluginResult = nil;
    NSDictionary* echo = [command.arguments objectAtIndex:0];
    if (echo != nil && [echo count] > 0) {
        for (NSString *key in echo) {
            [Bugly setUserValue:echo[key] forKey:key];
        }
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"SUCCESS"];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

-(void)pluginInitialize {
    [Bugly startWithAppId:nil];
}

@end
