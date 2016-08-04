package com.example.api;

import com.example.api.net.HttpEngine;
import com.example.model.AccessTokenBO;
import com.example.model.activity.ActivityBO;
import com.example.model.activity.ActivityCreateBO;
import com.example.model.activity.ActivityQueryBO;
import com.example.model.user.UserDto;
import com.example.model.user.UserViewDto;
import com.example.model.volunteer.VolunteerCreateDto;
import com.example.model.volunteer.VolunteerDto;
import com.example.model.volunteer.VolunteerQueryDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/2 14:44
 * 修改备注：
 */
public class ApiImpl implements Api {
    private static final String TAG = "ApiImpl";

    private HttpEngine httpEngine;

    public ApiImpl() {
        httpEngine = HttpEngine.getInstance();
    }

    @Override
    public AccessTokenBO getAccessToken(String username, String password) {
        try {
            return httpEngine.getAccessToken(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AccessTokenBO getAccessToken(String refreshToken) {
        try {
            return httpEngine.getAccessToken(refreshToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ApiResponse<List<String>> userCreate(List<UserDto> creates, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(creates);

        Type typeOdT = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();

        try {
            return httpEngine.postApiHandler(params, USER_CREATE, typeOdT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<UserViewDto> userLogin(String userName, String password, String assessToken) {

        /*Map<String,String> params=new HashMap<>();
        params.put("password",password);
        params.put("userName",userName);*/

        List<String> params = new ArrayList<>();
        params.add(userName);
        params.add(password);

        Type typeOfT = new TypeToken<ApiResponse<UserViewDto>>() {
        }.getType();

        try {
            return httpEngine.getApiHandler(params, USER_LOGIN, typeOfT, assessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<List<String>> activityCreate(List<ActivityCreateBO> creates, String assessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(creates);
        Type typeOfT = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();
        try {
            return httpEngine.postApiHandler(params, ACTIVITY_CREATE, typeOfT, assessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<ActivityBO> activityQuery(ActivityQueryBO query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<ActivityBO>>() {
        }.getType();
        try {
            return httpEngine.postApiHandler(params, ACTIVITY_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<List<String>> volunteerCreate(List<VolunteerCreateDto> creates, String assessToken){
        Gson gson = new Gson();
        String params = gson.toJson(creates);
        Type typeOfT = new TypeToken<ApiResponse<List<String>>>(){
        }.getType();
        try {
            return httpEngine.postApiHandler(params, VOLUNTEER_CREATE, typeOfT, assessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<VolunteerDto> volunteerQuery(VolunteerQueryDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<VolunteerDto>>(){
        }.getType();
        try {
            return httpEngine.postApiHandler(params, VOLUNTEER_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ApiResponse<>(false,"未知错误");
    }

    @Override
    public ApiResponse<VolunteerViewDto> volunteerDetail(String id, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(id);
        Type typeOft = new TypeToken<ApiResponse<VolunteerViewDto>>(){
        }.getType();
        try {
            return httpEngine.getApiHandler(params, VOLUNTEER_DETAIL, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false,"未知错误");
    }
}
