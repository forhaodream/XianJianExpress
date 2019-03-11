package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class OrderActivity extends BaseActivity implements View.OnClickListener {
    private TextView nameTv, phoneTv, startTv, endTv, priceTv;
    private TextView nextTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        findId();
        getInfo();
    }

    private void findId() {
        nameTv = $(R.id.order_name);
        phoneTv = $(R.id.order_phone);
        startTv = $(R.id.order_start);
        endTv = $(R.id.order_end);
        priceTv = $(R.id.order_msg_price);
        nextTv = $(R.id.order_pay);
        nextTv.setOnClickListener(this);
    }


    private void getInfo() {
        /**
         *  SharedPreferences saveSend = getSharedPreferences("save_send", 1);
         nameSend = saveSend.getString("nameS", null);
         phoneSend = saveSend.getString("phoneS", null);
         adsSend = saveSend.getString("adsS", null);
         agentSend = saveSend.getString("agentS", null);
         agentPhoneSend = saveSend.getString("agentPhoneS", null);
         */
        /***
         *  SharedPreferences.Editor order = getSharedPreferences("save_order", 2).edit();
         order.putString("nameSend", nameSend);
         order.putString("phoneSend", phoneSend);
         order.putString("startAds", adsSend);
         order.putString("endAds", adsTake);
         order.commit();
         */
        // 始发地信息
        SharedPreferences orderInfo = getSharedPreferences("save_order", 2);
        nameTv.setText(orderInfo.getString("nameSend", null));
        phoneTv.setText(orderInfo.getString("phoneSend", null));
        startTv.setText(orderInfo.getString("startAds", null));
        endTv.setText(orderInfo.getString("endAds", null));
        // 价格信息
        SharedPreferences orderPrice = getSharedPreferences("save_endPrice", MODE_PRIVATE);
        priceTv.setText(String.valueOf(orderPrice.getInt("endPrice", 0)));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_pay:
                Intent toOrder = new Intent(OrderActivity.this, PayDemoActivity.class);
                startActivity(toOrder);
                break;
        }
    }
}
