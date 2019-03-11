package com.heitugs.xiannongexpress.ui.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;
import com.heitugs.xiannongexpress.model.LoginModel;
import com.heitugs.xiannongexpress.utils.ToastUtils;
import com.heitugs.xiannongexpress.utils.UrlUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextInputLayout usernameWrapper;
    private TextInputLayout passwordWrapper;
    private TextView trueBtn, registerBtn, findCodeTv;
    private EditText userEd, pwEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findId();
    }

    public void findId() {
        userEd = $(R.id.username);
        pwEd = $(R.id.password);
        usernameWrapper = $(R.id.usernameWrapper);
        passwordWrapper = $(R.id.passwordWrapper);
        usernameWrapper.setHint("请输入账号");
        passwordWrapper.setHint("请输入密码");
        trueBtn = $(R.id.login_login);
        registerBtn = $(R.id.login_register);
        trueBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        findCodeTv = $(R.id.find_code);
        findCodeTv.setOnClickListener(this);

    }


    private void login() {
        String url = UrlUtil.loginUrl + "username=" + userEd.getText().toString().trim()
                + "&password=" + pwEd.getText().toString().trim();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String b = response.body().string();
                Gson gson = new Gson();
                LoginModel loginModel = gson.fromJson(b, LoginModel.class);
                System.out.println("------>" + loginModel.getUsernum());
                if (loginModel.getMsg().equals("成功")) {
                    Looper.prepare();
                    ToastUtils.showInfo(LoginActivity.this, "登录成功");
                    Intent toHome = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(toHome);
                    SharedPreferences.Editor userNum = getSharedPreferences("save_uid", MODE_PRIVATE).edit();
                    userNum.putString("uid", loginModel.getUsernum());
                    System.out.println("uid" + loginModel.getUsernum());
                    userNum.commit();
                    Looper.loop();
                } else {
                    Looper.prepare();
                    ToastUtils.showInfo(LoginActivity.this, "请先注册");
                    Looper.loop();
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_login:
                login();
                break;
            case R.id.login_register:
                Intent toRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(toRegister);
                break;
            case R.id.find_code:
                Intent toFind = new Intent(LoginActivity.this, FoundCodeActivity.class);
                startActivity(toFind);
                break;
        }

    }
}
