package com.example.renhao.wevolunteer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.model.activity.ActivityViewDto;
import com.example.model.items.HomePageListItem;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.adapter.HomePageAdapter;
import com.example.renhao.wevolunteer.event.OrganizationDetailEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/12 9:34
 * 修改备注：
 */
public class OrganizationPage2 extends Fragment {
    private static final String TAG = "OrganizationPage2";
    @Bind(R.id.listvew_organization_projectItem)
    ListView mListvew;

    private HomePageAdapter adapter;
    private List<HomePageListItem> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_organization_page2, container, false);
        ButterKnife.bind(this, v);
        EventBus.getDefault().register(this);
        initListView();
        return v;
    }

    @Subscribe
    public void onEventMainThread(OrganizationDetailEvent event) {
        List<ActivityViewDto> dates = event.getCompanyViewDto().getActivitys();
        if (dates == null)
            return;
        list = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            HomePageListItem item = new HomePageListItem();
            item.setType(dates.get(i).getType());
            item.setState(dates.get(i).getStatus());
            item.setTitle(dates.get(i).getActivityName());
            item.setNum(dates.get(i).getRecruited());
            item.setMaxNum(dates.get(i).getRecruitNumber());
            item.setTime(dates.get(i).getLengthTime());
            item.setImg(dates.get(i).getAppImgUrl());
            list.add(item);
        }

        adapter.setDate(list);

    }

    private void initListView() {
        adapter = new HomePageAdapter(getActivity(), list);
        mListvew.setAdapter(adapter);

        mListvew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
