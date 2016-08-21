package com.example.core;

import com.example.core.listener.AccessTokenListener;
import com.example.model.ActionCallbackListener;
import com.example.model.area.AreaListDto;
import com.example.model.company.CompanyListDto;
import com.example.model.company.CompanyQueryOptionDto;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityCreateBO;
import com.example.model.activity.ActivityListDto;
import com.example.model.activity.ActivityQueryOptionDto;
import com.example.model.activity.ActivityViewDto;
import com.example.model.company.CompanyViewDto;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.dictionary.DictionaryQueryOptionDto;
import com.example.model.dictionary.DictionaryTypeListDto;
import com.example.model.dictionary.DictionaryTypeQueryOptionDto;
import com.example.model.dictionary.DictionaryViewDto;
import com.example.model.organization.OrganizationListDto;
import com.example.model.organization.OrganizationQueryOptionDto;
import com.example.model.signRecord.SignInOutDto;
import com.example.model.user.UserDto;
import com.example.model.user.UserListDto;
import com.example.model.user.UserViewDto;
import com.example.model.volunteer.VolunteerBaseListDto;
import com.example.model.volunteer.VolunteerBaseQueryOptionDto;
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

    public void activityQuery(ActivityQueryOptionDto activityQueryOptionDto, ActionCallbackListener<PagedListEntityDto<ActivityListDto>> listener);

    //用户登录利用票据获取到的username，来获取用户的信息
    public void userNameLogin(String username,ActionCallbackListener<UserListDto> listener);

    /**
     * activity 详细查询方法
     *
     * @param activityId
     * @param listener
     */
    public void activityDetail(String activityId, ActionCallbackListener<ActivityViewDto> listener);

    //志愿者（新增）
    public void volunteerCreate(List<VolunteerCreateDto> volunteerCreateBOs, ActionCallbackListener<List<String>> listener);

    //志愿者（查询）
    public void volunteerQuery(VolunteerQueryDto volunteerQueryBO, ActionCallbackListener<VolunteerDto> listener);

    //志愿者详情信息
    public void volunteerDetail(String id, ActionCallbackListener<VolunteerViewDto> listener);

    //志愿者（修改）
    public void volunteerEdit(List<VolunteerEditDto> volunteerEditDto, ActionCallbackListener<List<String>> listener);

    //company POST 查询
    public void companyQuery(CompanyQueryOptionDto companyQueryOptionDto, ActionCallbackListener<PagedListEntityDto<CompanyListDto>> listener);

    //company get 查询
    public void companyDetail(String id, ActionCallbackListener<CompanyViewDto> listener);

    //company post创建
    public void companyCreat(List<CompanyListDto> companyListDtos, ActionCallbackListener<List<String>> listener);

    //company 更新
    public void companyUpdate(List<CompanyListDto> companyListDtos, ActionCallbackListener<List<String>> listener);


    /**
     * dictionary 查询
     *
     * @param query
     * @param listener
     */
    public void dictionaryQuery(DictionaryQueryOptionDto query, ActionCallbackListener<PagedListEntityDto<DictionaryListDto>> listener);

    public void dictionaryQueryDefault(String typeCode, String parentId, ActionCallbackListener<List<DictionaryListDto>> listener);

    public void dictionaryQueryDetailDefault(String typeCode, String code, ActionCallbackListener<DictionaryViewDto> listener);


    /**
     * dictionary 类型 查询
     *
     * @param query
     * @param listener
     */
    public void dictionaryTypeQuery(DictionaryTypeQueryOptionDto query, ActionCallbackListener<PagedListEntityDto<DictionaryTypeListDto>> listener);

    /**
     * 所属机构查询
     *
     * @param query
     * @param listener
     */
    public void organizationQuery(OrganizationQueryOptionDto query, ActionCallbackListener<PagedListEntityDto<OrganizationListDto>> listener);


    public void organizationQueryChild(String parentId,ActionCallbackListener<List<OrganizationListDto>> listener);

    /**
     * 所在区域  get
     *一级一级展开查询
     * @param parentId
     * @param listener
     */
    public void AreaQuery(String parentId,ActionCallbackListener<List<AreaListDto>> listener);

    /**
     * 志愿者服务站点
     */
    public void volunteerBaseQuery(VolunteerBaseQueryOptionDto query,ActionCallbackListener<PagedListEntityDto<VolunteerBaseListDto>> listener);

    /**
     * 签到签退明细
     */
    public void signRecordCreate(List<SignInOutDto> signInOutDtos,ActionCallbackListener<List<String>> listener);

}
