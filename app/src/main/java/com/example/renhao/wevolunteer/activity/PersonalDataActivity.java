package com.example.renhao.wevolunteer.activity;
/*
 *
 * Created by Ge on 2016/8/9  13:50.
 *
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.core.AppActionImpl;
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.model.user.UserPhotoDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.utils.Util;
import com.example.renhao.wevolunteer.view.Attribute_Pop;
import com.example.renhao.wevolunteer.view.Portrait_Pop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonalDataActivity extends BaseActivity implements View.OnClickListener {

    private Portrait_Pop portrait_PPwindow;
    private Attribute_Pop attribute_PPwindow;
    private int ppwindow_flag;

    public static Activity PDactivity;
    private VolunteerViewDto Personal_Data;
    private Button exit;
    private TextView tv_myNickName, tv_myPhone, tv_myUserName, tv_myTrueName, tv_myIDNumber, tv_myAttribute;

    //需要更改的值 头像暂无
    private boolean IsShowTrueName;
    private String MyNickNmae;
    private String MyPassWord;
    private String MYMobile;
    private String MyTrueName;
    private String MyIDNumber;
    private Integer MyAttribute_code;
    private String MyPolity;
    private String MyArea;
    private String MySkilled;
    private String MyWorkunit;
    private String MyAddr;
    private String MyServiceTimeIntention;
    private String MyServiceIntention;


    //头像二进制流
    private byte[] send_portrait;

    private ImageView portrait;
    /* 头像文件名 */
    private static final String IMAGE_FILE_NAME = "nbvolunteer_myportrait.jpg";

    // 裁剪后图片的宽(X)和高(Y),100 X 100的正方形。
    private static int output_X = 100;
    private static int output_Y = 100;

    /* 拍照.图库请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;


    //onclick TAG
    private final int BACK = -1;
    private final int MY_PORTRAIT = 0;
    private final int MY_NICKNAME = 1;
    private final int CHANGE_PASSWORD = 2;
    private final int MY_PHONENUMBER = 3;
    private final int MY_USERNAME = 4;
    private final int MY_TRUENAME = 5;
    private final int MY_IDNUMBER = 6;
    private final int MY_ATTRIBUTE = 7;
    private final int MY_POLITICSSTATUS = 8;
    private final int MY_AREA = 9;
    private final int MY_ORG = 10;
    private final int MY_MAJOR = 11;
    private final int MY_NOWCOMPANY = 12;
    private final int MY_HOMEADDRESS = 13;
    private final int MY_IMAGETIME = 14;
    private final int MY_IMAGETYPE = 15;

    private final int UPDATE_UI = 0;

    private ImageView top_btn_back;
    private LinearLayout myPortrait;
    private LinearLayout myNickName;
    private LinearLayout changePassword;
    private LinearLayout myPhoneNumber;
    private LinearLayout myUserName;
    private LinearLayout myTrueName;
    private LinearLayout myIDNumber;
    private LinearLayout myAttribute;
    private LinearLayout myPoliticsStatus;
    private LinearLayout myArea;
    private LinearLayout myORG;
    private LinearLayout myMajor;
    private LinearLayout myNowCompany;
    private LinearLayout myHomeAddress;
    private LinearLayout myImageTime;
    private LinearLayout myImageType;

    private String volunteerId;

    /*Handler更新UI*/
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_UI:
                    repeat_update();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void repeat_update() {

        volunteerId = LocalDate.getInstance(this).getLocalDate("volunteerId", "");

        if (Personal_Data.getNickName() != null) {
            IsShowTrueName = Personal_Data.getShowTrueName();
            MyNickNmae = Personal_Data.getNickName();
            tv_myNickName.setText(MyNickNmae);
        }

        if (Personal_Data.getMobile() != null) {
            MYMobile = Personal_Data.getMobile();
            tv_myPhone.setText(MYMobile);
        }
        //暂无用户名参数

        if (Personal_Data.getTrueName() != null) {
            MyTrueName = Personal_Data.getTrueName();
            tv_myTrueName.setText(MyTrueName);
        }
        if (Personal_Data.getIdNumber() != null) {
            MyIDNumber = Personal_Data.getIdNumber();
            tv_myIDNumber.setText(MyIDNumber);
        }
        if (Personal_Data.getJobStatusStr() != null) {
           /* MyAttribute_code = Personal_Data.getJobStatus();
            if (MyAttribute_code == 0)
                tv_myAttribute.setText("在校");
            if (MyAttribute_code == 1)
                tv_myAttribute.setText("在职");
            if (MyAttribute_code == 2)
                tv_myAttribute.setText("退休");*/
            tv_myAttribute.setText(Personal_Data.getJobStatusStr());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        Intent intent = getIntent();
        Personal_Data = (VolunteerViewDto) intent.getSerializableExtra("data");

        PDactivity = PersonalDataActivity.this;//用于非本activity控制此activity
        //需要更改展示的UI组件
        portrait = (ImageView) findViewById(R.id.imageview_item_myPortrait);
        tv_myNickName = (TextView) findViewById(R.id.tv_PD_myNickName);
        tv_myPhone = (TextView) findViewById(R.id.tv_PD_myPhoneNumber);
        tv_myUserName = (TextView) findViewById(R.id.tv_PD_myUserName);
        tv_myTrueName = (TextView) findViewById(R.id.tv_PD_myTrueName);
        tv_myIDNumber = (TextView) findViewById(R.id.tv_PD_myIDNumber);
        tv_myAttribute = (TextView) findViewById(R.id.tv_PD_myAttribute);

        exit = (Button) findViewById(R.id.btn_personalData_quit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitLogin();
            }
        });

        try {
            portrait.setImageBitmap(getBitmapFromByte(intent.getByteArrayExtra("portrait")));
        } catch (Exception e) {

        }

        initViewsEven();
        //更改显示的内容
        repeat_update();

        if (!Personal_Data.getSpeciality()) {
            myPoliticsStatus.setVisibility(View.GONE);
           // myArea.setVisibility(View.GONE);
           // myORG.setVisibility(View.GONE);
            myMajor.setVisibility(View.GONE);
            myNowCompany.setVisibility(View.GONE);
            myHomeAddress.setVisibility(View.GONE);
           // myImageTime.setVisibility(View.GONE);
            myImageType.setVisibility(View.GONE);
        }
    }

    /**
     * 退出登录
     */
    private void exitLogin() {
        LocalDate.getInstance(getApplicationContext()).setLocalDate("volunteerId", "");
        LocalDate.getInstance(getApplicationContext()).setLocalDate("isLogin", false);
        LocalDate.getInstance(getApplicationContext()).setLocalDate("access_token", "");
        getAccessToken();
        setResult(RESULT_OK);
        finish();
    }


    //给组件添加监听
    private void initViewsEven() {

        //find组件
        top_btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        myPortrait = (LinearLayout) findViewById(R.id.LL_PD_myPortrait);
        myNickName = (LinearLayout) findViewById(R.id.LL_PD_myNickName);
        changePassword = (LinearLayout) findViewById(R.id.LL_PD_changePassword);
        myPhoneNumber = (LinearLayout) findViewById(R.id.LL_PD_myPhoneNumber);
        myUserName = (LinearLayout) findViewById(R.id.LL_PD_myUserName);
        myTrueName = (LinearLayout) findViewById(R.id.LL_PD_myTrueName);
        myIDNumber = (LinearLayout) findViewById(R.id.LL_PD_myIDNumber);
        myAttribute = (LinearLayout) findViewById(R.id.LL_PD_myAttribute);
        myPoliticsStatus = (LinearLayout) findViewById(R.id.LL_PD_myPoliticsStatus);
        myArea = (LinearLayout) findViewById(R.id.LL_PD_myArea);
        myORG = (LinearLayout) findViewById(R.id.LL_PD_myORG);
        myMajor = (LinearLayout) findViewById(R.id.LL_PD_myMajor);
        myNowCompany = (LinearLayout) findViewById(R.id.LL_PD_myNowCompany);
        myHomeAddress = (LinearLayout) findViewById(R.id.LL_PD_myHomeAddress);
        myImageTime = (LinearLayout) findViewById(R.id.LL_PD_myImageTime);
        myImageType = (LinearLayout) findViewById(R.id.LL_PD_myImageType);

        //设置监听
        top_btn_back.setOnClickListener(this);
        myPortrait.setOnClickListener(this);
        myNickName.setOnClickListener(this);
        changePassword.setOnClickListener(this);
        myPhoneNumber.setOnClickListener(this);
        myUserName.setOnClickListener(this);
        myTrueName.setOnClickListener(this);
        myIDNumber.setOnClickListener(this);
        myAttribute.setOnClickListener(this);
        myPoliticsStatus.setOnClickListener(this);
        myArea.setOnClickListener(this);
        myORG.setOnClickListener(this);
        myMajor.setOnClickListener(this);
        myNowCompany.setOnClickListener(this);
        myHomeAddress.setOnClickListener(this);
        myImageTime.setOnClickListener(this);
        myImageType.setOnClickListener(this);

        //添加标签
        top_btn_back.setTag(BACK);
        myPortrait.setTag(MY_PORTRAIT);
        myNickName.setTag(MY_NICKNAME);
        changePassword.setTag(CHANGE_PASSWORD);
        myPhoneNumber.setTag(MY_PHONENUMBER);
        myUserName.setTag(MY_USERNAME);
        myTrueName.setTag(MY_TRUENAME);
        myIDNumber.setTag(MY_IDNUMBER);
        myAttribute.setTag(MY_ATTRIBUTE);
        myPoliticsStatus.setTag(MY_POLITICSSTATUS);
        myArea.setTag(MY_AREA);
        myORG.setTag(MY_ORG);
        myMajor.setTag(MY_MAJOR);
        myNowCompany.setTag(MY_NOWCOMPANY);
        myHomeAddress.setTag(MY_HOMEADDRESS);
        myImageTime.setTag(MY_IMAGETIME);
        myImageType.setTag(MY_IMAGETYPE);
    }


    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        Intent intent = new Intent();
        intent.putExtra("personal_data", Personal_Data);
        switch (tag) {
            case BACK:
                this.finish();
                break;
            case MY_PORTRAIT:
                portrait_PPwindow = new Portrait_Pop(this, itemsOnClick);
                portrait_PPwindow.showAtLocation(this.findViewById(R.id.SV_PD),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                ppwindow_flag = 0;
                break;
            case MY_NICKNAME:
                intent.setClass(this, NickNameActivity.class);
                startActivityForResult(intent, MY_NICKNAME);
                break;
            case CHANGE_PASSWORD:
                intent.putExtra("ID", volunteerId);
                intent.setClass(this, SetPasswordActivity.class);
                startActivity(intent);
                break;
            case MY_PHONENUMBER:
                intent.setClass(this, MobilePhoneActivity.class);
                startActivityForResult(intent, MY_PHONENUMBER);
                break;
            case MY_USERNAME:
                //暂时删除
                break;
            case MY_TRUENAME:
                break;
            case MY_IDNUMBER:
                break;
            case MY_ATTRIBUTE:
                /*attribute_PPwindow = new Attribute_Pop(this, itemsOnClick);
                attribute_PPwindow.showAtLocation(this.findViewById(R.id.SV_PD),
                        Gravity.CENTER, 0, 0); //设置layout在PopupWindow中显示的位置
                ppwindow_flag = 1;*/
                intent.setClass(this, AttributeAtivity.class);
                startActivityForResult(intent, MY_ATTRIBUTE);
                break;
            case MY_POLITICSSTATUS:
                intent.setClass(this, PoliticsstatusActivity.class);
                startActivityForResult(intent, MY_POLITICSSTATUS);
                break;
            case MY_AREA:
                intent.setClass(this, AreaSelectionActivity.class);
                startActivity(intent);
                break;
            case MY_ORG:
                intent.setClass(this, MyORGActivity.class);
                startActivity(intent);
                break;
            case MY_MAJOR:
                intent.setClass(this, SpecilaAbilityActivity.class);
                startActivityForResult(intent, MY_MAJOR);
                break;
            case MY_NOWCOMPANY:
                intent.setClass(this, CompanyActivity.class);
                startActivityForResult(intent, MY_NOWCOMPANY);
                break;
            case MY_HOMEADDRESS:
                intent.setClass(this, ResidenceActivity.class);
                startActivityForResult(intent, MY_HOMEADDRESS);
                break;
            case MY_IMAGETIME:
                intent.setClass(this, ServiceTimeActivity.class);
                startActivityForResult(intent, MY_IMAGETIME);
                break;
            case MY_IMAGETYPE:
                intent.setClass(this, ServiceCategoryActivity.class);
                startActivity(intent);
                break;
        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            //点击以后销毁pop框，将背景恢复
            if (ppwindow_flag == 0) {
                portrait_PPwindow.dismiss();
            }
            if (ppwindow_flag == 1) {
                attribute_PPwindow.dismiss();
            }
            backgroundAlpha(1);

            switch (v.getId()) {
                case R.id.btn_take_photo:
                    choseHeadImageFromCameraCapture();
                    break;
                case R.id.btn_pick_photo:
                    chosePortraitFromGallery();
                    break;
                case R.id.btn_attribute_student:
                    Personal_Data.setJobStatus(0);
                    personal_data_UpDate();
                    break;
                case R.id.btn_attribute_on_job:
                    Personal_Data.setJobStatus(1);
                    personal_data_UpDate();
                    break;
                case R.id.btn_attribute_retire:
                    Personal_Data.setJobStatus(2);
                    personal_data_UpDate();
                    break;
                default:
                    break;
            }


        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //各个页面的resultCode不可为0，此为系统back键的resultCode,代表用户没有进行任何操作
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        Bundle result = new Bundle();
        switch (requestCode) {
            //修改个人资料后更新展示的内容
            case MY_NICKNAME:
                result = data.getExtras();
                Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                SendHandlerMsg(UPDATE_UI);
                break;
            case CHANGE_PASSWORD:
                //无需回调
                break;
            case MY_PHONENUMBER:
                result = data.getExtras();
                Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                SendHandlerMsg(UPDATE_UI);
                break;
            case MY_ATTRIBUTE:
                result = data.getExtras();
                Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                SendHandlerMsg(UPDATE_UI);
                break;
            case MY_POLITICSSTATUS:
                result = data.getExtras();
                Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                break;
            case MY_MAJOR:
                result = data.getExtras();
                Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                break;
            case MY_NOWCOMPANY:
                result = data.getExtras();
                Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                break;
            case MY_HOMEADDRESS:
                result = data.getExtras();
                Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                break;
            case MY_IMAGETIME:
                result = data.getExtras();
                Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                break;

            //照片的返回结果处理
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(data.getData());
                break;
            case CODE_CAMERA_REQUEST:
                if (Util.hasSDcard()) {
                    File tempFile = new File(Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG).show();
                }
                break;
            case CODE_RESULT_REQUEST:
                if (data != null) {
                    setImageToHeadView(data);
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void SendHandlerMsg(int msg) {
        Message message = new Message();
        message.what = msg;
        myHandler.sendMessage(message);
    }


    //从本地相册选择图片作为头像
    private void chosePortraitFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (Util.hasSDcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }

        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }


    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :选择框宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            UpdatePortrait(photo);
        }
    }

    private void UpdatePortrait(final Bitmap bitmap) {

        byte[] send_portrait = getBitmapByte(bitmap);
        UserPhotoDto vl_updates = new UserPhotoDto();
        vl_updates.setUserId(volunteerId);
        vl_updates.setPhoto(Base64.encodeToString(send_portrait, Base64.DEFAULT));

        AppActionImpl.getInstance(this).update_portrait(vl_updates, new ActionCallbackListener<String>() {
            @Override
            public void onSuccess(String data) {
                portrait.setImageBitmap(bitmap);
                repeat_update();
                showToast("修改成功");
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("网络异常，请检查后重试");
            }
        });
    }


    //背景透明度设置
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = PDactivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        PDactivity.getWindow().setAttributes(lp);
    }

    //个人属性更新
    private void personal_data_UpDate() {
        List<VolunteerViewDto> vl_updates = new ArrayList<>();
        vl_updates.add(Personal_Data);
        AppActionImpl.getInstance(this).volunteerUpdate(vl_updates, new ActionCallbackListener<String>() {
            @Override
            public void onSuccess(String data) {
                repeat_update();
                showToast("修改成功");
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("网络异常，请检查后重试");
            }
        });
    }

    //图片转换成二进制流
    public byte[] getBitmapByte(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //参数1转换类型，参数2压缩质量，参数3字节流资源
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    //二进制流转换为图片
    public Bitmap getBitmapFromByte(byte[] temp) {
        if (temp != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        } else {
            return null;
        }
    }
}
