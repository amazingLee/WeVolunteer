package com.example.renhao.wevolunteer.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.model.items.HomePageListItem;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.adapter.HomePageAdapter;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/8 13:35
 * 修改备注：
 */
public class ActivityFragment extends Fragment {
    private static final String TAG = "ActivityFragment";
    @Bind(R.id.ptrListview_activity_list)
    PullToRefreshListView mPtr;

    private HomePageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        ButterKnife.bind(this, view);
        initPullToRefreshListView();

        return view;
    }

    /**
     * 初始化下拉刷新控件
     *
     */
    private void initPullToRefreshListView() {
        /*this.mPtr.getRefreshableView().addHeaderView(this.imageSliderView);//天假imageslider
        this.mPtr.getRefreshableView().addHeaderView(gridView);//添加imageslider*/


        mPtr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        mPtr.setMode(PullToRefreshBase.Mode.PULL_FROM_START);//设置头部下拉刷新
        //设置刷新时显示的文本
        ILoadingLayout startLayout = mPtr.getLoadingLayoutProxy(true, false);//开始头部的layout
        startLayout.setPullLabel("正在下拉刷新....");
        startLayout.setRefreshingLabel("正在玩命加载....");
        startLayout.setReleaseLabel("放开刷新");

        List<HomePageListItem> list = new ArrayList<>();
        list.add(new HomePageListItem(0, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img1.imgtn.bdimg.com/it/u=2098084338,2714656019&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(0, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img2.imgtn.bdimg.com/it/u=3602495621,3151039405&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(0, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img2.imgtn.bdimg.com/it/u=2469638367,241984841&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(0, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img1.imgtn.bdimg.com/it/u=2132856998,369268727&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(0, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img0.imgtn.bdimg.com/it/u=2838777204,1513243120&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(0, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img1.imgtn.bdimg.com/it/u=2666315763,3359766164&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(0, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img1.imgtn.bdimg.com/it/u=37827399,354780988&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(0, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img5.imgtn.bdimg.com/it/u=1130424812,3907942231&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(0, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img5.imgtn.bdimg.com/it/u=2152352573,3450421690&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(0, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img5.imgtn.bdimg.com/it/u=3896921233,133782688&fm=21&gp=0.jpg"));
        adapter = new HomePageAdapter(getActivity(), list);
        mPtr.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
