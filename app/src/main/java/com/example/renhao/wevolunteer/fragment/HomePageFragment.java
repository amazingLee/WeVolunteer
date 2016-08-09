package com.example.renhao.wevolunteer.Fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.model.items.HomePageGridItem;
import com.example.model.items.HomePageListItem;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.adapter.HomePageAdapter;
import com.example.renhao.wevolunteer.adapter.HomePageNoScrollGridAdapter;
import com.example.renhao.wevolunteer.view.NoScrollGridView;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：WeVolunteer
 * 类描述：主页
 * 创建人：renhao
 * 创建时间：2016/8/5 16:38
 * 修改备注：
 */
public class HomePageFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private static final String TAG = "HpmePageFragment";

    private PullToRefreshListView mPtrListviewHomapageList;
    private SliderLayout mSliderHomepageImgslider;
    private View imageSliderView;
    private NoScrollGridView gridView;
    private HomePageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        initImageSliderView();

        initGridButton();

        initPullToRefreshListView(view);
        return view;
    }

    /**
     * 初始化下拉刷新控件
     *
     * @param view
     */
    private void initPullToRefreshListView(View view) {
        mPtrListviewHomapageList = (PullToRefreshListView) view.findViewById(R.id.ptrListview_homapage_list);

        this.mPtrListviewHomapageList.getRefreshableView().addHeaderView(this.imageSliderView);//天假imageslider
        this.mPtrListviewHomapageList.getRefreshableView().addHeaderView(gridView);//添加imageslider


        mPtrListviewHomapageList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        mPtrListviewHomapageList.setMode(PullToRefreshBase.Mode.PULL_FROM_START);//设置头部下拉刷新
        //设置刷新时显示的文本
        ILoadingLayout startLayout = mPtrListviewHomapageList.getLoadingLayoutProxy(true, false);//开始头部的layout
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
        mPtrListviewHomapageList.setAdapter(adapter);
    }

    /**
     * 初始化按钮，并为按钮绑定监听
     */
    private void initGridButton() {
        gridView = new NoScrollGridView(getActivity());
        //gridView.setBackgroundResource(R.drawable.border_shallowblack);
        gridView.setNumColumns(3);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Logger.v(TAG, "注册志愿者");
                        break;
                    case 1:
                        Logger.v(TAG, "注册专业志愿者");
                        break;
                    case 2:
                        Logger.v(TAG, "组织");
                        break;
                    case 3:
                        Logger.v(TAG, "岗位");
                        break;
                    case 4:
                        Logger.v(TAG, "活动");
                        break;
                    case 5:
                        Logger.v(TAG, "积分商城");
                        break;
                }
            }
        });
        ArrayList<HomePageGridItem> items = new ArrayList<>();
        items.add(new HomePageGridItem(R.drawable.regist, "注册", "志愿者", R.color.colorCyan));
        items.add(new HomePageGridItem(R.drawable.professionalregist, "注册", "专业志愿者", R.color.colorCyan));
        items.add(new HomePageGridItem(R.drawable.organization, "组织", null, R.color.colorCyan));
        items.add(new HomePageGridItem(R.drawable.jobs, "岗位", null, R.color.colorBlue));
        items.add(new HomePageGridItem(R.drawable.activity, "活动", null, R.color.colorGreen));
        items.add(new HomePageGridItem(R.drawable.shop, "积分商城", null, R.color.colorOrange));
        gridView.setAdapter(new HomePageNoScrollGridAdapter(getActivity(), items));
    }

    /**
     * 初始化图片滑动器
     */
    private void initImageSliderView() {
        this.imageSliderView = LayoutInflater.from(getActivity()).inflate(R.layout.view_imageslider, null);
        mSliderHomepageImgslider = (SliderLayout) imageSliderView.findViewById(R.id.slider_homepage_imgslider);
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal",
                "http://img5.imgtn.bdimg.com/it/u=773976410,3208791615&fm=11&gp=0.jpg");
        url_maps.put("Big Bang Theory",
                "http://img2.imgtn.bdimg.com/it/u=1688175549,654537977&fm=21&gp=0.jpg");
        url_maps.put("House of Cards",
                "http://img2.imgtn.bdimg.com/it/u=2456952410,3637246060&fm=21&gp=0.jpg");
        url_maps.put("Game of Thrones",
                "http://img0.imgtn.bdimg.com/it/u=636093399,3331762473&fm=21&gp=0.jpg");

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    //.description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mSliderHomepageImgslider.addSlider(textSliderView);
        }
    }


    //图片滑动器的监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Logger.v(TAG, "page  position  " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Logger.v(TAG, "image click  " + slider.getBundle().getString("extra"));
    }
}
