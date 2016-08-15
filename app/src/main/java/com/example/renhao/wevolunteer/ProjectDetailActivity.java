package com.example.renhao.wevolunteer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.core.AppAction;
import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.Company.CompanyListDto;
import com.example.model.Company.CompanyQueryOptionDto;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityListDto;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/9 16:09
 * 修改备注：
 */
public class ProjectDetailActivity extends AppCompatActivity {
    private static final String TAG = "ProjectDetailActivity";
    @Bind(R.id.btn_projectdetail_share)
    Button mBtnShare;
    @Bind(R.id.btn_projectdetail_apply)
    Button mBtnApply;
    @Bind(R.id.btn_projectdetail_focuson)
    Button mBtnFocuson;
    @Bind(R.id.bottombar_projectdetail)
    LinearLayout mBottombar;
    @Bind(R.id.imageview_projectdetail_itemImage)
    ImageView mImageview;
    @Bind(R.id.textview_projectdetail_tilte)
    TextView mTvTilte;
    @Bind(R.id.tv_projectdetail_numName)
    TextView mTvNumName;
    @Bind(R.id.tv_projectdetail_num)
    TextView mTvNum;
    @Bind(R.id.tv_projectdetail_timeName)
    TextView mTvTimeName;
    @Bind(R.id.tv_projectdetail_time)
    TextView mTvTime;
    @Bind(R.id.tv_projectdetail_state)
    TextView mTvState;
    @Bind(R.id.relative_projectdetail)
    RelativeLayout mRelative;
    @Bind(R.id.relative_projectdetail_item)
    RelativeLayout mRelativeItem;
    @Bind(R.id.tv_projectDetail_typeName)
    TextView mTvTypeName;
    @Bind(R.id.tv_projectDetail_type)
    TextView mTvType;
    @Bind(R.id.tv_projectDetail_effectiveTimeName)
    TextView mTvEffectiveTimeName;
    @Bind(R.id.tv_projectDetail_effectiveTime)
    TextView mTvEffectiveTime;
    @Bind(R.id.tv_projectDetail_locationName)
    TextView mTvLocationName;
    @Bind(R.id.tv_projectDetail_location)
    TextView mTvLocation;
    @Bind(R.id.tv_projectDetail_foundersName)
    TextView mTvFoundersName;
    @Bind(R.id.tv_projectDetail_founders)
    TextView mTvFounders;
    @Bind(R.id.tv_projectDetail_contactName)
    TextView mTvContactName;
    @Bind(R.id.tv_projectDetail_contact)
    TextView mTvContact;
    @Bind(R.id.tv_projectDetail_skillName)
    TextView mTvSkillName;
    @Bind(R.id.tv_projectDetail_skill)
    TextView mTvSkill;
    @Bind(R.id.tv_projectDetail_infuse)
    TextView mTvInfuse;
    @Bind(R.id.tv_projectDetail_detailName)
    TextView mTvDetailName;
    @Bind(R.id.relative_projectDetail_detailName)
    RelativeLayout mRelativeDetailName;
    @Bind(R.id.tv_projectDetail_detail)
    TextView mTvDetail;
    @Bind(R.id.tv_projectDetail_signedName)
    TextView mTvSignedName;
    @Bind(R.id.imageview_projectDetail_more)
    ImageView mImageviewMore;
    @Bind(R.id.tv_projectDetail_maxNum)
    TextView mTvMaxNum;
    @Bind(R.id.tv_projectDetail_haveSignedNum)
    TextView mTvSignedNum;
    @Bind(R.id.relative_projectDetail_signed)
    RelativeLayout mRelativeSigned;
    @Bind(R.id.imageview_projectDetail_signedPeople_1)
    ImageView mImageviewPeople1;
    @Bind(R.id.imageview_projectDetail_signedPeople_2)
    ImageView mImageviewPeople2;
    @Bind(R.id.imageview_projectDetail_signedPeople_3)
    ImageView mImageviewPeople3;
    @Bind(R.id.imageview_projectDetail_signedPeople_4)
    ImageView mImageviewPeople4;
    @Bind(R.id.imageview_projectDetail_signedPeople_5)
    ImageView mImageviewPeople5;
    @Bind(R.id.relative_projectDetail_signedPeople)
    LinearLayout mRelativeSignedPeople;
    @Bind(R.id.scrollview_projectdetail)
    ScrollView mScrollview;
    @Bind(R.id.tv_projectDetail_registerTimeName)
    TextView mTvRegisterTimeName;
    @Bind(R.id.tv_projectDetail_registerTime)
    TextView mTvRegisterTime;

