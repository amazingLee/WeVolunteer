package com.example.renhao.wevolunteer;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.MrL.qrcodescan.MipcaActivityCapture;
import com.example.core.local.LocalDate;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.event.FragmentResultEvent;
import com.example.renhao.wevolunteer.fragment.FindPageFragment;
import com.example.renhao.wevolunteer.fragment.HomePageFragment;
import com.example.renhao.wevolunteer.fragment.MySelfFragment;
import com.example.renhao.wevolunteer.fragment.SigninPageFragment;
import com.example.renhao.wevolunteer.view.ChangeColorIconWithTextView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
public class IndexActivity extends BaseActivity {
    private static final String TAG = "IndexActivity";

    public static final int HOME = 0;
    public static final int FIND = 1;
    public static final int SIGNIN = 2;
    public static final int MYSELF = 3;

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
    private FindPageFragment mFindPageFragment;
    private SigninPageFragment mSigninPageFragment;
    private MySelfFragment mMySelfFragment;

    private int fragmentPosition = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        initActionBar();
        setFragment(HOME);

        //判断用户是否登录
        boolean isLogin = LocalDate.getInstance(this).getLocalDate("isLogin", true);
        if (isLogin) {
            Logger.v(TAG, "user is login");
            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
            String strExpires = LocalDate.getInstance(this).getLocalDate("expires", "");
            Logger.v(TAG, strExpires);
            if (!strExpires.equals("")) {
                try {
                    Date expires = format.parse(strExpires);
                    //判断票据是否过期
                    if (new Date().getTime() >= expires.getTime()) {
                        Logger.v(TAG, "accesstoken is overdue");
                        getAccessToken();
                    } else {
                        //用户已登录，票据尚未过期--刷新票据
                        Logger.v(TAG, "accesstoken is not overdue");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                //第一次登录
                Logger.v(TAG, "第一次登录  " + strExpires);
                getAccessToken();
            }
        } else {
            getAccessToken();
        }
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
                Intent intent = new Intent(IndexActivity.this, MipcaActivityCapture.class);
                startActivityForResult(intent, 0);
            }
        });
        magnifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        if (fragmentPosition == position)
            return;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        mChangrTvIndexHomepage.setIconColor(getResources().getColor(R.color.colorGray));
        mChangrTvIndexFind.setIconColor(getResources().getColor(R.color.colorGray));
        mChangrTvIndexSignin.setIconColor(getResources().getColor(R.color.colorGray));
        mChangrTvIndexMyself.setIconColor(getResources().getColor(R.color.colorGray));
        switch (position) {
            case HOME:
                mChangrTvIndexHomepage.setIconColor(getResources().getColor(R.color.colorCyan));
                if (mHomePageFragment == null) {
                    mHomePageFragment = new HomePageFragment();
                }
                setFractionTranslate(transaction, HOME);
                transaction.replace(R.id.framelayout_index_content, mHomePageFragment);
                fragmentPosition = HOME;
                break;
            case FIND:
                mChangrTvIndexFind.setIconColor(getResources().getColor(R.color.colorCyan));
                if (mFindPageFragment == null) {
                    mFindPageFragment = new FindPageFragment();
                }
                setFractionTranslate(transaction, FIND);
                transaction.replace(R.id.framelayout_index_content, mFindPageFragment);
                fragmentPosition = FIND;
                break;
            case SIGNIN:
                mChangrTvIndexSignin.setIconColor(getResources().getColor(R.color.colorCyan));
                if (mSigninPageFragment == null) {
                    mSigninPageFragment = new SigninPageFragment();
                }
                setFractionTranslate(transaction, SIGNIN);
                transaction.replace(R.id.framelayout_index_content, mSigninPageFragment);
                fragmentPosition = SIGNIN;
                break;
            case MYSELF:
                mChangrTvIndexMyself.setIconColor(getResources().getColor(R.color.colorCyan));
                if (mMySelfFragment == null) {
                    mMySelfFragment = new MySelfFragment();
                }
                setFractionTranslate(transaction, MYSELF);
                transaction.replace(R.id.framelayout_index_content, mMySelfFragment);
                fragmentPosition = MYSELF;
                break;

        }
        transaction.commit();

    }

    /**
     * 判断两种活动切换的效果
     *
     * @param transaction
     * @param type
     */
    private void setFractionTranslate(FragmentTransaction transaction, int type) {
        if (fragmentPosition > type) {
            transaction.setCustomAnimations(
                    R.animator.fragment_slide_left_in, R.animator.fragment_slide_right_out,
                    R.animator.fragment_slide_right_in, R.animator.fragment_slide_left_out);
        } else {
            transaction.setCustomAnimations(
                    R.animator.fragment_slide_right_in, R.animator.fragment_slide_left_out,
                    R.animator.fragment_slide_left_in, R.animator.fragment_slide_right_out);
        }

    }

    @OnClick({R.id.changrTv_index_homepage, R.id.changrTv_index_find, R.id.changrTv_index_signin, R.id.changrTv_index_myself})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changrTv_index_homepage:
                setFragment(HOME);
                break;
            case R.id.changrTv_index_find:
                setFragment(FIND);
                break;
            case R.id.changrTv_index_signin:
                setFragment(SIGNIN);
                break;
            case R.id.changrTv_index_myself:
                setFragment(MYSELF);
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(FragmentResultEvent resultEvent) {
        switch (resultEvent.getResultCode()) {
            case HOME:
                setFragment(HOME);
                break;
            case FIND:
                setFragment(FIND);
                break;
            case SIGNIN:
                setFragment(SIGNIN);
                break;
            case MYSELF:
                setFragment(MYSELF);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String qrcodeMsg = data.getExtras().getString("result");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(qrcodeMsg);
        builder.setTitle("二维码内容");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }
}
