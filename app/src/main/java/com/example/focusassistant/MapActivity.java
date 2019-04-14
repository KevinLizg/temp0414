package com.example.focusassistant;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class MapActivity extends AppCompatActivity {
    private LocationClient locationClient;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private boolean isFirstLoc=true;//记录是否是第一次定位
    private MyLocationConfiguration.LocationMode locationMode;//当前定位模式
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());//初始化地图SDK
        setContentView(R.layout.activity_map);

        mMapView = findViewById(R.id.bmapview);//获取地图组件
        mBaiduMap=mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(18).build()));
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                1,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        locationUpdates(location);
                    }
                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {
                    }
                    @Override
                    public void onProviderEnabled(String s) {
                    }
                    @Override
                    public void onProviderDisabled(String s) {
                    }
                }
        );

    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }
    @Override
    protected void onPause() {
        mMapView.onPause();
        mMapView=null;
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mMapView=null;
        super.onDestroy();

    }

    @Override
    protected void onStart() {
        mBaiduMap.setBaiduHeatMapEnabled(true);
        super.onStart();

    }

    @Override
    protected void onStop() {
        mBaiduMap.setBaiduHeatMapEnabled(false);
        super.onStop();

    }

    public void locationUpdates(Location location){
        if(location!=null){
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
            Log.i("Location","维度："+location.getLatitude()+"|经度"+location.getLongitude());
            if(isFirstLoc){
                MapStatusUpdate u= MapStatusUpdateFactory.newLatLng(ll);//更新坐标位置
                mBaiduMap.animateMapStatus(u);//设置地图位置
                isFirstLoc=false;
            }
            //构造定位数据
            MyLocationData locationData=new MyLocationData.Builder().accuracy(location.getAccuracy()).
                    direction(100).
                    latitude(location.getLatitude()).
                    longitude(location.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(locationData);
            BitmapDescriptor bitmapDescriptor= BitmapDescriptorFactory.fromResource(R.drawable.arrow);
            locationMode=MyLocationConfiguration.LocationMode.NORMAL;//设置定位模式
            MyLocationConfiguration config=new MyLocationConfiguration(locationMode,true,bitmapDescriptor);
            mBaiduMap.setMyLocationConfiguration(config);//显示定位

        }else{
            Log.i("Location","没有获取到GPS信息");
        }
    }


}
