package com.example.renhao.wevolunteer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renhao.wevolunteer.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MyProjectAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<HashMap<String, Object>> listItem;

    public MyProjectAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);

    }

    //适配器与数据数组绑定
    private ArrayList<HashMap<String, Object>> getDate() {
        listItem = new ArrayList<HashMap<String, Object>>();
        {
            for (int i = 0; i < 4; i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                String get_date = "项目测试";
                map.put("ItemDate", get_date);
                listItem.add(map);
            }
            return listItem;
        }
    }

    @Override
    public int getCount() {
        return getDate().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public final class ViewHolder {
        public TextView name;
        public TextView TIME;
        public TextView STATE;
        public ImageView PIC;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Log.v("MyListViewBase", "getView " + position + " " + convertView);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_myproject, null);//根据布局产生一个view
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.tv_myProject_projectName);
            holder.STATE = (TextView) convertView.findViewById(R.id.tv_myProject_state);
            holder.PIC = (ImageView) convertView.findViewById(R.id.iv_myProject_item);
            holder.TIME = (TextView) convertView.findViewById(R.id.tv_myProject_projectTime);
            convertView.setTag(holder);//绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
//        holder.name.setText("项目测试测试");
//        holder.STATE.setText("已结束");
//        holder.TIME.setText("2");

        return convertView;
    }


}
