package com.example.renhao.wevolunteer.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.MrL.qrcodescan.MipcaActivityCapture;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.core.AppActionImpl;
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityListDto;
import com.example.model.activity.ActivityQueryOptionDto;
import com.example.model.activity.ActivityViewDto;
import com.example.model.signRecord.SignInOutDto;
import com.example.model.volunteer.VolunteerBaseListDto;
import com.example.model.volunteer.VolunteerBaseQueryOptionDto;
import com.example.renhao.wevolunteer.ProjectDetailActivity;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.event.QRCodeResultEvent;
import com.example.renhao.wevolunteer.utils.Util;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 发现地图界面
 * A simple {@link Fragment} subclass.
 */
public class FindPageFragment extends Fragment implements LocationSource,
        AMapLocationListener, AMap.OnMapLoadedListener, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, View.OnClickListener {
    private static final String TAG = "FindPageFragment";

    public static final int MAP_ALL = -1;
    public static final int MAP_JOB = 1;
    public static final int MAP_ACTIVITY = 0;
    public static final int MAP_CENTER = 3;
    public static final int MAP_STATION = 4;

    private int showMap = MAP_ALL;

    public int getShowMap() {
        return showMap;
    }

    public void setShowMap(int showMap) {
        this.showMap = showMap;
        addMarkersToMap();
    }

    private boolean isShow = false;

    private AMap aMap;
    private MapView mapView;
    private OnLocationChangedListener mListener;//声明定位回调监听器
    private AMapLocationClient mlocationClient;//声明AMapLocationClient类对象
    private AMapLocationClientOption mLocationOption;
    private UiSettings mUiSettings;//设置地图控件对象

    private LatLng localLatLng;

    private List<ActivityListDto> activityDatas = new ArrayList<>();
    private List<ActivityListDto> jobDatas = new ArrayList<>();
    private List<VolunteerBaseListDto> guidanceCenterDatas = new ArrayList<>();
    private List<VolunteerBaseListDto> serviceStationDatas = new ArrayList<>();

    private Boolean flag = false;//地图签到按钮的判断
    private boolean tag = false;

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

       /* Bundle data = getArguments();//获得从activity中传递过来的值
        tag = data.getBoolean("Tag",false);
        Logger.v(TAG, tag + "---");*/
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
        setType(tag);
        //getbundle();
        init();
        setmListener();


        return localView;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    //true:签到  false:发现
    public void setType(boolean type) {
        flag = LocalDate.getInstance(getActivity()).getLocalDate("flag", false);
        if (type) {
            if (!flag) {
                sign_in.setVisibility(View.VISIBLE);
            } else {
                sign_out.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                startChronometer();
            }
            tag = true;
        } else {
            sign_in.setVisibility(View.GONE);
            sign_out.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.INVISIBLE);
            tag = false;
        }
    }

    //利用Intent的Bundle来进行传值
    private void getbundle() {
        Bundle data = getArguments();//获得从activity中传递过来的值
        String string = data.getString("Tag");
        SharedPreferences sp = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
        flag = sp.getBoolean("flag", false);
        if (string != null && string.equals("true")) {//true:签到  false:发现
            if (!flag) {
                sign_in.setVisibility(View.VISIBLE);
            } else {
                sign_out.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                startChronometer();
            }
        } else if (string != null && string.equals("false")) {
            sign_in.setVisibility(View.GONE);
            sign_out.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.INVISIBLE);
        } else {
            sign_in.setVisibility(View.GONE);
            sign_out.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.INVISIBLE);
        }
    }

    //接受消息的函数
    @Subscribe
    public void onEventMainThread(final QRCodeResultEvent event) {

        final String volunteerId = LocalDate.getInstance(getActivity()).getLocalDate("volunteerId", "");
        boolean isLogin = LocalDate.getInstance(getActivity()).getLocalDate("isLogin", false);
        if (TextUtils.isEmpty(volunteerId) || !isLogin) {
            showToast("请先登录");
            return;
        }


        //判断距离是否符合条件
        AppActionImpl.getInstance(getActivity()).activityDetail(event.getQrcodeMsg(),
                new ActionCallbackListener<ActivityViewDto>() {
                    @Override
                    public void onSuccess(ActivityViewDto data) {
                        if (data == null) {
                            showToast("该活动或岗位不存在");
                            return;
                        }
                        double lat = data.getLat().doubleValue();
                        double lng = data.getLng().doubleValue();
                        double distance = getDistance(lat, lng);
                        if (distance > data.getScopeType().doubleValue()) {
                            showToast("距离太远");
                        } else {
                            //0 未知 1 签到 2签退
                            Logger.v(TAG, event.getQrcodeMsg());
                            switch (event.getState()) {
                                case 0:
                                    boolean f = LocalDate.getInstance(getActivity()).getLocalDate("flag", false);
                                    if (f) {
                                        signOut(event.getQrcodeMsg(), volunteerId);
                                    } else {
                                        signIn(event.getQrcodeMsg(), volunteerId);
                                    }
                                    break;
                                case 1:
                                    signIn(event.getQrcodeMsg(), volunteerId);
                                    break;
                                case 2:
                                    signOut(event.getQrcodeMsg(), volunteerId);
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("网络超时，请稍后再试");
                    }
                });
    }

    private void setmListener() {
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
        Logger.d(TAG, "setUpMap");
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
     * title 当用户点击标记，在信息窗口上显示的字符串。
     * snippet 附加文本，显示在标题下方。
     * draggable 如果您允许用户可以自由移动标记，设置为“ true ”。默认情况下为“ false ”。
     */
    private void addMarkersToMap() {

/*        if (flag) {
            Logger.v(TAG, flag + "");
            aMap.clear();
            return;
        }*/
        aMap.clear();
        switch (showMap) {
            case MAP_ALL:
                getActivityData();//50
                getJobActivityData();//50
                getGuidanceCenterData(1);//all
                getServiceStationData(1);//all
                break;
            case MAP_JOB:
                getJobActivityData();//50
                break;
            case MAP_ACTIVITY:
                getActivityData();//50
                break;
            case MAP_CENTER:
                getGuidanceCenterData(1);//all
                break;
            case MAP_STATION:
                getServiceStationData(1);//all
                break;
        }
    }

    /**
     * 获取活动的数据
     */
    private void getActivityData() {
        ActivityQueryOptionDto query = new ActivityQueryOptionDto();
        query.setPageIndex(1);
        query.setPageSize(50);
        query.setType(0);
        AppActionImpl.getInstance(getActivity()).activityQuery(query, new ActionCallbackListener<PagedListEntityDto<ActivityListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<ActivityListDto> data) {
                if (data.getRows() == null || data.getRows().size() == 0)
                    return;
                activityDatas = data.getRows();
                for (int i = 0; i < activityDatas.size(); i++) {
                    ActivityListDto dto = activityDatas.get(i);
                    Double lng = dto.getLng().doubleValue();
                    Double lat = dto.getLat().doubleValue();
                    String addr = dto.getAddr();
                    String activityName = dto.getActivityName();
                    LatLng latlng = new LatLng(lat, lng);
                    if (aMap == null || !isShow)
                        break;
                    Marker marker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                            .position(latlng).title(activityName)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_activity))
                            .snippet("地点:" + addr).draggable(true));
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", MAP_ACTIVITY);
                    bundle.putString("id", dto.getId());
                    marker.setObject(bundle);
                }
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }

    /**
     * 获取岗位的数据
     */
    private void getJobActivityData() {
        ActivityQueryOptionDto query = new ActivityQueryOptionDto();
        query.setPageIndex(1);
        query.setPageSize(50);
        query.setType(1);
        AppActionImpl.getInstance(getActivity()).activityQuery(query, new ActionCallbackListener<PagedListEntityDto<ActivityListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<ActivityListDto> data) {
                if (data.getRows() == null || data.getRows().size() == 0)
                    return;
                jobDatas = data.getRows();
                for (int i = 0; i < jobDatas.size(); i++) {
                    ActivityListDto dto = jobDatas.get(i);
                    Double lng = dto.getLng().doubleValue();
                    Double lat = dto.getLat().doubleValue();
                    String addr = dto.getAddr();
                    String jobActivityName = dto.getActivityName();
                    LatLng latlng = new LatLng(lat, lng);
                    if (aMap == null || !isShow)
                        break;
                    Marker marker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                            .position(latlng).title(jobActivityName)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_job_activity))
                            .snippet("地点:" + addr)
                            .draggable(true));
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", MAP_JOB);
                    bundle.putString("id", dto.getId());
                    marker.setObject(bundle);
                }
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }

    /**
     * 获取志愿者指导中心数据
     */
    private void getGuidanceCenterData(int page) {
        VolunteerBaseQueryOptionDto query = new VolunteerBaseQueryOptionDto();
        query.setTypeId(2);
        query.setPageIndex(page);
        AppActionImpl.getInstance(getActivity()).volunteerBaseQuery(query, new ActionCallbackListener<PagedListEntityDto<VolunteerBaseListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<VolunteerBaseListDto> data) {

                if (data.getRows() == null || data.getRows().size() == 0)
                    return;

                guidanceCenterDatas = data.getRows();
                for (int i = 0; i < guidanceCenterDatas.size(); i++) {
                    VolunteerBaseListDto dto = guidanceCenterDatas.get(i);
                    Double lng = Double.parseDouble(dto.getLng());
                    Double lat = Double.parseDouble(dto.getLat());
                    String addr = dto.getAddress();
                    String title = dto.getTitle();
                    LatLng latlng = new LatLng(lat, lng);
                    if (aMap == null || !isShow)
                        break;
                    aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                            .position(latlng).title(title)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_guidance_center))
                            .snippet("地点:" + addr)
                            .draggable(true));
                }

                if (!isShow)
                    return;

                if (data.getHasNextPage()) {
                    getGuidanceCenterData(data.getPageIndex() + 1);
                }

            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });

    }

    /**
     * 获取志愿者服务站数据
     */
    private void getServiceStationData(int page) {
        VolunteerBaseQueryOptionDto query = new VolunteerBaseQueryOptionDto();
        query.setTypeId(1);
        query.setPageIndex(page);
        AppActionImpl.getInstance(getActivity()).volunteerBaseQuery(query, new ActionCallbackListener<PagedListEntityDto<VolunteerBaseListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<VolunteerBaseListDto> data) {

                if (data.getRows() == null || data.getRows().size() == 0)
                    return;

                serviceStationDatas = data.getRows();
                for (int i = 0; i < serviceStationDatas.size(); i++) {
                    VolunteerBaseListDto dto = serviceStationDatas.get(i);
                    Double lng = Double.parseDouble(dto.getLng());
                    Double lat = Double.parseDouble(dto.getLat());
                    String addr = dto.getAddress();
                    String title = dto.getTitle();
                    LatLng latlng = new LatLng(lat, lng);
                    if (aMap == null || !isShow)
                        break;
                    aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                            .position(latlng).title(title)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_service_station))
                            .snippet("地点:" + addr)
                            .draggable(true));
                }

                if (!isShow)
                    return;

                if (data.getHasNextPage()) {
                    getServiceStationData(data.getPageIndex() + 1);
                }
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }


    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        isShow = true;
        super.onResume();
        this.mapView.onResume();
        if (mlocationClient != null)
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
        Logger.d(TAG, "onPause");
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

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        isShow = false;
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        this.mapView.onDestroy();
        aMap = null;
        EventBus.getDefault().unregister(this);//反注册EventBus

    }


    /**
     * 定位成功后回调函数
     * CameraUpdateFactory.zoomTo(float) 改变缩放级别为一个定值，同时保持其他相同的属性。
     * 这些方法返回一个 CameraUpdate 对象，使用 AMap.moveCamera(CameraUpdate update) 方法更新可视区域显示在地图上。
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
       /* Logger.d(TAG, "onLocationChanged");*/
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                //this.mlocationClient.stopLocation();//停止定位
                // 获取当前地图中心点的坐标
                localLatLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                //this.aMap.moveCamera(CameraUpdateFactory.changeLatLng(localLatLng));
                //地图缩放级别为4-20级，缩放级别不必是一个整数。
                //缩放级别较低时，您可以看到更多地区的地图；缩放级别高时，您可以查看地区更加详细的地图。
                //this.aMap.moveCamera(CameraUpdateFactory.zoomTo(12.0F));// 改变缩放级别为一个定值，同时保持其他相同的属性。
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    //设置当前位置和活动的距离
    private double getDistance(double lat, double lng) {
        if (aMap == null) {
            return 1000000;
        }
        Location location = aMap.getMyLocation();
        LatLng myLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        LatLng centerLatLng = new LatLng(lat, lng);//这里是活动的中心位置经纬度
        // 计算量坐标点距离
        double distances = AMapUtils.calculateLineDistance(centerLatLng, myLatLng);
        //Toast.makeText(getActivity(), "当前距离中心点：" + ((int) distances), Toast.LENGTH_LONG).show();
        return distances;
    }


    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
       /* Logger.d(TAG, "activate");*/
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
        Logger.d(TAG, "deactivate");
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        //showToast("你点击了infoWindow窗口" + marker.getTitle());
        Bundle bundle = (Bundle) marker.getObject();
        int type = bundle.getInt("type");
        switch (type) {
            case MAP_JOB:
                Intent jobIntent = new Intent(getActivity(), ProjectDetailActivity.class);
                jobIntent.putExtra("type", MAP_JOB);
                jobIntent.putExtra("id", bundle.getString("id"));
                startActivity(jobIntent);
                break;
            case MAP_ACTIVITY:
                Intent activityIntent = new Intent(getActivity(), ProjectDetailActivity.class);
                activityIntent.putExtra("type", MAP_ACTIVITY);
                activityIntent.putExtra("id", bundle.getString("id"));
                startActivity(activityIntent);
                break;
        }
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

        String volunteerId = LocalDate.getInstance(getActivity()).getLocalDate("volunteerId", "");
        boolean isLogin = LocalDate.getInstance(getActivity()).getLocalDate("isLogin", false);
        if (TextUtils.isEmpty(volunteerId) || !isLogin) {
            showToast("请先登录");
            return;
        }

        //打开摄像头扫描二维码,扫描到activitytimeId的值返回到activityresult
        Intent intent = new Intent(getActivity(), MipcaActivityCapture.class);
        switch (v.getId()) {
            case R.id.btn_sign_in:
                startActivityForResult(intent, 1);
                break;
            case R.id.btn_sign_out:
                startActivityForResult(intent, 2);
                break;
        }
    }

    private void signIn(String qrcode, String volunteerId) {
        //直接发送二维码的内容
        Location location = aMap.getMyLocation();
        SignInOutDto create = new SignInOutDto();
        create.setVolunteerId(volunteerId);
        //create.setActivityTimeId();
        create.setSigntime(Util.getNowDate());
        create.setDeviceId(Util.getMac());
        create.setLat(location.getLatitude());
        create.setLng(location.getLongitude());
        create.setSourceType(0);
        create.setComputerStatus(0);
        create.setSignType(0);
        List<SignInOutDto> creates = new ArrayList<>();
        creates.add(create);
        AppActionImpl.getInstance(getActivity()).signRecordCreate(creates, new ActionCallbackListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                if (data == null) {
                    showToast("签到失败");
                    return;
                }
                if (data.size() < 1) {
                    showToast("签到失败");
                    return;
                }
                //getDistance();
                flag = saveFlag(true);
                sign_out.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                startChronometer();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("签到失败");
            }
        });
    }

    private void signOut(String qrcode, String volunteerId) {
        Location location = aMap.getMyLocation();
        //直接发送二维码的内容
        SignInOutDto create = new SignInOutDto();
        create.setVolunteerId(volunteerId);
        //create.setActivityTimeId();
        create.setSigntime(Util.getNowDate());
        create.setDeviceId(Util.getMac());
        create.setLat(location.getLatitude());
        create.setLng(location.getLongitude());
        create.setSourceType(0);
        create.setComputerStatus(0);
        create.setSignType(1);
        List<SignInOutDto> creates = new ArrayList<>();
        creates.add(create);
        AppActionImpl.getInstance(getActivity()).signRecordCreate(creates, new ActionCallbackListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                if (data == null) {
                    showToast("签退失败");
                    return;
                }
                if (data.size() < 1) {
                    showToast("签退失败");
                    return;
                }
                //getDistance();
                flag = saveFlag(false);
                sign_in.setVisibility(View.VISIBLE);
                sign_out.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                stopChronometer();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("签退失败");
            }
        });
    }

    /**
     * 开始计时
     */
    private void startChronometer() {
        if (chronometer == null)
            return;
        String lastTime = LocalDate.getInstance(getActivity()).getLocalDate("lastTime", "");
        long time = 0;
        Date now = new Date();
        if (TextUtils.isEmpty(lastTime)) {
            LocalDate.getInstance(getActivity()).setLocalDate("lastTime", now.getTime() + "");
        } else {
            time = now.getTime() - Long.parseLong(lastTime);
        }
        chronometer.setBase(SystemClock.elapsedRealtime() - time);
        chronometer.start();
    }

    /**
     * 结束计时
     */
    private void stopChronometer() {
        if (chronometer == null)
            return;
        chronometer.stop();
        LocalDate.getInstance(getActivity()).setLocalDate("lastTime", "");

    }


    private Boolean saveFlag(Boolean flag) {
        LocalDate.getInstance(getActivity()).setLocalDate("flag", flag);
        return flag;
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String qrcodeMsg = data.getExtras().getString("result");
        if (qrcodeMsg == null)
            return;
        if (qrcodeMsg.equals("0"))
            return;
        //将获取到的二维码的值传到FindPageFragment
        EventBus.getDefault().post(new QRCodeResultEvent(qrcodeMsg, requestCode));
    }
}
