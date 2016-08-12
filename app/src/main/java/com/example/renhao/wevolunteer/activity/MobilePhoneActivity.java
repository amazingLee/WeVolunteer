package com.example.renhao.wevolunteer.activity;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.view.Btn_TimeCountUtil;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 手机号码界面
 */
public class MobilePhoneActivity extends AppCompatActivity {
    private static final String TAG = "MobilePhoneActivity";

    private EventHandler eh;

    private EditText edit_get_Verify;
    private EditText edit_get_phone;
    private Button btn_getVerify;
    private Btn_TimeCountUtil btn_timeCountUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_phone);

        edit_get_Verify = (EditText) findViewById(R.id.edit_Verification);
        edit_get_phone = (EditText) findViewById(R.id.edit_myphone);
        //初始化短信分享SDK
        SMSSDK.initSDK(this, "15fc937ea69cc", "b09674e7e93e3d79cdc58ce78000cde2");

        eh = new EventHandler() {
            @Override
            public void afterEvent(final int event, final int result, final Object data) {
                Looper.prepare();
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        System.out.println("验证通过");
                        SMSSDK.unregisterAllEventHandler();//关闭sdkHandler服务
                        MobilePhoneActivity.this.finish();
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        Toast.makeText(MobilePhoneActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                    System.out.println(((Throwable) data).getMessage());
                }
                Looper.loop();
            }
        };

        SMSSDK.registerEventHandler(eh); //注册短信回调

        btn_getVerify = (Button) findViewById(R.id.btn_phone_getVerify);
        btn_getVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edit_get_phone.getText().toString().equals("")) {
                    //获取验证码按钮倒计时
                    btn_timeCountUtil = new Btn_TimeCountUtil(MobilePhoneActivity.this,
                            60000, 1000, btn_getVerify);
                    btn_timeCountUtil.start();
                    //获取验证码
                    SMSSDK.getVerificationCode("+86", edit_get_phone.getText().toString());
                } else {
                    Toast.makeText(MobilePhoneActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void submitVerification(View v) {
        if (!edit_get_Verify.getText().toString().equals("") && !edit_get_phone.getText().toString().equals("")) {
            System.out.println("click");
            SMSSDK.submitVerificationCode("+86", edit_get_phone.getText().toString(),
                    edit_get_Verify.getText().toString());
        } else {
            Toast.makeText(MobilePhoneActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            SMSSDK.unregisterAllEventHandler();//关闭sdkHandler服务
            this.finish();
        }
        return false;
    }
}
