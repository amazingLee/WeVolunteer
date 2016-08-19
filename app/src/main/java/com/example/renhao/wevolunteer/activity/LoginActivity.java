package com.example.renhao.wevolunteer.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.core.AppAction;
import com.example.core.AppActionImpl;
import com.example.core.listener.AccessTokenListener;
import com.example.model.AccessTokenBO;
import com.example.model.ActionCallbackListener;
import com.example.model.user.UserListDto;
import com.example.renhao.wevolunteer.R;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_username ;
    EditText et_password;
    Button btn_login;
    Button btn_register;
    ImageView img_back;

    private AppAction mAction;
    private String username;
    private String password;

    private SharedPreferences sp = null;
    private static final String FILE_NAME = "SaveUsernameAndPassword";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAction = new AppActionImpl(this);

        initView();
        initViewListener();
        setSharePreferences();
    }


    private void initView(){
        et_username = (EditText) findViewById(R.id.edit_login_username);
        et_password = (EditText) findViewById(R.id.edit_login_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        img_back = (ImageView) findViewById(R.id.imageView_btn_back);
    }

    private void initViewListener(){
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    private void setSharePreferences(){
        //获取SharedPreferences时，需要设置两参数
        //第一个是保存的文件的名称，第二个参数是保存的模式（是否只被本应用使用）
        sp = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        //从文件中获取保存的数据
        username = sp.getString("username","");
        password = sp.getString("password","");
        if (username !=null && !"".equals(username)){
            et_username.setText(username);
        }
        if (password !=null && !"".equals(password)){
            et_password.setText(password);
        }

    }

    private void onSaveContent(){
        super.onStop();
        username = et_username.getText().toString();
        password = et_password.getText().toString();
        sp = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                mAction.getAccessToken(username, password, new AccessTokenListener() {
                    @Override
                    public void success(AccessTokenBO accessTokenBO) {
                        showToast("success");
                        onSaveContent();
                        if (TextUtils.isEmpty(username)) {
                            showToast("账号不能为空");
                        } else if (TextUtils.isEmpty(password)) {
                            showToast("密码不能为空");
                        } else {
                            mAction.userNameLogin(username, new ActionCallbackListener<UserListDto>() {
                                @Override
                                public void onSuccess(UserListDto data) {
                                    showToast("success");
                                }

                                @Override
                                public void onFailure(String errorEvent, String message) {
                                    showToast("fail");
                                }
                            });
                        }
                    }

                    @Override
                    public void fail() {
                        showToast("fail");
                    }
                });


                break;
            case R.id.btn_register:
                createAlertDialog();
                showToast("点击注册");
                break;
            case R.id.imageView_btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void createAlertDialog(){
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



    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
