package com.example.model.Company;
/*
 *
 * Created by Ge on 2016/8/5.
 *
 */

public class CompanyQueryOptionDto {

    private String UserId;
    private String LikeName;//企业单位
    private String AreaCode;//区域编号
    private String AreaName;//区域名称
    private String AreaId;///区域标识
    private String Addr;//地址
    private String Person;//联系人
    private String Tel;//联系电话
    private String Mobile;//手机
    private int Status;//状态
    private String CreateTime;//创立时间
    private String GetTime;//申请时间
    private String Reject;//驳回说明
    private Number Lng;//经度
    private Number Lat;//纬度
    private String Linker;//法人联系人
    private String LinkerTel;//法人电话
    private String LinkerMobile;//法人手机
    private String AmountSrc;//资金来源
    private String OrgCode;//组织结构代码
    private Number ServiceTime;//服务时长
    private int QuartersNum;//岗位数
    private int ActivityNum;//活动数
    private String RegisterTime;//注册时间
    private int IsAuth;//是否认证
    private int Member;//成员人员
    private String Description;//描述
    private String Listurl;//列表图片
    private int Registration;//登记备案
    private String Institution;//登记备案机构
    private int SortIndex;//排序
    private String OrganizationId;//
    private String OrganizationName;//
    private String ServiceType;//服务类型
    private String ServiceTypeOther;//服务类型(其他)
    private String LanguageType;//语种类型
    private String IsDeleted;//删除状态
    private String KeyWord;//查询关键词
    private int PageIndex;//当前页索引，默认1
    private int PageSize;//每页记录条数，默认20
    private Object Sorts;//

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getLikeName() {
        return LikeName;
    }

    public void setLikeName(String likeName) {
        LikeName = likeName;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getAreaId() {
        return AreaId;
    }

    public void setAreaId(String areaId) {
        AreaId = areaId;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getGetTime() {
        return GetTime;
    }

    public void setGetTime(String getTime) {
        GetTime = getTime;
    }

    public String getReject() {
        return Reject;
    }

    public void setReject(String reject) {
        Reject = reject;
    }

    public Number getLng() {
        return Lng;
    }

    public void setLng(Number lng) {
        Lng = lng;
    }

    public Number getLat() {
        return Lat;
    }

    public void setLat(Number lat) {
        Lat = lat;
    }

    public String getLinker() {
        return Linker;
    }

    public void setLinker(String linker) {
        Linker = linker;
    }

    public String getLinkerTel() {
        return LinkerTel;
    }

    public void setLinkerTel(String linkerTel) {
        LinkerTel = linkerTel;
    }

    public String getLinkerMobile() {
        return LinkerMobile;
    }

    public void setLinkerMobile(String linkerMobile) {
        LinkerMobile = linkerMobile;
    }

    public String getAmountSrc() {
        return AmountSrc;
    }

    public void setAmountSrc(String amountSrc) {
        AmountSrc = amountSrc;
    }

    public String getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(String orgCode) {
        OrgCode = orgCode;
    }

    public Number getServiceTime() {
        return ServiceTime;
    }

    public void setServiceTime(Number serviceTime) {
        ServiceTime = serviceTime;
    }

    public int getQuartersNum() {
        return QuartersNum;
    }

    public void setQuartersNum(int quartersNum) {
        QuartersNum = quartersNum;
    }

    public int getActivityNum() {
        return ActivityNum;
    }

    public void setActivityNum(int activityNum) {
        ActivityNum = activityNum;
    }

    public String getRegisterTime() {
        return RegisterTime;
    }

    public void setRegisterTime(String registerTime) {
        RegisterTime = registerTime;
    }

    public int getIsAuth() {
        return IsAuth;
    }

    public void setIsAuth(int isAuth) {
        IsAuth = isAuth;
    }

    public int getMember() {
        return Member;
    }

    public void setMember(int member) {
        Member = member;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getListurl() {
        return Listurl;
    }

    public void setListurl(String listurl) {
        Listurl = listurl;
    }

    public int getRegistration() {
        return Registration;
    }

    public void setRegistration(int registration) {
        Registration = registration;
    }

    public String getInstitution() {
        return Institution;
    }

    public void setInstitution(String institution) {
        Institution = institution;
    }

    public int getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(int sortIndex) {
        SortIndex = sortIndex;
    }

    public String getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(String organizationId) {
        OrganizationId = organizationId;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getServiceTypeOther() {
        return ServiceTypeOther;
    }

    public void setServiceTypeOther(String serviceTypeOther) {
        ServiceTypeOther = serviceTypeOther;
    }

    public String getLanguageType() {
        return LanguageType;
    }

    public void setLanguageType(String languageType) {
        LanguageType = languageType;
    }

    public String getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        IsDeleted = isDeleted;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public Object getSorts() {
        return Sorts;
    }

    public void setSorts(Object sorts) {
        Sorts = sorts;
    }
}
