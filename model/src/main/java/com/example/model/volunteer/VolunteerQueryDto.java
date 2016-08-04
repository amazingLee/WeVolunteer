package com.example.model.volunteer;

/**
 * Created by Administrator on 2016/8/3.
 */
public class VolunteerQueryDto {
    public static final String TAG = "VolunteerQueryDto";


    private String UserId;      // (string, optional): 获取或设置 系统用户表IdUserId
    private String TrueName;    // (string, optional): 获取或设置 姓名
    private String OrgId;       // (string, optional): 获取或设置 所属机构Id
    private int CardType;       // (integer, optional): 获取或设置 证件类型
    private String IdNumber;    // (string, optional): 获取或设置 证件号码
    private String Nation;      // (string, optional): 获取或设置 国藉
    private String Volk;        // (string, optional): 获取或设置 民族
    private String Polity;      // (string, optional): 获取或设置 政治面貌
    private String Academy;     // (string, optional): 获取或设置 毕业院校
    private String SpecialtyType;   // (string, optional): 获取或设置 专业类型
    private String Specialty;   // (string, optional): 获取或设置 专业
    private String Degree;      // (string, optional): 获取或设置 学历
    private String Blood;       // (string, optional): 获取或设置 血型
    private String Origin;      // (string, optional): 获取或设置 籍贯
    private String AreaCode;    // (string, optional): 获取或设置 区域Code
    private String Addr;        // (string, optional): 获取或设置 地址
    private String PostCode;    // (string, optional): 获取或设置 邮政编码
    private String Telephone;   // (string, optional): 获取或设置 固定电话
    private String Mobile;      // (string, optional): 获取或设置 移动电话
    private String Email;       // (string, optional): 获取或设置 电子邮箱
    private int JobStatus;      // (integer, optional): 获取或设置 从业状态
    private double WorkTime;    // (number, optional): 获取或设置 工作年限
    private int Grade;      // (integer, optional): 获取或设置 职称
    private String Job;     // (string, optional): 获取或设置 职务
    private double ServiceTime; // (number, optional): 获取或设置 服务时间(小时)
    private double TrainTime;   // (number, optional): 获取或设置 培训时间
    private String Skilled;     // (string, optional): 获取或设置 特长
    private int LevelType;      // (integer, optional): 获取或设置 志愿者等级
    private double Score;       // (number, optional): 获取或设置 积分
    private int ChronographyType;   // (integer, optional): 获取或设置 计时类型
    private String ChronographyId;  // (string, optional): 获取或设置 计时工具编号
    private String Profession;      // (string, optional): 获取或设置 职业
    private double Height;          // (number, optional): 获取或设置 身高
    private String Workunit;        // (string, optional): 获取或设置 工作单位
    private double Historytime;     // (number, optional): 获取或设置 历时时数
    private double Credit;          // (number, optional): 获取或设置 征信
    private String Domicile;        // (string, optional): 获取或设置 现居地
    private int IsCompleteInfo;     // (integer, optional): 获取或设置 是否完成信息
    private int ZhLevel;        // (integer, optional): 获取或设置 中文水平
    private int EnLevel;        // (integer, optional): 获取或设置 外语水平
    private String UnitProperty;    // (string, optional): 获取或设置 单位性质
    private int SrcScore;       // (integer, optional): 获取或设置 原积分
    private double Workservicetime;     // (number, optional): 获取或设置 在职服务时长
    private double Schoolservicetime;   // (number, optional): 获取或设置 在校服务时长
    private double Retireservicetime;   // (number, optional): 获取或设置 退休服务时长
    private boolean IsSpeciality;       // (boolean, optional): 获取或设置 是否专业志愿者
    private boolean IsCertification;    // (boolean, optional): 获取或设置 是否认证
    private String CertificationTimeBeg;    // (string, optional): 获取或设置 认证时间开始
    private String CertificationTimeEnd;    // (string, optional): 获取或设置 认证时间结束
    private String LanguageType;        // (string, optional): 获取或设置 语种
    private String WeiXinOPENID;        // (string, optional): 获取或设置 微信ID
    private int AuditStatus;            // (integer, optional): 获取或设置 审核状态0未审核 1审核通过 2审核不通过
    private String AuditUserId;         // (string, optional): 获取或设置 审核人ID
    private String KeyWord;             // (string, optional): 查询关键词
    private int PageIndex;              // (integer, optional): 当前页索引，默认1
    private int PageSize;               // (integer, optional): 每页记录条数，默认20
    private Object Sorts;               // (object, optional)


    public String getAcademy() {
        return Academy;
    }

