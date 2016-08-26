package com.example.renhao.wevolunteer.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.core.AppActionImpl;
import com.example.core.listener.AccessTokenListener;
import com.example.core.local.LocalDate;
import com.example.model.AccessTokenBO;
import com.example.model.ActionCallbackListener;
import com.example.model.user.UserListDto;
import com.example.renhao.wevolunteer.IndexActivity;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    EditText et_username;
    EditText et_password;
    Button btn_login;
    Button btn_register;
    ImageView img_back;

    private String username;
    private String password;

    private SharedPreferences sp = null;
    private static final String FILE_NAME = "SaveUsernameAndPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initViewListener();
        setSharePreferences();
    }


    private void initView() {
        et_username = (EditText) findViewById(R.id.edit_login_username);
        et_password = (EditText) findViewById(R.id.edit_login_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        img_back = (ImageView) findViewById(R.id.imageView_btn_back);
    }

    private void initViewListener() {
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    private void setSharePreferences() {
        username = LocalDate.getInstance(this).getLocalDate("username", "");
        password = LocalDate.getInstance(this).getLocalDate("password", "");
        if (!TextUtils.isEmpty(username)) {
            et_username.setText(username);
        }
        if (!TextUtils.isEmpty(password)) {
            et_password.setText(password);
        }

    }

    private void onSaveContent() {
        super.onStop();
        username = et_username.getText().toString();
        password = et_password.getText().toString();
        LocalDate.getInstance(this).setLocalDate("username", username);
        LocalDate.getInstance(this).setLocalDate("password", password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                createAlertDialog();
                break;
            case R.id.imageView_btn_back:
                startActivity(new Intent(LoginActivity.this, IndexActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    private void login() {
        username = et_username.getText().toString();
        password = et_password.getText().toString();
        if (TextUtils.isEmpty(username)) {
            showToast("账号不能为空");
            return;
        } else if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空");
            return;
        } else {
            onSaveContent();
            AppActionImpl.getInstance(getApplicationContext()).getAccessToken(username, password, new AccessTokenListener() {
                @Override
                public void success(AccessTokenBO accessTokenBO) {
                    showToast("success");
                    AppActionImpl.getInstance(getApplicationContext()).userNameLogin(username, new ActionCallbackListener<UserListDto>() {
                        @Override
                        public void onSuccess(UserListDto data) {

                            //注册登录时需写的
                            LocalDate.getInstance(getApplicationContext()).setLocalDate("volunteerId", data.getId());
                            LocalDate.getInstance(getApplicationContext()).setLocalDate("isLogin", true);
                            LocalDate.getInstance(getApplicationContext()).setLocalDate("username", username);
                            LocalDate.getInstance(getApplicationContext()).setLocalDate("password", password);

                            showToast("登录成功");
                            //测试用的跳转
                            startActivity(new Intent(LoginActivity.this, IndexActivity.class));
                            finish();
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            showToast("登录失败");
                            LocalDate.getInstance(getApplicationContext()).setLocalDate("volunteerId", "");
                            LocalDate.getInstance(getApplicationContext()).setLocalDate("isLogin", false);
                        }
                    });
                }

                @Override
                public void fail() {
                    showToast("fail");
                }
            });

        }
    }

    private void createAlertDialog() {
        final AlertDialog builder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.register_dialog, null);
        Button btn_dialog_Volunteer = (Button) view.findViewById(R.id.btn_dialog_Volunteer);
        Button btn_dialog_majorVolunteer = (Button) view.findViewById(R.id.btn_dialog_majorVolunteer);
        if (btn_dialog_Volunteer != null) {
            btn_dialog_Volunteer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast("注册志愿者");
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    builder.dismiss();
                }
            });
        }
        if (btn_dialog_majorVolunteer != null) {
            btn_dialog_majorVolunteer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast("注册专业志愿者");
                    startActivity(new Intent(LoginActivity.this, RegisterProBonoActivity.class));
                    builder.dismiss();
                }
            });
        }

        builder.setView(view);
        builder.show();
    }


}
