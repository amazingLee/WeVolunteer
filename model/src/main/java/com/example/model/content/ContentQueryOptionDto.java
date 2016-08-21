package com.example.model.content;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/20 11:02
 * 修改备注：
 */
public class ContentQueryOptionDto {
    private String CategoryId;//(string, optional),
    private String ContentName;//(string, optional),
    private String SubContentName;//(string, optional),
    private String Description;//(string, optional),
    private String CategoryPathId;//(string, optional),
    private String CategoryPathName;//(string, optional),
    private Boolean IsOutsideLink;//(boolean, optional),
    private String Author;// (string, optional),
    private String Source;//(string, optional),
    private String Comment;//(string, optional),
    private Boolean IsTop;//(boolean, optional),
    private Boolean IsPic;//(boolean, optional),
    private String PubDepartment;//(string, optional),
    private String PubDateTimeBeg;// (string, optional),
    private String PubDateTimeEnd;// (string, optional),
    private Boolean IsPermission;//(boolean, optional),
    private String PermissionTime;//(string, optional),
    private String PermissionUserId;// (string, optional),
    private String PermissionUserName;//(string, optional),
    private String KeyWord;//(string, optional): 查询关键词
    private Integer PageIndex;//(integer, optional): 当前页索引，默认1
    private Integer PageSize;//(integer, optional): 每页记录条数，默认20
    private Object Sorts;//(object, optional)

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

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
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

    public Boolean getOutsideLink() {
        return IsOutsideLink;
    }

    public void setOutsideLink(Boolean outsideLink) {
        IsOutsideLink = outsideLink;
    }

    public Boolean getPermission() {
        return IsPermission;
    }

    public void setPermission(Boolean permission) {
        IsPermission = permission;
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

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public Integer getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        PageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public String getPermissionTime() {
        return PermissionTime;
    }

    public void setPermissionTime(String permissionTime) {
        PermissionTime = permissionTime;
    }

    public String getPermissionUserId() {
        return PermissionUserId;
    }

    public void setPermissionUserId(String permissionUserId) {
        PermissionUserId = permissionUserId;
    }

    public String getPermissionUserName() {
        return PermissionUserName;
    }

    public void setPermissionUserName(String permissionUserName) {
        PermissionUserName = permissionUserName;
    }

    public String getPubDateTimeBeg() {
        return PubDateTimeBeg;
    }

    public void setPubDateTimeBeg(String pubDateTimeBeg) {
        PubDateTimeBeg = pubDateTimeBeg;
    }

    public String getPubDateTimeEnd() {
        return PubDateTimeEnd;
    }

    public void setPubDateTimeEnd(String pubDateTimeEnd) {
        PubDateTimeEnd = pubDateTimeEnd;
    }

    public String getPubDepartment() {
        return PubDepartment;
    }

    public void setPubDepartment(String pubDepartment) {
        PubDepartment = pubDepartment;
    }

    public Object getSorts() {
        return Sorts;
    }

    public void setSorts(Object sorts) {
        Sorts = sorts;
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
}
