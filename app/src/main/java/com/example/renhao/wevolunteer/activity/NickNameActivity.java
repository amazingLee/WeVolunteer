package com.example.renhao.wevolunteer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.renhao.wevolunteer.R;

/**
 * 昵称界面
 */
public class NickNameActivity extends AppCompatActivity {
    private static final String TAG = "NickNameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_name);

        //回退按钮
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NickNameActivity.this.finish();
            }
        });

    }
}
