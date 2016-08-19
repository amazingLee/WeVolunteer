package com.example.model.user;

/**
 * 用户登录获取用户数据
 * Created by Administrator on 2016/8/17.
 */
public class UserListDto {
    private static final String TAG = "UserListDto";

    private String Id;// (string, optional): 获取或设置 人员标识 ,
    private String Version;// (integer, optional): 获取或设置 数据版本 ,
    private String UserName;// (string, optional): 获取或设置 用户名 ,
    private String RealName;// (string, optional): 获取或设置 用户姓名 ,
    private String Birthday;// (string, optional): 出生日期 ,
    private String Comment;// (string, optional): 获取或设置 描述 ,
    private String Email;// (string, optional): 获取或设置 邮件 ,
    private Boolean EmailConfirmed;// (boolean, optional): 邮件确认状态 ,
    private String PinYin;// (string, optional): 获取或设置 全拼 ,
    private String ShortPinYin;// (string, optional): 获取或设置 简拼 ,
    private String IdNumber;// (string, optional): 获取或设置 身份证 ,
    private Integer LoginErrorNumber;// (integer, optional): 获取或设置 登录错误次数 ,
    private Boolean IsActive;// (boolean, optional): 获取或设置 是否激活 ,
    private String LastLoginTime;// (string, optional): 获取或设置 上次登录时间 ,
    private String LoginTime;// (string, optional): 获取或设置 登录时间 ,
    private String Mobile;// (string, optional): 获取或设置 手机号码 ,
    private Boolean MobileConfirmed;// (boolean, optional): 手机确认状态 ,
    private Integer Sex;// (integer, optional): 获取或设置 性别 = ['0', '1'],
    private Integer SortIndex;// (integer, optional): 获取或设置 排序 ,
    private Integer UserType;// (integer, optional): 获取或设置 用户类型 ,
    private String OrganizationId;// (string, optional): 获取或设置 所属部门 ,
    private String OrganizationName;// (string, optional): 获取或设置 所属部门 ,
    private String JobId;// (string, optional): 获取或设置 岗位 ,
    private String JobName;// (string, optional): 获取或设置 岗位 ,
    private Boolean IsLocked;// (boolean, optional): 锁定
}
