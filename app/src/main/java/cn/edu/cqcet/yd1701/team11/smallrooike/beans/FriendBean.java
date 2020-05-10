package cn.edu.cqcet.yd1701.team11.smallrooike.beans;

import java.util.Arrays;

public class FriendBean {
//    好友信息
    private UserBean userBean;
//    好友类型 0：普通 1：屏蔽 2：拉黑
    private int friendtype;
//    备注
    private String remarks;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public int getFriendtype() {
        return friendtype;
    }

    public void setFriendtype(int friendtype) {
        this.friendtype = friendtype;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}