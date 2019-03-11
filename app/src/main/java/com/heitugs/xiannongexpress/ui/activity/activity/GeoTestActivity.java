package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.heitugs.xiannongexpress.R;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class GeoTestActivity extends Activity implements OnGetGeoCoderResultListener {
    GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
    BaiduMap mBaiduMap = null;
    MapView mMapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.test_geo);
        /**
         *  // 地图初始化
         mMapView = (MapView) findViewById(R.id.bmapView);
         mBaiduMap = mMapView.getMap();

         // 初始化搜索模块，注册事件监听
         mSearch = GeoCoder.newInstance();
         mSearch.setOnGetGeoCodeResultListener(this);
         */
        mMapView = (MapView) findViewById(R.id.b_map_view);
        mBaiduMap = mMapView.getMap();

        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);


    }

    public void searchButtonProcess(View v) {
        if (v.getId() == R.id.reversegeocode) {
            EditText lat = (EditText) findViewById(R.id.lat);
            EditText lon = (EditText) findViewById(R.id.lon);
            LatLng ptCenter = new LatLng((Float.valueOf(lat.getText()
                    .toString())), (Float.valueOf(lon.getText().toString())));
            // 反Geo搜索
            mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                    .location(ptCenter));
        } else if (v.getId() == R.id.geocode) {
            EditText editCity = (EditText) findViewById(R.id.city);
            EditText editGeoCodeKey = (EditText) findViewById(R.id.geocodekey);
            // Geo搜索
            mSearch.geocode(new GeoCodeOption().city(
                    editCity.getText().toString()).address(editGeoCodeKey.getText().toString()));
        }
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        double aa = geoCodeResult.getLocation().latitudeE6;
        double bb = geoCodeResult.getLocation().longitudeE6;
        System.out.println("-----------?" + aa + bb);
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

    }
}
