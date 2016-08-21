package com.example.renhao.wevolunteer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.renhao.wevolunteer.R;

/*问题反馈界面*/

public class ReportProblemActivity extends AppCompatActivity {
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
