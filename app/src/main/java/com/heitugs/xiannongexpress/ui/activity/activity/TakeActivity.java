package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;
import com.heitugs.xiannongexpress.model.OrderModel;
import com.heitugs.xiannongexpress.utils.UrlUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class TakeActivity extends BaseActivity implements View.OnClickListener {
    private Spinner sizeSpinner, kindsSpinner, timeSpinner;
    private List<String> dataList;
    private ArrayAdapter<String> arrayAdapter;
    private ImageView getImg, sendImg, returnImg;
    private RelativeLayout startMsg, endMsg;
    private String sizeStr;
    private String kindStr;
    private String timeStr;
    private String nameSend;
    private String phoneSend;
    private String adsSend;
    private String agentSend;
    private String agentPhoneSend;
    private String nameTake;
    private String phoneTake;
    private String adsTake;
    private String agentTake;
    private String agentPhoneTake;
    private String url;
    private String company, number;
    private String takeData;
    private String sendData;
    private float price = 1.5F;
    private double mileage;
    private TextView nextTv, priceTv;
    private int endPrice = 0;
    private long endMileage = 0;
    private String uid;
    private String fullSendAds, fullTakeAds;
    private String fromHomeStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take);
        findId();
        getUid();
        initSpinnerSize();
        initSpinnerKinds();
        initSpinnerTime();
        fromHome();
    }

    public void findId() {
        getImg = $(R.id.mail_get_img);
        sendImg = $(R.id.mail_send_img);
        startMsg = $(R.id.take_start);
        endMsg = $(R.id.take_end);
        returnImg = $(R.id.take_return);
        nextTv = $(R.id.take_next);
        priceTv = $(R.id.take_price);
        nextTv.setOnClickListener(this);
        returnImg.setOnClickListener(this);
        startMsg.setOnClickListener(this);
        endMsg.setOnClickListener(this);
        getImg.setOnClickListener(this);
        sendImg.setOnClickListener(this);
    }


    private void getSendData() {
        SharedPreferences saveSend = getSharedPreferences("save_send", 1);
        nameSend = saveSend.getString("nameS", null);
        phoneSend = saveSend.getString("phoneS", null);
        adsSend = saveSend.getString("adsS", null);
        fullSendAds = saveSend.getString("adsFullT", null);
        agentSend = saveSend.getString("agentS", null);
        agentPhoneSend = saveSend.getString("agentPhoneS", null);
        sendData = nameSend + phoneSend + adsSend + fullSendAds + agentSend + agentPhoneSend;

    }

    private void getUid() {
        SharedPreferences getUid = getSharedPreferences("save_uid", 0);
        uid = getUid.getString("uid", null);

    }

    private void getTakeData() {
        SharedPreferences saveTake = getSharedPreferences("save_take", 0);
        nameTake = saveTake.getString("nameT", null);
        phoneTake = saveTake.getString("phoneT", null);
        adsTake = saveTake.getString("addressT", null);
        fullTakeAds = saveTake.getString("addressFullT", null);
        agentTake = saveTake.getString("numberT", null);
        agentPhoneTake = saveTake.getString("companyT", null);
        takeData = nameTake + phoneTake + adsTake + fullTakeAds + agentTake + agentPhoneTake;
    }

    private void initData() {
        getSendData();
        getTakeData();
        url = UrlUtil.orderUrl + uid + "&size=1&type=a" + "&sendAddress=" + sendData + "&receiveAddress=" + takeData
                + "&size=" + sizeStr + "&kinds=" + kindStr + "&sendGoodsTime=" + timeStr + "&pay_status=等待接单" + "&price=" + endPrice;
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String order = response.body().string();
                Gson gson = new Gson();
                OrderModel orderModel = gson.fromJson(order, OrderModel.class);
                String str = orderModel.getMsg();
                System.out.println("数据:" + str);
                System.out.println("Url---->" + url);


            }
        });
    }

    private void fromHome() {
        SharedPreferences fromHome = getSharedPreferences("selectLocation", MODE_PRIVATE);
        fromHomeStr = fromHome.getString("city", null);

    }


    private void initSpinnerSize() {
        sizeSpinner = (Spinner) findViewById(R.id.spinner_size);
        dataList = new ArrayList<>();
        dataList.add("5kg以下/0.6m³以下");
        dataList.add("5-10kg/0.12m³");
        dataList.add("10-25kg/0.36m³");
        dataList.add("25-200kg/1m³");
        dataList.add("200-500kg/1.2m³");
        dataList.add("500-1000kg/1.8m³");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(arrayAdapter);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sizeStr = (String) sizeSpinner.getSelectedItem();
                priceTake();
                countPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerKinds() {
        kindsSpinner = (Spinner) findViewById(R.id.spinner_kinds);
        dataList = new ArrayList<>();
        dataList.add("农资产品");
        dataList.add("日常用品");
        dataList.add("易碎品");
        dataList.add("其他");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kindsSpinner.setAdapter(arrayAdapter);
        kindsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kindStr = (String) kindsSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerTime() {
        timeSpinner = (Spinner) findViewById(R.id.spinner_time);
        dataList = new ArrayList<>();
        dataList.add("9:00");
        dataList.add("10:00");
        dataList.add("11:00");
        dataList.add("12:00");
        dataList.add("13:00");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(arrayAdapter);
        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeStr = (String) timeSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void saveOrder() {
        SharedPreferences.Editor order = getSharedPreferences("save_order", 2).edit();
        order.putString("nameSend", nameSend);
        order.putString("phoneSend", phoneSend);
        order.putString("startAds", adsSend);
        order.putString("endAds", adsTake);
        order.putString("price", String.valueOf(price));
        order.commit();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_start:
                Intent sendMail = new Intent(TakeActivity.this, SendMsgActivity.class);
                startActivity(sendMail);
                break;
            case R.id.take_end:
                Intent getMail = new Intent(TakeActivity.this, TakeMsgActivity.class);
                startActivity(getMail);
                break;
            case R.id.take_return:
                finish();
                break;
            case R.id.take_next:
                Intent toOrder = new Intent(TakeActivity.this, OrderActivity.class);
                toOrder.putExtra("order_price", endPrice);
                startActivity(toOrder);
                initData();
                saveOrder();
                SharedPreferences.Editor savePrice = getSharedPreferences("save_endPrice", MODE_PRIVATE).edit();
                savePrice.putInt("endPrice", endPrice);
                savePrice.commit();
                break;

        }

    }

    private void priceTake() {
        Intent fromTakeMsg = getIntent();
        mileage = fromTakeMsg.getDoubleExtra("mileage", 0);

        // endMileage = (mileage*10000000)/10000000;
        System.out.println("sdaadadadad" + mileage);


    }

    /**
     * 计算价格
     */
    private void countPrice() {

//        double pricea = mileage / 1000;

        if (mileage != 0) {
            if (mileage < 10 && sizeStr.equals("5kg以下/0.6m³以下")) {
                endPrice = 8;
            } else if (mileage < 10 && sizeStr.equals("5-10kg/0.12m³")) {
                endPrice = 10;
                System.out.println("哈哈哈");
            } else if (mileage < 10 && sizeStr.equals("10-25kg/0.36m³")) {
                endPrice = 20;
            } else if (mileage < 10 && sizeStr.equals("25-200kg/1m³")) {
                endPrice = 30;
            } else if (mileage < 10 && sizeStr.equals("200-500kg/1.2m³")) {
                endPrice = 40;
            } else if (mileage < 10 && sizeStr.equals("500-1000kg/1.8m³")) {
                endPrice = 80;
            }
            // 10-20公里
            else if (mileage > 10 && mileage < 20 && sizeStr.equals("5kg以下/0.6m³以下")) {
                endPrice = 12;
            } else if (mileage > 10 && mileage < 20 && sizeStr.equals("5-10kg/0.12m³")) {
                endPrice = 15;
            } else if (mileage > 10 && mileage < 20 && sizeStr.equals("10-25kg/0.36m³")) {
                endPrice = 30;
            } else if (mileage > 10 && mileage < 20 && sizeStr.equals("25-200kg/1m³")) {
                endPrice = 45;
            } else if (mileage > 10 && mileage < 20 && sizeStr.equals("200-500kg/1.2m³")) {
                endPrice = 60;
            } else if (mileage > 10 && mileage < 20 && sizeStr.equals("500-1000kg/1.8m³")) {
                endPrice = 100;
            }
            /**
             * 20公里以上
             */
            if (mileage > 20 && sizeStr.equals("5kg以下/0.6m³以下")) {
                endPrice = 16;
            } else if (mileage > 20 && sizeStr.equals("5-10kg/0.12m³")) {
                endPrice = 20;
                System.out.println("哈哈哈");
            } else if (mileage > 20 && sizeStr.equals("10-25kg/0.36m³")) {
                endPrice = 40;
            } else if (mileage > 20 && sizeStr.equals("25-200kg/1m³")) {
                endPrice = 60;
            } else if (mileage > 20 && sizeStr.equals("200-500kg/1.2m³")) {
                endPrice = 80;
            } else if (mileage > 20 && sizeStr.equals("500-1000kg/1.8m³")) {
                endPrice = 150;
            }
        }
        priceTv.setText("¥" + endPrice);


    }


}
