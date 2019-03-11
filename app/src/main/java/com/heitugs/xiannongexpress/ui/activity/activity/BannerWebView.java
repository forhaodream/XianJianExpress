package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;

import com.heitugs.xiannongexpress.R;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class BannerWebView extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_banner);
        webView = (WebView) findViewById(R.id.banner_webview);
        webView.loadUrl("http://app.heitugs.com/html/2016-08/1376.html");

    }


}
