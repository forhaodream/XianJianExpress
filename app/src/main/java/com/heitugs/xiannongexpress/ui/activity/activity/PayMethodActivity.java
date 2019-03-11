package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class PayMethodActivity extends Dialog implements View.OnClickListener {
    private TextView aliPay,wxPay;
    public PayMethodActivity(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);
        aliPay = (TextView) findViewById(R.id.ali_pay);
        wxPay = (TextView) findViewById(R.id.wx_pay);
        aliPay.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ali_pay:



        }
    }
}