    public void setAcademy(String academy) {
        Academy = academy;
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

    public int getAuditStatus() {
        return AuditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        AuditStatus = auditStatus;
    }

    public String getAuditUserId() {
        return AuditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        AuditUserId = auditUserId;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }

    public int getCardType() {
        return CardType;
    }

    public void setCardType(int cardType) {
        CardType = cardType;
    }

    public String getCertificationTimeBeg() {
        return CertificationTimeBeg;
    }

    public void setCertificationTimeBeg(String certificationTimeBeg) {
        CertificationTimeBeg = certificationTimeBeg;
    }

    public String getCertificationTimeEnd() {
        return CertificationTimeEnd;
    }

    public void setCertificationTimeEnd(String certificationTimeEnd) {
        CertificationTimeEnd = certificationTimeEnd;
    }

    public String getChronographyId() {
        return ChronographyId;
    }

    public void setChronographyId(String chronographyId) {
        ChronographyId = chronographyId;
    }

    public int getChronographyType() {
        return ChronographyType;
    }

    public void setChronographyType(int chronographyType) {
        ChronographyType = chronographyType;
    }

    public double getCredit() {
        return Credit;
    }

    public void setCredit(double credit) {
        Credit = credit;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getDomicile() {
        return Domicile;
    }

    public void setDomicile(String domicile) {
        Domicile = domicile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getEnLevel() {
        return EnLevel;
    }

    public void setEnLevel(int enLevel) {
        EnLevel = enLevel;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public double getHistorytime() {
        return Historytime;
    }

    public void setHistorytime(double historytime) {
        Historytime = historytime;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
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

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public int getJobStatus() {
        return JobStatus;
    }

    public void setJobStatus(int jobStatus) {
        JobStatus = jobStatus;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getLanguageType() {
        return LanguageType;
    }

    public void setLanguageType(String languageType) {
        LanguageType = languageType;
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

    public String getNation() {
        return Nation;
    }

    public void setNation(String nation) {
        Nation = nation;
    }

    public String getOrgId() {
        return OrgId;
    }

    public void setOrgId(String orgId) {
        OrgId = orgId;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
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

    public String getPolity() {
        return Polity;
    }

    public void setPolity(String polity) {
        Polity = polity;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public double getRetireservicetime() {
        return Retireservicetime;
    }

    public void setRetireservicetime(double retireservicetime) {
        Retireservicetime = retireservicetime;
    }

    public double getSchoolservicetime() {
        return Schoolservicetime;
    }

    public void setSchoolservicetime(double schoolservicetime) {
        Schoolservicetime = schoolservicetime;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }

    public double getServiceTime() {
        return ServiceTime;
    }

    public void setServiceTime(double serviceTime) {
        ServiceTime = serviceTime;
    }

    public String getSkilled() {
        return Skilled;
    }

    public void setSkilled(String skilled) {
        Skilled = skilled;
    }

    public Object getSorts() {
        return Sorts;
    }

    public void setSorts(Object sorts) {
        Sorts = sorts;
    }

    public String getSpecialty() {
        return Specialty;
    }

    public void setSpecialty(String specialty) {
        Specialty = specialty;
    }

    public String getSpecialtyType() {
        return SpecialtyType;
    }

    public void setSpecialtyType(String specialtyType) {
        SpecialtyType = specialtyType;
    }

    public int getSrcScore() {
        return SrcScore;
    }

    public void setSrcScore(int srcScore) {
        SrcScore = srcScore;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public double getTrainTime() {
        return TrainTime;
    }

    public void setTrainTime(double trainTime) {
        TrainTime = trainTime;
    }

    public String getTrueName() {
        return TrueName;
    }

    public void setTrueName(String trueName) {
        TrueName = trueName;
    }

    public String getUnitProperty() {
        return UnitProperty;
    }

    public void setUnitProperty(String unitProperty) {
        UnitProperty = unitProperty;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getVolk() {
        return Volk;
    }

    public void setVolk(String volk) {
        Volk = volk;
    }

    public String getWeiXinOPENID() {
        return WeiXinOPENID;
    }

    public void setWeiXinOPENID(String weiXinOPENID) {
        WeiXinOPENID = weiXinOPENID;
    }

    public double getWorkservicetime() {
        return Workservicetime;
    }

    public void setWorkservicetime(double workservicetime) {
        Workservicetime = workservicetime;
    }

    public double getWorkTime() {
        return WorkTime;
    }

    public void setWorkTime(double workTime) {
        WorkTime = workTime;
    }

    public String getWorkunit() {
        return Workunit;
    }

    public void setWorkunit(String workunit) {
        Workunit = workunit;
    }

    public int getZhLevel() {
        return ZhLevel;
    }

    public void setZhLevel(int zhLevel) {
        ZhLevel = zhLevel;
    }
}
