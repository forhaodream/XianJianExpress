package com.heitugs.xiannongexpress.ui.activity.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class PhoneCodeActivity extends BaseActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_code);

        findId();
    }

    public void findId() {
        textView = $(R.id.phone_code_tv);

    }
}
