package com.example.renhao.wevolunteer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.core.AppAction;
import com.example.core.AppActionImpl;
import com.example.core.listener.AccessTokenListener;
import com.example.model.AccessTokenBO;
import com.example.model.ActionCallbackListener;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.view.Btn_TimeCountUtil;

/**
 * 手机号码界面
 */
public class MobilePhoneActivity extends AppCompatActivity {
    private static final String TAG = "MobilePhoneActivity";


    private AppAction mAction;

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


        //初始化连接
        mAction = new AppActionImpl(this);

        //暂时获取票据
        mAction.getAccessToken("AndroidUser", "8NDVQX", new AccessTokenListener() {
            @Override
            public void success(AccessTokenBO accessTokenBO) {
                showToast("success");
            }

            @Override
            public void fail() {
                showToast("fail");
            }
        });


        edit_get_Verify = (EditText) findViewById(R.id.edit_Verification);
        edit_get_phone = (EditText) findViewById(R.id.edit_myphone);


        btn_getVerify = (Button) findViewById(R.id.btn_phone_getVerify);
        btn_getVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edit_get_phone.getText().toString().equals("")) {
                    //获取验证码按钮倒计时
                    btn_timeCountUtil = new Btn_TimeCountUtil(MobilePhoneActivity.this,
                            60000, 1000, btn_getVerify);
                    btn_timeCountUtil.start();

                    String phone = edit_get_phone.getText().toString();
                    mAction.getVerification(phone, new ActionCallbackListener<String>() {


                        @Override
                        public void onSuccess(String data) {
                            showToast("验证码发送");
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
                String phone = edit_get_phone.getText().toString();
                if (Verification.equals("")) {
                    showToast("验证码不能为空");
                } else if (phone.equals("")) {
                    showToast("手机号码不能为空");
                } else {
                    mAction.getverify(phone, Verification, new ActionCallbackListener<String>() {

                        //已经可以成功验证，但是返回值取有点问题，取得正常以后再写跳转
                        @Override
                        public void onSuccess(String data) {

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
