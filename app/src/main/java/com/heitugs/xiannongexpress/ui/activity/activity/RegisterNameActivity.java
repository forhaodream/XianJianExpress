package com.heitugs.xiannongexpress.ui.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;
import com.heitugs.xiannongexpress.utils.UrlUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import java.io.IOException;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class RegisterNameActivity extends BaseActivity implements View.OnClickListener {
    private EditText phoneEd, passwordEd;
    private TextView finishTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_name);
        findId();
    }

    private void findId() {
        phoneEd = $(R.id.phone_number);
        passwordEd = $(R.id.phone_password);
        finishTv = $(R.id.register_finish);
        finishTv.setOnClickListener(this);
    }

    private void registerSuccess() {
        String url = UrlUtil.registerSuccessUrl + "&username=" + phoneEd.getText().toString().trim() + "&usertype=0&RealName=李明&sex=0&IDcardFiles=2304211234&password=" + passwordEd.getText().toString().trim();

        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request mRequest = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(mRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                String success = response.body().string();
                if (success != null) {
                    Looper.prepare();
                    Toast.makeText(RegisterNameActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent toLogin = new Intent(RegisterNameActivity.this, LoginActivity.class);
                    startActivity(toLogin);
                    Looper.loop();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_finish:
                registerSuccess();
                break;
        }
    }
}
