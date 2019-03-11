package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class BaiduMapActivity extends BaseActivity implements View.OnClickListener {
    private View baiduMap;
    private PoiSearch mPoiSearch;
    private PoiCitySearchOption poiCitySearchOption;
    private OnGetPoiSearchResultListener mListener;
    private TextView mapTrue, mapSearchTrue;
    private String city;
    private EditText searchKeyWord;
    private String keyword;
    private String poiName;
    private String poiAdd;
    private String idString;

    private BaiduMap mBaiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map);
        searchKeyWord = $(R.id.map_edit);
        findId();
        mPoiSearch = PoiSearch.newInstance();
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // 处理点击事件
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                // 处理地图标注点事件
                return false;
            }
        });


    }

    public void findId() {

        baiduMap = $(R.id.baidu_map);
        mapTrue = $(R.id.map_true_start);
        mapTrue.setOnClickListener(this);
        mapSearchTrue = $(R.id.map_search_true);
        mapSearchTrue.setOnClickListener(this);

        keyword = searchKeyWord.getText().toString();
        SharedPreferences fromTake = getSharedPreferences("search_city", 3);
        city = fromTake.getString("search_city", null);
    }


    private void setBaiduMap() {
//        poiCitySearchOption = new PoiCitySearchOption().city("佳木斯").keyword("中心医院");
//        mPoiSearch.searchInCity(poiCitySearchOption);
        mPoiSearch.setOnGetPoiSearchResultListener(mListener);

        mListener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                //获取POI检索结果
                poiName = poiResult.getAllPoi().get(0).name;
                poiAdd = poiResult.getAllPoi().get(0).address;
                idString = poiResult.getAllPoi().get(0).uid;
                System.out.println("--------------->" + poiName);
                System.out.println("--------------->" + poiAdd);
                System.out.println("--------------->" + idString);
//                String location = poiResult.getAllPoi().get(0).name;
//                System.out.println("-------->" + location);

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                // 获取Place详情页检索结果
                System.out.println("-------->" + poiDetailResult);

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };
//        mPoiSearch.setOnGetPoiSearchResultListener(mListener);
//        mPoiSearch.searchInCity((new PoiCitySearchOption()).city("佳木斯").keyword("美食").pageNum(10));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPoiSearch.destroy();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.map_search_true:
                poiCitySearchOption = new PoiCitySearchOption().city(city).keyword(searchKeyWord.getText().toString());
                mPoiSearch.searchInCity(poiCitySearchOption);
                setBaiduMap();
                break;
            case R.id.map_true_start:
                SharedPreferences.Editor toTakeData = getSharedPreferences("save_take_data", 5).edit();
                toTakeData.putString("toTakeCity", poiName);
                toTakeData.putString("toTakeAds", poiAdd);
                toTakeData.commit();
                Intent toTakeMsg = new Intent(BaiduMapActivity.this, TakeMsgActivity.class);
                toTakeMsg.putExtra("ads", poiAdd);
                startActivity(toTakeMsg);
                finish();
                break;


        }
    }
}
