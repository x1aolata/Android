package com.example.campusnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;
import com.google.android.material.snackbar.Snackbar;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Node[] Places = new Node[12];
    public LocationClient mLocationClient;
    public TextView positionText;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;
    public Graph graph;
    BaiduMap.OnMarkerClickListener onMarkerClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        setTitle("小邋遢的校园导航");
        positionText = (TextView) findViewById(R.id.position_text_view);
        graph = new Graph();
        final Switch switch_map = findViewById(R.id.switch_map);


        // switch
        {
            switch_map.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);


                    } else {
                        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    }

                }
            });
        }


        // 权限申请
        {
            List<String> permissionList = new ArrayList<>();
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (!permissionList.isEmpty()) {
                String[] permissions = permissionList.toArray(new String[permissionList.size()]);
                ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
            } else {
                requestLocation();
            }
        }

        // 初始化并加入Markers
        init();
        addMarkers(Places);


        //交通路况图
        //  baiduMap.setTrafficEnabled(true);
        //热力图
        //   baiduMap.setBaiduHeatMapEnabled(true);


        // 下拉菜单

        final NiceSpinner niceSpinnerstart, niceSpinnerend;

        LinkedList<String> data;
        niceSpinnerstart = (NiceSpinner) findViewById(R.id.start_NiceSpinner);
        niceSpinnerstart.setTextColor(Color.BLACK);
//        data = new LinkedList<>(Arrays.asList("体检中心", "操场", "校门北口", "银杏景观", "邯郸音乐厅", "图书馆", "餐厅", "信息学部", "花园景观", "校门东口", "网计学院", "校门南口"));
        data = new LinkedList<>(Arrays.asList(graph.getPlace()));

        niceSpinnerstart.attachDataSource(data);


        niceSpinnerend = (NiceSpinner) findViewById(R.id.end_NiceSpinner);
        niceSpinnerend.setTextColor(Color.BLACK);
//        data = new LinkedList<>(Arrays.asList("体检中心", "操场", "校门北口", "银杏景观", "邯郸音乐厅", "图书馆", "餐厅", "信息学部", "花园景观", "校门东口", "网计学院", "校门南口"));
//        data = new LinkedList<>(Arrays.asList(graph.getPlace()));
        niceSpinnerend.attachDataSource(data);

        // 求最短路径
        Button search_shortestPath = findViewById(R.id.search_shortestPath);
        search_shortestPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (niceSpinnerstart.getText().toString().equals(niceSpinnerend.getText().toString())) {
                    Snackbar.make(view, "原地绕圈圈有意思吗...", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                } else {
                    int distance = graph.Floyd(niceSpinnerstart.getText().toString(), niceSpinnerend.getText().toString());
                    Snackbar.make(view, "从" + niceSpinnerstart.getText() + "到" + niceSpinnerend.getText() + "的距离为：" + distance + "米。", Snackbar.LENGTH_SHORT).setAction("Action", null).show();

//                    Toast.makeText(MainActivity.this, "从" + niceSpinnerstart.getText() + "到" + niceSpinnerend.getText() + "的距离为：" + distance + "米。", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 显示最短路径
        Button display_shortestPath = findViewById(R.id.display_shortestPath);
        display_shortestPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baiduMap.clear();

                if (niceSpinnerstart.getText().toString().equals(niceSpinnerend.getText().toString())) {
                    Snackbar.make(view, "原地绕圈圈有意思吗...", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                } else {
                    List<LatLng> points = new ArrayList<LatLng>();
                    int[] route = graph.Route(niceSpinnerstart.getText().toString(), niceSpinnerend.getText().toString());
                    String s = "";
                    int i;
                    for (i = 0; i < route.length; i++) {
                        if (route[i] == -1)
                            break;
                    }
                    for (int j = 0; j < i; j++) {
                        s += Places[j].getName();
                        Log.d("x1aolata", "onClick: "+Places[route[j]].getName());
                        points.add(new LatLng(Places[route[j]].getLatitude(), Places[route[j]].getLongitude()));
                    }
                    renderingPath(points);

                    int distance = graph.Floyd(niceSpinnerstart.getText().toString(), niceSpinnerend.getText().toString());
                    Snackbar.make(view, "从" + niceSpinnerstart.getText() + "到" + niceSpinnerend.getText() + "的距离为：" + distance + "米。", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
//                    Snackbar.make(view, s, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                baiduMap.removeMarkerClickListener(onMarkerClickListener);
                addMarkers(Places);

            }
        });

        // 清除
        Button clean_shortestPath = findViewById(R.id.clean_shortestPath);
        clean_shortestPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baiduMap.clear();
                baiduMap.removeMarkerClickListener(onMarkerClickListener);
                addMarkers(Places);


            }
        });

    }

    public void init() {

        String[] name = {"体检中心", "操场", "校门北口", "银杏景观", "邯郸音乐厅", "图书馆", "餐厅", "信息学部", "花园景观", "校门东口", "网计学院", "校门南口"};
        double[] longitude = {115.568463, 115.572838, 115.575169, 115.577738, 115.56897, 115.572595, 115.576009, 115.571207, 115.572303, 115.574818, 115.569518, 115.571894};
        double[] latitude = {38.88999, 38.891099, 38.889526, 38.889031, 38.889411, 38.887894, 38.888122, 38.887059, 38.886111, 38.886585, 38.885668, 38.883077};
        Log.d("x1aolata", "init: " + latitude.length + "   " + longitude.length);
        for (int i = 0; i < name.length; i++) {
            Places[i] = new Node(name[i], String.valueOf(i), "都是一样的", longitude[i], latitude[i]);
//            Places[i].setName(name[i]);
//            Places[i].setNumber(String.valueOf(i));
//            Places[i].setAbout("都是一样的");
//            Places[i].setLatitude(latitude[i]);
//            Places[i].setLongitude(longitude[i]);
        }
    }

    /**
     * 添加地图标记
     *
     * @param Places
     */
    public void addMarkers(Node[] Places) {
        LatLng point;
        BitmapDescriptor bitmap;
        OverlayOptions option;
        for (Node place : Places) {
            // 标位置点
            point = new LatLng(place.getLatitude(), place.getLongitude());
            //构建Marker图标
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.dw2);
            //构建MarkerOption，用于在地图上添加Marker
            option = new MarkerOptions().position(point).icon(bitmap).title(place.getName());
            //在地图上添加Marker，并显示
            baiduMap.addOverlay(option);

            // 显示文字
            //文字覆盖物位置坐标
            LatLng llText = new LatLng(place.getLatitude(), place.getLongitude());
            //构建TextOptions对象
            OverlayOptions mTextOptions = new TextOptions()
                    .text(place.getName()) //文字内容
                    .bgColor(0xAAFFFF00) //背景色
                    .fontSize(24) //字号
                    .fontColor(0xFFFF00FF) //文字颜色
                    .rotate(-30) //旋转角度
                    .position(llText);
            //在地图上显示文字覆盖物
            Overlay mText = baiduMap.addOverlay(mTextOptions);

        }


