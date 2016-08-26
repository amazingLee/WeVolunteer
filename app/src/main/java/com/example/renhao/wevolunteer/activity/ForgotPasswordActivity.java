package com.example.renhao.wevolunteer.activity;

import android.os.Bundle;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;

/**
 * 忘记（找回）密码界面
 */
public class ForgotPasswordActivity extends BaseActivity {
    private static final String TAG = "ForgotPasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }
}
