package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.renhao.wevolunteer.R;

/**
 * 申请专业志愿者界面 （属于我的界面里）
 */
public class ApplyProBonoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ApplyProBonoActivity";

    private final int BACK = -1;
    private final int MYPOLITICSSTATUS = 0;
    private final int MYAREA = 1;
    private final int MYORGR = 2;
    private final int MYMAJO = 3;
    private final int MYNOWCOMPANY = 4;
    private final int MYHOMEADDRESS = 5;
    private final int MYIMAGETIME = 6;
    private final int MYIMAGETYPE = 7;
    private final int MYPROFESSIONAL = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_pro_bono);

        setClickListener();

    }

    private void setClickListener() {

        //findViewById
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        LinearLayout myPoliticsStatus = (LinearLayout) findViewById(R.id.LL_apply_politics);
        LinearLayout myArea = (LinearLayout) findViewById(R.id.LL_apply_area);
        LinearLayout myORG = (LinearLayout) findViewById(R.id.LL_apply_ORG);
        LinearLayout myMajor = (LinearLayout) findViewById(R.id.LL_apply_major);
        LinearLayout myNowCompany = (LinearLayout) findViewById(R.id.LL_apply_nowCompany);
        LinearLayout myHomeAddress = (LinearLayout) findViewById(R.id.LL_apply_homeAddressa);
        LinearLayout myImageTime = (LinearLayout) findViewById(R.id.LL_apply_imageTime);
        LinearLayout myImageType = (LinearLayout) findViewById(R.id.LL_apply_imageType);
        LinearLayout myProfessional = (LinearLayout) findViewById(R.id.LL_apply_professional);

        //设置监听器
        btn_back.setOnClickListener(this);
        myPoliticsStatus.setOnClickListener(this);
        myArea.setOnClickListener(this);
        myORG.setOnClickListener(this);
        myMajor.setOnClickListener(this);
        myNowCompany.setOnClickListener(this);
        myHomeAddress.setOnClickListener(this);
        myImageTime.setOnClickListener(this);
        myImageType.setOnClickListener(this);
        myProfessional.setOnClickListener(this);

        //设置标签
        btn_back.setTag(BACK);
        myPoliticsStatus.setTag(MYPOLITICSSTATUS);
        myArea.setTag(MYAREA);
        myORG.setTag(MYORGR);
        myMajor.setTag(MYMAJO);
        myNowCompany.setTag(MYNOWCOMPANY);
        myHomeAddress.setTag(MYHOMEADDRESS);
        myImageTime.setTag(MYIMAGETIME);
        myImageType.setTag(MYIMAGETYPE);
        myProfessional.setTag(MYPROFESSIONAL);


    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        int tag = (int) v.getTag();
        switch (tag) {
            case BACK:
                this.finish();
                break;
            case MYPOLITICSSTATUS:

                break;
            case MYAREA:
                intent.setClass(this,AreaSelectionActivity.class);
                startActivity(intent);
                break;
            case MYORGR:
                intent.setClass(this,MyORGActivity.class);
                startActivity(intent);
                break;
            case MYMAJO:
                intent.setClass(this,MajorAbilityActivity.class);
                startActivity(intent);
                break;
            case MYNOWCOMPANY:


                break;
            case MYHOMEADDRESS:
                intent.setClass(this,ResidenceActivity.class);
                startActivity(intent);
                break;
            case MYIMAGETIME:
                intent.setClass(this,ServiceTimeActivity.class);
                startActivity(intent);
                break;
            case MYIMAGETYPE:
                intent.setClass(this,ServiceCategoryActivity.class);
                startActivity(intent);
                break;
            case MYPROFESSIONAL:
                intent.setClass(this,ProfessionalSelectionActivity.class);
                startActivity(intent);
                break;
        }

    }
}
