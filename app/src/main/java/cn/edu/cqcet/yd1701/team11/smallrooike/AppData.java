package cn.edu.cqcet.yd1701.team11.smallrooike;

import android.app.Application;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.alibaba.fastjson.JSONArray;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.edu.cqcet.yd1701.team11.smallrooike.beans.UserBean;

/**
 * Created by Intellij IDEA.
 * APP公共需求参数
 * @author 22510
 * @create 2020/4/9 19:39 星期四
 */
public class AppData extends Application {
    private View view;
//    用户信息
    private static UserBean userBean;
//    用户登录状态
    private String token;
//    小程序(更多)
    private JSONArray applets;
//    维护状态
    private boolean maintain = false;
//    用户IP
    private String ip;
//    手机短信验证码发送间隔时间
    private int phoneSmsTime = 60;
//    发送短信条件
    private boolean isPhoneSms = false;
//    发送短信倒计时
    private ScheduledExecutorService mService;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ScheduledExecutorService getmService() {
        return mService;
    }

    public void setmService(ScheduledExecutorService mService) {
        this.mService = mService;
    }

    public boolean isPhoneSms() {
        return isPhoneSms;
    }

    public void setPhoneSms(boolean phoneSms) {
        isPhoneSms = phoneSms;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        AppData.userBean = userBean;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONArray getApplets() {
        return applets;
    }

    public void setApplets(JSONArray applets) {
        this.applets = applets;
    }

    public boolean isMaintain() {
        return maintain;
    }

    public void setMaintain(boolean maintain) {
        this.maintain = maintain;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPhoneSmsTime() {
        return phoneSmsTime;
    }

    public void setPhoneSmsTime(int phoneSmsTime) {
        this.phoneSmsTime = phoneSmsTime;
    }
}
