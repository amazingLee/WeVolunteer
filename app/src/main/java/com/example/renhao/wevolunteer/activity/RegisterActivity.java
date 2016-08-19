package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.core.AppAction;
import com.example.core.AppActionImpl;
import com.example.core.listener.AccessTokenListener;
import com.example.model.AccessTokenBO;
import com.example.model.ActionCallbackListener;
import com.example.model.area.AreaListDto;
import com.example.model.dictionary.DictionaryViewDto;
import com.example.model.volunteer.VolunteerCreateDto;
import com.example.renhao.wevolunteer.R;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册界面
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "RegisterActivity";

    CheckBox cb_school;
    CheckBox cb_job;
    CheckBox cb_retire;
    CheckBox cb_register_agree;
    EditText et_nickname;
    EditText et_truename;
    EditText et_id_number;
    EditText et_password;
    EditText et_Repassword;
    EditText et_phone;
    EditText et_verification_code;
    Button btn_verification_code;
    Button btn_register_volunteer;
    Button btn_back_login;
    ImageView img_back;
    LinearLayout ll_area;
    LinearLayout ll_org;

    private String isCheck_text;
    private String isCheck_register_agree;
    private String nickname;
    private String truename;
    private String id_number;
    private String phone;
    private String password;
    private String Repassword;
    private String verification_code;

    private AppAction mAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_register);
        mAction = new AppActionImpl(this);
        mAction.getAccessToken(null, null, new AccessTokenListener() {
            @Override
            public void success(AccessTokenBO accessTokenBO) {
                showToast("success");
            }

            @Override
            public void fail() {
                showToast("fail");
            }
        });

        initView();
        initViewListener();
    }

    private void initView(){
        cb_school = (CheckBox) findViewById(R.id.cb_school);
        cb_job = (CheckBox) findViewById(R.id.cb_job);
        cb_retire = (CheckBox) findViewById(R.id.cb_retire);
        cb_register_agree = (CheckBox) findViewById(R.id.cb_register_agree);
        et_nickname = (EditText) findViewById(R.id.et_register_nickname);
        et_truename = (EditText) findViewById(R.id.et_register_trueName);
        et_id_number = (EditText) findViewById(R.id.et_register_id_number);
        et_phone = (EditText) findViewById(R.id.et_register_phone);
        et_password = (EditText) findViewById(R.id.et_register_password);
        et_Repassword = (EditText) findViewById(R.id.et_register_Repassword);
        et_verification_code = (EditText) findViewById(R.id.et_register_verification_code);
        btn_verification_code = (Button) findViewById(R.id.btn_register_verification_code);
        btn_register_volunteer = (Button) findViewById(R.id.btn_register_volunteer);
        btn_back_login = (Button) findViewById(R.id.btn_back_login);
        img_back = (ImageView) findViewById(R.id.imageView_btn_back);
        ll_area = (LinearLayout) findViewById(R.id.LL_apply_area);
        ll_org = (LinearLayout) findViewById(R.id.LL_apply_ORG);

    }

    private void initViewListener(){
        cb_school.setOnClickListener(this);
        cb_job.setOnClickListener(this);
        cb_retire.setOnClickListener(this);
        cb_register_agree.setOnClickListener(this);
        btn_verification_code.setOnClickListener(this);
        btn_register_volunteer.setOnClickListener(this);
        btn_back_login.setOnClickListener(this);
        img_back.setOnClickListener(this);
        ll_area.setOnClickListener(this);
        ll_org.setOnClickListener(this);
    }
    //用户填写的注册的信息
    private void registerInfo(){
        nickname = et_nickname.getText().toString();
        truename = et_truename.getText().toString();
        id_number = et_id_number.getText().toString();
        phone = et_phone.getText().toString();
        password = et_password.getText().toString();
        Repassword = et_Repassword.getText().toString();
        verification_code = et_verification_code.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb_school:
                //根据选中的状态实现不同的功能
                if (((CheckBox)v).isChecked()){
                    cb_job.setChecked(false);
                    cb_retire.setChecked(false);
                    isCheck_text = cb_school.getHint().toString();
                    showToast(isCheck_text);
                }else {
                    isCheck_text = null;
                    showToast("已取消");
                }
                break;
            case R.id.cb_job:
                //根据选中的状态实现不同的功能
                if (((CheckBox)v).isChecked()){
                    cb_school.setChecked(false);
                    cb_retire.setChecked(false);
                    isCheck_text = cb_job.getHint().toString();
                    showToast(isCheck_text);
                }else {
                    isCheck_text = null;
                    showToast("已取消");
                }
                break;
            case R.id.cb_retire:
                //根据选中的状态实现不同的功能
                if (((CheckBox)v).isChecked()){
                    cb_job.setChecked(false);
                    cb_school.setChecked(false);
                    isCheck_text = cb_retire.getHint().toString();
                    showToast(isCheck_text);
                }else {
                    isCheck_text = null;
                    showToast("已取消");
                }
                break;
            case R.id.LL_apply_area:

                mAction.getAccessToken(null, null, new AccessTokenListener() {
                    @Override
                    public void success(AccessTokenBO accessTokenBO) {
                        AreaQuery();
                        startActivity(new Intent(RegisterActivity.this, AreaSelectionActivity.class));
                        showToast("success");
                    }

                    @Override
                    public void fail() {
                        showToast("fail");
                    }
                });

                break;
            case R.id.LL_apply_ORG:
                mAction.getAccessToken("AndroidUser", "8NDVQX", new AccessTokenListener() {
                    @Override
                    public void success(AccessTokenBO accessTokenBO) {


                        startActivity(new Intent(RegisterActivity.this, AffiliatedInstitutionActivity.class));
                        showToast("success");
                    }

                    @Override
                    public void fail() {
                        showToast("fail");
                    }
                });


                break;
            case R.id.cb_register_agree:
                if (((CheckBox)v).isChecked()){
                    isCheck_register_agree = cb_register_agree.getText().toString();
                    showToast(isCheck_register_agree);
                }else {
                    isCheck_register_agree = null;
                    showToast("已取消");
                }
                break;
            case R.id.btn_register_verification_code:
                showToast("点击发送短信");
                break;
            case R.id.btn_register_volunteer:
                registerInfo();
                RegisterSubmit();
                showToast("提交注册成功," +isCheck_text+","+isCheck_register_agree+
                        ","+truename+","+id_number+","+phone+","+verification_code);
                break;
            case R.id.btn_back_login:
                showToast("已有账号  点击登录");
                finish();
                break;
            case R.id.imageView_btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    //提交注册信息的接口
    public void RegisterSubmit(){
        VolunteerCreateDto vl_create = new VolunteerCreateDto();
        vl_create.setLoginUserName("AndroidUser1");
        vl_create.setReUserPassword("123456");
        vl_create.setAreaCode("3302030101");
        vl_create.setTrueName("葛焕然");
        vl_create.setUserPassword("123456");
        vl_create.setIdNumber("332502199407143993");
        vl_create.setMobile("15728006853");
        vl_create.setOrgId("011de0b0-9c25-4002-98be-29df51bd2fbe");
        vl_create.setEmail("750954284@qq.com");
        Logger.v(TAG, new Gson().toJson(vl_create));
        List<VolunteerCreateDto> vl_creates = new ArrayList<>();
        vl_creates.add(vl_create);
        mAction.volunteerCreate(vl_creates, new ActionCallbackListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                showToast("success");
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("fail");
            }
        });
    }

    private void getPersonAttribute(){
        AppActionImpl.getInstance(getApplicationContext()).dictionaryQueryDetailDefault("PersonAttribute", "1b87202f-9174-4191-b104-1af9a70e8639", new ActionCallbackListener<DictionaryViewDto>() {
            @Override
            public void onSuccess(DictionaryViewDto data) {

            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }

    private void AreaQuery(){
        mAction.AreaQuery("ac689592-5a3e-4015-8609-cdeed42df6ab", new ActionCallbackListener<List<AreaListDto>>() {
            @Override
            public void onSuccess(List<AreaListDto> data) {
                showToast("success");
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("fail");
            }
        });
    }


    private void showToast(String msg){
        Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

}