//        // 设置监听器
//        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
//            //marker被点击时回调的方法
//            //若响应点击事件，返回true，否则返回false
//            //默认返回false
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                Toast.makeText(MainActivity.this, marker.getTitle() + "被点击了", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, DetailedInfo.class);
//                intent.putExtra("lable", marker.getTitle());
//                startActivity(intent);
//                return true;
//            }
//        });

        onMarkerClickListener = new BaiduMap.OnMarkerClickListener() {
            //marker被点击时回调的方法
            //若响应点击事件，返回true，否则返回false
            //默认返回false
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MainActivity.this, marker.getTitle() + "被点击了", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DetailedInfo.class);
                intent.putExtra("lable", marker.getTitle());
                startActivity(intent);
                return true;
            }
        };
        baiduMap.setOnMarkerClickListener(onMarkerClickListener);


    }

    public void renderingPath(List<LatLng> points) {
        //设置折线的属性
        OverlayOptions mOverlayOptions = new PolylineOptions()
                .width(10)
                .color(0xAAFF0000).dottedLine(true)
                .points(points)
                .color(Color.GREEN);
        //在地图上绘制折线
        //mPloyline 折线对象
        Overlay mPolyline = baiduMap.addOverlay(mOverlayOptions);

    }

    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            // Toast.makeText(this, "nav to " + location.getAddrStr(), Toast.LENGTH_SHORT).show();
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }


    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }


    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(final BDLocation location) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentPosition = new StringBuilder();
                    currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
                    currentPosition.append("经线：").append(location.getLongitude()).append("\n");
                    currentPosition.append("国家：").append(location.getCountry()).append("\n");
                    currentPosition.append("省：").append(location.getProvince()).append("\n");
                    currentPosition.append("市：").append(location.getCity()).append("\n");
                    currentPosition.append("区：").append(location.getDistrict()).append("\n");
                    currentPosition.append("街道：").append(location.getStreet()).append("\n");
                    currentPosition.append("定位方式：");
                    if (location.getLocType() == BDLocation.TypeGpsLocation) {
                        currentPosition.append("GPS");
                    } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                        currentPosition.append("网络");
                    }
                    positionText.setText(currentPosition);
                }
            });
            if (location.getLocType() == BDLocation.TypeGpsLocation
                    || location.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo(location);
            }

        }
    }


    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(1000);

        // 强制使用GPS定位
        // option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);

        // 表示需要获取位置的详细信息
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }
}
