package com.example.renhao.wevolunteer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/21 23:11
 * 修改备注：
 */
public class Util {
    private static final String TAG = "Util";

    public static final String IMG_URL = "http://115.238.150.174:5018";

    //二进制流转换为图片
    public static Bitmap getBitmapFromByte(byte[] temp) {
        if (temp != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        } else {
            return null;
        }
    }

    //二进制流转换为图片
    public static Bitmap getBitmapFromByte(String img) {
        byte[] temp = Base64.decode(img.getBytes(), Base64.DEFAULT);
        if (temp != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        } else {
            return null;
        }
    }

    /**
     * 获取手机的MAC地址
     *
     * @return
     */
    public static String getMac() {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        Logger.v(TAG, macSerial);
        return macSerial;
    }

    /**
     * 获取当前的时间
     *
     * @return
     */
    public static String getNowDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(new Date());
    }

    /**
     * 获取当前时间
     *
     * @param formatStr 时间日期格式
     * @return
     */
    public static String getNowDate(String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr, Locale.CHINA);
        return format.format(new Date());
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSDcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPhoneNumber(String number) {
        String pattern = "0?(13|14|15|18)[0-9]{9}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(number);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static boolean isEmail(String email) {
        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(email);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static boolean isID(String id) {
        String pattern = "\\d{17}[\\d|x]|\\d{15}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(id);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static String getRealUrl(String url) {
        String temp = "http://11";
        if (url != null)
            if (url.length() > 1)
                temp = IMG_URL + url.substring(1, url.length());
        return temp;
    }

    public static String html2String(String html) {
        html = html.replace("&nbsp;", " ");
        html = html.replace("<br />", "\n");
        return html;
    }
}
