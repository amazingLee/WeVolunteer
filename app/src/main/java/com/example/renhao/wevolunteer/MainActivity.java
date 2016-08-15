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
import com.example.model.Company.CompanyListDto;
import com.example.model.Company.CompanyQueryOptionDto;
import com.example.model.activity.ActivityCreateBO;
import com.example.model.activity.ActivityQueryOptionDto;
import com.example.model.user.UserDto;
import com.example.model.user.UserViewDto;
import com.example.model.volunteer.VolunteerCreateDto;
import com.example.model.volunteer.VolunteerDto;
import com.example.model.volunteer.VolunteerEditDto;
import com.example.model.volunteer.VolunteerQueryDto;
import com.example.model.volunteer.VolunteerViewDto;
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
                "获取应用接口AccessToken",//0
                "获取用户接口AccessToken",//1
                "activity query",//2
                "activity create",//3
                "user login",//4
                "user create",//5
                "volunteer create",//6
                "volunteer query",//7
                "volunteer detail",//8
                "volunteer update",//9
                "Company query",//10
                "Company details get",//11
                "Company Creat",//12
                "Company update",//13
                "Company nextsortindex"//14获取下一个排序
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
                ActivityQueryOptionDto query = new ActivityQueryOptionDto();
                query.setType(0);
                query.setStartTime("2016-08-01T05:50:43.562Z");
                query.setPageIndex(0);
                query.setPageSize(5);
                Logger.v(TAG, new Gson().toJson(query));
               /* mAction.activityQuery(query, new ActionCallbackListener<PagedListEntityDto>() {
                    @Override
                    public void onSuccess(PagedListEntityDto data) {
                        showToast("success");
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("fail");
                    }
                });*/
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
                        showToast("ff");
                    }
                });
                break;
            case 6:
                VolunteerCreateDto vl_create = new VolunteerCreateDto();
                vl_create.setLoginUserName("AndroidUser");
                vl_create.setReUserPassword("8NDVQX");
                vl_create.setAreaCode("鄞州区");
                vl_create.setTrueName("刘欣");
                vl_create.setUserPassword("8NDVQX");
                vl_create.setIdNumber("332502199407143994");
                vl_create.setMobile("15728006854");
                vl_create.setOrgId("浙江万里");
                Logger.v(TAG, new Gson().toJson(vl_create));
                List<VolunteerCreateDto> vl_creates = new ArrayList<>();
                vl_creates.add(vl_create);
                mAction.volunteerCreate(vl_creates, new ActionCallbackListener<List<String>>() {
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
            case 7:
                VolunteerQueryDto vl_query = new VolunteerQueryDto();
                vl_query.setPageIndex(0);
                vl_query.setPageSize(3);
                Logger.v(TAG, new Gson().toJson(vl_query));
                mAction.volunteerQuery(vl_query, new ActionCallbackListener<VolunteerDto>() {
                    @Override
                    public void onSuccess(VolunteerDto data) {
                        showToast("success");
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("fail");
                    }
                });
                break;
            case 8:
                mAction.volunteerDetail("4d8583ef-c5fe-4782-b808-9f34defc2383", new ActionCallbackListener<VolunteerViewDto>() {

                    @Override
                    public void onSuccess(VolunteerViewDto data) {
                        showToast("success");
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("fail");
                    }
                });
                break;

            case 9:
                VolunteerEditDto vl_update = new VolunteerEditDto();
                vl_update.setId("4d8583ef-c5fe-4782-b808-9f34defc2383");
                vl_update.setAreaCode("鄞州");//区域必选
                vl_update.setTrueName("刘欣");//姓名必填！
                vl_update.setOrganizationId("浙江万里");//所属机构必选
                vl_update.setIdNumber("332502199407143994");//证件号码必填
                vl_update.setMobile("15728006854");//移动电话必填
                List<VolunteerEditDto> vl_updates = new ArrayList<>();
                vl_updates.add(vl_update);
                mAction.volunteerEdit(vl_updates, new ActionCallbackListener<List<String>>() {
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


            case 10:
                CompanyQueryOptionDto Company_query = new CompanyQueryOptionDto();
                Company_query.setPageIndex(0);
                Company_query.setPageSize(3);
                Company_query.setIsAuth(0);//查询不到已经通过审核的组织
                Logger.v(TAG, new Gson().toJson(Company_query));
               /* mAction.companyQuery(Company_query, new ActionCallbackListener<com.example.model.Company.PagedListEntityDto>() {

                    @Override
                    public void onSuccess(com.example.model.Company.PagedListEntityDto data) {
                        showToast("success");
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("fail");
                    }
                });*/
                break;
            case 11:
                mAction.companyGet("cf17245e-e6a5-4f97-9d33-c55ce89ba9e3", new ActionCallbackListener<CompanyListDto>() {
                    @Override
                    public void onSuccess(CompanyListDto data) {
                        showToast("success");
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("fail");
                    }
                });
                break;
            case 12:
                CompanyListDto cp_create = new CompanyListDto();

                cp_create.setUserName("AndroidUser");

                Logger.v(TAG, new Gson().toJson(cp_create));
                List<CompanyListDto> cp_creates = new ArrayList<>();
                cp_creates.add(cp_create);
                mAction.companyCreat(cp_creates, new ActionCallbackListener<List<String>>() {
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
            case 13:
                CompanyListDto cp_update = new CompanyListDto();

                List<CompanyListDto> cp_updates = new ArrayList<>();
                cp_updates.add(cp_update);
                mAction.companyUpdate(cp_updates, new ActionCallbackListener<List<String>>() {
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


        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
