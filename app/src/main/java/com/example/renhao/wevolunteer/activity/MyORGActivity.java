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
 * 所属机构界面
 */
public class MyORGActivity extends AppCompatActivity {
    private static final String TAG = "MyORGActivity";

    private List<String> actions;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myORG);
        listView = (ListView) findViewById(R.id.listView_myORG);
        String[] s = new String[]{
                "海曙区志愿协会",
                "江东区志愿协会",
                "江北区志愿协会",
                "鄞州区志愿协会",
                "镇海区志愿协会",
                "北仑区志愿协会",
                "慈溪市志愿协会",
                "余姚市志愿协会",
                "奉化市志愿协会",
                "宁海县志愿协会",
                "象山县志愿协会",
        };
        actions = Arrays.asList(s);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,R.layout.myORG_item,R.id.tv_Institution,actions);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
