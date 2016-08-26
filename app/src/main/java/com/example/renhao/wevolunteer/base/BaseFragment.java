package com.example.renhao.wevolunteer.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/26 18:29
 * 修改备注：
 */
public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";

    protected boolean isFragmentExist = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isFragmentExist = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        isFragmentExist = false;
        super.onDestroy();
    }
}
