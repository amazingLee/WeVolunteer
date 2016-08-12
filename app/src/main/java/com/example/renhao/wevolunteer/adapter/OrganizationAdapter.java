package com.example.renhao.wevolunteer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.items.OrganizationListItem;
import com.example.renhao.wevolunteer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/9 13:06
 * 修改备注：
 */
public class OrganizationAdapter extends BaseAdapter {
    private static final String TAG = "OrganizationAdapter";

    private Context mContext;
    private ArrayList<OrganizationListItem> mDate;

    public OrganizationAdapter(Context mContext, ArrayList<OrganizationListItem> mDate) {
        this.mContext = mContext;
        this.mDate = mDate;
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
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_organization, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        OrganizationListItem item = mDate.get(position);

        Picasso.with(mContext).load(item.getIconUrl())
                .into(viewHolder.mIcon);//加载图片

        viewHolder.mName.setText(item.getName());
        viewHolder.mAddress.setText(item.getAddress());

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.imageview_organizationitem_icon)
        ImageView mIcon;
        @Bind(R.id.tv_organizationitem_name)
        TextView mName;
        @Bind(R.id.tv_organizationitem_address)
        TextView mAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}