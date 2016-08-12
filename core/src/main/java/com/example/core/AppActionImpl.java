package com.example.core;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.example.api.Api;
import com.example.api.ApiImpl;
import com.example.api.ApiResponse;
import com.example.core.listener.AccessTokenListener;
import com.example.core.local.LocalDate;
import com.example.model.AccessTokenBO;
import com.example.model.ActionCallbackListener;
import com.example.model.Company.CompanyListDto;
import com.example.model.Company.CompanyQueryOptionDto;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityCreateBO;
import com.example.model.activity.ActivityListDto;
import com.example.model.activity.ActivityQueryOptionDto;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.dictionary.DictionaryQueryOptionDto;
import com.example.model.user.UserDto;
import com.example.model.user.UserViewDto;
import com.example.model.volunteer.VolunteerCreateDto;
import com.example.model.volunteer.VolunteerDto;
import com.example.model.volunteer.VolunteerEditDto;
import com.example.model.volunteer.VolunteerQueryDto;
import com.example.model.volunteer.VolunteerViewDto;

import java.util.List;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/2 16:47
 * 修改备注：
 */
public class AppActionImpl implements AppAction {
    private static final String TAG = "AppActionImpl";

    private Context context;
    private Api api;

    public AppActionImpl(Context context) {
        this.context = context;
        this.api = new ApiImpl();
    }

    @Override
    public void getAccessToken(final String username, final String password, final AccessTokenListener tokenListener) {
        new AsyncTask<Void, Void, AccessTokenBO>() {
            @Override
            protected AccessTokenBO doInBackground(Void... params) {
                return api.getAccessToken(username, password);
            }

            @Override
            protected void onPostExecute(AccessTokenBO accessTokenBO) {
                if (accessTokenBO != null) {
                    if (!TextUtils.isEmpty(accessTokenBO.getAccess_token())) {
                        saveAccessToken(accessTokenBO);
                    }
                    tokenListener.success(accessTokenBO);
                } else {
                    tokenListener.fail();
                }
            }
        }.execute();
    }

    @Override
    public void userCreate(final List<UserDto> creates, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {

            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.userCreate(creates, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "未知错误");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", "未知错误");
                }
            }
        }.execute();
    }

    @Override
    public void userLogin(final String userName, final String password, final ActionCallbackListener<UserViewDto> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<UserViewDto>>() {

            @Override
            protected ApiResponse<UserViewDto> doInBackground(Void... params) {
                return api.userLogin(userName, password, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<UserViewDto> result) {
                if (result == null) {
                    listener.onFailure("", "未知错误");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", "未知错误");
                }
            }
        }.execute();
    }

    @Override
    public void activityCreate(final List<ActivityCreateBO> activityCreateBOs, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {
            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.activityCreate(activityCreateBOs, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "未知错误");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", "未知错误");
                }
            }
        }.execute();
    }

    @Override
    public void activityQuery(final ActivityQueryOptionDto activityQueryOptionDto, final ActionCallbackListener<PagedListEntityDto> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<PagedListEntityDto<ActivityListDto>>>() {

            @Override
            protected ApiResponse<PagedListEntityDto<ActivityListDto>> doInBackground(Void... params) {
                return api.activityQuery(activityQueryOptionDto, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<PagedListEntityDto<ActivityListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "未知错误");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    //志愿者（新增）
    @Override
    public void volunteerCreate(final List<VolunteerCreateDto> volunteerCreateBOs, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {
            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.volunteerCreate(volunteerCreateBOs, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "未知错误");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    //志愿者（查询）
    @Override
    public void volunteerQuery(final VolunteerQueryDto volunteerQueryBO, final ActionCallbackListener<VolunteerDto> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<VolunteerDto>>() {
            @Override
            protected ApiResponse<VolunteerDto> doInBackground(Void... params) {
                return api.volunteerQuery(volunteerQueryBO, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<VolunteerDto> result) {
                if (result == null) {
                    listener.onFailure("", "未知错误");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    //志愿者（详情信息）
    @Override
    public void volunteerDetail(final String id, final ActionCallbackListener<VolunteerViewDto> listener) {
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<VolunteerViewDto>>() {
            @Override
            protected ApiResponse<VolunteerViewDto> doInBackground(Void... params) {
                return api.volunteerDetail(id, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<VolunteerViewDto> result) {
                if (result == null) {
                    listener.onFailure("", "未知错误");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void volunteerEdit(final List<VolunteerEditDto> volunteerEditDto, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {
            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.volunteerEdit(volunteerEditDto, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "未知错误");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();

    }


    //组织查询POST
    @Override
    public void companyQuery(final CompanyQueryOptionDto companyQueryOptionDto, final ActionCallbackListener<com.example.model.Company.PagedListEntityDto> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<com.example.model.Company.PagedListEntityDto>>() {
            @Override
            protected ApiResponse<com.example.model.Company.PagedListEntityDto> doInBackground(Void... params) {
                return api.companyQuery(companyQueryOptionDto, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<com.example.model.Company.PagedListEntityDto> result) {
                if (result == null) {
                    listener.onFailure("", "未知错误");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void companyGet(final String id, final ActionCallbackListener<CompanyListDto> listener) {
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<CompanyListDto>>() {
            @Override
            protected ApiResponse<CompanyListDto> doInBackground(Void... params) {
                return api.companyGet(id, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<CompanyListDto> result) {
                if (result == null) {
                    listener.onFailure("", "未知错误");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void companyCreat(final List<CompanyListDto> companyListDtos, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {
            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.companyCreat(companyListDtos, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "未知错误");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void companyUpdate(final List<CompanyListDto> companyListDtos, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {
            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.companyUpdate(companyListDtos, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "未知错误");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void dictionaryQuery(final DictionaryQueryOptionDto query, final ActionCallbackListener<PagedListEntityDto<DictionaryListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<PagedListEntityDto<DictionaryListDto>>>() {
            @Override
            protected ApiResponse<PagedListEntityDto<DictionaryListDto>> doInBackground(Void... params) {
                return api.dictionaryQuery(query, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<PagedListEntityDto<DictionaryListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "未知错误");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }


    private void saveAccessToken(AccessTokenBO accessTokenBO) {
        LocalDate.getInstance(context).setLocalDate("access_token", accessTokenBO.getAccess_token());
        LocalDate.getInstance(context).setLocalDate("token_type", accessTokenBO.getToken_type());
        LocalDate.getInstance(context).setLocalDate("expires_in", accessTokenBO.getExpires_in());
        LocalDate.getInstance(context).setLocalDate("client_id", accessTokenBO.getClient_id());
        LocalDate.getInstance(context).setLocalDate("refresh_token", accessTokenBO.getRefresh_token());
        LocalDate.getInstance(context).setLocalDate("userName", accessTokenBO.getUserName());
        LocalDate.getInstance(context).setLocalDate("scope", accessTokenBO.getScope());
        LocalDate.getInstance(context).setLocalDate("issued", accessTokenBO.getIssued());
        LocalDate.getInstance(context).setLocalDate("expires", accessTokenBO.getExpires());
    }
}
