package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.volunteer.VolunteerCreateDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.utils.Util;
import com.example.renhao.wevolunteer.view.Btn_TimeCountUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";

    public static final int AREA_REGISTER = 0;
    public static final int ORG_REGISTER = 2;

    CheckBox cb_school;
    CheckBox cb_job;
    CheckBox cb_retire;
    CheckBox cb_register_agree;
    EditText et_nickname;
    EditText et_truename;
    EditText et_id_number;
    EditText et_password;
    EditText et_Repassword;
    EditText et_email;
    EditText et_phone;
    EditText et_verification_code;
    Button btn_verification_code;
    Button btn_register_volunteer;
    Button btn_back_login;
    ImageView img_back;
    LinearLayout ll_area;
    LinearLayout ll_org;
    LinearLayout ll_credentials_type;
    TextView tv_cardType;
    TextView areaNameTv, orgNameTv;

    private int isCheck_Code = -1;
    private String isCheck_register_agree;
    private String nickname;
    private String truename;
    private String cardType;
    private String cardCode;
    private String id_number;
    private String phone;
    private String password;
    private String Repassword;
    private String email;
    private String verification_code;
    private String areaName;
    private String areaId;
    private String areaCode;
    private String orgName;
    private String orgCode;
    private String orgId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_register);

        initView();
        initViewListener();

    }

    private void initView() {
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
        et_email = (EditText) findViewById(R.id.et_register_email);
        et_verification_code = (EditText) findViewById(R.id.et_register_verification_code);
        btn_verification_code = (Button) findViewById(R.id.btn_register_verification_code);
        btn_register_volunteer = (Button) findViewById(R.id.btn_register_volunteer);
        btn_back_login = (Button) findViewById(R.id.btn_back_login);
        img_back = (ImageView) findViewById(R.id.imageView_btn_back);
        ll_area = (LinearLayout) findViewById(R.id.LL_apply_area);
        ll_org = (LinearLayout) findViewById(R.id.LL_apply_ORG);
        ll_credentials_type = (LinearLayout) findViewById(R.id.ll_credentials_type);
        tv_cardType = (TextView) findViewById(R.id.tv_credentials_type);
        areaNameTv = (TextView) findViewById(R.id.tv_register_areaName);
        orgNameTv = (TextView) findViewById(R.id.tv_register_orgName);

    }

    private void initViewListener() {
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
        ll_credentials_type.setOnClickListener(this);
    }

    //用户填写的注册的信息
    private void registerInfo() {
        nickname = et_nickname.getText().toString();
        truename = et_truename.getText().toString();
        cardType = tv_cardType.getText().toString();
        id_number = et_id_number.getText().toString();
        phone = et_phone.getText().toString();
        password = et_password.getText().toString();
        Repassword = et_Repassword.getText().toString();
        email = et_email.getText().toString();
        verification_code = et_verification_code.getText().toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String cardType_text = data.getStringExtra("type_text");
            if (cardType_text != null) {
                tv_cardType.setText(cardType_text);
                cardCode = data.getStringExtra("typeCode");
            }
        }
        if (requestCode == AREA_REGISTER) {
            areaName = data.getStringExtra("areaName");
            areaId = data.getStringExtra("areaId");
            areaCode = data.getStringExtra("areaCode");
            areaNameTv.setText(areaName);
        }

        if (requestCode == ORG_REGISTER) {
            orgName = data.getStringExtra("orgName");
            orgId = data.getStringExtra("orgId");
            orgNameTv.setText(orgName);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_credentials_type:
                startActivityForResult(new Intent(RegisterActivity.this, CardTypeActivity.class), 1);
                break;
            case R.id.cb_school:
                //根据选中的状态实现不同的功能
                if (((CheckBox) v).isChecked()) {
                    cb_job.setChecked(false);
                    cb_retire.setChecked(false);
                    isCheck_Code = 0;
                } else {
                    isCheck_Code = -1;
                }
                break;
            case R.id.cb_job:
                //根据选中的状态实现不同的功能
                if (((CheckBox) v).isChecked()) {
                    cb_school.setChecked(false);
                    cb_retire.setChecked(false);
                    isCheck_Code = 1;
                } else {
                    isCheck_Code = -1;
                }
                break;
            case R.id.cb_retire:
                //根据选中的状态实现不同的功能
                if (((CheckBox) v).isChecked()) {
                    cb_job.setChecked(false);
                    cb_school.setChecked(false);
                    isCheck_Code = 2;
                } else {
                    isCheck_Code = -1;
                }
                break;
            case R.id.LL_apply_area:
                //AreaQuery();
                Intent areaIntent = new Intent(RegisterActivity.this, AreaSelectionActivity.class);
                areaIntent.putExtra("type", AREA_REGISTER);
                startActivityForResult(areaIntent, AREA_REGISTER);
                break;
            case R.id.LL_apply_ORG:
                //organizationQueryChild();
                Intent orgIntent = new Intent(RegisterActivity.this, MyORGActivity.class);
                orgIntent.putExtra("type", ORG_REGISTER);
                startActivityForResult(orgIntent, ORG_REGISTER);
                break;
            case R.id.cb_register_agree:
                if (((CheckBox) v).isChecked()) {
                    isCheck_register_agree = cb_register_agree.getText().toString();
                    showToast(isCheck_register_agree);
                } else {
                    isCheck_register_agree = null;
                    showToast("已取消");
                }
                break;
            case R.id.btn_register_verification_code:
                sendCode();
                break;
            case R.id.btn_register_volunteer:
                informationJudge();
                break;
            case R.id.btn_back_login:
                finish();
                break;
            case R.id.imageView_btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    private Btn_TimeCountUtil btn_timeCountUtil;//发送验证码的计时器

    private void sendCode() {
        //先判断手机号码是否输入正确
        String phoneNumber = et_phone.getText().toString();
        if (Util.isPhoneNumber(phoneNumber)) {
            //开始计时
            btn_timeCountUtil = new Btn_TimeCountUtil(RegisterActivity.this, 60000, 1000, btn_verification_code);
            btn_timeCountUtil.start();
            //发送短信验证码
            AppActionImpl.getInstance(getApplicationContext()).getVerification(phoneNumber, new ActionCallbackListener<String>() {
                @Override
                public void onSuccess(String data) {
                    showToast("验证码已发送");
                }

                @Override
                public void onFailure(String errorEvent, String message) {
                    showToast("验证码发送失败");
                }
            });
        } else {
            showToast("请填写正确的手机号码");
        }
    }

    //提交注册信息的接口
    public void RegisterSubmit() {
        VolunteerCreateDto vl_create = new VolunteerCreateDto();
        vl_create.setLoginUserName(nickname);
        vl_create.setTrueName(truename);
        vl_create.setUserPassword(password);
        vl_create.setReUserPassword(Repassword);
        vl_create.setIdNumber(id_number);
        vl_create.setMobile(phone);
        vl_create.setAreaCode(areaCode);
        vl_create.setOrgId(orgId);
        vl_create.setEmail(email);
        vl_create.setJobStatus(isCheck_Code);
        vl_create.setCardType(Integer.parseInt(cardCode));
        vl_create.setId(Util.getMac());
        List<VolunteerCreateDto> vl_creates = new ArrayList<>();
        vl_creates.add(vl_create);
        AppActionImpl.getInstance(getApplicationContext()).volunteerCreate(vl_creates, new ActionCallbackListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                if (data == null || data.size() < 1)
                    return;
                showToast("注册成功");
                finish();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("注册失败");
            }
        });
    }

   /* *//**
     *
     *//*
    private void organizationQueryChild() {
        AppActionImpl.getInstance(getApplicationContext()).organizationQueryChild("00000000-0000-0000-0000-000000000000", new ActionCallbackListener<List<OrganizationListDto>>() {
            @Override
            public void onSuccess(List<OrganizationListDto> data) {
                showToast("success");
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("fail");
            }
        });
    }

    private void AreaQuery() {
        AppActionImpl.getInstance(getApplicationContext()).AreaQuery("ac689592-5a3e-4015-8609-cdeed42df6ab", new ActionCallbackListener<List<AreaListDto>>() {
            @Override
            public void onSuccess(List<AreaListDto> data) {
                showToast("success");
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("fail");
            }
        });
    }*/

    /**
     * 判断用户注册信息是否为空
     */
    private void informationJudge() {
        registerInfo();
        if (TextUtils.isEmpty(nickname)) {
            showToast("昵称不能为空");
        } else if (TextUtils.isEmpty(truename)) {
            showToast("姓名不能为空");
        } else if (TextUtils.isEmpty(cardCode)) {
            showToast("证件类型不能为空");
        } else if (!Util.isID(id_number)) {
            showToast("证件号码错误");
        } else if (!Util.isEmail(email)) {
            showToast("请输入正确的电子邮箱");
        } else if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空");
        } else if (TextUtils.isEmpty(Repassword)) {
            showToast("确认密码不能为空");
        } else if (!Repassword.equals(password)) {
            showToast("两次密码不同，请重新确认");
        } else if (isCheck_Code == -1) {
            showToast("请选择个人属性");
        } else if (TextUtils.isEmpty(phone)) {
            showToast("请输入手机号");
        } else if (TextUtils.isEmpty(areaCode)) {
            showToast("请选择所在区域");
        } else if (TextUtils.isEmpty(orgId)) {
            showToast("请选择所属机构");
        } else if (TextUtils.isEmpty(verification_code)) {
            showToast("请输入验证码");
        } else if (TextUtils.isEmpty(isCheck_register_agree)) {
            showToast("请同意《宁波市注册志愿者管理法办》");
        } else {
            //验证验证码是否正确
            AppActionImpl.getInstance(getApplicationContext()).getverify(phone, verification_code,
                    new ActionCallbackListener<Boolean>() {
                        @Override
                        public void onSuccess(Boolean data) {
                            RegisterSubmit();
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            showToast("验证码错误");
                        }
                    });

        }
    }

}
