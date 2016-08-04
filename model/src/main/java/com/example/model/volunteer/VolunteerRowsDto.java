package com.example.model.volunteer;

/**
 * Created by Administrator on 2016/8/3.
 */
public class VolunteerRowsDto {
    public static final String TAG = "VolunteerRowsDto";


    private String Id;      //(string, optional): 获取或设置 志愿者标识
    private String TrueName;    //(string, optional): 获取或设置 姓名
    private String OrganizationName;    //(string, optional): 获取或设置 所属机构
    private int CardType;   //(integer, optional): 获取或设置 证件类型
    private String AreaName;    //(string, optional): 获取或设置 区域
    private String Addr;    //(string, optional): 获取或设置 地址
    private String Mobile;  //(string, optional): 获取或设置 移动电话
    private int LevelType;  //(integer, optional): 获取或设置 志愿者等级
    private double Score;   //(number, optional): 获取或设置 积分
    private String Domicile;    //(string, optional): 获取或设置 现居地
    private int IsCompleteInfo;     // (integer, optional): 获取或设置 是否完成信息
    private boolean IsSpeciality;   // (boolean, optional): 获取或设置 是否专业志愿者
    private boolean IsCertification;    // (boolean, optional): 获取或设置 是否认证
    private int AuditStatus;     // (integer, optional): 获取或设置 审核状态0未审核 1审核通过 2审核不通过


    @Override
    public String toString() {
        return "VolunteerRowsDto{" +
                "Addr='" + Addr + '\'' +
                ", Id='" + Id + '\'' +
                ", TrueName='" + TrueName + '\'' +
                ", OrganizationName='" + OrganizationName + '\'' +
                ", CardType=" + CardType +
                ", AreaName='" + AreaName + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", LevelType=" + LevelType +
                ", Score=" + Score +
                ", Domicile='" + Domicile + '\'' +
                ", IsCompleteInfo=" + IsCompleteInfo +
                ", IsSpeciality=" + IsSpeciality +
                ", IsCertification=" + IsCertification +
                ", AuditStatus=" + AuditStatus +
                '}';
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public int getAuditStatus() {
        return AuditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        AuditStatus = auditStatus;
    }

    public int getCardType() {
        return CardType;
    }

    public void setCardType(int cardType) {
        CardType = cardType;
    }

    public String getDomicile() {
        return Domicile;
    }

    public void setDomicile(String domicile) {
        Domicile = domicile;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public boolean isCertification() {
        return IsCertification;
    }

    public void setIsCertification(boolean isCertification) {
        IsCertification = isCertification;
    }

    public int getIsCompleteInfo() {
        return IsCompleteInfo;
    }

    public void setIsCompleteInfo(int isCompleteInfo) {
        IsCompleteInfo = isCompleteInfo;
    }

    public boolean isSpeciality() {
        return IsSpeciality;
    }

    public void setIsSpeciality(boolean isSpeciality) {
        IsSpeciality = isSpeciality;
    }

    public int getLevelType() {
        return LevelType;
    }

    public void setLevelType(int levelType) {
        LevelType = levelType;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }

    public String getTrueName() {
        return TrueName;
    }

    public void setTrueName(String trueName) {
        TrueName = trueName;
    }
}