    private View mCustomView;//actionbar的自定义视图
    ImageView indicatorImg;
    TextView titleTv;
    ImageView magnifierImg;

    private ActivityListDto mDate;

    private AppAction mAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectdetail);
        ButterKnife.bind(this);

        mAction = new AppActionImpl(this);

        mDate = (ActivityListDto) getIntent().getSerializableExtra("date");//获取活动或岗位的数据

        initActionBar();

        initView();
    }

    private void initView() {
        mTvTilte.setText(mDate.getActivityName());
        mTvNum.setText(mDate.getRecruited() + "/" + mDate.getRecruitNumber() + "人");
        mTvTime.setText(mDate.getLengthTime() + "小时");

        mTvType.setText("活动类型");

        mTvRegisterTime.setText("0000-00-00" + "-\n" + mDate.getEndTime());

        mTvEffectiveTime.setText(mDate.getStartTime() + "-\n" + mDate.getFinishTime());

        mTvLocation.setText(mDate.getAddr());

        mTvFounders.setText(mDate.getCompanyName());

        //通过CompanyId获取联系人和联系方式
        CompanyQueryOptionDto queryOptionDto = new CompanyQueryOptionDto();
        queryOptionDto.setOrganizationId(mDate.getCompanyId());
        Logger.v(TAG,mDate.getCompanyId());
        mAction.companyQuery(queryOptionDto, new ActionCallbackListener<PagedListEntityDto<CompanyListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<CompanyListDto> data) {
                if (data.getRows().size() <= 0)
                    return;
                CompanyListDto companyListDto = data.getRows().get(0);
                mTvContact.setText(companyListDto.getPerson() +
                        "/" + companyListDto.getTel() +
                        "/" + companyListDto.getMobile());
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });

        mTvDetail.setText(mDate.getJobText());

        mTvSignedNum.setText(mDate.getRecruited() + "");

        mTvMaxNum.setText("/" + mDate.getRecruitNumber());

        if (mDate.getStatus() == 1) {
            mTvState.setText("招募中");
        } else if (mDate.getStatus() == 0) {
            mTvState.setText("已结束");
        }

        if (mDate.getType() == 0) {
            //活动
            mTvSkill.setVisibility(View.INVISIBLE);
            mTvInfuse.setVisibility(View.INVISIBLE);
            mTvTypeName.setText("活动类型");
            mTvRegisterTimeName.setText("活动报名时间");
            mTvEffectiveTimeName.setText("活动有效时间");
            mTvLocationName.setText("活动服务地址");
            mTvFoundersName.setText("活动发起单位");
            mTvDetailName.setText("活动详情");
            mTvSignedName.setText("活动详情");
        } else if (mDate.getType() == 1) {
            //岗位
            mTvTypeName.setText("岗位类型");
            mTvRegisterTimeName.setText("岗位报名时间");
            mTvEffectiveTimeName.setText("岗位有效时间");
            mTvLocationName.setText("岗位服务地址");
            mTvFoundersName.setText("岗位发起单位");
            mTvDetailName.setText("岗位详情");
            mTvSignedName.setText("岗位详情");

            mTvSkill.setText("服务专业技能");
        }

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
        if (mDate.getType() == 0) {
            titleTv.setText("活动");
        } else if (mDate.getType() == 1) {
            titleTv.setText("岗位");
        }

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

    @OnClick({R.id.btn_projectdetail_share, R.id.btn_projectdetail_apply, R.id.btn_projectdetail_focuson, R.id.imageview_projectdetail_itemImage, R.id.tv_projectDetail_contactName, R.id.tv_projectDetail_contact})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_projectdetail_share:
                Holder share = new ViewHolder(R.layout.dialog_share);
                DialogPlus dialogShare = DialogPlus.newDialog(this)
                        .setContentHolder(share)
                        .setCancelable(true)
                        .setGravity(Gravity.BOTTOM)
                        .create();
                dialogShare.show();
                break;
            case R.id.btn_projectdetail_apply:
                Holder holder = new ViewHolder(R.layout.dialog_caldroid);
                DialogPlus dialogPlus = DialogPlus.newDialog(this)
                        .setContentHolder(holder)
                        .setCancelable(true)
                        .setGravity(Gravity.BOTTOM)
                        .create();
                dialogPlus.show();
                break;
            case R.id.btn_projectdetail_focuson:
                break;
            case R.id.imageview_projectdetail_itemImage:
                break;
            case R.id.tv_projectDetail_contactName:
                break;
            case R.id.tv_projectDetail_contact:
                break;
        }
    }
}
