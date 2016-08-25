package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 昵称界面
 */
public class NickNameActivity extends AppCompatActivity {
    private static final String TAG = "NickNameActivity";


    private VolunteerViewDto personal_data;
    private String MyNickName;
    private boolean IsShowTrueName;

    private TextView tv_nickname;
    private CheckBox isShow;
    private TextView update_submit;
    private ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_name);

        Intent intent = getIntent();
        personal_data = (VolunteerViewDto) intent.getSerializableExtra("personal_data");
        MyNickName = personal_data.getNickName();
        IsShowTrueName = personal_data.getShowTrueName();

        tv_nickname = (TextView) findViewById(R.id.edit_NickName_name);
        isShow = (CheckBox) findViewById(R.id.checkBox_NickName_isShow);
        if (MyNickName != null)
            tv_nickname.setText(MyNickName);
        isShow.setChecked(IsShowTrueName);


        update_submit = (TextView) findViewById(R.id.tv_nickname_update);
        update_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyNickName = tv_nickname.getText().toString();
                IsShowTrueName = isShow.isChecked();
                //修改项
                personal_data.setNickName(MyNickName);
                personal_data.setShowTrueName(IsShowTrueName);

                List<VolunteerViewDto> vl_updates = new ArrayList<>();
                vl_updates.add(personal_data);
                AppActionImpl.getInstance(getApplicationContext()).volunteerUpdate(vl_updates, new ActionCallbackListener<String>() {
                    @Override
                    public void onSuccess(String data) {

                        Intent result = new Intent();
                        result.putExtra("personal_data", personal_data);
                        setResult(RESULT_OK, result);
                        showToast("修改成功");
                        NickNameActivity.this.finish();
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("网络异常，请检查后重试");
                    }
                });
            }
        });

        //回退按钮
        btn_back = (ImageView) findViewById(R.id.imageView_nickname_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NickNameActivity.this.finish();
            }
        });

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
