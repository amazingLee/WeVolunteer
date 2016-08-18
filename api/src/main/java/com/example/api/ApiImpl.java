package com.example.api;

import android.text.TextUtils;

import com.example.api.net.HttpEngine;
import com.example.model.AccessTokenBO;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityCreateBO;
import com.example.model.activity.ActivityListDto;
import com.example.model.activity.ActivityQueryOptionDto;
import com.example.model.activity.ActivityViewDto;
import com.example.model.company.CompanyListDto;
import com.example.model.company.CompanyQueryOptionDto;
import com.example.model.company.CompanyViewDto;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.dictionary.DictionaryQueryOptionDto;
import com.example.model.dictionary.DictionaryTypeListDto;
import com.example.model.dictionary.DictionaryTypeQueryOptionDto;
import com.example.model.dictionary.DictionaryViewDto;
import com.example.model.organization.OrganizationListDto;
import com.example.model.organization.OrganizationQueryOptionDto;
import com.example.model.user.UserDto;
import com.example.model.user.UserViewDto;
import com.example.model.volunteer.VolunteerCreateDto;
import com.example.model.volunteer.VolunteerDto;
import com.example.model.volunteer.VolunteerEditDto;
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
    public ApiResponse<PagedListEntityDto<ActivityListDto>> activityQuery(ActivityQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<PagedListEntityDto<ActivityListDto>>>() {
        }.getType();
        try {
            return httpEngine.postApiHandler(params, ACTIVITY_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<ActivityViewDto> activityDetail(String activityId, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(activityId);
        Type typeOfT = new TypeToken<ApiResponse<ActivityViewDto>>() {
        }.getType();
        try {
            return httpEngine.getApiHandler(params, ACTIVITY_DETAIL, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<List<String>> volunteerCreate(List<VolunteerCreateDto> creates, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(creates);
        Type typeOfT = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();
        try {
            return httpEngine.postApiHandler(params, VOLUNTEER_CREATE, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<VolunteerDto> volunteerQuery(VolunteerQueryDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<VolunteerDto>>() {
        }.getType();
        try {
            return httpEngine.postApiHandler(params, VOLUNTEER_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<VolunteerViewDto> volunteerDetail(String id, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(id);
        Type typeOft = new TypeToken<ApiResponse<VolunteerViewDto>>() {
        }.getType();
        try {
            return httpEngine.getApiHandler(params, VOLUNTEER_DETAIL, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<List<String>> volunteerEdit(List<VolunteerEditDto> update, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(update);
        Type typeOfT = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();
        try {
            return httpEngine.postApiHandler(params, VOLUNTEER_UPDATE, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<PagedListEntityDto<CompanyListDto>> companyQuery(CompanyQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<PagedListEntityDto<CompanyListDto>>>() {
        }.getType();
        try {
            return httpEngine.postApiHandler(params, COMPANY_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<CompanyViewDto> companyDetail(String id, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(id);
        Type typeOft = new TypeToken<ApiResponse<CompanyViewDto>>() {
        }.getType();
        try {
            return httpEngine.getApiHandler(params, COMPANY_DETAIL, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<List<String>> companyCreat(List<CompanyListDto> creates, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(creates);
        Type typeOfT = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();
        try {
            return httpEngine.postApiHandler(params, COMPANY_CREAT, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<List<String>> companyUpdate(List<CompanyListDto> update, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(update);
        Type typeOfT = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();
        try {
            return httpEngine.postApiHandler(params, COMPANY_UPDATE, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<PagedListEntityDto<DictionaryListDto>> dictionaryQuery(DictionaryQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<PagedListEntityDto<DictionaryListDto>>>() {
        }.getType();
        try {
            return httpEngine.postApiHandler(params, DICTIONARY_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<PagedListEntityDto<DictionaryTypeListDto>> dictionaryTypeQuery(DictionaryTypeQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<PagedListEntityDto<DictionaryTypeListDto>>>() {
        }.getType();
        try {
            return httpEngine.postApiHandler(params, DICTIONARYTYPE_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<List<DictionaryListDto>> dictionaryQueryDefault(String typeCode, String parentId, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(typeCode);
        if (TextUtils.isEmpty(parentId)) {
            params.add("00000000-0000-0000-0000-000000000000");
        } else {
            params.add(parentId);
        }
        Type typeOft = new TypeToken<ApiResponse<List<DictionaryListDto>>>() {
        }.getType();
        try {
            return httpEngine.getApiHandler(params, DICTIONARYTYPE_QUERY_DEFAULT, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<DictionaryViewDto> dictionaryQueryDetailDefault(String typeCode, String code, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(typeCode);
        params.add(code);
        Type typeOft = new TypeToken<ApiResponse<DictionaryViewDto>>() {
        }.getType();
        try {
            return httpEngine.getApiHandler(params, DICTIONARYTYPE_QUERY_DETAIL_DEFAULT, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }

    @Override
    public ApiResponse<PagedListEntityDto<OrganizationListDto>> organizationQuery(OrganizationQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<PagedListEntityDto<OrganizationListDto>>>() {
        }.getType();
        try {
            return httpEngine.postApiHandler(params, ORGANIZATION_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "未知错误");
    }
}
