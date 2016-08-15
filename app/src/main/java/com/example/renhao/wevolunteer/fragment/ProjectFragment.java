package com.example.renhao.wevolunteer.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.core.AppAction;
import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.dictionary.DictionaryQueryOptionDto;
import com.example.model.dictionary.DictionaryTypeListDto;
import com.example.model.dictionary.DictionaryTypeQueryOptionDto;
import com.example.model.items.HomePageListItem;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.adapter.HomePageAdapter;
import com.example.renhao.wevolunteer.adapter.ListDropDownAdapter;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.orhanobut.logger.Logger;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/8 13:36
 * 修改备注：
 */
public class ProjectFragment extends Fragment {
    private static final String TAG = "JobsFragment";
    public static final int ACTIVITY = 0;
    public static final int JOBS = 1;

    public int PROJECT_TYPE = ACTIVITY;

    @Bind(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;

    PullToRefreshListView contentView;

    private ListDropDownAdapter typeAdapter;
    private ListDropDownAdapter stateAdapter;
    private ListDropDownAdapter areaAdapter;
    private ListDropDownAdapter smartAdapter;
    private List<View> popupViews = new ArrayList<>();

    private HomePageAdapter adapter;

    private String headers[] = {"类型","状态","区域","智能筛选"};
    private String[] type = {"类型"};
    private String[] state = {"状态"};
    private String[] area = {"区域"};
    private String[] smart = {"智能筛选"};

    public AppAction mAction;

    /**
     * 设置此fragment的类型
     *
     * @param type 0 活动，1 岗位
     */
    public void setProjectFragmentType(int type) {
        PROJECT_TYPE = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        ButterKnife.bind(this, view);

        mAction = new AppActionImpl(getActivity());

        initDropDownMenu();

        View v = inflater.inflate(R.layout.view_prtlistview, null);
        //PullToRefreshListView contentView = new PullToRefreshListView(getActivity());
        contentView = ButterKnife.findById(v, R.id.ptrListview_view_list);

        initPtrListView(contentView);

        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);

        initDictionary();

        return view;
    }

    private void initDictionary() {
        //类型
        DictionaryQueryOptionDto queryOptionDto = new DictionaryQueryOptionDto();
        queryOptionDto.setCulture("语言");
        mAction.dictionaryQuery(queryOptionDto, new ActionCallbackListener<PagedListEntityDto<DictionaryListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<DictionaryListDto> data) {
                Logger.v(TAG, "-------" + data.getRows().size());
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
        //状态
        DictionaryTypeQueryOptionDto typeQueryOptionDto=new DictionaryTypeQueryOptionDto();
        typeQueryOptionDto.setCode("ActivityType");
        mAction.dictionaryTypeQuery(typeQueryOptionDto, new ActionCallbackListener<PagedListEntityDto<DictionaryTypeListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<DictionaryTypeListDto> data) {
                Logger.v(TAG, "-------" + data.getRows().size());
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
        //区域
        //智能筛选
    }

    private void initPtrListView(final PullToRefreshListView mPtr) {


        mPtr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new FinishRefresh().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new FinishRefresh().execute();
            }
        });
        mPtr.setMode(PullToRefreshBase.Mode.BOTH);//设置头部下拉刷新
        //设置刷新时显示的文本
        ILoadingLayout startLayout = mPtr.getLoadingLayoutProxy(true, false);//开始头部的layout
        startLayout.setPullLabel("正在下拉刷新....");
        startLayout.setRefreshingLabel("正在玩命加载....");
        startLayout.setReleaseLabel("放开刷新");

        ILoadingLayout endLayout = mPtr.getLoadingLayoutProxy(false, true);//开始底部的layout
        endLayout.setPullLabel("正在下拉刷新....");
        endLayout.setRefreshingLabel("正在玩命加载....");
        endLayout.setReleaseLabel("放开刷新");

        List<HomePageListItem> list = new ArrayList<>();
        list.add(new HomePageListItem(PROJECT_TYPE, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img1.imgtn.bdimg.com/it/u=2098084338,2714656019&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(PROJECT_TYPE, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img2.imgtn.bdimg.com/it/u=3602495621,3151039405&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(PROJECT_TYPE, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img2.imgtn.bdimg.com/it/u=2469638367,241984841&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(PROJECT_TYPE, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img1.imgtn.bdimg.com/it/u=2132856998,369268727&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(PROJECT_TYPE, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img0.imgtn.bdimg.com/it/u=2838777204,1513243120&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(PROJECT_TYPE, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img1.imgtn.bdimg.com/it/u=2666315763,3359766164&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(PROJECT_TYPE, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img1.imgtn.bdimg.com/it/u=37827399,354780988&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(PROJECT_TYPE, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img5.imgtn.bdimg.com/it/u=1130424812,3907942231&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(PROJECT_TYPE, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img5.imgtn.bdimg.com/it/u=2152352573,3450421690&fm=21&gp=0.jpg"));
        list.add(new HomePageListItem(PROJECT_TYPE, 0, "托起心中的太阳 慈溪市自信心困境教育帮助", 10, 100, "11",
                "http://img5.imgtn.bdimg.com/it/u=3896921233,133782688&fm=21&gp=0.jpg"));
        adapter = new HomePageAdapter(getActivity(), list);
        mPtr.setAdapter(adapter);
    }

    private void initDropDownMenu() {
        //类型
        final ListView typeView = (ListView) LayoutInflater.from(getActivity()).inflate(R.layout.view_listview, null);
        typeAdapter = new ListDropDownAdapter(getActivity(), Arrays.asList(type));
        typeView.setDividerHeight(0);
        typeView.setAdapter(typeAdapter);
        typeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : type[position]);
                mDropDownMenu.closeMenu();
            }
        });
        //状态
        final ListView stateView = new ListView(getActivity());
        stateAdapter = new ListDropDownAdapter(getActivity(), Arrays.asList(state));
        stateView.setDividerHeight(0);
        stateView.setAdapter(stateAdapter);
        stateView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stateAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : state[position]);
                mDropDownMenu.closeMenu();
            }
        });
        //区域
        final ListView areaView = new ListView(getActivity());
        areaAdapter = new ListDropDownAdapter(getActivity(), Arrays.asList(area));
        areaView.setDividerHeight(0);
        areaView.setAdapter(areaAdapter);
        areaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                areaAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : area[position]);
                mDropDownMenu.closeMenu();
            }
        });
        //智能筛选
        final ListView smartView = new ListView(getActivity());
        smartView.setDividerHeight(300);
        smartAdapter = new ListDropDownAdapter(getActivity(), Arrays.asList(smart));
        smartView.setDividerHeight(0);
        smartView.setAdapter(smartAdapter);
        smartView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                smartAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[3] : smart[position]);
                mDropDownMenu.closeMenu();
            }
        });

        //init popupViews
        popupViews.add(typeView);
        popupViews.add(stateView);
        popupViews.add(areaView);
        popupViews.add(smartView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    //测试用方法
    private class FinishRefresh extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//          adapter.notifyDataSetChanged();
            contentView.onRefreshComplete();
        }
    }
}
