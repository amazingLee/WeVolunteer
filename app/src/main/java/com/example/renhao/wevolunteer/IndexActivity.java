package com.example.renhao.wevolunteer;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.renhao.wevolunteer.fragment.HomePageFragment;
import com.example.renhao.wevolunteer.view.ChangeColorIconWithTextView;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/5 11:19
 * 修改备注：
 */
public class IndexActivity extends AppCompatActivity {
    private static final String TAG = "IndexActivity";
    @Bind(R.id.changrTv_index_homepage)
    ChangeColorIconWithTextView mChangrTvIndexHomepage;
    @Bind(R.id.changrTv_index_find)
    ChangeColorIconWithTextView mChangrTvIndexFind;
    @Bind(R.id.changrTv_index_signin)
    ChangeColorIconWithTextView mChangrTvIndexSignin;
    @Bind(R.id.changrTv_index_myself)
    ChangeColorIconWithTextView mChangrTvIndexMyself;
    @Bind(R.id.linaerlayout_index_bottombar)
    LinearLayout mLinaerlayoutIndexBottombar;
    @Bind(R.id.framelayout_index_content)
    FrameLayout mFramelayoutIndexContent;

    private View mCustomView;//actionbar的自定义视图
    private HomePageFragment mHomePageFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);

        initActionBar();
        setFragment(0);
    }

    /**
     * 初始化actionbar 并绑定监听
     */
    private void initActionBar() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        mCustomView = mInflater.inflate(R.layout.actionbar_homepage, null);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0, 0);

        LinearLayout scan = (LinearLayout) mCustomView.findViewById(R.id.linearlayout_homepage_scan);
        ImageView magnifier = (ImageView) mCustomView.findViewById(R.id.imageview_homepage_magnifier);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.v(TAG, "scan");
            }
        });
        magnifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.v(TAG, "magnifier");
            }
        });
    }

    /**
     * 设置fragment
     * 0主页，1发现，2签到，3我的
     *
     * @param position
     */
    public void setFragment(int position) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mChangrTvIndexHomepage.setIconColor(getResources().getColor(R.color.colorGray));
        mChangrTvIndexFind.setIconColor(getResources().getColor(R.color.colorGray));
        mChangrTvIndexSignin.setIconColor(getResources().getColor(R.color.colorGray));
        mChangrTvIndexMyself.setIconColor(getResources().getColor(R.color.colorGray));
        switch (position) {
            case 0:
                mChangrTvIndexHomepage.setIconColor(getResources().getColor(R.color.colorCyan));
                if (mHomePageFragment == null) {
                    mHomePageFragment = new HomePageFragment();
                }
                transaction.replace(R.id.framelayout_index_content, mHomePageFragment);
                break;
        }
        transaction.commit();
    }

    @OnClick({R.id.changrTv_index_homepage, R.id.changrTv_index_find, R.id.changrTv_index_signin, R.id.changrTv_index_myself})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changrTv_index_homepage:
                break;
            case R.id.changrTv_index_find:
                break;
            case R.id.changrTv_index_signin:
                break;
            case R.id.changrTv_index_myself:
                break;
        }
    }
}
