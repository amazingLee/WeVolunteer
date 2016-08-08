package com.example.renhao.wevolunteer.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.renhao.wevolunteer.R;
import com.orhanobut.logger.Logger;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/7 21:13
 * 修改备注：
 */
public class FindPageFragment extends Fragment implements LocationSource,
        AMapLocationListener, AMap.OnMapLoadedListener, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener {
    private static final String TAG = "FindPageFragment";
    MapView mMapFindfragmentFindview;
    AMap mAMap;
    private OnLocationChangedListener mListener;//声明定位回调监听器
    private AMapLocationClient mlocationClient;//声明AMapLocationClient类对象
    private AMapLocationClientOption mLocationOption;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        mMapFindfragmentFindview = (MapView) view.findViewById(R.id.map_findfragment_findview);
        mMapFindfragmentFindview.onCreate(savedInstanceState);
        init();


        return view;
    }

    private void setUpMap() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        mAMap.setMyLocationStyle(myLocationStyle);
        mAMap.setLocationSource(this);// 设置定位监听
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        mAMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);

        mAMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
        mAMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
        mAMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
        //aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
        //addMarkersToMap();// 往地图上添加marker
    }

    private void init() {
        if (mAMap == null) {
            mAMap = mMapFindfragmentFindview.getMap();
            setUpMap();
        }
    }

    @Override
    public void onDestroyView() {
        Logger.v(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Logger.v(TAG, "onDestroy");
        mMapFindfragmentFindview.onDestroy();
        super.onDestroy();
        mlocationClient.onDestroy();
        mlocationClient=null;
    }

    @Override
    public void onResume() {
        mMapFindfragmentFindview.onResume();
        super.onResume();
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

    @Override
    public void onPause() {
        mMapFindfragmentFindview.onPause();
        super.onPause();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mMapFindfragmentFindview.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        Logger.v(TAG,"onLocationChanged");
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                //this.mlocationClient.stopLocation();//停止定位
                // 获取当前地图中心点的坐标
                LatLng localLatLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                this.mAMap.moveCamera(CameraUpdateFactory.changeLatLng(localLatLng));
                //地图缩放级别为4-20级，缩放级别不必是一个整数。
                //缩放级别较低时，您可以看到更多地区的地图；缩放级别高时，您可以查看地区更加详细的地图。
                this.mAMap.moveCamera(CameraUpdateFactory.zoomTo(12.0F));// 改变缩放级别为一个定值，同时保持其他相同的属性。
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
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
}
