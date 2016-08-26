package com.example.renhao.wevolunteer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityTimeSimpleDto;
import com.example.model.activity.ActivityViewDto;
import com.example.model.activityRecruit.ActivityRecruitDto;
import com.example.model.activityRecruit.ActivityRecruitListDto;
import com.example.model.activityRecruit.ActivityRecruitQueryOptionDto;
import com.example.model.activityattention.ActivityAttentionDto;
import com.example.model.activityattention.ActivityAttentionListDto;
import com.example.model.activityattention.ActivityAttentionQueryOptionDto;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.jobActivity.JobActivityViewDto;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.handler.MxgsaTagHandler;
import com.example.renhao.wevolunteer.utils.Util;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/9 16:09
 * 修改备注：
 */
public class ProjectDetailActivity extends BaseActivity {
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
    @Bind(R.id.projectdetail_border)
    View mProjectdetailBorder;
    @Bind(R.id.relative_projectDetail_skill)
    LinearLayout mRelativeSkill;
    @Bind(R.id.relative_projectDetail_skill_border)
    View mSkillBorder;
    @Bind(R.id.tv_projectDetail_infuse_border)
    View mInfuseBorder;
    @Bind(R.id.projectdetail_parent)
    RelativeLayout mParent;

    private View mCustomView;//actionbar的自定义视图
    private ImageView indicatorImg;
    private TextView titleTv;
    private ImageView magnifierImg;

    private int origin;

    private ActivityViewDto mActivityViewDto;
    private JobActivityViewDto mJobActivityViewDto;

    private List<ActivityTimeSimpleDto> times = new ArrayList<>();
    private List<String> sTime = new ArrayList<>();

