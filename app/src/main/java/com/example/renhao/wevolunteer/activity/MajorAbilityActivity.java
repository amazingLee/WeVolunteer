package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.Attachment.AttachmentParaDto;
import com.example.model.Attachment.AttachmentsReturnDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.adapter.List_major_pic_Adapter;
import com.example.renhao.wevolunteer.base.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * 专业能力界面
 */
public class MajorAbilityActivity extends BaseActivity {

    private TextView tv_submit;
    private EditText edit_major;
    private LinearLayout Choose_pic;

    private VolunteerViewDto personal_data;
    private String my_major;

    private Bitmap bitmap;
    private List_major_pic_Adapter list_major_pic_Adapter;
    private ListView list_show;
    private List<String> show_pic;
    private static int REQUEST_IMAGE = 1;
    private static int CODE_DELETE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_ability);

        final Intent intent = getIntent();
        personal_data = (VolunteerViewDto) intent.getSerializableExtra("personal_data");
        edit_major = (EditText) findViewById(R.id.edit_major);
        if (personal_data.getSkilled() != null) {
            my_major = personal_data.getSkilled();
            edit_major.setText(my_major);
        }
        Choose_pic = (LinearLayout) findViewById(R.id.LL_major_CertificatePic);
        Choose_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MajorAbilityActivity.this, MultiImageSelectorActivity.class);
                // 是否显示调用相机拍照
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                // 最大图片选择数量
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
                // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });

        list_show = (ListView) findViewById(R.id.lv_major_ability_pic);
        list_major_pic_Adapter = new List_major_pic_Adapter(this);
        list_show.setAdapter(list_major_pic_Adapter);
        list_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MajorAbilityActivity.this, Delete_certificate.class);
                intent.putExtra("path", show_pic.get(position));
                intent.putExtra("position", position);
                startActivityForResult(intent, CODE_DELETE);
            }
        });

        show_pic = new ArrayList<>();
        tv_submit = (TextView) findViewById(R.id.tv_major_submit);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_major = edit_major.getText().toString();
                List<VolunteerViewDto> vl_updates = new ArrayList<>();
                personal_data.setSkilled(my_major);
                vl_updates.add(personal_data);
                AppActionImpl.getInstance(getApplicationContext()).volunteerUpdate(vl_updates, new ActionCallbackListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Intent result = new Intent();
                        result.putExtra("personal_data", personal_data);
                        setResult(RESULT_OK, result);
                        //附件上传
                        AttachmentParaDto attachment = new AttachmentParaDto();
                        List<AttachmentParaDto> files = new ArrayList<>();
                        //获取当前事件作为文件名
                        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
                        String date = sDateFormat.format(new java.util.Date());
                        for (int i = 0; i < show_pic.size(); i++) {
                            //压缩图片
                            bitmap = decodeSampledBitmapFromFile(show_pic.get(i), 560, 320);
                            byte[] temp = getBitmapByte(bitmap);
                            attachment.setFileData(Base64.encodeToString(temp, Base64.DEFAULT));
                            attachment.setFileName("Major" + i + date);
                            attachment.setMaxSize("10");
                            attachment.setIsPublic("1");
                            attachment.setPcWH("560|320");
                            attachment.setAppWH("280|160");
                            files.add(attachment);
                        }

                        AppActionImpl.getInstance(getApplicationContext()).update_major_attachment(files,
                                new ActionCallbackListener<AttachmentsReturnDto>() {
                                    @Override
                                    public void onSuccess(AttachmentsReturnDto data) {
                                        showToast("全部上传成功");
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(String errorEvent, String message) {
                                        showToast("证书上传失败");
                                    }
                                });

                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("网络异常，请检查后重试");
                    }
                });
            }
        });


        //回退按钮
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MajorAbilityActivity.this.finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理图片
                for (int i = 0; i < path.size(); i++) {
                    show_pic.add(path.get(i));
                }
                list_major_pic_Adapter.getpic(show_pic);
                list_major_pic_Adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == CODE_DELETE) {
            int position = data.getIntExtra("position", -1);
            show_pic.remove(position);
            list_major_pic_Adapter.notifyDataSetChanged();
        }
    }


    //压缩图片
    //通过目标要求的宽和高，来计算出inSampleSize的代码
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    //先要通过设置inJustDecodeBounds为true，解析一次，获取到options的图片原始宽高。
    // 然后计算出inSampleSize之后，把inJustDecodeBounds置为false，再解析一次
    public static Bitmap decodeSampledBitmapFromFile(String path,
                                                     int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
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


}
