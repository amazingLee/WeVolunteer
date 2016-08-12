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
import com.example.renhao.wevolunteer.activity.ApplyProBonoActivity;
import com.example.renhao.wevolunteer.activity.FAQActivity;
import com.example.renhao.wevolunteer.activity.MyORGActivity;
import com.example.renhao.wevolunteer.activity.PersonalDataActivity;
import com.example.renhao.wevolunteer.activity.ProfessionalSelectionActivity;
import com.example.renhao.wevolunteer.activity.ReportProblemActivity;

public class PersonalFragment extends Fragment implements View.OnClickListener {
    private View mainview;


    private final int PROFESSIONAL = 0;
    private final int TO_PROFESSIONAL = 1;
    private final int TO_JOB = 2;
    private final int TO_ATTENTION = 3;
    private final int TO_ORG = 4;
    private final int TO_RANK = 5;
    private final int TO_INFORMATION = 6;
    private final int TO_ABOUTUS = 7;
    private final int TO_WIPE_CACHE = 8;
    private final int TO_REPORT_PROBLEM = 9;
    private final int TO_FAQ = 10;

    boolean ifprofessional = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainview = inflater.inflate(R.layout.fragment_personal, container, false);


        SetClickListen();//设置点击事件的监听以及初始化组件

        return mainview;
    }

    private void SetClickListen() {
        View bottomView = mainview.findViewById(R.id.bottom_part);//先找到include的组件

        //初始化组件
        LinearLayout professional_true = (LinearLayout) bottomView.findViewById(R.id.LL_PF_Professional_true);
        LinearLayout Professional_false = (LinearLayout) bottomView.findViewById(R.id.LL_PF_Professional_false);
        LinearLayout job = (LinearLayout) bottomView.findViewById(R.id.LL_PF_job);
        LinearLayout attention = (LinearLayout) bottomView.findViewById(R.id.LL_PF_attention);
        LinearLayout ORG = (LinearLayout) bottomView.findViewById(R.id.LL_PF_ORG);
        LinearLayout rank = (LinearLayout) bottomView.findViewById(R.id.LL_PF_rank);
        LinearLayout Information = (LinearLayout) bottomView.findViewById(R.id.LL_PF_information);
        LinearLayout aboutUS = (LinearLayout) bottomView.findViewById(R.id.LL_PF_aboutUS);
        LinearLayout WIPE_CACHE = (LinearLayout) bottomView.findViewById(R.id.LL_PF_WIPE_CACHE);
        LinearLayout REPORT_PROBLEM = (LinearLayout) bottomView.findViewById(R.id.LL_PF_REPORT_PROBLEM);
        LinearLayout FAQ = (LinearLayout) bottomView.findViewById(R.id.LL_PF_FAQ);


        //添加点击监听
        professional_true.setOnClickListener(this);
        Professional_false.setOnClickListener(this);
        job.setOnClickListener(this);
        attention.setOnClickListener(this);
        ORG.setOnClickListener(this);
        rank.setOnClickListener(this);
        Information.setOnClickListener(this);
        aboutUS.setOnClickListener(this);
        WIPE_CACHE.setOnClickListener(this);
        REPORT_PROBLEM.setOnClickListener(this);
        FAQ.setOnClickListener(this);

        //添加点击标签
        professional_true.setTag(PROFESSIONAL);
        Professional_false.setTag(TO_PROFESSIONAL);
        job.setTag(TO_JOB);
        attention.setTag(TO_ATTENTION);
        ORG.setTag(TO_ORG);
        rank.setTag(TO_RANK);
        Information.setTag(TO_INFORMATION);
        aboutUS.setTag(TO_ABOUTUS);
        WIPE_CACHE.setTag(TO_WIPE_CACHE);
        REPORT_PROBLEM.setTag(TO_REPORT_PROBLEM);
        FAQ.setTag(TO_FAQ);


        //判断是否为专业志愿志愿者，显示不同按钮
        if (ifprofessional) {
            professional_true.setVisibility(View.VISIBLE);
            Professional_false.setVisibility(View.GONE);
        } else {
            professional_true.setVisibility(View.GONE);
            Professional_false.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        System.out.println(tag);
        Intent intent = new Intent();
        switch (tag) {
            case PROFESSIONAL:
                intent.setClass(getActivity(), ProfessionalSelectionActivity.class);
                startActivity(intent);
                break;
            case TO_PROFESSIONAL:
                intent.setClass(getActivity(), ApplyProBonoActivity.class);
                startActivity(intent);
                break;
            case TO_JOB:
                break;
            case TO_ATTENTION:
                break;
            case TO_ORG:
                intent.setClass(getActivity(), MyORGActivity.class);
                startActivity(intent);
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
