#devin-cordova-plugin-bugly

原作者：https://gitee.com/shiyutian/devin-cordova-plugin-bugly

## 修改处

- 删除安装时指定id和version，改为安卓appid、ios的appid、是否调试模式
- 安卓增加setUserId和postCatchedException方法

## 安装使用
```bash
cordova plugin add https://github.com/chenyuanchn/devin-cordova-plugin-bugly --variable BUGLY_APPID_ANDROID=yourAndrodId  --variable BUGLY_APPID_IOS=yourIOSId  --variable BUGLY_ENABLE_DEBUG=trueOrFalse
```
## 注意

- ios上还未实现setUserId和postCatchedException方法，请等之后更新
- 因为在ionic上测试的时候，自带捕捉webview的报错无反应，本人是使用ionic全局捕捉异常后通过postCatchedException上传的

```typescript
import {IonicErrorHandler} from 'ionic-angular';

declare let cordova;

/**
 * Ionic全局异常捕获
 */
export class GlobalIonicErrorHandler extends IonicErrorHandler {

    /**
     * 异常处理
     * @param err 异常信息
     */
    handleError(err: any): void {
        let _message = err._nativeError || err.message || err._nativeError.message || "";
        let _stack = err._nativeError || err.stack || err._nativeError.stack;
        let params: any = { message: _message, stack: _stack};
        console.log("错误：" + _message);
        console.log("堆栈：" + _stack);
        if(typeof  cordova !=='undefined' && cordova
            && typeof cordova.plugins.bugly !=='undefined' && cordova.plugins.bugly){
            cordova.plugins.bugly.setUserId(localStorage.userId);
            cordova.plugins.bugly.postCatchedException(JSON.stringify(params));
        }
    }
}
```
