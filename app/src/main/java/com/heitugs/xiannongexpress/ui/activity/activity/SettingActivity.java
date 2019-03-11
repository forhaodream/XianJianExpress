package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private TextView loginOut;
    private String stateStr;
    SharedPreferences preferences = null;
    SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        loginOut = $(R.id.login_out);
        loginOut.setOnClickListener(this);
        preferences = getSharedPreferences(TAG, Activity.MODE_PRIVATE);
        editor = preferences.edit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_out:
                Intent toLogin = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(toLogin);
                SharedPreferences state = getSharedPreferences("save_uid", MODE_PRIVATE);
                stateStr = state.getString("uid", null);
                state.edit().clear();
                finish();
                break;
        }
    }

}
