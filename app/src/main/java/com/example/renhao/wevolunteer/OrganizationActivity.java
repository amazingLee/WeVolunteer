package com.example.renhao.wevolunteer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.model.items.OrganizationListItem;
import com.example.renhao.wevolunteer.adapter.ListDropDownAdapter;
import com.example.renhao.wevolunteer.adapter.OrganizationAdapter;
import com.example.renhao.wevolunteer.view.ChangeColorIconWithTextView;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.orhanobut.logger.Logger;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/9 11:29
 * 修改备注：
 */
public class OrganizationActivity extends AppCompatActivity {
    private static final String TAG = "Organization";
    @Bind(R.id.changrTv_view_homepage)
    ChangeColorIconWithTextView mHomepage;
    @Bind(R.id.changrTv_view_find)
    ChangeColorIconWithTextView mFind;
    @Bind(R.id.changrTv_view_signin)
    ChangeColorIconWithTextView mSignin;
    @Bind(R.id.changrTv_view_myself)
    ChangeColorIconWithTextView mMyself;
    @Bind(R.id.dropDownMenu_organization_menu)
    DropDownMenu mDropDownMenu;

    PullToRefreshListView contentView;
    private View mCustomView;//actionbar的自定义视图
    ImageView indicatorImg;
    TextView titleTv;
    ImageView magnifierImg;

    private ListDropDownAdapter typeAdapter;
    private ListDropDownAdapter areaAdapter;
    private OrganizationAdapter adapter;

    private List<View> popupViews = new ArrayList<>();

    private String[] headers = {"类型", "区域"};
    private String[] type = {"类型", "类型1", "类型2", "类型3"};
    private String[] area = {"区域", "全市", "海曙区", "江东区"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);
        ButterKnife.bind(this);
       /* mHomepage = (ChangeColorIconWithTextView) findViewById(R.id.changrTv_view_homepage);
        mFind = (ChangeColorIconWithTextView) findViewById(R.id.changrTv_view_find);
        mSignin = (ChangeColorIconWithTextView) findViewById(R.id.changrTv_view_signin);
        mMyself = (ChangeColorIconWithTextView) findViewById(R.id.changrTv_view_myself);
        mDropDownMenu= (DropDownMenu) findViewById(R.id.dropDownMenu_organization_menu);*/
        initActionBar();

        initDropDownMenu();

