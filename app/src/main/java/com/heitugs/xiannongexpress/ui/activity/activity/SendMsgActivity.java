package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.heitugs.xiannongexpress.BuildConfig;
import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;

/**
 * Created by Administrator on 2016/9/3 0003.
 */
public class SendMsgActivity extends BaseActivity implements View.OnClickListener, OnGetGeoCoderResultListener {
    private ImageView returnImg;
    private EditText nameEd, phoneEd, adsEd, agentEd, agentPhoneEd;
    private TextView trueBtn;
    private Button sendAds;
    private EditText fullAdsEd;
    private PoiSearch mPoiSearch;
    private PoiCitySearchOption poiCitySearchOption;
    private OnGetPoiSearchResultListener mListener;
    private GeoCoder mSearch = null;
    private String poiName;
    private String poiAdd;
    private String idString;
    private double latitude;
    private double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_mail_send);
        Toast.makeText(this, "请输入详细地址后点击获取", Toast.LENGTH_LONG).show();
        findId();
        mPoiSearch = PoiSearch.newInstance();

        // 初始化搜索模块，注册时间监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);


    }


    private void searchAds() {
        mPoiSearch.setOnGetPoiSearchResultListener(mListener);

        mListener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                //获取POI检索结果
//                poiName = poiResult.getAllPoi().get(0).name;
//                poiAdd = poiResult.getAllPoi().get(0).address;
//                idString = poiResult.getAllPoi().get(0).type.toString();
                if (poiResult == null) {
                    showErrorDialog();
                }
                System.out.println("---name-->" + poiName);
                System.out.println("---Add-->" + poiAdd);
                System.out.println("---String-->" + idString);
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            }
        };

    }

    public void findId() {
        returnImg = $(R.id.send_msg_return);
        nameEd = $(R.id.send_name);
        phoneEd = $(R.id.send_phone);
        agentEd = $(R.id.send_agent);
        agentPhoneEd = $(R.id.send_agent_phone);
        trueBtn = $(R.id.send_true);
        sendAds = $(R.id.send_get_map);
        adsEd = $(R.id.send_address);
        fullAdsEd = $(R.id.send_full_address);
        sendAds.setOnClickListener(this);
        returnImg.setOnClickListener(this);
        trueBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_msg_return:
                finish();
                break;
            case R.id.send_get_map:
                String city = adsEd.getText().toString().trim();
                String keywood = fullAdsEd.getText().toString().trim();
                poiCitySearchOption = new PoiCitySearchOption().city(city).keyword(keywood);
                mPoiSearch.searchInCity(poiCitySearchOption);
                searchAds();
//                if (poiName != null && poiAdd != null) {
//                    mSearch.geocode(new GeoCodeOption().city(city).address(keywood));
//                } else {
//                    showErrorDialog();
//                }
                //mSearch.geocode(new GeoCodeOption().city("佳木斯").address("火车站"));
                mSearch.geocode(new GeoCodeOption().city(city).address(keywood));
                break;
            case R.id.send_true:
                if (nameEd.getText().toString().length() == 0
                        || phoneEd.getText().toString().length() == 0
                        || adsEd.getText().toString().length() == 0
                        || fullAdsEd.getText().toString().length() == 0) {
                    showDialog();
                } else {
                    Intent toTakeMsg = new Intent(SendMsgActivity.this, TakeMsgActivity.class);
                    toTakeMsg.putExtra("latitudeS", latitude);
                    toTakeMsg.putExtra("longitudeS", longitude);
                    startActivity(toTakeMsg);
                    saveSendData();
                }
                break;

        }
    }

    private void showErrorDialog() {
        ErrorDialog dialog = new ErrorDialog(SendMsgActivity.this);
        dialog.show();
    }

    private void showDialog() {
        MyDialog dialog = new MyDialog(SendMsgActivity.this);
        dialog.show();
    }


    private void saveSendData() {
        SharedPreferences.Editor saveSendData = getSharedPreferences("save_send", 1).edit();
        saveSendData.putString("nameS", nameEd.getText().toString());
        saveSendData.putString("phoneS", phoneEd.getText().toString());
        saveSendData.putString("adsS", adsEd.getText().toString());
        saveSendData.putString("adsFullT", fullAdsEd.getText().toString());
        saveSendData.putString("agentS", agentEd.getText().toString());
        saveSendData.putString("agentPhoneS", agentPhoneEd.getText().toString());
        saveSendData.commit();

    }


    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(SendMsgActivity.this, "请输入真实的地址", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        String strInfo = String.format("纬度：%f 经度：%f",
                result.getLocation().latitude, result.getLocation().longitude);
        Toast.makeText(SendMsgActivity.this, "获取成功", Toast.LENGTH_LONG).show();
        sendAds.setFocusable(false);
        latitude = result.getLocation().latitudeE6;
        longitude = result.getLocation().longitudeE6;
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

    }
}
