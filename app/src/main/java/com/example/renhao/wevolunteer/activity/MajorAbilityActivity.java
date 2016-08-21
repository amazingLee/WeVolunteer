package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.core.AppAction;
import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 专业能力界面
 */
public class MajorAbilityActivity extends AppCompatActivity {

    private AppAction mAction;

    private TextView tv_submit;
    private EditText edit_major;

    private VolunteerViewDto personal_data;
    private String my_major;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_ability);

        mAction = new AppActionImpl(this);
        Intent intent = getIntent();
        personal_data = (VolunteerViewDto) intent.getSerializableExtra("personal_data");
        edit_major = (EditText) findViewById(R.id.edit_major);
        if (personal_data.getSkilled() != null) {
            my_major = personal_data.getSkilled();
            edit_major.setText(my_major);
        }














        tv_submit= (TextView) findViewById(R.id.tv_major_submit);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_major = edit_major.getText().toString();
                List<VolunteerViewDto> vl_updates = new ArrayList<>();
                personal_data.setSkilled(my_major);
                vl_updates.add(personal_data);
                mAction.volunteerUpdate(vl_updates, new ActionCallbackListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Intent result = new Intent();
                        result.putExtra("personal_data", personal_data);
                        setResult(RESULT_OK, result);
                        showToast("修改成功");
                        MajorAbilityActivity.this.finish();
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("网络异常，请检查后重试");
                    }
                });
            }
        });


        //回退按钮
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MajorAbilityActivity.this.finish();
            }
        });

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
