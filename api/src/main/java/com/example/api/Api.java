package com.example.api;

import com.example.model.AccessTokenBO;
import com.example.model.activity.ActivityBO;
import com.example.model.activity.ActivityCreateBO;
import com.example.model.activity.ActivityQueryBO;
import com.example.model.user.UserDto;
import com.example.model.user.UserViewDto;
import com.example.model.volunteer.VolunteerCreateDto;
import com.example.model.volunteer.VolunteerDto;
import com.example.model.volunteer.VolunteerQueryDto;

import java.util.List;

/**
 * 项目名称：WeVolunteer
 * 类描述：接口类
 * 创建人：renhao
 * 创建时间：2016/8/2 13:42
 * 修改备注：
 */
public interface Api {

    public static final String USER_CREATE="Nbcei.Framework.Api.Impl/v1/user/create";
    public static final String USER_LOGIN = "Nbcei.Framework.Api.Impl/v1/user/get";
    public static final String ACTIVITY_CREATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activity/create";
    public static final String ACTIVITY_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activity/query";

    //志愿者
    public static final String VOLUNTEER_CREATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/create";
    public static final String VOLUNTEER_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/query";
    /**
     * 获取accessToken
     *
     * @param username 用户名
     * @param password 密码MD5加密
     * @return 返回票据
     */
    public AccessTokenBO getAccessToken(String username, String password);

    /**
     * 刷新AccessToken
     *
     * @param refreshToken 刷新票据
     * @return 返回票据
     */
    public AccessTokenBO getAccessToken(String refreshToken);

    public ApiResponse<List<String>> userCreate(List<UserDto> creates, String accessToken);

    public ApiResponse<UserViewDto> userLogin(String userName, String password, String assessToken);

    public ApiResponse<List<String>> activityCreate(List<ActivityCreateBO> creates, String assessToken);

    public ApiResponse<ActivityBO> activityQuery(ActivityQueryBO query, String accessToken);

    public ApiResponse<List<String>> volunteerCreate(List<VolunteerCreateDto> creates, String accessToken);

    public ApiResponse<VolunteerDto> volunteerQuery(VolunteerQueryDto query,String accessToken);

}
