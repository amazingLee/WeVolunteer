package com.example.model.content;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/20 11:05
 * 修改备注：
 */
public class ContentListDto {
    private String Id;//(string, optional),
    private String CategoryId;//(string, optional),
    private String CategoryName;//(string, optional),
    private String ContentName;//(string, optional),
    private String SubContentName;// (string, optional),
    private String Description;//(string, optional),
    private String CategoryPathId;// (string, optional),
    private String CategoryPathName;//(string, optional),
    private Boolean IsOutsideLink;// (boolean, optional),
    private String OutsideUrl;//(string, optional),
    private String Author;// (string, optional),
    private String Source;//(string, optional),
    private String Keyword;//(string, optional),
    private Boolean IsTop;//(boolean, optional),
    private Boolean IsPic;// (boolean, optional),
    private Integer VisiteRecord;//(integer, optional),
    private String PubDepartment;//(string, optional),
    private String PubDateTime;// (string, optional),
    private Integer SortIndex;//(integer, optional)

    private String AppLstUrl;

    public String getAppLstUrl() {
        return AppLstUrl;
    }

    public void setAppLstUrl(String appLstUrl) {
        AppLstUrl = appLstUrl;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryPathId() {
        return CategoryPathId;
    }

    public void setCategoryPathId(String categoryPathId) {
        CategoryPathId = categoryPathId;
    }

    public String getCategoryPathName() {
        return CategoryPathName;
    }

    public void setCategoryPathName(String categoryPathName) {
        CategoryPathName = categoryPathName;
    }

    public String getContentName() {
        return ContentName;
    }

    public void setContentName(String contentName) {
        ContentName = contentName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Boolean getOutsideLink() {
        return IsOutsideLink;
    }

    public void setOutsideLink(Boolean outsideLink) {
        IsOutsideLink = outsideLink;
    }

    public Boolean getPic() {
        return IsPic;
    }

    public void setPic(Boolean pic) {
        IsPic = pic;
    }

    public Boolean getTop() {
        return IsTop;
    }

    public void setTop(Boolean top) {
        IsTop = top;
    }

    public String getKeyword() {
        return Keyword;
    }

    public void setKeyword(String keyword) {
        Keyword = keyword;
    }

    public String getOutsideUrl() {
        return OutsideUrl;
    }

    public void setOutsideUrl(String outsideUrl) {
        OutsideUrl = outsideUrl;
    }

    public String getPubDateTime() {
        return PubDateTime;
    }

    public void setPubDateTime(String pubDateTime) {
        PubDateTime = pubDateTime;
    }

    public String getPubDepartment() {
        return PubDepartment;
    }

    public void setPubDepartment(String pubDepartment) {
        PubDepartment = pubDepartment;
    }

    public Integer getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        SortIndex = sortIndex;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getSubContentName() {
        return SubContentName;
    }

    public void setSubContentName(String subContentName) {
        SubContentName = subContentName;
    }

    public Integer getVisiteRecord() {
        return VisiteRecord;
    }

    public void setVisiteRecord(Integer visiteRecord) {
        VisiteRecord = visiteRecord;
    }
}