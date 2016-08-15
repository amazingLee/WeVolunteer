package com.example.renhao.wevolunteer.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.core.AppActionImpl;
import com.example.core.listener.AccessTokenListener;
import com.example.model.AccessTokenBO;
import com.orhanobut.logger.Logger;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/15 11:28
 * 修改备注：
 */
public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    private ProgressDialog normalDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        normalDialog = new ProgressDialog(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 显示提示框
     *
     * @param msg
     */
    protected void showNormalDialog(String msg) {
        normalDialog.setMessage(msg);
        normalDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        normalDialog.show();
    }

    /**
     * 提示框消失
     */
    protected void dissMissNormalDialog() {
        normalDialog.dismiss();
    }

    /**
     * 获得应用接口票据
     */
    protected void getAccessToken() {
        showNormalDialog("正在连接服务器");
        AppActionImpl.getInstance(this).getAccessToken(null, null, new AccessTokenListener() {
            @Override
            public void success(AccessTokenBO accessTokenBO) {
                Logger.v(TAG, "get token success");
                dissMissNormalDialog();
            }

            @Override
            public void fail() {
                dissMissNormalDialog();
                Logger.v(TAG, "get token fail");
            }
        });
    }
}
