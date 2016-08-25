package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.utils.Util;
import com.example.renhao.wevolunteer.view.Btn_TimeCountUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 手机号码界面
 */
public class MobilePhoneActivity extends AppCompatActivity {
    private static final String TAG = "MobilePhoneActivity";

    private VolunteerViewDto personal_data;
    private String myPhone;

    private EditText edit_get_Verify;
    private EditText edit_get_phone;
    private Button btn_getVerify;
    private Btn_TimeCountUtil btn_timeCountUtil;
    private ImageView btn_back;
    private TextView btn_send_Verification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_phone);

        Intent intent = getIntent();
        personal_data = (VolunteerViewDto) intent.getSerializableExtra("personal_data");

        edit_get_Verify = (EditText) findViewById(R.id.edit_Verification);
        edit_get_phone = (EditText) findViewById(R.id.edit_myphone);


        btn_getVerify = (Button) findViewById(R.id.btn_phone_getVerify);
        btn_getVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPhone = edit_get_phone.getText().toString();
                if (Util.isPhoneNumber(myPhone)) {
                    //获取验证码按钮倒计时
                    btn_timeCountUtil = new Btn_TimeCountUtil(MobilePhoneActivity.this,
                            60000, 1000, btn_getVerify);
                    btn_timeCountUtil.start();
                    String phone = myPhone;
                    AppActionImpl.getInstance(getApplicationContext()).getVerification(phone, new ActionCallbackListener<String>() {
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
                    Toast.makeText(MobilePhoneActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobilePhoneActivity.this.finish();
            }
        });

        btn_send_Verification = (TextView) findViewById(R.id.tv_submitVerification);
        btn_send_Verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Verification = edit_get_Verify.getText().toString();
                final String phone = edit_get_phone.getText().toString();
                if (Verification.equals("")) {
                    showToast("验证码不能为空");
                } else if (phone.equals("")) {
                    showToast("手机号码不能为空");
                } else {
                    AppActionImpl.getInstance(getApplicationContext()).getverify(phone, Verification, new ActionCallbackListener<Boolean>() {
                        @Override
                        public void onSuccess(Boolean data) {
                            if (data) {
                                //修改项
                                personal_data.setMobile(phone);
                                List<VolunteerViewDto> vl_updates = new ArrayList<>();
                                vl_updates.add(personal_data);
                                AppActionImpl.getInstance(getApplicationContext()).volunteerUpdate(vl_updates, new ActionCallbackListener<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        Intent result = new Intent();
                                        result.putExtra("personal_data", personal_data);
                                        setResult(RESULT_OK, result);
                                        MobilePhoneActivity.this.finish();
                                    }

                                    @Override
                                    public void onFailure(String errorEvent, String message) {
                                        showToast("网络异常，请检查后重试");
                                    }
                                });
                            } else {
                                showToast("验证码错误");
                            }
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {

                        }
                    });
                }
            }
        });
    }


    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
        }
        return false;
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
