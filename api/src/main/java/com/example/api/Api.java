package com.example.api;

import com.example.model.AccessTokenBO;
import com.example.model.Company.CompanyQueryOptionDto;
import com.example.model.Company.CompanyListDto;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityCreateBO;
import com.example.model.activity.ActivityListDto;
import com.example.model.activity.ActivityQueryOptionDto;
import com.example.model.activity.ActivityViewDto;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.dictionary.DictionaryQueryOptionDto;
import com.example.model.dictionary.DictionaryTypeListDto;
import com.example.model.dictionary.DictionaryTypeQueryOptionDto;
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
 * 类描述：接口类
 * 创建人：renhao
 * 创建时间：2016/8/2 13:42
 * 修改备注：
 */
public interface Api {

    public static final String USER_CREATE = "Nbcei.Framework.Api.Impl/v1/user/create";
    public static final String USER_LOGIN = "Nbcei.Framework.Api.Impl/v1/user/get";

    public static final String ACTIVITY_CREATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activity/create";
    public static final String ACTIVITY_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activity/query";
    public static final String ACTIVITY_DETAIL = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activity/details";

    //志愿者
    public static final String VOLUNTEER_CREATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/create";
    public static final String VOLUNTEER_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/query";
    public static final String VOLUNTEER_DETAIL = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/Volunteer/details";
    public static final String VOLUNTEER_UPDATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/Volunteer/update";

    //
    //组织
    public static final String COMPANY_CREAT = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/create";
    public static final String COMPANY_UPDATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/update";
    public static final String COMPANY_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/query";
    public static final String COMPANY_GET = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/details";

    //字典
    public static final String DICTIONARY_QUERY = "Nbcei.Framework.Api.Impl/v1/dictionary/query ";
    public static final String DICTIONARYTYPE_QUERY = "Nbcei.Framework.Api.Impl/v1/dictionarytype/query";

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

    public ApiResponse<PagedListEntityDto<ActivityListDto>> activityQuery(ActivityQueryOptionDto query, String accessToken);

    /**
     * activity 详细信息查询方法
     *
     * @param activityId
     * @param accessToken
     * @return
     */
    public ApiResponse<ActivityViewDto> activityDetail(String activityId, String accessToken);

    public ApiResponse<List<String>> volunteerCreate(List<VolunteerCreateDto> creates, String accessToken);

    public ApiResponse<VolunteerDto> volunteerQuery(VolunteerQueryDto query, String accessToken);

    public ApiResponse<VolunteerViewDto> volunteerDetail(String id, String accessToken);

    public ApiResponse<List<String>> volunteerEdit(List<VolunteerEditDto> update, String accessToken);

    public ApiResponse<PagedListEntityDto<CompanyListDto>> companyQuery(CompanyQueryOptionDto query, String accessToken);

    public ApiResponse<CompanyListDto> companyGet(String id, String acessToken);

    public ApiResponse<List<String>> companyCreat(List<CompanyListDto> creates, String accessToken);

    public ApiResponse<List<String>> companyUpdate(List<CompanyListDto> update, String accessToken);

    /**
     * @param query
     * @param accessToken
     * @return
     */
    public ApiResponse<PagedListEntityDto<DictionaryListDto>> dictionaryQuery(DictionaryQueryOptionDto query, String accessToken);

    public ApiResponse<PagedListEntityDto<DictionaryTypeListDto>> dictionaryTypeQuery(DictionaryTypeQueryOptionDto query, String accessToken);

}
