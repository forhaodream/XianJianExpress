package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.baidu.mapapi.SDKInitializer;
import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.utils.LocationUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class LocationTestActivity extends Activity implements LocationUtils.OnResultMapListener {
    private LocationUtils mLocationUtils;
    private View baiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        mLocationUtils = new LocationUtils(getApplicationContext());
        setContentView(R.layout.activity_baidu_map);
        baiduMap = findViewById(R.id.baidu_map);
        // 开启定位
        mLocationUtils.startLocation();
        mLocationUtils.registerOnResult(this);
        mLocationUtils.getLocation("北京", "天安门");
    }


    // 存储地理信息
    private Map<String, Object> resultMap = new HashMap<String, Object>();


    @Override
    public void onReverseGeoCodeResult(Map<String, Object> map) {
        resultMap = map;
        Log.i("data", "result====>" + resultMap.toString());
    }

    @Override
    public void onGeoCodeResult(Map<String, Object> map) {
        Log.i("data", "result====>" + map.toString());

    }
}
