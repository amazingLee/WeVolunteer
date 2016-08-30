package com.example.renhao.wevolunteer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;

/*问题反馈界面*/

public class ReportProblemActivity extends BaseActivity {
    private static final String TAG = "ReportProblemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_problem);

        //回退按钮
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportProblemActivity.this.finish();
            }
        });

    }
}
