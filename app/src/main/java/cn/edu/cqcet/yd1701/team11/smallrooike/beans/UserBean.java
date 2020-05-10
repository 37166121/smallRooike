package cn.edu.cqcet.yd1701.team11.smallrooike.beans;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/10/31 21:14
 */
public class UserBean implements Serializable {
    private String uuid;
//    头像
    @JSONField(serialize=false)
    private byte[] avatar;
//    昵称
    private String nickname;
//    密码转md5
    private String pwdmd5;
//    邮箱
    private String email;
//    tel
    private String tel;
//    姓名
    private String username;
//    性别
    private String sex;
//    出生日期
    private Date dob;
//    家庭住址
    private String home;

    @Override
    public String toString() {
        return "UserBean{" +
                "uuid='" + uuid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pwdmd5='" + pwdmd5 + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", dob=" + dob +
                ", home='" + home + '\'' +
                '}';
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwdmd5() {
        return pwdmd5;
    }

    public void setPwdmd5(String pwdmd5) {
        this.pwdmd5 = pwdmd5;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}