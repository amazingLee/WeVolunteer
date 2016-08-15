package com.example.model.items;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/5 23:00
 * 修改备注：
 */
public class HomePageListItem {
    private static final String TAG = "HomePageItem";

    private int type;//0活动，1岗位
    private int state;//0招募中,1已结束
    private String title;
    private int num;
    private int maxNum;
    private String time;
    private String img;

    public HomePageListItem() {
    }

    public HomePageListItem(int type, int state, String title, int num, int maxNum, String time, String img) {
        this.type = type;
        this.state = state;
        this.title = title;
        this.num = num;
        this.maxNum = maxNum;
        this.time = time;
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
