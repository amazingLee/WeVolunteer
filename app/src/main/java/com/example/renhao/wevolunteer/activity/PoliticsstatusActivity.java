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
 * 政治面貌
 */
public class PoliticsstatusActivity extends AppCompatActivity {
    private static final String TAG = "ResidenceActivity";

    private AppAction mAction;

    private VolunteerViewDto personal_data;
    private TextView submit;
    private EditText edit_myPolity;

    private String myPolity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicsstatus);

        mAction = new AppActionImpl(this);

        Intent intent = getIntent();
        personal_data = (VolunteerViewDto) intent.getSerializableExtra("personal_data");
        submit = (TextView) findViewById(R.id.tv_myPolity_submit);
        edit_myPolity = (EditText) findViewById(R.id.edit_myPolity);

        if (personal_data.getPolity() != null) {
            myPolity = personal_data.getPolity();
            edit_myPolity.setText(myPolity);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPolity=edit_myPolity.getText().toString();
                List<VolunteerViewDto> vl_updates = new ArrayList<>();
                personal_data.setPolity(myPolity);
                vl_updates.add(personal_data);
                mAction.volunteerUpdate(vl_updates, new ActionCallbackListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Intent result = new Intent();
                        result.putExtra("personal_data", personal_data);
                        setResult(RESULT_OK, result);
                        showToast("修改成功");
                        PoliticsstatusActivity.this.finish();
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
                PoliticsstatusActivity.this.finish();
            }
        });

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
