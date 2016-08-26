package com.example.renhao.wevolunteer.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/17 10:33
 * 修改备注：
 */
public class BaseFragmentV4 extends Fragment {
    private static final String TAG = "BaseFragment";

    public static final int ACTIVITY = 0;
    public static final int JOBS = 1;

    public static final int REFRESH = 0;
    public static final int ADD = 1;

    protected boolean isFragmentExist = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isFragmentExist = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        isFragmentExist = false;
        super.onDestroy();
    }

    protected void showToast(String msg) {
        if (isFragmentExist)
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
