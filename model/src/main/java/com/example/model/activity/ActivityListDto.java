package com.example.model.activity;

import java.io.Serializable;

/**
 * 项目名称：BaseAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/7/23 15:49
 * 修改备注：
 */
public class ActivityListDto implements Serializable{
   private  String Id;//(string, optional): 获取或设置 活动标识
   private  String ActivityName;//(string, optional): 获取或设置 活动名称
   private  String StartTime;//(string, optional): 获取或设置 活动开始时间
   private  String FinishTime;// (string, optional): 获取或设置 活动结束时间
   private  String Addr;//(string, optional): 获取或设置 地址
   private  String JobText;//(string, optional),
   private  Integer RecruitNumber;// (integer, optional): 获取或设置 招募人数
   private  Integer Recruited;//(integer, optional): 获取或设置 已招募
   private  Integer Status;//(integer, optional): 获取或设置 状态
   private  String StatusName;//(string, optional),
   private  Integer SingleRecruit;//(integer, optional),
   private  String EndTime;// (string, optional),
   private  String LengthTime;// (number, optional),
   private  String DaySTime;//(string, optional),
   private  String DayETime;//(string, optional),
   private  String CompanyId;// (string, optional),
   private  String Lng;//(number, optional),
   private  String Lat;//(number, optional),
   private  Integer Type;//(integer, optional),
   private  String AreaCode;// (string, optional),
   private  String AreaName;//(string, optional),
   private  String CompanyName;//(string, optional)

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
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

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getDayETime() {
        return DayETime;
    }

    public void setDayETime(String dayETime) {
        DayETime = dayETime;
    }

    public String getDaySTime() {
        return DaySTime;
    }

    public void setDaySTime(String daySTime) {
        DaySTime = daySTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getJobText() {
        return JobText;
    }

    public void setJobText(String jobText) {
        JobText = jobText;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLengthTime() {
        return LengthTime;
    }

    public void setLengthTime(String lengthTime) {
        LengthTime = lengthTime;
    }

    public String getLng() {
        return Lng;
    }

    public void setLng(String lng) {
        Lng = lng;
    }

    public int getRecruited() {
        return Recruited;
    }

    public void setRecruited(int recruited) {
        Recruited = recruited;
    }

    public int getRecruitNumber() {
        return RecruitNumber;
    }

    public void setRecruitNumber(int recruitNumber) {
        RecruitNumber = recruitNumber;
    }

    public int getSingleRecruit() {
        return SingleRecruit;
    }

    public void setSingleRecruit(int singleRecruit) {
        SingleRecruit = singleRecruit;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
