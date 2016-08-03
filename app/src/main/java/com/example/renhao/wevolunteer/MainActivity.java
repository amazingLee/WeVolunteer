package com.example.renhao.wevolunteer;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.core.AppAction;
import com.example.core.AppActionImpl;
import com.example.core.listener.AccessTokenListener;
import com.example.model.AccessTokenBO;
import com.example.model.ActionCallbackListener;
import com.example.model.activity.ActivityBO;
import com.example.model.activity.ActivityCreateBO;
import com.example.model.activity.ActivityQueryBO;
import com.example.model.user.UserDto;
import com.example.model.user.UserViewDto;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {
    public static final String TAG = "MainActivity";

    private List<String> actions;

    private AppAction mAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAction = new AppActionImpl(this);

        String[] s = new String[]{
                "获取应用接口AccessToken",
                "获取用户接口AccessToken",
                "activity query",
                "activity create",
                "user login",
                "user create"
        };
        actions = Arrays.asList(s);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, android.R.id.text1, actions);
        setListAdapter(arrayAdapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                mAction.getAccessToken(null, null, new AccessTokenListener() {
                    @Override
                    public void success(AccessTokenBO accessTokenBO) {
                        showToast("success");
                    }

                    @Override
                    public void fail() {
                        showToast("fail");
                    }
                });
                break;
            case 1:
                mAction.getAccessToken("AndroidUser", "8NDVQX", new AccessTokenListener() {
                    @Override
                    public void success(AccessTokenBO accessTokenBO) {
                        showToast("success");
                    }

                    @Override
                    public void fail() {
                        showToast("fail");
                    }
                });
                break;
            case 2:
                ActivityQueryBO query = new ActivityQueryBO();
                query.setType(0);
                query.setStartTime("2016-08-01T05:50:43.562Z");
                query.setPageIndex(0);
                query.setPageSize(5);
                Logger.v(TAG, new Gson().toJson(query));
                mAction.activityQuery(query, new ActionCallbackListener<ActivityBO>() {
                    @Override
                    public void onSuccess(ActivityBO data) {
                        showToast("success");
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("fail");
                    }
                });
                break;
            case 3:
                ActivityCreateBO create = new ActivityCreateBO();
                create.setActivityName("关爱自闭症儿童");
                create.setStartTime("2016-08-02T05:50:43.555Z");
                create.setDaySTime("7:00-8:00");
                create.setDayETime("14:00-16:00");
                create.setLengthTime(100);
                create.setBeginTime("2016-08-02T05:50:43.556Z");
                create.setEndTimeChoice(0);
                create.setScore(10);
                create.setAreaId("1");
                create.setAreaName("浙江万里");
                create.setAddr("浙江万里学院");
                create.setPersonNum(10);
                create.setJoinNum(10);
                create.setRecruitNumber(10);
                create.setStatus(0);
                Logger.v(TAG, new Gson().toJson(create));
                List<ActivityCreateBO> creats = new ArrayList<>();
                creats.add(create);
                mAction.activityCreate(creats, new ActionCallbackListener<List<String>>() {
                    @Override
                    public void onSuccess(List<String> data) {
                        showToast("success");
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("fail");
                    }
                });
                break;
            case 4:
                mAction.userLogin("AndroidUser", "8NDVQX", new ActionCallbackListener<UserViewDto>() {
                    @Override
                    public void onSuccess(UserViewDto data) {
                        showToast("success");
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("fail");
                    }
                });
                break;
            case 5:
                UserDto userDto = new UserDto();
                userDto.setId("1");
                userDto.setVersion(1);
                userDto.setUserName("li");
                userDto.setRealName("li");
                userDto.setSex(0);
                userDto.setUserType(1);
                userDto.setOrganizationId("1");

                List<UserDto> userDtos = new ArrayList<>();
                userDtos.add(userDto);
                mAction.userCreate(userDtos, new ActionCallbackListener<List<String>>() {
                    @Override
                    public void onSuccess(List<String> data) {
                        showToast("success");
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("fail");
                    }
                });
                break;
            case 6:

                break;
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
