package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.dictionary.DictionaryListDto;
import com.example.renhao.wevolunteer.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class PersonalAttributeActivity extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    TextView textView;
    LinearLayout ll_back;
    private List<String> person_type;
    private List<String> personCode;
    private String type_text;
    private String typeCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_attribute);
        initView();
        initmListener();
        getCardType();
    }
    private void initView() {
        listView = (ListView) findViewById(R.id.listView_perosn_type);
        textView = (TextView) findViewById(R.id.tv_submit);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
    }

    private void initmListener() {
        textView.setOnClickListener(this);
        ll_back.setOnClickListener(this);
    }

    //证件类型
    private void getCardType() {
        AppActionImpl.getInstance(this).dictionaryQueryDefault("PersonAttribute", "",
                new ActionCallbackListener<List<DictionaryListDto>>() {
                    @Override
                    public void onSuccess(List<DictionaryListDto> data) {
                        personCode = new ArrayList<String>();
                        person_type = new ArrayList<String>();
                        for (int i = 0; i < data.size(); i++) {
                            person_type.add(data.get(i).getName());
                            personCode.add(data.get(i).getCode());
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                PersonalAttributeActivity.this, android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, person_type);
                        listView.setAdapter(arrayAdapter);
                        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//这里我们直接在源代码中设置选择模式
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                type_text = ((TextView) view).getText().toString();
                                typeCode = personCode.get(position);
                                Logger.v("--", typeCode);
                            }
                        });

                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:
                String text = type_text;
                if (!TextUtils.isEmpty(text)) {
                    Intent intent = new Intent();
                    intent.setClass(PersonalAttributeActivity.this, RegisterActivity.class);
                    intent.putExtra("personCode", typeCode);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(PersonalAttributeActivity.this, "请选择个人属性", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_back:
                finish();
                break;
            default:
                break;
        }
    }

}
