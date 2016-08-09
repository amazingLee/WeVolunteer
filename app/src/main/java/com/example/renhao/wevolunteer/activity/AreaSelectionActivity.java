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
 * 区域选择界面
 */
public class AreaSelectionActivity extends AppCompatActivity {
    private static final String TAG = "AreaSelectionActivity";

    private List<String> actions;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_selection);
        listView = (ListView) findViewById(R.id.listView_area_selection);
        String[] s = new String[]{
                "海曙区",
                "江东区",
                "江北区",
                "鄞州区",
                "镇海区",
                "北仑区",
                "慈溪市",
                "余姚市",
                "奉化市",
                "宁海县",
                "象山县",
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
