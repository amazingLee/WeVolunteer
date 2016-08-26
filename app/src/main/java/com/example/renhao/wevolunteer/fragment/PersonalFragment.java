package com.example.renhao.wevolunteer.fragment;
/*
 *
 * Created by Ge on 2016/8/8  10:03.
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.core.AppActionImpl;
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.activity.AboutUsActivity;
import com.example.renhao.wevolunteer.activity.ApplyProBonoActivity;
import com.example.renhao.wevolunteer.activity.FAQActivity;
import com.example.renhao.wevolunteer.activity.LoginActivity;
import com.example.renhao.wevolunteer.activity.MajorAbilityActivity;
import com.example.renhao.wevolunteer.activity.MyORGActivity;
import com.example.renhao.wevolunteer.activity.MyProjectActivity;
import com.example.renhao.wevolunteer.activity.PersonalDataActivity;
import com.example.renhao.wevolunteer.activity.ReportProblemActivity;
import com.example.renhao.wevolunteer.base.BaseFragment;
import com.example.renhao.wevolunteer.utils.Util;
import com.orhanobut.logger.Logger;

public class PersonalFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "PersonalFragment";

    private View mainview;
    private boolean isCreat = false;

    private VolunteerViewDto eventbus_data;
    private View bottomView;
    private LinearLayout professional_true;
    private LinearLayout Professional_false;
    private TextView tv_true_name, tv_specialty, tv_integral;
    private View middleView;
    private TextView tv_AllTime, tv_SchoolTime, tv_InJobTime, tv_RetireTime;
    private LinearLayout group;
    private ImageView image_portrait;

    private final int UPDATE_UI = 0;
    private final int UPDATE_PORTRAIT = 1;

    //onclick TAG
    private final int PROFESSIONAL_SELECTION = 0;
    private final int APPLY_PROFESSIONAL = 1;
    private final int TO_MYPROJECT = 2;
    private final int TO_ATTENTION = 3;
    private final int TO_ORG = 4;
    private final int TO_RANK = 5;
    private final int TO_INFORMATION = 6;
    private final int TO_ABOUTUS = 7;
    private final int TO_WIPE_CACHE = 8;
    private final int TO_REPORT_PROBLEM = 9;
    private final int TO_FAQ = 10;
    private final int LOGIN = 11;

    private boolean isSpeciality;
    private boolean isShowTrueName;
    private boolean isInit = false;//为了让第一次显示时用户未登录进入登录界面
    private String true_name;
    private String nick_name;
    private String specialty;
    private String integral;
    private int LevelType;
    private String AllServiceTime;
    private String WorkServiceTime;
    private String SchoolServiceTime;
    private String RetireServiceTime;
    private byte[] my_portrait;

    /*Handler更新UI*/
   /* Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_UI:
                    //整体页面更新
                    repeat_update();
                    break;
                case UPDATE_PORTRAIT:
                    //头像显示
                    if (my_portrait != null) {
                        image_portrait.setImageBitmap(Util.getBitmapFromByte(my_portrait));
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };*/


    private void repeat_update() {
        //判断是否为专业志愿志愿者，显示不同按钮
        if (isSpeciality) {
            //专业志愿者
            tv_specialty.setVisibility(View.VISIBLE);
            tv_specialty.setText(specialty);
            professional_true.setVisibility(View.VISIBLE);
            Professional_false.setVisibility(View.GONE);
        } else {
            //普通志愿者
            tv_specialty.setVisibility(View.INVISIBLE);
            professional_true.setVisibility(View.GONE);
            Professional_false.setVisibility(View.VISIBLE);
        }
        //头部
        if (isShowTrueName) {
            tv_true_name.setText(true_name);
        } else {
            tv_true_name.setText(nick_name);
        }
        tv_integral.setText(integral + " 分");
        SetStars();//根据level显示星级

        //中部
        tv_AllTime.setText(AllServiceTime);
        tv_SchoolTime.setText(WorkServiceTime);
        tv_InJobTime.setText(SchoolServiceTime);
        tv_RetireTime.setText(RetireServiceTime);
    }


    //判断星星是否已经生成
    private boolean isStarYet = false;

    //根据Level显示星星
    private void SetStars() {
        if (LevelType != 0 && !isStarYet) {
            group = (LinearLayout) mainview.findViewById(R.id.LL_personal_StarsGroup);
            ImageView[] imageViews = new ImageView[LevelType];
            int width = (int) getActivity().getResources().getDimension(R.dimen.imageview_stars_width);
            int heigth = (int) getActivity().getResources().getDimension(R.dimen.imageview_stars_height);
            for (int i = 0; i < LevelType; i++) {
                ImageView imageView = new ImageView(getActivity());
                imageView.setImageResource(R.drawable.star);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, heigth);
                imageView.setLayoutParams(params);
                group.addView(imageView);
                imageViews[i] = imageView;
            }
            isStarYet = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isInit = true;
        mainview = inflater.inflate(R.layout.fragment_personal, container, false);

        /*头部*/
        image_portrait = (ImageView) mainview.findViewById(R.id.image_personal_portrait);
        image_portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* boolean isLogin = LocalDate.getInstance(getActivity()).getLocalDate("isLogin", false);
                if (!isLogin) {
                    //如果用户未登录则跳转到登录界面
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }*/
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        /*find UI界面组件
        include的middle_view*/
        middleView = mainview.findViewById(R.id.middle_part);
        tv_AllTime = (TextView) middleView.findViewById(R.id.tv_personal_ServiceAllTime);
        tv_SchoolTime = (TextView) middleView.findViewById(R.id.tv_personal_ServiceSchoolTime);
        tv_InJobTime = (TextView) middleView.findViewById(R.id.tv_personal_ServiceWorkTime);
        tv_RetireTime = (TextView) middleView.findViewById(R.id.tv_personal_ServiceRetireTime);
        /*include的bottom_view*/
        bottomView = mainview.findViewById(R.id.bottom_part);
        tv_true_name = (TextView) mainview.findViewById(R.id.tv_LL_true_name);
        tv_specialty = (TextView) mainview.findViewById(R.id.tv_LL_specialty);
        tv_integral = (TextView) mainview.findViewById(R.id.tv_LL_integral);
        professional_true = (LinearLayout) bottomView.findViewById(R.id.LL_PF_Professional_true);
        Professional_false = (LinearLayout) bottomView.findViewById(R.id.LL_PF_Professional_false);


        initViewsEven();//设置点击事件的监听以及初始化组件

        //初次进入设置UI
        repeat_update();
        isCreat = true;

        return mainview;
    }

    private void initViewsEven() {
        //初始化组件
        LinearLayout job = (LinearLayout) bottomView.findViewById(R.id.LL_PF_job);
        LinearLayout attention = (LinearLayout) bottomView.findViewById(R.id.LL_PF_attention);
        LinearLayout ORG = (LinearLayout) bottomView.findViewById(R.id.LL_PF_ORG);
        LinearLayout rank = (LinearLayout) bottomView.findViewById(R.id.LL_PF_rank);
        LinearLayout Information = (LinearLayout) bottomView.findViewById(R.id.LL_PF_information);
        LinearLayout aboutUS = (LinearLayout) bottomView.findViewById(R.id.LL_PF_aboutUS);
        LinearLayout WIPE_CACHE = (LinearLayout) bottomView.findViewById(R.id.LL_PF_WIPE_CACHE);
        LinearLayout REPORT_PROBLEM = (LinearLayout) bottomView.findViewById(R.id.LL_PF_REPORT_PROBLEM);
        LinearLayout FAQ = (LinearLayout) bottomView.findViewById(R.id.LL_PF_FAQ);


        //添加点击监听
        professional_true.setOnClickListener(this);
        Professional_false.setOnClickListener(this);
        job.setOnClickListener(this);
        attention.setOnClickListener(this);
        ORG.setOnClickListener(this);
        rank.setOnClickListener(this);
        Information.setOnClickListener(this);
        aboutUS.setOnClickListener(this);
        WIPE_CACHE.setOnClickListener(this);
        REPORT_PROBLEM.setOnClickListener(this);
        FAQ.setOnClickListener(this);

        //添加点击标签
        professional_true.setTag(PROFESSIONAL_SELECTION);
        Professional_false.setTag(APPLY_PROFESSIONAL);
        job.setTag(TO_MYPROJECT);
        attention.setTag(TO_ATTENTION);
        ORG.setTag(TO_ORG);
        rank.setTag(TO_RANK);
        Information.setTag(TO_INFORMATION);
        aboutUS.setTag(TO_ABOUTUS);
        WIPE_CACHE.setTag(TO_WIPE_CACHE);
        REPORT_PROBLEM.setTag(TO_REPORT_PROBLEM);
        FAQ.setTag(TO_FAQ);

    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        final Intent intent = new Intent();

        boolean isLigon = LocalDate.getInstance(getActivity()).getLocalDate("isLogin", false);
        if (!isLigon) {
            showToast("请先登录");
            return;
        }

        switch (tag) {
            case PROFESSIONAL_SELECTION:
                if (eventbus_data == null) {
                    showToast("登录异常");
                    return;
                }
                intent.setClass(getActivity(), MajorAbilityActivity.class);
                intent.putExtra("personal_data", eventbus_data);
                startActivity(intent);
                break;
            case APPLY_PROFESSIONAL:
                toSpecialRegister(intent);
                break;
            case TO_MYPROJECT:
                intent.putExtra("title", this.getResources().getString(R.string.title_myproject));
                intent.putExtra("activityType", MyProjectActivity.MY_PROJECT);
                intent.setClass(getActivity(), MyProjectActivity.class);
                startActivity(intent);
                break;
            case TO_ATTENTION:
                intent.putExtra("title", this.getResources().getString(R.string.title_myattention));
                intent.putExtra("activityType", MyProjectActivity.MY_ATTENTION);
                intent.setClass(getActivity(), MyProjectActivity.class);
                startActivity(intent);
                break;
            case TO_ORG:
                if (eventbus_data == null) {
                    showToast("登录异常");
                    return;
                }
                intent.setClass(getActivity(), MyORGActivity.class);
                intent.putExtra("personal_data", eventbus_data);
                startActivity(intent);
                break;
            case TO_RANK:
                break;
            case TO_INFORMATION:
                toInformation(intent);
                break;
            case TO_ABOUTUS:
                intent.setClass(getActivity(), AboutUsActivity.class);
                startActivity(intent);
                break;
            case TO_WIPE_CACHE:
               /* LocalDate.getInstance(getActivity()).setLocalDate("isLogin", false);*/
                break;
            case TO_REPORT_PROBLEM:
                intent.setClass(getActivity(), ReportProblemActivity.class);
                startActivity(intent);
                break;
            case TO_FAQ:
                intent.setClass(getActivity(), FAQActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void toSpecialRegister(final Intent intent) {
        String volunteerId = LocalDate.getInstance(getActivity()).getLocalDate("volunteerId", "");
        if (TextUtils.isEmpty(volunteerId)) {
            showToast("请先登录");
            return;
        }

        AppActionImpl.getInstance(getActivity()).get_volunteerDetail(volunteerId,
                new ActionCallbackListener<VolunteerViewDto>() {
                    @Override
                    public void onSuccess(VolunteerViewDto data) {
                        if (data == null) {
                            showToast("服务器连接失败");
                            return;
                        }
                        intent.putExtra("personal_data", data);
                        intent.setClass(getActivity(), ApplyProBonoActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("服务器连接失败");
                    }
                });
    }

    private void toInformation(final Intent intent) {
        if (my_portrait != null) {
            intent.putExtra("portrait", my_portrait);
        }
        String volunteerId = LocalDate.getInstance(getActivity()).getLocalDate("volunteerId", "");
        if (TextUtils.isEmpty(volunteerId)) {
            showToast("请先登录");
            return;
        }

        AppActionImpl.getInstance(getActivity()).get_volunteerDetail(volunteerId,
                new ActionCallbackListener<VolunteerViewDto>() {
                    @Override
                    public void onSuccess(VolunteerViewDto data) {
                        if (data == null) {
                            showToast("服务器连接失败");
                            return;
                        }
                        intent.putExtra("data", data);
                        intent.setClass(getActivity(), PersonalDataActivity.class);
                        startActivityForResult(intent, TO_INFORMATION);
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("服务器连接失败");
                    }
                });
    }

    @Override
    public void onResume() {
        //注册登录时需写的
      /*  LocalDate.getInstance(getActivity()).setLocalDate("volunteerId", "ecb8263d-4559-4dff-8db5-13be1059d6fa");
        LocalDate.getInstance(getActivity()).setLocalDate("isLogin", true);
        LocalDate.getInstance(getActivity()).setLocalDate("username", "15728006854");
        LocalDate.getInstance(getActivity()).setLocalDate("password", "123456");*/
        super.onResume();

        if (!LocalDate.getInstance(getActivity()).getLocalDate("isLogin", false)) {
            image_portrait.setImageResource(R.drawable.personal_no_portrait);
            tv_true_name.setText("");
        }
        getVolunteerDate();

        /*LocalDate.getInstance(getActivity()).setLocalDate("volunteerId", "");
        LocalDate.getInstance(getActivity()).setLocalDate("isLogin", false);*/


/*        //暂时获取票据
        AppActionImpl.getInstance(getActivity()).getAccessToken("15728006854", "123456", new AccessTokenListener() {
            @Override
            public void success(AccessTokenBO accessTokenBO) {

                //取得头像
                AppActionImpl.getInstance(getActivity()).get_portrait("ecb8263d-4559-4dff-8db5-13be1059d6fa", new ActionCallbackListener<String>() {

                            @Override
                            public void onSuccess(String data) {
                                try {
                                    my_portrait = Base64.decode(data.getBytes(), Base64.DEFAULT);
                                    Message message = new Message();
                                    message.what = UPDATE_PORTRAIT;
                                    myHandler.sendMessage(message);
                                } catch (Exception e) {
                                }
                            }

                            @Override
                            public void onFailure(String errorEvent, String message) {

                            }
                        }

                );


            }

            @Override
            public void fail() {
                showToast("fail");
            }
        });*/

//        //志愿者信息post查询
//        VolunteerQueryDto vl_query = new VolunteerQueryDto();
//
//        vl_query.setPageIndex(0);
//
//
//        Logger.v(TAG, new Gson().toJson(vl_query));
//        mAction.volunteerQuery(vl_query, new ActionCallbackListener<PagedListEntityDto<VolunteerListDto>>() {
//            @Override
//            public void onSuccess(PagedListEntityDto<VolunteerListDto> data) {
//                VolunteerListDto personal_data = data.getRows().get(0);
//                isSpeciality = personal_data.getSpeciality();
//
//                true_name=personal_data.getTrueName();
//                integral=personal_data.getScore().toString();
//
//
//                Message message = new Message();
//                message.what =UPDATE_UI;
//                myHandler.sendMessage(message);
//
//
//            }
//
//            @Override
//            public void onFailure(String errorEvent, String message) {
//                showToast("fail");
//            }
//        });
    }

    private void getVolunteerDate() {
        final String volunteerId = LocalDate.getInstance(getActivity()).getLocalDate("volunteerId", "");
        boolean isLogin = LocalDate.getInstance(getActivity()).getLocalDate("isLogin", false);
        if (isInit) {
            isInit = false;
            if (TextUtils.isEmpty(volunteerId) || !isLogin) {
                //如果用户未登录则跳转到登录界面
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, LOGIN);
                return;
            }
        }


        //获取志愿者的信息
        AppActionImpl.getInstance(getActivity()).get_volunteerDetail(volunteerId,
                new ActionCallbackListener<VolunteerViewDto>() {

                    @Override
                    public void onSuccess(VolunteerViewDto data) {
                        if (data == null) {
                            //如果用户登录失败则跳转到登录界面
                            if (isInit) {
                                isInit = false;
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivityForResult(intent, LOGIN);
                            }
                            return;
                        }
                        getVolunteerPhoto(volunteerId);
                        //EventBus将data传到另一个activity中备用
                        eventbus_data = data;

                        isSpeciality = data.getSpeciality();
                        isShowTrueName = data.getShowTrueName();

                        //头部
                        true_name = data.getTrueName();
                        nick_name = data.getNickName();
                        integral = data.getScore().toString();
                        LevelType = data.getLevelType();
                        if (specialty != null)
                            specialty = data.getSpecialty();

                        //服务时长
                        double schoolTime = data.getSchoolservicetime() == null ? 0 : data.getSchoolservicetime();
                        SchoolServiceTime = schoolTime + "小时";
                        double workTime = data.getWorkservicetime() == null ? 0 : data.getWorkservicetime();
                        WorkServiceTime = workTime + "小时";
                        double retireTime = data.getRetireservicetime() == null ? 0 : data.getRetireservicetime();
                        RetireServiceTime = retireTime + "小时";
                        AllServiceTime = schoolTime + workTime + retireTime + "小时";

                        //非第一次进入时利用handler更新UI
                        if (isCreat) {
                           /* Message message = new Message();
                            message.what = UPDATE_UI;
                            myHandler.sendMessage(message);*/
                            repeat_update();
                        }

                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("网络异常，请检查后重试");
                    }
                });
    }

    private void getVolunteerPhoto(String volunteerId) {
        //获取头像
        AppActionImpl.getInstance(getActivity()).get_portrait(volunteerId,
                new ActionCallbackListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        if (!TextUtils.isEmpty(data)) {
                            my_portrait = Base64.decode(data.getBytes(), Base64.DEFAULT);
                            image_portrait.setImageBitmap(Util.getBitmapFromByte(data));
                        }
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });
    }

    @Override
    public void onDestroy() {
        if (group != null)
            group.removeAllViews();
        isCreat = false;
        super.onDestroy();
    }

    private void showToast(String msg) {
        if (isCreat)
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TO_INFORMATION) {
            Logger.v(TAG, "onActivityResult");
            getVolunteerDate();
        }
    }
}
