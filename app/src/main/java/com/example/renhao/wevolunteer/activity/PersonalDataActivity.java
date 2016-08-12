package com.example.renhao.wevolunteer.activity;
/*
 *
 * Created by Ge on 2016/8/9  13:50.
 *
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.view.Portrait_Pop;

import java.io.File;

public class PersonalDataActivity extends AppCompatActivity implements View.OnClickListener {

    private Portrait_Pop portrait_pPwindow;
    public static Activity PDactivity;


    private ImageView portrait;
    /* 头像文件名 */
    private static final String IMAGE_FILE_NAME = "nbvolunteer_myportrait.jpg";

    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),80 X 80的正方形。
    private static int output_X = 80;
    private static int output_Y = 80;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        PDactivity = PersonalDataActivity.this;//用于非本activity控制此activity

        SetOnclickListener();

    }

    //给组件添加监听
    private void SetOnclickListener() {

        //find组件
        ImageView top_btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        portrait = (ImageView) findViewById(R.id.imageview_item_myPortrait);
        LinearLayout myPortrait = (LinearLayout) findViewById(R.id.LL_PD_myPortrait);
        LinearLayout myNickName = (LinearLayout) findViewById(R.id.LL_PD_myNickName);
        LinearLayout changePassword = (LinearLayout) findViewById(R.id.LL_PD_changePassword);
        LinearLayout myPhoneNumber = (LinearLayout) findViewById(R.id.LL_PD_myPhoneNumber);
        LinearLayout myUserName = (LinearLayout) findViewById(R.id.LL_PD_myUserName);
        LinearLayout myTrueName = (LinearLayout) findViewById(R.id.LL_PD_myTrueName);
        LinearLayout myIDNumber = (LinearLayout) findViewById(R.id.LL_PD_myIDNumber);
        LinearLayout myAttribute = (LinearLayout) findViewById(R.id.LL_PD_myAttribute);
        LinearLayout myPoliticsStatus = (LinearLayout) findViewById(R.id.LL_PD_myPoliticsStatus);
        LinearLayout myArea = (LinearLayout) findViewById(R.id.LL_PD_myArea);
        LinearLayout myORG = (LinearLayout) findViewById(R.id.LL_PD_myORG);
        LinearLayout myMajor = (LinearLayout) findViewById(R.id.LL_PD_myMajor);
        LinearLayout myNowCompany = (LinearLayout) findViewById(R.id.LL_PD_myNowCompany);
        LinearLayout myHomeAddress = (LinearLayout) findViewById(R.id.LL_PD_myHomeAddress);
        LinearLayout myImageTime = (LinearLayout) findViewById(R.id.LL_PD_myImageTime);
        LinearLayout myImageType = (LinearLayout) findViewById(R.id.LL_PD_myImageType);

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
        switch (tag) {
            case BACK:
                this.finish();
                break;
            case MY_PORTRAIT:
                portrait_pPwindow = new Portrait_Pop(this, itemsOnClick);
                portrait_pPwindow.showAtLocation(this.findViewById(R.id.SV_PD),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                break;
            case MY_NICKNAME:
                intent.setClass(this, NickNameActivity.class);
                startActivity(intent);
                break;
            case CHANGE_PASSWORD:
                intent.setClass(this, SetPasswordActivity.class);
                startActivity(intent);
                break;
            case MY_PHONENUMBER:
                intent.setClass(this, MobilePhoneActivity.class);
                startActivity(intent);
                break;
            case MY_USERNAME:
                break;
            case MY_TRUENAME:
                break;
            case MY_IDNUMBER:
                break;
            case MY_ATTRIBUTE:
                break;
            case MY_POLITICSSTATUS:
                break;
            case MY_AREA:
                intent.setClass(this, AreaSelectionActivity.class);
                startActivity(intent);
                break;
            case MY_ORG:
                break;
            case MY_MAJOR:
                intent.setClass(this, MajorAbilityActivity.class);
                startActivity(intent);
                break;
            case MY_NOWCOMPANY:
                break;
            case MY_HOMEADDRESS:
                intent.setClass(this, ResidenceActivity.class);
                startActivity(intent);
                break;
            case MY_IMAGETIME:
                intent.setClass(this, ServiceTimeActivity.class);
                startActivity(intent);
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
            portrait_pPwindow.dismiss();
            backgroundAlpha(1);
            switch (v.getId()) {
                case R.id.btn_take_photo:
                    choseHeadImageFromCameraCapture();

                    break;
                case R.id.btn_pick_photo:
                    chosePortraitFromGallery();

                    break;
                default:
                    break;
            }


        }

    };

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
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }

        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, intent);
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
            portrait.setImageBitmap(photo);
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    //背景透明度设置
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = PDactivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        PDactivity.getWindow().setAttributes(lp);
    }
}
