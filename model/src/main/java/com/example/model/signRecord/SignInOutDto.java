package com.example.model.signRecord;

/**
 * Created by Administrator on 2016/8/20.
 */
public class SignInOutDto {
    private String ActivityId;// (string, optional): 获取或设置 活动Id ,
    private String ActivityRecruitId;// (string, optional): 获取或设置 报名Id ,
    private String VolunteerId;// (string, optional): 获取或设置 志愿者Id ,
    private String SignTime;// (string, optional): 获取或设置 签到时间 ,
    private Number Lng;// (number, optional): 获取或设置 经度 ,
    private Number Lat;// (number, optional): 获取或设置 纬度 ,
    private Integer Source;// (integer, optional): 获取或设置 来源 0 移动端 1 PC ,
    private String SignIp;// (string, optional): 获取或设置 签到IP ,
    private Boolean IsSignOut;// (boolean, optional): 获取或设置 是否签离 true签离 flase签到 ,
    private String SignCreateTime;// (string, optional): 获取或设置 签到创建时间 ,
    private String SignCreateUserId;// (string, optional): 获取或设置 签到创建用户ID ,
    private String SignCreateUserName;// (string, optional): 获取或设置 签到创建用户名

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getActivityRecruitId() {
        return ActivityRecruitId;
    }

    public void setActivityRecruitId(String activityRecruitId) {
        ActivityRecruitId = activityRecruitId;
    }

    public Boolean getIsSignOut() {
        return IsSignOut;
    }

    public void setIsSignOut(Boolean isSignOut) {
        IsSignOut = isSignOut;
    }

    public Number getLat() {
        return Lat;
    }

    public void setLat(Number lat) {
        Lat = lat;
    }

    public Number getLng() {
        return Lng;
    }

    public void setLng(Number lng) {
        Lng = lng;
    }

    public String getSignCreateTime() {
        return SignCreateTime;
    }

    public void setSignCreateTime(String signCreateTime) {
        SignCreateTime = signCreateTime;
    }

    public String getSignCreateUserId() {
        return SignCreateUserId;
    }

    public void setSignCreateUserId(String signCreateUserId) {
        SignCreateUserId = signCreateUserId;
    }

    public String getSignCreateUserName() {
        return SignCreateUserName;
    }

    public void setSignCreateUserName(String signCreateUserName) {
        SignCreateUserName = signCreateUserName;
    }

    public String getSignIp() {
        return SignIp;
    }

    public void setSignIp(String signIp) {
        SignIp = signIp;
    }

    public String getSignTime() {
        return SignTime;
    }

    public void setSignTime(String signTime) {
        SignTime = signTime;
    }

    public Integer getSource() {
        return Source;
    }

    public void setSource(Integer source) {
        Source = source;
    }

    public String getVolunteerId() {
        return VolunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        VolunteerId = volunteerId;
    }
}
