package com.example.model.activity;

/**
 * 项目名称：BaseAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/7/23 15:49
 * 修改备注：
 */
public class ActivityRowsBO {
    private static final String TAG = "ActivityRowsBO";

    private String Id;
    private String ActivityName;
    private String StartTime;
    private String FinishTime;
    private String Addr;
    private int RecruitNumber;
    private int Recruited;
    private int Status;

    @Override
    public String toString() {
        return "ActivityRowsBO{" +
                "Id='" + Id + '\'' +
                ", ActivityName='" + ActivityName + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", FinishTime='" + FinishTime + '\'' +
                ", Addr='" + Addr + '\'' +
                ", RecruitNumber=" + RecruitNumber +
                ", Recruited=" + Recruited +
                ", Status=" + Status +
                '}';
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public int getRecruitNumber() {
        return RecruitNumber;
    }

    public void setRecruitNumber(int recruitNumber) {
        RecruitNumber = recruitNumber;
    }

    public int getRecruited() {
        return Recruited;
    }

    public void setRecruited(int recruited) {
        Recruited = recruited;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
