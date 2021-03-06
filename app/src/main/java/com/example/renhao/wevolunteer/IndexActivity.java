package com.example.renhao.wevolunteer;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
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
import com.example.renhao.wevolunteer.event.QRCodeResultEvent;
import com.example.renhao.wevolunteer.fragment.FindPageFragment;
import com.example.renhao.wevolunteer.fragment.HomePageFragment;
import com.example.renhao.wevolunteer.fragment.PersonalFragment;
import com.example.renhao.wevolunteer.fragment.SigninPageFragment;
import com.example.renhao.wevolunteer.view.ChangeColorIconWithTextView;
import com.example.renhao.wevolunteer.view.PopupMenu;
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
import cn.sharesdk.framework.ShareSDK;

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
    private PersonalFragment mPersonalFragment;

    private PopupMenu mPopupMenu;
    private ImageView magnifier;

    private int fragmentPosition = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //初始化shareSDK
        ShareSDK.initSDK(this);
        //在使用百度或高德地图时，防止切换Fragment出现闪屏黑屏情况。
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        initActionBar();
        setFragment(HOME);

        //判断用户是否登录
        boolean isLogin = LocalDate.getInstance(this).getLocalDate("isLogin", false);
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
        String[] tabs = {"全部", "岗位", "活动", "志愿者指导中心", "志愿者服务站"};
        mPopupMenu = new PopupMenu(this, tabs);
        mPopupMenu.setWidth(360);

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        mCustomView = mInflater.inflate(R.layout.actionbar_homepage, null);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0, 0);

        LinearLayout scan = (LinearLayout) mCustomView.findViewById(R.id.linearlayout_homepage_scan);
        magnifier = (ImageView) mCustomView.findViewById(R.id.imageview_homepage_magnifier);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(SIGNIN);
                Intent intent = new Intent(IndexActivity.this, MipcaActivityCapture.class);
                startActivityForResult(intent, 1);
            }
        });
        magnifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fragmentPosition == FIND) {
                    mPopupMenu.showLocation(R.id.imageview_homepage_magnifier);
                    mPopupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                        @Override
                        public void onClick(PopupMenu.MENUITEM item, String str) {
                            switch (str) {
                                case "全部":
                                    if (mFindPageFragment != null)
                                        mFindPageFragment.setShowMap(FindPageFragment.MAP_ALL);
                                    break;
                                case "岗位":
                                    if (mFindPageFragment != null)
                                        mFindPageFragment.setShowMap(FindPageFragment.MAP_JOB);
                                    break;
                                case "活动":
                                    if (mFindPageFragment != null)
                                        mFindPageFragment.setShowMap(FindPageFragment.MAP_ACTIVITY);
                                    break;
                                case "志愿者指导中心":
                                    if (mFindPageFragment != null)
                                        mFindPageFragment.setShowMap(FindPageFragment.MAP_CENTER);
                                    break;
                                case "志愿者服务站":
                                    if (mFindPageFragment != null)
                                        mFindPageFragment.setShowMap(FindPageFragment.MAP_STATION);
                                    break;
                            }
                        }
                    });
                } else {
                    Intent intent = new Intent(IndexActivity.this, SearchActivity.class);
                    startActivity(intent);
                }
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
                magnifier.setImageResource(R.drawable.magnifier);
                mChangrTvIndexHomepage.setIconColor(getResources().getColor(R.color.colorCyan));
                if (mHomePageFragment == null) {
                    mHomePageFragment = new HomePageFragment();
                }
                setFractionTranslate(transaction, HOME);
                transaction.replace(R.id.framelayout_index_content, mHomePageFragment);
                fragmentPosition = HOME;
                break;
            case FIND:
                magnifier.setImageResource(R.drawable.icon_menu);
                mChangrTvIndexFind.setIconColor(getResources().getColor(R.color.colorCyan));
                if (fragmentPosition != SIGNIN) {

                    if (mFindPageFragment == null) {
                        mFindPageFragment = new FindPageFragment();
                       /* Bundle data = new Bundle();
                        data.putBoolean("Tag", false);
                        mFindPageFragment.setArguments(data);*/
                        mFindPageFragment.setTag(false);
                    } else {
                        //发送消息，隐藏签到按钮控件
                       /* EventBus.getDefault().post(new FlagEvent("find"));*/
                        mFindPageFragment.setType(false);
                    }
                    setFractionTranslate(transaction, FIND);
                    transaction.replace(R.id.framelayout_index_content, mFindPageFragment);
                } else {
                    //发送消息，隐藏签到按钮控件
                    /*EventBus.getDefault().post(new FlagEvent("find"));*/
                    mFindPageFragment.setType(false);
                }

                fragmentPosition = FIND;
                break;
            case SIGNIN:
                magnifier.setImageResource(R.drawable.magnifier);
                mChangrTvIndexSignin.setIconColor(getResources().getColor(R.color.colorCyan));
                if (fragmentPosition != FIND) {

                    if (mFindPageFragment == null) {
                        mFindPageFragment = new FindPageFragment();
                       /* Bundle data = new Bundle();
                        data.putBoolean("Tag", true);
                        mFindPageFragment.setArguments(data);*/
                        mFindPageFragment.setTag(true);
                    } else {
                        /*EventBus.getDefault().post(new FlagEvent("sign_in"));*/
                        mFindPageFragment.setType(true);
                    }
                    setFractionTranslate(transaction, FIND);
                    transaction.replace(R.id.framelayout_index_content, mFindPageFragment);
                } else {
                    //发送消息，显示签到按钮控件
                   /* EventBus.getDefault().post(new FlagEvent("sign_in"));*/
                    mFindPageFragment.setType(true);
                }
                fragmentPosition = SIGNIN;
                break;
            case MYSELF:
                magnifier.setImageResource(R.drawable.magnifier);
                mChangrTvIndexMyself.setIconColor(getResources().getColor(R.color.colorCyan));
                if (mPersonalFragment == null) {
                    mPersonalFragment = new PersonalFragment();
                }
                setFractionTranslate(transaction, MYSELF);
                transaction.replace(R.id.framelayout_index_content, mPersonalFragment);
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
        ShareSDK.stopSDK(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String qrcodeMsg = data.getExtras().getString("result");
        Logger.v("QRCode", "qrcode result in indexActivity  " + qrcodeMsg);
        if (qrcodeMsg == null)
            return;
        if (qrcodeMsg.equals("0"))
            return;
        Logger.v(TAG, requestCode);
        if (requestCode == 0) {
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
        } else if (requestCode == 1) {
            //将获取到的二维码的值传到FindPageFragment
            EventBus.getDefault().post(new QRCodeResultEvent(qrcodeMsg, 0));
        }
    }
}
