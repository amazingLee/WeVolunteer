package com.example.renhao.wevolunteer.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityListDto;
import com.example.model.activity.ActivityQueryOptionDto;
import com.example.model.items.HomePageListItem;
import com.example.renhao.wevolunteer.ProjectDetailActivity;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.adapter.HomePageAdapter;
import com.example.renhao.wevolunteer.adapter.ListDropDownAdapter;
import com.example.renhao.wevolunteer.base.BaseFragment;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
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
public class ProjectFragment extends BaseFragment {
    private static final String TAG = "JobsFragment";


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

    private String headers[] = {"类型", "状态", "区域", "智能筛选"};
    private String[] type = {"类型"};
    private String[] state = {"状态"};
    private String[] area = {"区域"};
    private String[] smart = {"智能筛选"};

    private List<HomePageListItem> list = new ArrayList<>();
    private List<ActivityListDto> dates = new ArrayList<>();
    private int PageIndex;//(integer, optional): 当前页码
    private int PageSize;//(integer, optional): 每页条数
    private int TotalCount;// (integer, optional): 总共记录数
    private int TotalPages;//(integer, optional): 总共分页数
    private int StartPosition;// (integer, optional): 记录开始位置
    private int EndPosition;//(integer, optional): 记录结束位置
    private boolean HasPreviousPage;// (boolean, optional): 是否有上一页
    private boolean HasNextPage;//(boolean, optional): 是否有下一页

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

        initDropDownMenu();

        View v = inflater.inflate(R.layout.view_prtlistview, null);
        //PullToRefreshListView contentView = new PullToRefreshListView(getActivity());
        contentView = ButterKnife.findById(v, R.id.ptrListview_view_list);

        initPtrListView(contentView);

        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);

        initDictionary();

        initDate(REFRESH);

        return view;
    }

    private void initDate(final int type) {
        ActivityQueryOptionDto dto = new ActivityQueryOptionDto();
        dto.setType(PROJECT_TYPE);
        if (type == ADD) {
            dto.setPageIndex(PageIndex + 1);
        }
        AppActionImpl.getInstance(getActivity()).activityQuery(dto, new ActionCallbackListener<PagedListEntityDto<ActivityListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<ActivityListDto> data) {
                contentView.onRefreshComplete();
                if (type == REFRESH) {
                    list = new ArrayList<HomePageListItem>();
                    dates = new ArrayList<ActivityListDto>();
                }
                dates = data.getRows();
                for (int i = 0; i < dates.size(); i++) {
                    ActivityListDto listDto = dates.get(i);
                    HomePageListItem item = new HomePageListItem();
                    item.setType(listDto.getType());
                    item.setState(listDto.getStatus());
                    item.setTitle(listDto.getActivityName());
                    item.setNum(listDto.getRecruited());
                    item.setMaxNum(listDto.getRecruitNumber());
                    item.setTime(listDto.getLengthTime());
                    item.setImg("");
                    list.add(item);
                }
                PageIndex = data.getPageIndex();
                PageSize = data.getPageSize();
                TotalCount = data.getTotalCount();
                TotalPages = data.getTotalPages();
                StartPosition = data.getStartPosition();
                EndPosition = data.getEndPosition();
                HasPreviousPage = data.getHasPreviousPage();
                HasNextPage = data.getHasNextPage();

                if (!HasNextPage) {
                    showToast("到底了");
                }

                adapter.setDate(list);
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                contentView.onRefreshComplete();
            }
        });

    }

    private void initDictionary() {
        //类型
      /*  DictionaryQueryOptionDto queryOptionDto = new DictionaryQueryOptionDto();
        //queryOptionDto.setCulture("语言");
        AppActionImpl.getInstance(getActivity()).dictionaryQuery(queryOptionDto,
                new ActionCallbackListener<PagedListEntityDto<DictionaryListDto>>() {
                    @Override
                    public void onSuccess(PagedListEntityDto<DictionaryListDto> data) {
                        Logger.v(TAG, "-------" + data.getRows().size());
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });*/
        //状态
       /* DictionaryTypeQueryOptionDto typeQueryOptionDto = new DictionaryTypeQueryOptionDto();
        //typeQueryOptionDto.setCode("ActivityType");
        AppActionImpl.getInstance(getActivity()).dictionaryTypeQuery(typeQueryOptionDto,
                new ActionCallbackListener<PagedListEntityDto<DictionaryTypeListDto>>() {
                    @Override
                    public void onSuccess(PagedListEntityDto<DictionaryTypeListDto> data) {
                        Logger.v(TAG, "-------" + data.getRows().size());
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });*/
        //区域
        //智能筛选
    }

    private void initPtrListView(final PullToRefreshListView mPtr) {


        mPtr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                initDate(REFRESH);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (HasNextPage) {
                    initDate(ADD);
                } else {
                    showToast("已经是最后一页");
                    new FinishRefresh().execute();

                }

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

        adapter = new HomePageAdapter(getActivity(), list);
        mPtr.setAdapter(adapter);

        mPtr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
                intent.putExtra("id", dates.get(position - 1).getId());
                intent.putExtra("type", dates.get(position - 1).getType());
                startActivity(intent);
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化下拉菜单
     */
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
                Thread.sleep(100);
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
