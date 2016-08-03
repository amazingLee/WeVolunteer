package com.example.core;

import com.example.core.listener.AccessTokenListener;
import com.example.model.ActionCallbackListener;
import com.example.model.activity.ActivityBO;
import com.example.model.activity.ActivityCreateBO;
import com.example.model.activity.ActivityQueryBO;
import com.example.model.user.UserDto;
import com.example.model.user.UserViewDto;

import java.util.List;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/2 16:40
 * 修改备注：
 */
public interface AppAction {
    /**
     * 获取票据
     *
     * @param username 用户名
     * @param password 密码MD5加密
     */
    public void getAccessToken(String username, String password, AccessTokenListener tokenListener);

    public void userCreate(List<UserDto> creates, ActionCallbackListener<List<String>> listener);

    public void userLogin(String userName, String password, ActionCallbackListener<UserViewDto> listener);

    public void activityCreate(List<ActivityCreateBO> activityCreateBOs, ActionCallbackListener<List<String>> listener);

    public void activityQuery(ActivityQueryBO activityQueryBO, ActionCallbackListener<ActivityBO> listener);
}
