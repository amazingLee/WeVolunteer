package com.example.renhao.wevolunteer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.items.HomePageListItem;
import com.example.renhao.wevolunteer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/5 22:57
 * 修改备注：
 */
public class HomePageAdapter extends BaseAdapter {
    private static final String TAG = "HomePageAdapter";

    private Context mContext;
    private List<HomePageListItem> mDate;
    private LayoutInflater layoutInflater;

    public HomePageAdapter(Context context, List<HomePageListItem> mDate) {
        mContext = context;
        this.mDate = mDate;
        this.layoutInflater = layoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDate.size();
    }

    @Override
    public Object getItem(int position) {
        return mDate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listitem_homepage, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview_homepage_itemImage);
            viewHolder.typeTv = (TextView) convertView.findViewById(R.id.textview_listitem_type);
            viewHolder.titleTv = (TextView) convertView.findViewById(R.id.textview_listitem_tilte);
            viewHolder.numNameTv = (TextView) convertView.findViewById(R.id.tv_listitem_numName);
            viewHolder.numTv = (TextView) convertView.findViewById(R.id.tv_listitem_num);
            viewHolder.timeNameTv = (TextView) convertView.findViewById(R.id.tv_listitem_timeName);
            viewHolder.timeTv = (TextView) convertView.findViewById(R.id.tv_listitem_time);
            viewHolder.stateTv = (TextView) convertView.findViewById(R.id.tv_listitem_state);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HomePageListItem item = mDate.get(position);

        if (!item.getImg().equals("")) {
            Picasso.with(mContext)
                    .load(item.getImg())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.imageView);//加载图片
        }


        viewHolder.titleTv.setText(item.getTitle());

        viewHolder.numTv.setText(item.getNum() + "/" + item.getMaxNum() + " 人");
        float h = Float.parseFloat(item.getTime()) / 60;
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
        viewHolder.timeTv.setText(df.format(h) + "小时");

        if (item.getType() == 0)//活动
        {
            viewHolder.typeTv.setText("活动");
            viewHolder.numNameTv.setText("活动招募人数");
            viewHolder.timeNameTv.setText("活动服务时长");
        } else {
            viewHolder.typeTv.setText("岗位");
            viewHolder.numNameTv.setText("岗位招募人次");
            viewHolder.timeNameTv.setText("单次服务时长");
        }

        if (item.getState() == 0) {
            viewHolder.stateTv.setText("招募中");
        } else {
            viewHolder.stateTv.setText("已结束");
        }

        return convertView;
    }


    static class ViewHolder {
        ImageView imageView;
        TextView typeTv, titleTv, numNameTv, numTv, timeNameTv, timeTv, stateTv;
    }
}
