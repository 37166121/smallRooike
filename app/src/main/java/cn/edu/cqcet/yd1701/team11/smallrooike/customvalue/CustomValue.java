package cn.edu.cqcet.yd1701.team11.smallrooike.customvalue;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/9 13:40 星期四
 */
public class CustomValue {
    //    IP
    private static final String IP = "http://www.aliyunm.top";
    //    端口
    private static final String CAPCHA = ":8000";
    private static final String USER = ":7900";
    private static final String NEWS = ":7800";
    private static final String ZUUL = ":7700";
    //    服务器IP
    private static final String SERVER_IP = IP + ZUUL + "/api";
    private static final String CAPTCHA_SERVICE = IP + CAPCHA;
    //    登录
    public static String LOGIN_PATH = SERVER_IP +"/user/getUser/";
    //    添加用户
    public static String LOGON_PATH = SERVER_IP +"/user/addUser";
    //    添加头像
    public static String LOGON_SETAVATAR_PATH = SERVER_IP +"/user/setAvatar/";
    //    获取图片验证码
    public static String CAPTCHA_IMAGE = CAPTCHA_SERVICE +"/image";
    //    获取手机短信验证码
    public static String CAPTCHA_SENDSMS = CAPTCHA_SERVICE +"/sendSms/";
    //    获取邮箱验证码
    public static String CAPTCHA_SENDMAIL = CAPTCHA_SERVICE +"/sendMail/";
    //    验证验证码
    public static String OKCAPTCHA_PATH = CAPTCHA_SERVICE +"/isCaptcha/";
    //    谷歌广告ID
    public static final String ADMOB_AD_UNIT_ID = "ca-app-pub-3940256099942544/2247696110";
}