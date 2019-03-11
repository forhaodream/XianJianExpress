package com.heitugs.xiannongexpress.ui.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2016/9/5 0005.
 */
public class PersonalActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout setting;
    private Button bg;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        findId();
    }

    private void findId() {
        setting = $(R.id.personal_setting);
        setting.setOnClickListener(this);
        textView = $(R.id.personal_ch);
        textView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_setting:
                Intent toSetting = new Intent(PersonalActivity.this, SettingActivity.class);
                startActivity(toSetting);
                break;
            case R.id.personal_ch:
                textView.setVisibility(View.VISIBLE);
                break;
        }
    }
}
