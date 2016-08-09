package com.example.renhao.wevolunteer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renhao.wevolunteer.R;

import java.util.Arrays;
import java.util.List;

/**
 * 意向志愿服务类别
 */
public class ServiceCategoryActivity extends AppCompatActivity {
    private static final String TAG = "ServiceCategoryActivity";

    private List<String> actions;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_category);
        listView = (ListView) findViewById(R.id.listView_service_category);
        String[] s = new String[]{
                "赛会服务",
                "助老助残",
                "文体活动",
                "儿童关怀",
                "科普宣传",
                "动物保护",
                "生态环保",
                "医疗保卫",
                "社区服务",
                "文化教育",
                "扶贫帮困",
                "安全生产",
                "支教助学",
                "邻里守望",
                "应急救援",
        };
        actions = Arrays.asList(s);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_multiple_choice,android.R.id.text1,actions);
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);//这里我们直接在源代码中设置选择模式
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
