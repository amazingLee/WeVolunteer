package com.example.renhao.wevolunteer.activity;
/*
 *
 * Created by Ge on 2016/8/15  9:41.
 * 我的项目和我的关注共用此activity
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.adapter.MyProjectAdapter;

public class MyProjectActivity extends AppCompatActivity {

    private ListView lv_myProject;
    private TextView tv_title;
    private MyProjectAdapter myProjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myproject);

        //设置我的项目的适配器
        lv_myProject = (ListView) findViewById(R.id.lv_myproject);
        myProjectAdapter = new MyProjectAdapter(this);
        lv_myProject.setAdapter(myProjectAdapter);

        //设置标题
        tv_title = (TextView) findViewById(R.id.tv_myProject_title);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tv_title.setText(title);


        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyProjectActivity.this.finish();
            }
        });
    }
}