    private String id;
    private int type;
    private Map<String, String> notes = new HashMap<>();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    private boolean isAttention = false;
    private boolean isInitAttention = false;
    private String volunteerId;
    private String attentionId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectdetail);
        ButterKnife.bind(this);

        volunteerId = LocalDate.getInstance(this).getLocalDate("volunteerId", "");

        type = getIntent().getIntExtra("type", 0);
        origin = getIntent().getIntExtra("origin", 0);

        initActionBar();

        id = getIntent().getStringExtra("id");
        initView();
        getDetail();

        isAttention();

        getSiginInVolunteer();

    }


    /**
     * 获取已经报名的志愿者，并获取其头像显示
     */
    private void getSiginInVolunteer() {
        ActivityRecruitQueryOptionDto queryOptionDto = new ActivityRecruitQueryOptionDto();
        queryOptionDto.setActivityId(id);
        AppActionImpl.getInstance(this).activityRecruitQuery(queryOptionDto,
                new ActionCallbackListener<PagedListEntityDto<ActivityRecruitListDto>>() {
                    @Override
                    public void onSuccess(PagedListEntityDto<ActivityRecruitListDto> data) {
                        if (data.getRows() == null || data.getRows().size() < 1)
                            return;
                        setVolunteerPhoto(data.getRows(), 0);
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });
    }

    /**
     * 利用迭代的方法获取并显示用户的头像
     *
     * @param list
     * @param position
     */
    private void setVolunteerPhoto(final List<ActivityRecruitListDto> list, final int position) {
        if (list == null || list.size() < (position + 1))
            return;
        //取得头像
        AppActionImpl.getInstance(this).get_portrait(list.get(position).getVolunteerId(),
                new ActionCallbackListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        if (data == null)
                            return;
                        Bitmap bitmap = Util.getBitmapFromByte(data);
                        ImageView view = (ImageView) mRelativeSignedPeople.getChildAt(position);
                        view.setVisibility(View.VISIBLE);
                        //设置图片
                        view.setImageBitmap(bitmap);
                        setVolunteerPhoto(list, position + 1);
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        ImageView view = (ImageView) mRelativeSignedPeople.getChildAt(position);
                        view.setVisibility(View.VISIBLE);
                        //设置图片
                        view.setImageResource(R.drawable.personal_no_portrait);
                        setVolunteerPhoto(list, position + 1);
                    }
                });
    }

    /**
     * 加载数据
     */
    public void getDetail() {
        showNormalDialog("正在加载数据...");
        if (type == 0) {
            getActivityDetail();
        } else if (type == 1) {
            getJobActivityDetail();
        }

    }

    private void getActivityDetail() {
        AppActionImpl.getInstance(this).activityDetail(id, new ActionCallbackListener<ActivityViewDto>() {
            @Override
            public void onSuccess(ActivityViewDto data) {
                dissMissNormalDialog();

                if (data == null)
                    return;

                mActivityViewDto = data;
                times = data.getActivityTimes();
                setActivityDetail(data);
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                dissMissNormalDialog();
            }
        });
    }

    private void setActivityDetail(ActivityViewDto data) {
        mTvTilte.setText(data.getActivityName());

        mTvNum.setText(data.getRecruited() + "/" + data.getRecruitNumber() + "人");

        float h = data.getLengthTime().floatValue() / 60;
        DecimalFormat df = new DecimalFormat("#.##");
        mTvTime.setText(df.format(h) + "小时");

        mTvState.setText(data.getOperationState());

        String imagUrl = "";
        if (!TextUtils.isEmpty(data.getAppLstUrl())) {
            imagUrl = data.getAppLstUrl();
            Picasso.with(getApplicationContext()).load(Util.getRealUrl(imagUrl))
                    .placeholder(R.drawable.img_unload)
                    .error(R.drawable.img_unload)
                    .into(mImageview);
        } else {
            mImageview.setImageResource(R.drawable.img_unload);
        }

        //岗位类型 可能有多个
        String[] activityTypeName = data.getActivityTypeName().split(",");
        String typeName = "";

        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("^[\\u4E00-\\u9FA5]+$");
        for (int i = 0; i < activityTypeName.length; i++) {
            matcher = pattern.matcher(activityTypeName[i]);
            if (matcher.matches()) {
                typeName += activityTypeName[i] + "   ";
            }
        }
        mTvType.setText(typeName);//

        mTvRegisterTime.setText(data.getBeginTime() + "-\n" + data.getEndTime());

        mTvEffectiveTime.setText(data.getStartTime() + " - \n" + data.getEndTime());

        mTvLocation.setText(data.getAddr());

        mTvFounders.setText(data.getCompanyName());

        String contactStr = "";
        contactStr += TextUtils.isEmpty(data.getLinker()) ? "" : data.getLinker() + "\n";
        contactStr += TextUtils.isEmpty(data.getTel()) ? "" : data.getTel() + "\n";
        contactStr += TextUtils.isEmpty(data.getMobile()) ? "" : data.getMobile();
        mTvContact.setText(contactStr);

        mTvDetail.setText(Html.fromHtml(data.getJobText(), null, new MxgsaTagHandler(this)));

        mTvSignedNum.setText(data.getRecruited() + "");

        mTvMaxNum.setText("/" + data.getRecruitNumber());

        sTime = new ArrayList<>();
        for (int i = 0; i < data.getActivityTimes().size(); i++) {
            ActivityTimeSimpleDto dto = data.getActivityTimes().get(i);


            try {
                Date date = format.parse(dto.getSTime());
                CalendarDay temp = new CalendarDay(date);
                notes.put(temp.toString(), "余" + (dto.getAllowNum() - dto.getRecruitedNum()));
                sTime.add(i, temp.getYear() + "-" + (temp.getMonth() + 1) + "-" + temp.getDay());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        /*for (int i = (data.getRecruited() > 5 ? 5 : data.getRecruited()) - 1; i >= 0; i--) {
            ImageView view = (ImageView) mRelativeSignedPeople.getChildAt(i);
            view.setVisibility(View.VISIBLE);
            //设置图片
            view.setImageResource(R.mipmap.ic_launcher);
        }*/
    }

    private void getJobActivityDetail() {
        AppActionImpl.getInstance(this).jobActivityDetail(id, new ActionCallbackListener<JobActivityViewDto>() {
            @Override
            public void onSuccess(JobActivityViewDto data) {
                dissMissNormalDialog();

                if (data == null)
                    return;

                mJobActivityViewDto = data;
                times = data.getActivityTimes();
                setJobActivityDetail(data);
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                dissMissNormalDialog();
            }
        });
    }

    private void setJobActivityDetail(JobActivityViewDto data) {
        mTvTilte.setText(data.getActivityName());

        mTvNum.setText(data.getRecruited() + "/" + data.getRecruitNumber() + "人");

        float h = data.getLengthTime().floatValue() / 60;
        DecimalFormat df = new DecimalFormat("#.##");
        mTvTime.setText(df.format(h) + "小时");

        mTvState.setText(data.getOperationState());

        String imagUrl = "";
        if (!TextUtils.isEmpty(data.getAppLstUrl())) {
            imagUrl = data.getAppLstUrl();
            Picasso.with(getApplicationContext()).load(Util.getRealUrl(imagUrl))
                    .placeholder(R.drawable.img_unload)
                    .error(R.drawable.img_unload)
                    .into(mImageview);
        } else {
            mImageview.setImageResource(R.drawable.img_unload);
        }

        //岗位类型 可能有多个
        String[] activityTypeName = data.getActivityTypeName().split(",");
        String typeName = "";

        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("^[\\u4E00-\\u9FA5]+$");
        for (int i = 0; i < activityTypeName.length; i++) {
            matcher = pattern.matcher(activityTypeName[i]);
            if (matcher.matches()) {
                typeName += activityTypeName[i] + "   ";
            }
        }
        mTvType.setText(typeName);//

        mTvRegisterTime.setText(data.getBeginTime() + "-\n" + data.getEndTime());

        mTvEffectiveTime.setText(data.getStartTime());

        mTvLocation.setText(data.getAddr());

        mTvFounders.setText(data.getCompanyName());

        String contactStr = "";
        contactStr += TextUtils.isEmpty(data.getLinker()) ? "" : data.getLinker() + "\n";
        contactStr += TextUtils.isEmpty(data.getTel()) ? "" : data.getTel() + "\n";
        contactStr += TextUtils.isEmpty(data.getMobile()) ? "" : data.getMobile();
        mTvContact.setText(contactStr);

        getSpecialType(data.getSpecialityType());

        mTvDetail.setText(Html.fromHtml(data.getJobText(), null, new MxgsaTagHandler(this)));

        sTime = new ArrayList<>();
        for (int i = 0; i < data.getActivityTimes().size(); i++) {
            ActivityTimeSimpleDto dto = data.getActivityTimes().get(i);

            try {
                Date date = format.parse(dto.getSTime());
                CalendarDay temp = new CalendarDay(date);
                notes.put(temp.toString(), "余" + (dto.getAllowNum() - dto.getRecruitedNum()));
                sTime.add(i, temp.getYear() + "-" + (temp.getMonth() + 1) + "-" + temp.getDay());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        mTvSignedNum.setText(data.getRecruited() + "");

        mTvMaxNum.setText("/" + data.getRecruitNumber());

       /* for (int i = (data.getRecruited() > 5 ? 5 : data.getRecruited()) - 1; i >= 0; i--) {
            ImageView view = (ImageView) mRelativeSignedPeople.getChildAt(i);
            view.setVisibility(View.VISIBLE);
            //设置图片
            view.setImageResource(R.mipmap.ic_launcher);
        }*/
    }

    private void initView() {
        if (type == 0) {
            //活动
            mTvSkill.setVisibility(View.GONE);
            mTvInfuse.setVisibility(View.GONE);
            mRelativeSkill.setVisibility(View.GONE);
            mSkillBorder.setVisibility(View.GONE);
            mInfuseBorder.setVisibility(View.GONE);


            mTvTypeName.setText("活动类型");
            mTvRegisterTimeName.setText("活动报名时间");
            mTvEffectiveTimeName.setText("活动有效时间");
            mTvLocationName.setText("活动服务地址");
            mTvFoundersName.setText("活动发起单位");
            mTvDetailName.setText("活动详情");
            mTvSignedName.setText("活动详情");
            mTvNumName.setText("活动招募人数");
            mTvTimeName.setText("活动服务时长");
        } else if (type == 1) {
            //岗位
            mTvTypeName.setText("岗位类型:");
            mTvRegisterTimeName.setText("岗位报名时间:");
            mTvEffectiveTimeName.setText("岗位起始时间:");
            mTvLocationName.setText("岗位服务地址:");
            mTvFoundersName.setText("岗位发起单位:");
            mTvDetailName.setText("岗位详情");
            mTvSignedName.setText("岗位详情");
            mTvNumName.setText("岗位招募人数");
            mTvTimeName.setText("岗位服务时长");

            /*mTvSkill.setText("");*/
        }

/*        for (int i = (mDate.getRecruited() > 5 ? 5 : mDate.getRecruited()) - 1; i >= 0; i--) {
            ImageView view = (ImageView) mRelativeSignedPeople.getChildAt(i);
            view.setVisibility(View.VISIBLE);
            //设置图片
            view.setImageResource(R.mipmap.ic_launcher);
        }*/

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
        if (type == 0) {
            titleTv.setText("活动");
        } else if (type == 1) {
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
                if (origin == 1)
                    finish();
                else {
                    Intent intent = new Intent(ProjectDetailActivity.this, SearchActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void getSpecialType(String typeCodes) {

        if (typeCodes == null)
            return;

        final String[] types = typeCodes.split(",");

        AppActionImpl.getInstance(this).dictionaryQueryDefault("SPECIALITY", "",
                new ActionCallbackListener<List<DictionaryListDto>>() {
                    @Override
                    public void onSuccess(List<DictionaryListDto> data) {
                        if (data == null)
                            return;
                        String specialType = "";

                        for (int i = 0; i < data.size(); i++) {
                            for (int j = 0; j < types.length; j++) {
                                if (data.get(i).getCode().equals(types[j])) {
                                    specialType += data.get(i).getName() + "  ";
                                }
                            }
                        }

                        mTvSkill.setText(specialType);
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });
    }

    @OnClick({R.id.btn_projectdetail_share,
            R.id.btn_projectdetail_apply,
            R.id.btn_projectdetail_focuson,
            R.id.relative_projectDetail_detailName,
            R.id.imageview_projectdetail_itemImage,
            R.id.tv_projectDetail_contactName,
            R.id.tv_projectDetail_contact})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_projectdetail_share:
//                Holder share = new ViewHolder(R.layout.dialog_share);
//                DialogPlus dialogShare = DialogPlus.newDialog(this)
//                        .setContentHolder(share)
//                        .setCancelable(true)
//                        .setGravity(Gravity.BOTTOM)
//                        .create();
//                dialogShare.show();
                showShare();
                break;
            case R.id.btn_projectdetail_apply:
                siginIn();
                break;
            case R.id.btn_projectdetail_focuson:
                if (TextUtils.isEmpty(volunteerId)) {
                    showToast("请先登录");
                    return;
                }
                if (isInitAttention)
                    setOrCelAttention();
                else
                    isAttention();
                break;
            case R.id.imageview_projectdetail_itemImage:
                break;
            case R.id.tv_projectDetail_contactName:
                break;
            case R.id.tv_projectDetail_contact:
                break;
            case R.id.relative_projectDetail_detailName:
                Intent intent = new Intent(ProjectDetailActivity.this, JobDetailActivity.class);
                if (type == 0) {
                    intent.putExtra("jobText", mActivityViewDto.getJobText());
                    intent.putExtra("text", mActivityViewDto.getText());
                    intent.putExtra("origin", origin);
                } else if (type == 1) {
                    intent.putExtra("jobText", mJobActivityViewDto.getJobText());
                    intent.putExtra("text", mJobActivityViewDto.getText());
                    intent.putExtra("origin", origin);
                }
                startActivityForResult(intent, 0);
                break;
        }
    }

    private void siginIn() {
        //判断是否已经登录
        boolean isLogin = LocalDate.getInstance(this).getLocalDate("isLogin", false);
        if (!isLogin || TextUtils.isEmpty(volunteerId)) {
            showToast("请先登录");
            return;
        }

        Holder holder = new ViewHolder(R.layout.dialog_caldroid);
        final DialogPlus dialogPlus = DialogPlus.newDialog(this)
                .setContentHolder(holder)
                .setCancelable(true)
                .setGravity(Gravity.BOTTOM)
                .create();
        final TextView selsecTv = (TextView) dialogPlus.getHolderView().findViewById(R.id.tv_dialogCaldroid_dateSelect);
        Button signUpBtn = (Button) dialogPlus.getHolderView().findViewById(R.id.btn_dialogCaldroid_signUp);
        MaterialCalendarView calendarView = (MaterialCalendarView) dialogPlus.getHolderView().findViewById(R.id.calendarView);
        calendarView.setTileHeightDp(44);
        calendarView.setArrowColor(getResources().getColor(R.color.colorCyan));
        calendarView.setNotes(notes);
        calendarView.setOnClickListener(new MaterialCalendarView.CloseListener() {
            @Override
            public void close(View view) {
                dialogPlus.dismiss();
            }
        });
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                selsecTv.setText(date.getYear() + "-" + (date.getMonth() + 1) + "-" + date.getDay());
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(selsecTv.getText())) {
                    showToast("请选择一个日期");
                    return;
                }
                //筛选出选择了哪个日期
                int select = -1;
                for (int i = 0; i < sTime.size(); i++) {
                    if (sTime.get(i).equals(selsecTv.getText().toString())) {
                        select = i;
                        break;
                    }
                }
                //判断是否还有剩余
                final ActivityTimeSimpleDto dto = times.get(select);
                if (dto.getRecruitedNum().intValue() == dto.getAllowNum().intValue()) {
                    showToast("报名人数已满");
                    return;
                }

                showNormalDialog("请稍后...");
                //判断是否已经报名
                ActivityRecruitQueryOptionDto queryOptionDto = new ActivityRecruitQueryOptionDto();
                queryOptionDto.setActivityId(id);
                queryOptionDto.setVolunteerId(volunteerId);
                queryOptionDto.setActivityTimeId(dto.getId());
                AppActionImpl.getInstance(getApplicationContext()).activityRecruitQuery(queryOptionDto,
                        new ActionCallbackListener<PagedListEntityDto<ActivityRecruitListDto>>() {
                            @Override
                            public void onSuccess(PagedListEntityDto<ActivityRecruitListDto> data) {
                                if (data.getRows() == null || data.getRows().size() == 0) {
                                    //报名
                                    List<ActivityRecruitDto> recruitDtos = new ArrayList<ActivityRecruitDto>();
                                    ActivityRecruitDto recruitDto = new ActivityRecruitDto();
                                    recruitDto.setActivityId(id);
                                    recruitDto.setVolunteerId(volunteerId);
                                    recruitDto.setActivityTimeId(dto.getId());
                                    recruitDto.setBaoMingDate(Util.getNowDate());
                                    recruitDto.setAuditStatus(0);
                                    recruitDto.setSign(0);
                                    recruitDto.setSignout(0);
                                    recruitDto.setSource(0);
                                    //获取Mac地址
                                    recruitDto.setIP(Util.getMac());
                                    recruitDto.setDeleted(false);
                                    recruitDtos.add(recruitDto);

                                    AppActionImpl.getInstance(getApplicationContext()).activityRecruitCreate(recruitDtos,
                                            new ActionCallbackListener<List<String>>() {
                                                @Override
                                                public void onSuccess(List<String> data) {
                                                    if (data != null) {
                                                        showToast("报名成功");
                                                        dialogPlus.dismiss();
                                                    } else {
                                                        showToast("报名失败");
                                                    }
                                                    dissMissNormalDialog();
                                                }

                                                @Override
                                                public void onFailure(String errorEvent, String message) {
                                                    dissMissNormalDialog();
                                                }
                                            });
                                } else {
                                    dissMissNormalDialog();
                                    showToast("您已报名");
                                }
                            }

                            @Override
                            public void onFailure(String errorEvent, String message) {
                                dissMissNormalDialog();
                            }
                        });
            }
        });

        dialogPlus.show();
    }

    /**
     * 查看用户是否已经关注此项目，是为已关注，否为关注
     */
    private void isAttention() {
        if (TextUtils.isEmpty(volunteerId))
            return;
        ActivityAttentionQueryOptionDto queryOptionDto = new ActivityAttentionQueryOptionDto();
        queryOptionDto.setActivityId(id);
        queryOptionDto.setUserId(volunteerId);
        AppActionImpl.getInstance(this).activityAttentionQuery(queryOptionDto,
                new ActionCallbackListener<PagedListEntityDto<ActivityAttentionListDto>>() {
                    @Override
                    public void onSuccess(PagedListEntityDto<ActivityAttentionListDto> data) {
                        if (data.getRows() != null && data.getRows().size() > 0) {
                            isAttention = true;
                            mBtnFocuson.setText("已关注");
                            attentionId = data.getRows().get(0).getId();
                        } else {
                            isAttention = false;
                            mBtnFocuson.setText("关注");
                        }
                        isInitAttention = true;
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });
    }

    /**
     * 关注或取消关注
     */
    private void setOrCelAttention() {
        if (isAttention) {
            if (TextUtils.isEmpty(attentionId))
                return;
            List<String> id = new ArrayList<>();
            id.add(attentionId);
            AppActionImpl.getInstance(this).activityAttentionDelete(id, new ActionCallbackListener<String>() {
                @Override
                public void onSuccess(String data) {
                    mBtnFocuson.setText("关注");
                    isAttention = false;
                }

                @Override
                public void onFailure(String errorEvent, String message) {

                }
            });
        } else {
            List<ActivityAttentionDto> dtos = new ArrayList<>();
            ActivityAttentionDto dto = new ActivityAttentionDto();
            dto.setActivityId(id);
            dto.setUserId(volunteerId);
            dto.setAttentionTime(Util.getNowDate());
            dtos.add(dto);

            AppActionImpl.getInstance(this).activityAttentionCreate(dtos,
                    new ActionCallbackListener<List<String>>() {
                        @Override
                        public void onSuccess(List<String> data) {
                            if (data == null)
                                return;
                            attentionId = data.get(0);
                            mBtnFocuson.setText("已关注");
                            isAttention = true;
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            showToast("关注失败，请稍后再试");
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*if (resultCode == 0)
            finish();*/
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.ssdk_oks_share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

}
