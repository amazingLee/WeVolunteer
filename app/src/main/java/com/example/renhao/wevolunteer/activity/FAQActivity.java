package com.example.renhao.wevolunteer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;

import java.util.Arrays;
import java.util.List;

/**
 * 常见问题界面
 */
public class FAQActivity extends BaseActivity {
    private static final String TAG = "FAQActivity";

    private List<String> actions;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        listView = (ListView) findViewById(R.id.listView_FAQ);

        //回退按钮
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQActivity.this.finish();
            }
        });

        String[] s = new String[]{
                "如何注册志愿者账号？",
                "如何注册专业志愿者？",
                "志愿者流程",
                "如何购买下单？",
                "如何查找想要的商品？",
                "可以拨打客服热线订购商品吗？",
                "如何填写收货地址？",
                "如何查看购物车中的商品？",
                "浏览器COOKIE功能如何开启？",
                "购物车常见问题和购物技巧",
                "谨防假借京东名义的诈骗",

        };
        actions = Arrays.asList(s);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, R.layout.faq_item, R.id.common_problem_item, actions);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
}
