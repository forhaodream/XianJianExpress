package com.heitugs.xiannongexpress.ui.activity.activity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class FoundCodeActivity extends BaseActivity implements View.OnClickListener {
    private ImageView returnImg;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_code);
        returnImg = $(R.id.code_return);
        returnImg.setOnClickListener(this);
        webView = $(R.id.find_code_webView);
        webView.loadUrl("http://app.heitugs.com/user/getpass.html");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.code_return:
                finish();
                break;


        }
    }


}
