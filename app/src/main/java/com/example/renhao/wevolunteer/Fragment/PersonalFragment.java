package com.example.renhao.wevolunteer.Fragment;
/*
 *
 * Created by Ge on 2016/8/8  10:03.
 *
 */

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.activity.AboutUsActivity;
import com.example.renhao.wevolunteer.activity.FAQActivity;
import com.example.renhao.wevolunteer.activity.PersonalDataActivity;
import com.example.renhao.wevolunteer.activity.ReportProblemActivity;

public class PersonalFragment extends Fragment implements View.OnClickListener {
    private View mainview;


    private final int TO_PROFESSIONAL = 0;
    private final int TO_JOB = 1;
    private final int TO_ATTENTION = 2;
    private final int TO_ORG = 3;
    private final int TO_RANK = 4;
    private final int TO_INFORMATION=5;
    private final int TO_ABOUTUS = 6;
    private final int TO_WIPE_CACHE = 7;
    private final int TO_REPORT_PROBLEM = 8;
    private final int TO_FAQ = 9;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainview = inflater.inflate(R.layout.fragment_personal, container, false);


        SetClickListen();//设置又有点击事件的监听

        return mainview;
    }

    private void SetClickListen() {
        View bottomView=mainview.findViewById(R.id.bottom_part);//先找到include的组件
        //初始化组件
        LinearLayout Professional = (LinearLayout) bottomView.findViewById(R.id.item_Professional);
        LinearLayout job = (LinearLayout) bottomView.findViewById(R.id.item_job);
        LinearLayout attention = (LinearLayout) bottomView.findViewById(R.id.item_attention);
        LinearLayout ORG = (LinearLayout) bottomView.findViewById(R.id.item_ORG);
        LinearLayout rank = (LinearLayout) bottomView.findViewById(R.id.item_rank);
        LinearLayout Information= (LinearLayout) bottomView.findViewById(R.id.item_information);
        LinearLayout aboutUS = (LinearLayout) bottomView.findViewById(R.id.item_aboutUS);
        LinearLayout WIPE_CACHE = (LinearLayout) bottomView.findViewById(R.id.item_WIPE_CACHE);
        LinearLayout REPORT_PROBLEM = (LinearLayout) bottomView.findViewById(R.id.item_REPORT_PROBLEM);
        LinearLayout FAQ = (LinearLayout) bottomView.findViewById(R.id.item_FAQ);
        //添加监听
        Professional.setOnClickListener(this);
        job.setOnClickListener(this);
        attention.setOnClickListener(this);
        ORG.setOnClickListener(this);
        rank.setOnClickListener(this);
        Information.setOnClickListener(this);
        aboutUS.setOnClickListener(this);
        WIPE_CACHE.setOnClickListener(this);
        REPORT_PROBLEM.setOnClickListener(this);
        FAQ.setOnClickListener(this);
        //添加标签
        Professional.setTag(TO_PROFESSIONAL);
        job.setTag(TO_JOB);
        attention.setTag(TO_ATTENTION);
        ORG.setTag(TO_ORG);
        rank.setTag(TO_RANK);
        Information.setTag(TO_INFORMATION);
        aboutUS.setTag(TO_ABOUTUS);
        WIPE_CACHE.setTag(TO_WIPE_CACHE);
        REPORT_PROBLEM.setTag(TO_REPORT_PROBLEM);
        FAQ.setTag(TO_FAQ);
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        Intent intent = new Intent();
        switch (tag) {
            case TO_PROFESSIONAL:
                break;
            case TO_JOB:
                break;
            case TO_ATTENTION:
                break;
            case TO_ORG:
                break;
            case TO_RANK:
                break;
            case TO_INFORMATION:
                intent.setClass(getActivity(), PersonalDataActivity.class);
                startActivity(intent);
                break;
            case TO_ABOUTUS:
                intent.setClass(getActivity(), AboutUsActivity.class);
                startActivity(intent);
                break;
            case TO_WIPE_CACHE:
                break;
            case TO_REPORT_PROBLEM:
                intent.setClass(getActivity(), ReportProblemActivity.class);
                startActivity(intent);
                break;
            case TO_FAQ:
                intent.setClass(getActivity(), FAQActivity.class);
                startActivity(intent);
                break;
        }
    }

}
