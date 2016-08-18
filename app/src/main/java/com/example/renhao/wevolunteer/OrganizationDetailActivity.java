package com.example.renhao.wevolunteer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.company.CompanyViewDto;
import com.example.renhao.wevolunteer.adapter.OrganizationDetailAdapter;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.event.OrganizationDetailEvent;
import com.example.renhao.wevolunteer.view.SlidingTabLayout;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/12 9:18
 * 修改备注：
 */
public class OrganizationDetailActivity extends BaseActivity {
    private static final String TAG = "OrganizationDetailActivity";
    @Bind(R.id.imageview_organization_icon)
    ImageView mOrganizationIcon;
    @Bind(R.id.slidtab_organization_tabs)
    SlidingTabLayout mSlidtabTabs;
    @Bind(R.id.viewpage_organization_pager)
    ViewPager mViewpage;

    private View mCustomView;//actionbar的自定义视图
    private ImageView indicatorImg;
    private TextView titleTv;
    private ImageView magnifierImg;

    private String companyId;

    private CharSequence titles[] = {"组织介绍", "已发布 岗位/活动"};
    private OrganizationDetailAdapter adapter;
    private int numberOfTabs = 2;
    private ViewPager pager;
    private SlidingTabLayout tabs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizationdetail);
        ButterKnife.bind(this);

        initActionBar();

        companyId = getIntent().getStringExtra("id");
        initView();
        initOrganizationDetail();

        Picasso.with(this)
                .load(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(mOrganizationIcon);

    }

    private void initView() {
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new OrganizationDetailAdapter(getSupportFragmentManager(), titles, numberOfTabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.viewpage_organization_pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.slidtab_organization_tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        /*tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorBlack);
            }
        });*/

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    private void initOrganizationDetail() {
        AppActionImpl.getInstance(this).companyDetail(companyId,
                new ActionCallbackListener<CompanyViewDto>() {
                    @Override
                    public void onSuccess(CompanyViewDto data) {
                        //获得组织图片并显示
                        if (TextUtils.isEmpty(data.getListurl())) {
                            Picasso.with(getApplicationContext())
                                    .load(data.getListurl())
                                    .error(R.mipmap.ic_launcher)
                                    .into(mOrganizationIcon);
                        } else {
                            Picasso.with(getApplicationContext())
                                    .load(R.mipmap.ic_launcher)
                                    .error(R.mipmap.ic_launcher)
                                    .into(mOrganizationIcon);
                        }

                        //组织介绍
                        //已发布岗位/活动
                        EventBus.getDefault().post(new OrganizationDetailEvent(data));
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("数据加载失败");
                    }
                });
    }

    private void initActionBar() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        mCustomView = mInflater.inflate(R.layout.actionbar_normal, null);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0, 0);
        titleTv = (TextView) mCustomView.findViewById(R.id.tv_normal_projectswitch);
        titleTv.setText("组织");
        indicatorImg = (ImageView) mCustomView.findViewById(R.id.imageview_normal_indicator);
        indicatorImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        magnifierImg = (ImageView) mCustomView.findViewById(R.id.imageview_normal_magnifier);
        magnifierImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.v(TAG, "magnifierImg");
            }
        });
    }

    @OnClick(R.id.imageview_organization_icon)
    public void onClick() {
    }
}
