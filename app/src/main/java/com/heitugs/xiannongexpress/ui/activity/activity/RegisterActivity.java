package com.heitugs.xiannongexpress.ui.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;
import com.heitugs.xiannongexpress.utils.ToastUtils;
import com.heitugs.xiannongexpress.utils.UrlUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextInputLayout phoneWrapper;
    private TextInputLayout codeWrapper;
    private TextInputLayout passwordWrapper;
    private TextView finishTv, getCode, regSuccess;
    private EditText phoneEd, codeEd, passwordEd;
    private ImageView returnImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findId();
    }

    public void findId() {
        phoneWrapper = $(R.id.phoneWrapper);
        codeWrapper = $(R.id.codeWrapper);
        phoneEd = $(R.id.phone_number);
        codeEd = $(R.id.phone_code);
        passwordWrapper = $(R.id.passwordWrapper);
        passwordEd = $(R.id.password);
        regSuccess = $(R.id.register_success);
        finishTv = $(R.id.register_finish);
        finishTv.setOnClickListener(this);
        getCode = $(R.id.get_phone_code);
        getCode.setOnClickListener(this);
        returnImg = $(R.id.pc_return);
        returnImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_phone_code:
                getRegisterCode();
                break;
            case R.id.register_finish:
                register();
                break;
            case R.id.register_success:
                registerSuccess();
                break;
            case R.id.pc_return:
                finish();
                break;

        }
    }

    // 申请验证码成功
    private void getRegisterCode() {
        String getCodeUrl = UrlUtil.getCodeUrl + phoneEd.getText().toString().trim();
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request mRequest = new Request.Builder().url(getCodeUrl).build();
        Call call = mOkHttpClient.newCall(mRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String code = response.body().string();
                System.out.println("------------>" + code);
                if (code != null) {
                    Looper.prepare();
                    ToastUtils.showInfo(RegisterActivity.this, "申请成功");
                    Looper.loop();
                }

            }
        });
    }

    // 验证码验证成功
    private void register() {
        String url = UrlUtil.registerUrl + "&code=" + phoneEd.getText().toString().trim() + "&sendphone=" + codeEd.getText().toString().trim();
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request mRequest = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(mRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String codeState = response.body().string();
                if (codeState != null) {
                    Looper.prepare();
                    Intent intent = new Intent(RegisterActivity.this, RegisterNameActivity.class);
                    startActivity(intent);
                    Looper.loop();
                }
            }
        });
    }

    private void registerSuccess() {
        String url = UrlUtil.registerSuccessUrl + "&username=" + phoneEd.getText().toString().trim() + "&usertype=0&RealName=李明&sex=0&IDcardFiles=2304211234&password=" + passwordEd.getText().toString().trim();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request mRequest = new Request.Builder().url(url).build();
        Call mCall = okHttpClient.newCall(mRequest);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                String success = response.body().string();
                if (success != null) {
                    Looper.prepare();
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);

                    Looper.loop();
                }
            }
        });


    }
}
