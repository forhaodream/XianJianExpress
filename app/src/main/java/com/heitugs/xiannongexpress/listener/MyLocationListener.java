package com.heitugs.xiannongexpress.listener;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.heitugs.xiannongexpress.BuildConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class MyLocationListener implements BDLocationListener {
    @Override
    public void onReceiveLocation(BDLocation location) {
//        if (location == null) {
//            return;
//        }
//        // 存储地理位置信息
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("time", location.getTime()); //定位时间
//        map.put("loc_type", location.getLocType());//定位方式
//        map.put("latitude", location.getLatitude());// 纬度
//        map.put("lontitude", location.getLongitude());// 经度
//        map.put("radius", location.getRadius());// 定位精度
//        map.put("city", location.getCity());// 获取城市
//        map.put("street", location.getStreet());// 获取街道信息
//        // 如果是GPS定位
//        if (location.getLocType() == BDLocation.TypeGpsLocation) {
//            // 定位速度
//            map.put("speed", location.getSpeed());
//            // 获得锁定的卫星数
//            map.put("satellite", location.getSatelliteNumber());
//        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//            // 如果是网路定位
//            // 获取地址信息
//            map.put("address", location.getAddrStr());
//            Log.d("data", map.toString());
//            if (mOnLocationResultListener != null) {
//                mOnLocationResultListener.onResultData(map);
//            }
//        }
        //Receive Location
        StringBuffer sb = new StringBuffer(256);
        sb.append("time : ");
        sb.append(location.getTime());
        sb.append("\nerror code : ");
        sb.append(location.getLocType());
        sb.append("\nlatitude : ");
        sb.append(location.getLatitude());
        sb.append("\nlontitude : ");
        sb.append(location.getLongitude());
        sb.append("\nradius : ");
        sb.append(location.getRadius());
        if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
            sb.append("\nspeed : ");
            sb.append(location.getSpeed());// 单位：公里每小时
            sb.append("\nsatellite : ");
            sb.append(location.getSatelliteNumber());
            sb.append("\nheight : ");
            sb.append(location.getAltitude());// 单位：米
            sb.append("\ndirection : ");
            sb.append(location.getDirection());// 单位度
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            sb.append("\ndescribe : ");
            sb.append("gps定位成功");

        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            //运营商信息
            sb.append("\noperationers : ");
            sb.append(location.getOperators());
            sb.append("\ndescribe : ");
            sb.append("网络定位成功");
        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            sb.append("\ndescribe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");
        } else if (location.getLocType() == BDLocation.TypeServerError) {
            sb.append("\ndescribe : ");
            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            sb.append("\ndescribe : ");
            sb.append("网络不同导致定位失败，请检查网络是否通畅");
        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            sb.append("\ndescribe : ");
            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
        }
        sb.append("\nlocationdescribe : ");
        sb.append(location.getLocationDescribe());// 位置语义化信息
        List<Poi> list = location.getPoiList();// POI数据
        if (list != null) {
            sb.append("\npoilist size = : ");
            sb.append(list.size());
            for (Poi p : list) {
                sb.append("\npoi= : ");
                sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            }
        }
        Log.i("BaiduLocationApiDem", sb.toString());


    }

    // 委托接口
    private OnLocationResultListener mOnLocationResultListener;

    /**
     * 注册委托接口事件
     */
    public void registerOnLocationResultListener(OnLocationResultListener mListener) {
        this.mOnLocationResultListener = mListener;
    }

    /**
     * 获取地址信息接口
     */
    public interface OnLocationResultListener {
        public void onResultData(Map<String, Object> map);
    }

}
