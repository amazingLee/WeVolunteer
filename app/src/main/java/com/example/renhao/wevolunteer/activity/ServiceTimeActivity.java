package com.example.renhao.wevolunteer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renhao.wevolunteer.R;

import java.util.Arrays;
import java.util.List;

/**
 * 服务时间界面
 * 备注：感觉这里有待完善，选择时间上有点问题
 */
public class ServiceTimeActivity extends AppCompatActivity {
    private static final String TAG = "ServiceTimeActivity";
    private List<String> actions;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_time);
        listView = (ListView) findViewById(R.id.listView_service_time);

        //回退按钮
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceTimeActivity.this.finish();
            }
        });

        String[] s = new String[]{
                "周一",
                "周二",
                "周三",
                "周四",
                "周五",
                "周六",
                "周日"
        };
        actions = Arrays.asList(s);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, R.layout.service_time_item, R.id.tv_week, actions);
        listView.setAdapter(arrayAdapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
