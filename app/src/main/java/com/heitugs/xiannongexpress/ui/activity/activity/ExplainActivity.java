package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.baidu.platform.comapi.map.A;
import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class ExplainActivity extends BaseActivity {
    private WebView webView;
    private ImageView returnImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_explain);
        returnImg = $(R.id.explain_return);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView = $(R.id.explain_webView);
        webView.loadUrl("http://app.heitugs.com/html/2016-08/1376.html");
    }
}
