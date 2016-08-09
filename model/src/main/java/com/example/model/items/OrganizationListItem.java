package com.example.model.items;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/9 13:08
 * 修改备注：
 */
public class OrganizationListItem {
    private static final String TAG = "OrganizationListItem";

   private  String iconUrl;
   private  String name;
   private  String address;

    public OrganizationListItem(String iconUrl, String name, String address) {
        this.iconUrl = iconUrl;
        this.name = name;
        this.address = address;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