        View v = LayoutInflater.from(this).inflate(R.layout.view_prtlistview, null);
        contentView = ButterKnife.findById(v, R.id.ptrListview_view_list);
        initPtrListView(contentView);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);

        mHomepage.setIconColor(getResources().getColor(R.color.colorCyan));
    }

    @Override
    protected void onResume() {

        super.onResume();
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

    private void initPtrListView(final PullToRefreshListView mPtr) {
        mPtr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new FinishRefresh().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new FinishRefresh().execute();
            }
        });
        mPtr.setMode(PullToRefreshBase.Mode.BOTH);//设置头部下拉刷新
        //设置刷新时显示的文本
        ILoadingLayout startLayout = mPtr.getLoadingLayoutProxy(true, false);//开始头部的layout
        startLayout.setPullLabel("正在下拉刷新....");
        startLayout.setRefreshingLabel("正在玩命加载....");
        startLayout.setReleaseLabel("放开刷新");

        ILoadingLayout endLayout = mPtr.getLoadingLayoutProxy(false, true);//开始底部的layout
        endLayout.setPullLabel("正在下拉刷新....");
        endLayout.setRefreshingLabel("正在玩命加载....");
        endLayout.setReleaseLabel("放开刷新");

        ArrayList<OrganizationListItem> list = new ArrayList<>();
        list.add(new OrganizationListItem(
                "http://img1.imgtn.bdimg.com/it/u=2098084338,2714656019&fm=21&gp=0.jpg"
                , "共青团宁波市江北区委员会", "地点：宁波市象山县观海南路28号"));
        list.add(new OrganizationListItem(
                "http://img2.imgtn.bdimg.com/it/u=3602495621,3151039405&fm=21&gp=0.jpg"
                , "共青团宁波市江北区委员会", "地点：宁波市象山县观海南路28号"));
        list.add(new OrganizationListItem(
                "http://img2.imgtn.bdimg.com/it/u=2469638367,241984841&fm=21&gp=0.jpg"
                , "共青团宁波市江北区委员会", "地点：宁波市象山县观海南路28号"));
        list.add(new OrganizationListItem(
                "http://img1.imgtn.bdimg.com/it/u=2132856998,369268727&fm=21&gp=0.jpg"
                , "共青团宁波市江北区委员会", "地点：宁波市象山县观海南路28号"));
        list.add(new OrganizationListItem(
                "http://img0.imgtn.bdimg.com/it/u=2838777204,1513243120&fm=21&gp=0.jpg"
                , "共青团宁波市江北区委员会", "地点：宁波市象山县观海南路28号"));
        list.add(new OrganizationListItem(
                "http://img1.imgtn.bdimg.com/it/u=2666315763,3359766164&fm=21&gp=0.jpg"
                , "共青团宁波市江北区委员会", "地点：宁波市象山县观海南路28号"));
        list.add(new OrganizationListItem(
                "http://img1.imgtn.bdimg.com/it/u=37827399,354780988&fm=21&gp=0.jpg"
                , "共青团宁波市江北区委员会", "地点：宁波市象山县观海南路28号"));
        list.add(new OrganizationListItem(
                "http://img5.imgtn.bdimg.com/it/u=1130424812,3907942231&fm=21&gp=0.jpg"
                , "共青团宁波市江北区委员会", "地点：宁波市象山县观海南路28号"));
        list.add(new OrganizationListItem(
                "http://img5.imgtn.bdimg.com/it/u=2152352573,3450421690&fm=21&gp=0.jpg"
                , "共青团宁波市江北区委员会", "地点：宁波市象山县观海南路28号"));
        list.add(new OrganizationListItem(
                "http://img5.imgtn.bdimg.com/it/u=3896921233,133782688&fm=21&gp=0.jpg"
                , "共青团宁波市江北区委员会", "地点：宁波市象山县观海南路28号"));
        adapter = new OrganizationAdapter(this, list);
        mPtr.setAdapter(adapter);
    }

    private void initDropDownMenu() {
        //类型
        final ListView typeView = new ListView(this);
        typeAdapter = new ListDropDownAdapter(this, Arrays.asList(type));
        typeView.setDividerHeight(0);
        typeView.setAdapter(typeAdapter);
        typeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : type[position]);
                mDropDownMenu.closeMenu();
            }
        });
        //区域
        final ListView areaView = new ListView(this);
        areaAdapter = new ListDropDownAdapter(this, Arrays.asList(area));
        areaView.setDividerHeight(0);
        areaView.setAdapter(areaAdapter);
        areaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                areaAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : area[position]);
                mDropDownMenu.closeMenu();
            }
        });

        //init popupViews
        popupViews.add(typeView);
        popupViews.add(areaView);
    }

    @OnClick({R.id.changrTv_view_homepage,
            R.id.changrTv_view_find,
            R.id.changrTv_view_signin,
            R.id.changrTv_view_myself})
    public void onClick(View view) {
        Logger.v(TAG, "onclick");
        switch (view.getId()) {
            case R.id.changrTv_view_homepage:
                setResult(0);
                finish();
                break;
            case R.id.changrTv_view_find:
                setResult(1);
                finish();
                break;
            case R.id.changrTv_view_signin:
                setResult(2);
                finish();
                break;
            case R.id.changrTv_view_myself:
                setResult(3);
                finish();
                break;
        }
    }

    //测试用方法
    private class FinishRefresh extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//          adapter.notifyDataSetChanged();
            contentView.onRefreshComplete();
        }
    }
}
