package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;
import com.heitugs.xiannongexpress.model.AdsModel;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private TextView explainTv;
    private TextView sendTv, takeTv;
    private RadioButton personRb, managerRb;
    private ImageView one, two, three, four;
    private Spinner locationSpinner;
    private List<String> dataList;
    private ArrayAdapter<String> arrayAdapter;
    private String locationStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findId();
        initLocation();
    }

    private void findId() {

        takeTv = (TextView) findViewById(R.id.home_take);
//        sendTv = (TextView) findViewById(R.id.home_send);
        takeTv.setOnClickListener(this);
//        sendTv.setOnClickListener(this);
        personRb = (RadioButton) findViewById(R.id.home_person);
        personRb.setOnClickListener(this);
        managerRb = (RadioButton) findViewById(R.id.order_manager);
        managerRb.setOnClickListener(this);
        one = $(R.id.banner_one);
       //  two = $(R.id.banner_two);
        three = $(R.id.banner_three);
        one.setOnClickListener(this);
        explainTv = $(R.id.home_explain);
        explainTv.setOnClickListener(this);

    }


    private void initLocation() {
        locationSpinner = (Spinner) findViewById(R.id.main_location);
        dataList = new ArrayList<>();
        dataList.add("哈尔滨");
        dataList.add("齐齐哈尔");
        dataList.add("牡丹江");
        dataList.add("佳木斯");
        dataList.add("大庆");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(arrayAdapter);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locationStr = (String) locationSpinner.getSelectedItem();
                //     Toast.makeText(HomeActivity.this, "城市：" + locationStr, Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor location = getSharedPreferences("selectLocation", MODE_PRIVATE).edit();
                location.putString("city", locationStr);
                location.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_location:
                Intent toLocation = new Intent(HomeActivity.this, LocationActivity.class);
                startActivity(toLocation);
                break;
            case R.id.home_take:
                Intent takeHome = new Intent(HomeActivity.this, TakeActivity.class);
                startActivity(takeHome);
                break;
//            case R.id.home_send:
//                Intent sendHome = new Intent(HomeActivity.this, SendActivity.class);
//                startActivity(sendHome);
//                break;
            case R.id.home_person:
                Intent personHome = new Intent(HomeActivity.this, PersonalActivity.class);
                startActivity(personHome);
                break;
            case R.id.order_manager:
                Intent toManager = new Intent(HomeActivity.this, OrderManagerActivity.class);
                startActivity(toManager);
                break;
            case R.id.banner_one:
                Intent toBanWeb = new Intent(HomeActivity.this, BannerWebView.class);
                startActivity(toBanWeb);
                break;
            case R.id.home_explain:
                Intent toExplain = new Intent(HomeActivity.this, ExplainActivity.class);
                startActivity(toExplain);
                break;
        }
    }


}
