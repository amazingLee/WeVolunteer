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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_organization_page2, container, false);
        ButterKnife.bind(this, v);

        initListView();
        return v;
    }

    private void initListView() {
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
        mListvew.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
