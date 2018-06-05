package com.devin.plugins.bugly;

import android.webkit.WebView;
import com.tencent.bugly.crashreport.CrashReport;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * This class echoes a string called from JavaScript.
 */
public class DevinCordovaPluginBugly extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("setTag")) {
            setTag(args);
            return true;
        } else if (action.equals("putUserData")) {
            putUserData(args);
            return true;
        } else if (action.equals("postCatchedException")) {
            postCatchedException(args);
            return true;
        } else if (action.equals("setUserId")) {
            setUserId(args);
            return true;
        }
        return false;
    }

    private void putUserData(JSONArray args) {
        if (args != null && args.length() > 0) {
            try {
                JSONObject jsonObject = args.getJSONObject(0);
                Iterator<String> iterator = jsonObject.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    Object obj = jsonObject.get(key);
                    CrashReport.putUserData(cordova.getActivity(), key, obj + "");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void setTag(JSONArray args) {
        if (args != null && args.length() > 0) {
            try {
                int tag = args.getInt(0);
                CrashReport.setUserSceneTag(cordova.getActivity(), tag);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void postCatchedException(JSONArray args) {
        if (args != null && args.length() > 0) {
            try {
                //RuntimeException e = new RuntimeException("自定义异常上报");
                throw new RuntimeException(args.toString());

            } catch (Exception e) {
                CrashReport.postCatchedException(e);
            }
        }
    }

    private void setUserId(JSONArray args) {
        if (args != null && args.length() > 0) {
            try {
                String userId = args.getString(0);
                CrashReport.setUserId(userId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        CrashReport.initCrashReport(cordova.getActivity().getApplicationContext());

        if (webView.getEngine().getView() instanceof WebView) {
            CrashReport.setJavascriptMonitor((WebView) webView.getEngine().getView(), true);
        }
    }

    protected void pluginInitialize() {

    }

    public void onDestroy() {

    }
}
