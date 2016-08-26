package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.Attachment.AttachmentParaDto;
import com.example.model.Attachment.AttachmentsReturnDto;
import com.example.model.volunteer.VolunteerCreateDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.event.UpLoadFileEvent;
import com.example.renhao.wevolunteer.utils.Util;
import com.example.renhao.wevolunteer.view.Btn_TimeCountUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册专业志愿者界面  （属于登录界面的）
 */
public class RegisterProBonoActivity extends AppCompatActivity {
    private static final String TAG = "RegisterProBonoActivity";

    public static final int AREA_REGISTER = 0;
    public static final int ORG_REGISTER = 2;
    public static final int MY_POLIT = 3;
    public static final int MY_MAJOR = 4;
    public static final int MY_SERVICETIME = 5;
    public static final int MY_SERVICETYPE = 6;
    public static final int MY_SPECIALITY = 7;


    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.et_register_nickname)
    EditText etRegisterNickname;
    @Bind(R.id.et_register_trueName)
    EditText etRegisterTrueName;
    @Bind(R.id.tv_credentials_type)
    TextView tvCredentialsType;
    @Bind(R.id.ll_credentials_type)
    LinearLayout llCredentialsType;
    @Bind(R.id.et_register_id_number)
    EditText etRegisterIdNumber;
    @Bind(R.id.et_register_email)
    EditText etRegisterEmail;
    @Bind(R.id.et_register_password)
    EditText etRegisterPassword;
    @Bind(R.id.et_register_Repassword)
    EditText etRegisterRepassword;
    @Bind(R.id.LL_apply_area)
    LinearLayout LLApplyArea;
    @Bind(R.id.LL_apply_ORG)
    LinearLayout LLApplyORG;
    @Bind(R.id.ll_PoliticalAttribute)
    LinearLayout llPoliticalAttribute;
    @Bind(R.id.ll_speciality_advantages)
    LinearLayout llSpecialityAdvantages;
    @Bind(R.id.ll_intention_time)
    LinearLayout llIntentionTime;
    @Bind(R.id.ll_intention_type)
    LinearLayout llIntentionType;
    @Bind(R.id.ll_professional_ability)
    LinearLayout llProfessionalAbility;
    @Bind(R.id.et_register_phone)
    EditText etRegisterPhone;
    @Bind(R.id.btn_register_verification_code)
    Button btnRegisterVerificationCode;
    @Bind(R.id.et_register_verification_code)
    EditText etRegisterVerificationCode;
    @Bind(R.id.cb_register_agree)
    CheckBox cbRegisterAgree;
    @Bind(R.id.btn_register_volunteer)
    Button btnRegisterVolunteer;
    @Bind(R.id.btn_back_login)
    Button btnBackLogin;
    @Bind(R.id.edit_register_workUnit)
    EditText editRegisterWorkUnit;
    @Bind(R.id.edit_register_Addr)
    EditText editRegisterAddr;


    private String isCheck_register_agree;
    private String nickname;
    private String truename;
    private String cardType;
    private String cardCode;
    private String id_number;
    private String password;
    private String Repassword;
    private String email;
    private String personalCode;
    private String areaName;
    private String areaCode;
    private String orgName;
    private String orgId;
    private String polity;
    private String specialtyType;
    private String serviceTimeIntention;
    private String serviceIntention;
    private String serviceIntentionOther;
    private String skill;
    private String workUnlt;
    private String address;
    private String phone;
    private String verification_code;

    private VolunteerViewDto personal_data;
    private List<AttachmentParaDto> files;

    private boolean Flag_major = false;
    private boolean Flag_time = false;
    private boolean Flag_type = false;
    private boolean Flag_speciality = false;
    private boolean Flag_polity = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_register_pro_bono);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        personal_data = new VolunteerViewDto();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String cardType_text = data.getStringExtra("type_text");
            if (cardType_text != null) {
                tvCredentialsType.setText(cardType_text);
                cardCode = data.getStringExtra("typeCode");
            }
            personalCode = data.getStringExtra("personCode");
        }

        if (requestCode == AREA_REGISTER && resultCode == RESULT_OK) {
            areaName = data.getStringExtra("areaName");
            areaCode = data.getStringExtra("areaCode");
//                areaNameTv.setText(areaName);
        }

        if (requestCode == ORG_REGISTER && resultCode == RESULT_OK) {
            orgName = data.getStringExtra("orgName");
            orgId = data.getStringExtra("orgId");
//                orgNameTv.setText(orgName);
        }

        if (resultCode == RESULT_OK) {
            Bundle result = new Bundle();
            switch (requestCode) {
                case MY_POLIT:
                    result = data.getExtras();
                    personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
                    if (personal_data != null) {
                        polity = personal_data.getPolity();
                        System.out.println("polity------" + polity);
                    }
                    Flag_polity = true;
                    break;
                case MY_MAJOR:
                    result = data.getExtras();
                    personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
                    if (personal_data != null) {
                        specialtyType = personal_data.getSpecialtyType();
                        System.out.println("specialtyType------" + specialtyType);
                    }
                    Flag_major = true;
                    break;
                case MY_SERVICETIME:
                    result = data.getExtras();
                    personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
                    if (personal_data != null) {
                        serviceTimeIntention = personal_data.getServiceTimeIntention();
                        System.out.println("serviceTime-------" + serviceTimeIntention);
                    }
                    Flag_time = true;
                    break;
                case MY_SERVICETYPE:
                    result = data.getExtras();
                    personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
                    if (personal_data != null) {
                        serviceIntention = personal_data.getServiceIntention();
                        System.out.println("ServiceIntention-----" + serviceIntention);
                    }
                    if (personal_data != null) {
                        serviceIntentionOther = personal_data.getServiceIntentionOther();
                        System.out.println("ServiceIntentionOther-----" + serviceIntentionOther);
                    }
                    Flag_type = true;
                    break;
                case MY_SPECIALITY:
                    result = data.getExtras();
                    personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
                    if (personal_data != null) {
                        skill = personal_data.getSkilled();
                        System.out.println("Skilled-----" + skill);
                    }
                    if (personal_data != null) {
                        personal_data.getCertificatePic();
                        System.out.println("CertificatePic-----" + personal_data.getCertificatePic());
                    }
                    Flag_speciality = true;
                    break;
                default:
                    break;
            }
        }

    }

    //用户填写的注册的信息
    private void registerInfo() {
        nickname = etRegisterNickname.getText().toString();
        truename = etRegisterTrueName.getText().toString();
        cardType = tvCredentialsType.getText().toString();
        id_number = etRegisterIdNumber.getText().toString();
        phone = etRegisterPhone.getText().toString();
        password = etRegisterPassword.getText().toString();
        Repassword = etRegisterRepassword.getText().toString();
        email = etRegisterEmail.getText().toString();
        workUnlt = editRegisterWorkUnit.getText().toString();
        address = editRegisterAddr.getText().toString();
        verification_code = etRegisterVerificationCode.getText().toString();
    }


    @OnClick({R.id.ll_back, R.id.ll_credentials_type, R.id.ll_personal_attribute, R.id.LL_apply_area, R.id.LL_apply_ORG, R.id.ll_PoliticalAttribute, R.id.ll_speciality_advantages, R.id.ll_intention_time, R.id.ll_intention_type, R.id.ll_professional_ability, R.id.btn_register_verification_code, R.id.cb_register_agree, R.id.btn_register_volunteer, R.id.btn_back_login})
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("personal_data", personal_data);
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_credentials_type:
                startActivityForResult(new Intent(RegisterProBonoActivity.this, CardTypeActivity.class), 1);
                break;
            case R.id.ll_personal_attribute:
                startActivityForResult(new Intent(RegisterProBonoActivity.this, PersonalAttributeActivity.class), 1);
                break;
            case R.id.LL_apply_area:
                Intent areaIntent = new Intent(RegisterProBonoActivity.this, AreaSelectionActivity.class);
                areaIntent.putExtra("type", AREA_REGISTER);
                startActivityForResult(areaIntent, AREA_REGISTER);
                break;
            case R.id.LL_apply_ORG:
                Intent orgIntent = new Intent(RegisterProBonoActivity.this, MyORGActivity.class);
                orgIntent.putExtra("type", ORG_REGISTER);
                startActivityForResult(orgIntent, ORG_REGISTER);
                break;
            case R.id.ll_PoliticalAttribute:
                intent.putExtra("type", 0);
                intent.setClass(this, PoliticsstatusActivity.class);
                startActivityForResult(intent, MY_POLIT);
                break;
            case R.id.ll_speciality_advantages:
                intent.setClass(this, SpecilaAbilityActivity.class);
                intent.putExtra("type", 0);
                startActivityForResult(intent, MY_MAJOR);
                break;
            case R.id.ll_intention_time:
                intent.setClass(this, ApplyServiceTimeActivity.class);
                startActivityForResult(intent, MY_SERVICETIME);
                break;
            case R.id.ll_intention_type:
                intent.setClass(this, ApplyServiceCategoryActivity.class);
                intent.putExtra("type", 0);
                startActivityForResult(intent, MY_SERVICETYPE);
                break;
            case R.id.ll_professional_ability:
                intent.setClass(this, ApplyMajorAbilityActivity.class);
                startActivityForResult(intent, MY_SPECIALITY);
                break;
            case R.id.btn_register_verification_code:
                sendCode();
                break;
            case R.id.cb_register_agree:
                if (((CheckBox) view).isChecked()) {
                    isCheck_register_agree = cbRegisterAgree.getText().toString();
                    showToast(isCheck_register_agree);
                } else {
                    isCheck_register_agree = null;
                    showToast("已取消");
                }
                break;
            case R.id.btn_register_volunteer:
                informationJudge();
                break;
            case R.id.btn_back_login:
                finish();
                break;
        }
    }

    private Btn_TimeCountUtil btn_timeCountUtil;//发送验证码的计时器

    private void sendCode() {
        //先判断手机号码是否输入正确
        String phoneNumber = etRegisterPhone.getText().toString();
        if (Util.isPhoneNumber(phoneNumber)) {
            //开始计时
            btn_timeCountUtil = new Btn_TimeCountUtil(RegisterProBonoActivity.this, 60000, 1000, btnRegisterVerificationCode);
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
        vl_create.setCardType(Integer.parseInt(cardCode));
        vl_create.setIdNumber(id_number);
        vl_create.setEmail(email);
        vl_create.setUserPassword(password);
        vl_create.setReUserPassword(Repassword);
        vl_create.setJobStatus(Integer.valueOf(personalCode));
        vl_create.setAreaCode(areaCode);
        vl_create.setOrgId(orgId);
        vl_create.setPolity(polity);//政治面貌
        vl_create.setSpecialtyType(specialtyType);//特长
        vl_create.setWorkunit(workUnlt);//工作单位
        vl_create.setAddr(address);//地址
        vl_create.setServiceTimeIntention(serviceTimeIntention);//服务时间意向
        vl_create.setServiceIntention(serviceIntention);//服务意向
        vl_create.setSkilled(skill);//专业类型
        vl_create.setMobile(phone);

        vl_create.setAuditStatus(0);//审核状态  0未审核 1审核通过 2审核不通过 ,这里到时候需要加一个字段为3的

        List<VolunteerCreateDto> vl_creates = new ArrayList<>();
        vl_creates.add(vl_create);
        AppActionImpl.getInstance(getApplicationContext()).volunteerCreate(vl_creates, new ActionCallbackListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                if (data == null || data.size() < 1)
                    return;
                showToast("申请成功，请耐心等待审核");
                finish();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("网络异常，请检查后重试");
            }
        });
    }

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
        } else if (TextUtils.isEmpty(personalCode)) {
            showToast("请选择个人属性");
        } else if (TextUtils.isEmpty(areaCode)) {
            showToast("请选择所在区域");
        } else if (TextUtils.isEmpty(orgId)) {
            showToast("请选择所属机构");
        }else if (!Flag_polity) {
            showToast("请选择政治面貌");
        }else if (!Flag_major) {
            showToast("请填写专业特长");
        } else if (!Flag_time) {
            showToast("请选择意向服务时间");
        } else if (!Flag_type) {
            showToast("请选择意向服务类型");
        } else if (!Flag_speciality) {
            showToast("请选择意向专业类型");
        } else if (TextUtils.isEmpty(workUnlt)){
            showToast("工作单位不能为空");
        }else if (TextUtils.isEmpty(address)){
            showToast("家庭地址不能为空");
        }else if (TextUtils.isEmpty(phone)) {
            showToast("请输入手机号");
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
                            if (data) {
                                AppActionImpl.getInstance(getApplicationContext()).update_major_attachment(files,
                                        new ActionCallbackListener<AttachmentsReturnDto>() {
                                            @Override
                                            public void onSuccess(AttachmentsReturnDto data) {
                                                //申请资料上传
                                                RegisterSubmit();
                                            }

                                            @Override
                                            public void onFailure(String errorEvent, String message) {
                                                showToast("证书上传失败");
                                            }
                                        });
                            } else {
                                showToast("验证码错误");
                            }
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            showToast("验证码错误");
                        }
                    });

        }
    }

    @Subscribe
    public void onEventMainThread(UpLoadFileEvent event) {
        files = event.getFiles();
        Logger.v("----", "get File");
    }

    private void showToast(String msg) {
        Toast.makeText(RegisterProBonoActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
