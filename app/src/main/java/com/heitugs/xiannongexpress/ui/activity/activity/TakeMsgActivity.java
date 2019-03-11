package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.DistanceUtil;
import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class TakeMsgActivity extends BaseActivity implements View.OnClickListener, OnGetGeoCoderResultListener {
    private ImageView returnImg;
    private Spinner companySpinner;
    private List<String> dataList;
    private ArrayAdapter<String> arrayAdapter;
    private String companyStr;
    private TextView trueTv, getMap;
    private EditText nameEd, phoneEd, adsEd, numberEd;
    private EditText fullAds;
    private String full;
    private PoiSearch mPoiSearch;
    private PoiCitySearchOption poiCitySearchOption;
    private OnGetPoiSearchResultListener mListener;
    private String poiName;
    private String poiAdd;
    private String idString;
    private GeoCoder mSearch;
    private double latitudeT;
    private double longitudeT;
    private double latitudeS;
    private double longitudeS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_mail_take);
        Toast.makeText(this, "请输入详细地址后点击获取", Toast.LENGTH_LONG).show();
        findId();
        mPoiSearch = PoiSearch.newInstance();
        initSpinnerCompany();
        //初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
    }

    private void getIntentData() {
        Intent fromSend = getIntent();
        latitudeS = fromSend.getDoubleExtra("latitudeS", 0);
        longitudeS = fromSend.getDoubleExtra("longitudeS", 0);
    }

    public void findId() {
        companySpinner = $(R.id.take_company);
        returnImg = $(R.id.take_return);
        trueTv = $(R.id.take_true);
        nameEd = $(R.id.take_name);
        phoneEd = $(R.id.take_phone);
        adsEd = $(R.id.take_address);
        numberEd = $(R.id.take_number);
        getMap = $(R.id.take_get_map);
        fullAds = $(R.id.full_address);
        returnImg.setOnClickListener(this);
        trueTv.setOnClickListener(this);
        getMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_location:
                finish();
                break;

            case R.id.take_get_map: // 获取
                getIntentData();
                String city = adsEd.getText().toString().trim();
                String keywood = fullAds.getText().toString().trim();
                poiCitySearchOption = new PoiCitySearchOption().city(city).keyword(keywood);
                mPoiSearch.searchInCity(poiCitySearchOption);
                searchAds();
                if (poiName != null || poiAdd != null) {
                    mSearch.geocode(new GeoCodeOption().city(city).address(keywood));
                } else {
                    showErrorDialog();
                }

                break;
            case R.id.take_true:
                double mileage = getShortDistance(longitudeS, latitudeS, longitudeT, latitudeT);
                double poi = mileage/10E8;
                if (nameEd.getText().toString().length() == 0
                        || phoneEd.getText().toString().length() == 0
                        || adsEd.getText().toString().length() == 0) {
                    showDialog();
                } else if (mileage != 0) {
                    Intent toTake = new Intent(TakeMsgActivity.this, TakeActivity.class);
                    toTake.putExtra("mileage", poi);
                    startActivity(toTake);
                    saveData();
                }
                break;
            case R.id.take_return:
                finish();
                break;
        }
    }

    private void showErrorDialog() {
        ErrorDialog dialog = new ErrorDialog(TakeMsgActivity.this);
        dialog.show();
    }

    private void showDialog() {
        MyDialog dialog = new MyDialog(TakeMsgActivity.this);
        dialog.show();
    }

    private void saveData() {
        SharedPreferences.Editor saveTake = getSharedPreferences("save_take", 0).edit();
        saveTake.putString("nameT", nameEd.getText().toString().trim());
        saveTake.putString("phoneT", phoneEd.getText().toString().trim());
        saveTake.putString("addressT", adsEd.getText().toString().trim());
        saveTake.putString("addressFullT", fullAds.getText().toString().trim());
        saveTake.putString("numberT", numberEd.getText().toString().trim());
        saveTake.putString("companyT", companyStr);
        saveTake.commit();
    }

    private void initSpinnerCompany() {
        dataList = new ArrayList<>();
        dataList.add("中国邮政EMS");
        dataList.add("申通快递");
        dataList.add("韵达快递");
        dataList.add("圆通快递");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        companySpinner.setAdapter(arrayAdapter);
        companySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                companyStr = (String) companySpinner.getSelectedItem();
//                Toast.makeText(TakeMsgActivity.this, "公司----" + companyStr, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void searchAds() {
        mPoiSearch.setOnGetPoiSearchResultListener(mListener);

        mListener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                //获取POI检索结果
                poiName = poiResult.getAllPoi().get(0).name;
                poiAdd = poiResult.getAllPoi().get(0).address;
                idString = poiResult.getAllPoi().get(0).uid;
                if (poiName == null || poiAdd == null) {
                    showDialog();
                }

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            }
        };

    }


    private static double DEF_PI = 3.14159265359; // PI
    private static double DEF_2PI = 6.28318530712; // 2*PI
    private static double DEF_PI180 = 0.01745329252; // PI/180.0
    private static double DEF_R = 6370693.5; // radius of earth

    private double getShortDistance(double lon1, double lat1, double lon2, double lat2) {
        double ew1, ns1, ew2, ns2;
        double dx, dy, dew;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 经度差
        dew = ew1 - ew2;
        // 若跨东经和西经180 度，进行调整
        if (dew > DEF_PI)
            dew = DEF_2PI - dew;
        else if (dew < -DEF_PI)
            dew = DEF_2PI + dew;
        dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
        dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
        // 勾股定理求斜边长
        distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }

    public double getLongDistance(double lon1, double lat1, double lon2, double lat2) {
        double ew1, ns1, ew2, ns2;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 求大圆劣弧与球心所夹的角(弧度)
        distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
        // 调整到[-1..1]范围内，避免溢出
        if (distance > 1.0)
            distance = 1.0;
        else if (distance < -1.0)
            distance = -1.0;
        // 求大圆劣弧长度
        distance = DEF_R * Math.acos(distance);
        return distance;
    }


    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(TakeMsgActivity.this, "请输入真实的地址", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        String strInfo = String.format("纬度：%f 经度：%f",
                result.getLocation().latitude, result.getLocation().longitude);
        Toast.makeText(TakeMsgActivity.this, "获取成功", Toast.LENGTH_LONG).show();
        getMap.setFocusable(false);
        latitudeT = result.getLocation().latitudeE6;
        longitudeT = result.getLocation().longitudeE6;
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

    }
}
