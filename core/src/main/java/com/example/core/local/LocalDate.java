package com.example.core.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 项目名称：BaseAndroid
 * 类描述： 储存或读取一些本地数据
 * 创建人：renhao
 * 创建时间：2016/7/22 14:29
 * 修改备注：
 */
public class LocalDate {
    public static final String PREFERENCE_NAME = "local_date";
    private static Context sContext;
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor editor;

    private LocalDate() {
        mSharedPreferences = sContext.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }

    public static LocalDate getInstance(Context context) {
        sContext = context;
        return LocalDateHolder.instance;
    }

    public static class LocalDateHolder {
        private static LocalDate instance = new LocalDate();
    }

    /**
     * 保存数据
     *
     * @param strName  键
     * @param strValue 键值
     */
    public void setLocalDate(String strName, String strValue) {
        editor = mSharedPreferences.edit();
        editor.putString(strName, strValue);
        editor.apply();
    }

    /**
     * 读取数据
     *
     * @param strName  键
     * @param dftValue 缺省值
     * @return 键值
     */
    public String getLocalDate(String strName, String dftValue) {
        return mSharedPreferences.getString(strName, dftValue);
    }
}
