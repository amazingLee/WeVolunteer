package com.example.renhao.wevolunteer.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.event.FlagEvent;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.example.renhao.wevolunteer.IndexActivity.flag;

/**
 * 发现地图界面
 * A simple {@link Fragment} subclass.
 */
public class FindPageFragment extends Fragment implements LocationSource,
        AMapLocationListener,AMap.OnMapLoadedListener, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener,View.OnClickListener {
    private static final String TAG = "FindPageFragment";

    private AMap aMap;
    private MapView mapView;
    private OnLocationChangedListener mListener;//声明定位回调监听器
    private AMapLocationClient mlocationClient;//声明AMapLocationClient类对象
    private AMapLocationClientOption mLocationOption;
    private UiSettings mUiSettings;//设置地图控件对象

    private LatLng latlng = new LatLng(29.8600630808,121.6242721236);

    Button sign_in;//签到
    Button sign_out;//签退
    LinearLayout linearLayout;//签到计时的布局
    Chronometer chronometer;//计时器


    public FindPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View localView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_find_page, null);
        //获取地图控件引用
        this.mapView = (MapView) localView.findViewById(R.id.map);
        this.sign_in = (Button) localView.findViewById(R.id.btn_sign_in);
        this.sign_out = (Button) localView.findViewById(R.id.btn_sign_out);
        this.linearLayout = (LinearLayout) localView.findViewById(R.id.time_layout);
        this.chronometer = (Chronometer) localView.findViewById(R.id.chronometer);
        chronometer.setFormat("%s");
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        this.mapView.onCreate(savedInstanceState);// 此方法必须重写
        getbundle();
        init();
        setmListener();
        return localView;
    }

    //利用Intent的Bundle来进行传值
    private void getbundle(){
        Bundle data = getArguments();//获得从activity中传递过来的值
        String string = data.getString("Tag");
        System.out.println("bundle=" + string);
        if(string != null && string.equals("true")) {
            System.out.println("back=="+flag);
            if (!flag){
                sign_in.setVisibility(View.VISIBLE);
            }else {
                sign_out.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
            }
        }else if (string != null && string.equals("false")){
            sign_in.setVisibility(View.GONE);
        }
    }

    //接受消息的函数
    @Subscribe
    public void onEventMainThread(FlagEvent event) {

        String msg = event.getMsg();
        Log.d("harvic", msg);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();

        if (msg.equals("find")){//判断当前是否点击了发现按钮
            sign_in.setVisibility(View.GONE);
            sign_out.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.INVISIBLE);
        }else if (msg.equals("sign_in")){//判断当前是否点击了签到按钮
//            System.out.println("flag="+flag.toString());
//            Boolean f1 = flag;
//            SharedPreferences sp = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putBoolean("flag",f1);
//            editor.commit();
            System.out.println("flag="+flag);
            if (!flag){
                sign_in.setVisibility(View.VISIBLE);
            }else {
                sign_out.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
            }

        }
    }

    private void setmListener(){
        sign_in.setOnClickListener(this);
        sign_out.setOnClickListener(this);
    }


    /**
     * 初始化AMap对象
     */
    private void init() {
        Logger.d(TAG, "init");
        if (aMap == null) {
            aMap = mapView.getMap();
            mUiSettings = aMap.getUiSettings();
            mUiSettings.setZoomControlsEnabled(false);//去除地图缩放按钮控件
        }
    }

    /**
     * 设置一些amap的属性
     * aMap.setMyLocationType()设置定位的类型：
     * 1.定位模式：AMap.LOCATION_TYPE_LOCATE
     * 2.跟随模式：AMap.LOCATION_TYPE_MAP_FOLLOW
     * 3.旋转模式：AMap.LOCATION_TYPE_MAP_ROTATE
     */
    private void setUpMap() {
        Logger.d(TAG,"setUpMap");
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);

        aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
        aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
        aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
//        aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
        addMarkersToMap();// 往地图上添加marker
    }

    /**
     * position(Required) 在地图上标记位置的经纬度值。参数不能为空。
     title 当用户点击标记，在信息窗口上显示的字符串。
     snippet 附加文本，显示在标题下方。
     draggable 如果您允许用户可以自由移动标记，设置为“ true ”。默认情况下为“ false ”。
     *
     */
    private void addMarkersToMap() {
        aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(latlng).title("宁波市")
                .snippet("地点:宁波市政府").draggable(true));
    }


    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        Logger.d(TAG, "onResume");
        super.onResume();
        this.mapView.onResume();
        if (mlocationClient!=null)
        mlocationClient.startLocation();
        if (aMap != null) {
            setUpMap();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        Logger.d(TAG,"onPause");
        super.onPause();
        this.mapView.onPause();
        deactivate();
    }

    /**
     * 方法必须重写
     * 保存数据
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Logger.d(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        this.mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        Logger.d(TAG,"onDestroy");
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        this.mapView.onDestroy();
        aMap=null;
        EventBus.getDefault().unregister(this);//反注册EventBus
    }


    /**
     * 定位成功后回调函数
     * CameraUpdateFactory.zoomTo(float) 改变缩放级别为一个定值，同时保持其他相同的属性。
     * 这些方法返回一个 CameraUpdate 对象，使用 AMap.moveCamera(CameraUpdate update) 方法更新可视区域显示在地图上。
     *
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        Logger.d(TAG, "onLocationChanged");
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                Logger.v(TAG,aMapLocation.getAddress());
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                this.mlocationClient.stopLocation();//停止定位
                // 获取当前地图中心点的坐标
                LatLng localLatLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                this.aMap.moveCamera(CameraUpdateFactory.changeLatLng(localLatLng));
                //地图缩放级别为4-20级，缩放级别不必是一个整数。
                //缩放级别较低时，您可以看到更多地区的地图；缩放级别高时，您可以查看地区更加详细的地图。
                this.aMap.moveCamera(CameraUpdateFactory.zoomTo(12.0F));// 改变缩放级别为一个定值，同时保持其他相同的属性。
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode()+ ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }else{
            if (aMapLocation!=null)
            Logger.v(TAG,aMapLocation.getAddress());
            else
                Logger.v(TAG,"aMapLocation is null");
        }
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        Logger.d(TAG,"activate");
        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(getActivity().getApplicationContext());
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        Logger.d(TAG,"deactivate");
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getActivity(), "你点击了infoWindow窗口" + marker.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapLoaded() {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_in:
                flag = true;
                saveFlag(flag);
                sign_out.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                break;
            case R.id.btn_sign_out:
                flag = false;
                saveFlag(flag);
                chronometer.stop();
                sign_in.setVisibility(View.VISIBLE);
                sign_out.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "签退成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private Boolean saveFlag(Boolean flag){
        SharedPreferences sp = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("flag", flag);
        editor.commit();
        return flag;
    }
}
