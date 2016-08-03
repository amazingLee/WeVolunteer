package com.example.api;

import java.util.List;

/**
 * 项目名称：BaseAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/7/19 11:24
 * 修改备注：
 */
public class ApiResponse<T> {
    private static final String TAG = "ApiResponse";

    private T Date;
    private boolean Success;
    private String Message;
    private List<String> Info;

    public ApiResponse(boolean Success, String Message) {
        this.Success = Success;
        this.Message = Message;
    }

    public T getDate() {
        return Date;
    }

    public void setDate(T date) {
        Date = date;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<String> getInfo() {
        return Info;
    }

    public void setInfo(List<String> info) {
        Info = info;
    }
}
