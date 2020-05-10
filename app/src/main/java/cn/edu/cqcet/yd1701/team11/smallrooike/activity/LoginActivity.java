package cn.edu.cqcet.yd1701.team11.smallrooike.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.*;
import android.os.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.edu.cqcet.yd1701.team11.smallrooike.R;
import cn.edu.cqcet.yd1701.team11.smallrooike.adapter.NavViewPagerPagerAdapter;
import cn.edu.cqcet.yd1701.team11.smallrooike.beans.UserBean;
import cn.edu.cqcet.yd1701.team11.smallrooike.AppData;
import cn.edu.cqcet.yd1701.team11.smallrooike.tools.ErrorTools;
import cn.edu.cqcet.yd1701.team11.smallrooike.tools.ViewTools;

import static cn.edu.cqcet.yd1701.team11.smallrooike.customvalue.CustomValue.*;
import static cn.edu.cqcet.yd1701.team11.smallrooike.tools.TypeConversionTools.*;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/10/30 19:40
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private AppData appData;
    private GeneralThread generalThread;
//    登录：用户名 密码 验证码
//    返回
    private ImageView mLoginLlTitleIvBreak;
//    登录验证码图片
    private ImageView mLoginIvYzm;
//    头像
    private ImageView mLoginIvAvatar;
//    昵称
    private EditText mLoginEtNickname;
//    密码
    private EditText mLoginEtPassword;
//    验证码输入框
    private EditText mLoginEtYzm;
//    注册
//    头像
    private ImageView mLogonIvAvatar;
//    昵称
    private EditText mLogonEtNickname;
//    密码
    private EditText mLogonEtPassword;
//    确定密码
    private EditText mLogonEtOkpassword;
//    邮箱
    private EditText mLogonEtEmail;
//    电话号码
    private EditText mLogonEtTel;
//    姓名
    private EditText mLogonEtNextUsername;
//    地址
    private EditText mLogonEtNextHome;
//    手机短信验证码
    private EditText mLogonEtYzm;
//    出生日期
    private TextView mLogonTvNextDob;
//    发送验证码
    private Button mLogonBtnYzm;
//    性别
    private RadioButton mLogonRgRbSexBoy;
    private RadioButton mLogonRgRbSexGirl;
//    登陆注册按钮
    private Button mLoginBtnLogin;
    private Button mLoginBtnLogon;
    private ViewPager mLogin_viewPager;
//    第n次下一步
    private int mLoginBtnCkSum = 0;
    private String result="";
    private Bitmap bitmap;
    private boolean imageCaptcha;
    private boolean sendCaptcha;
    private Calendar ca = Calendar.getInstance();
    private Uri tempUri;
    private int  mYear = ca.get(Calendar.YEAR);
    private int  mMonth = ca.get(Calendar.MONTH);
    private int  mDay = ca.get(Calendar.DAY_OF_MONTH);
    public static final int CHOOSE_PICTURE = 0;
    public static final int TAKE_PICTURE = 1;
    public static final int CROP_SMALL_PICTURE = 2;
    private Uri imageUri;// 拍照时的图片uri
    private ViewTools viewTools = new ViewTools();
    private ScheduledExecutorService mService;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_log_in_register);
        initView();
        initDate();
    }
    private void initView(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        this.mLogin_viewPager = findViewById(R.id.login_viewPager);
        List<View> views = new ArrayList<>();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        views.add(layoutInflater.inflate(R.layout.activity_login,null));
        views.add(layoutInflater.inflate(R.layout.activity_registered,null));
        mLogin_viewPager.setAdapter(new NavViewPagerPagerAdapter(views));
//        登录 控件
        this.mLoginIvAvatar = views.get(0).findViewById(R.id.login_iv_avatar);
        this.mLoginIvYzm = views.get(0).findViewById(R.id.login_iv_yzm);
        this.mLoginIvYzm.setOnClickListener(this);
        this.mLoginEtNickname = views.get(0).findViewById(R.id.login_et_nickname);
        this.mLoginEtPassword = views.get(0).findViewById(R.id.login_et_password);
        this.mLoginEtYzm = views.get(0).findViewById(R.id.login_et_yzm);
//        注册 控件
        this.mLogonIvAvatar = views.get(1).findViewById(R.id.logon_iv_avatar);
        this.mLogonIvAvatar.setOnClickListener(this);
        this.mLogonEtNickname = views.get(1).findViewById(R.id.logon_et_nickname);
        this.mLogonEtPassword = views.get(1).findViewById(R.id.logon_et_password);
        this.mLogonEtOkpassword = views.get(1).findViewById(R.id.logon_et_okpassword);
        this.mLogonEtEmail = views.get(1).findViewById(R.id.logon_et_email);
        this.mLogonEtTel = views.get(1).findViewById(R.id.logon_et_tel);
        this.mLogonEtNextUsername = views.get(1).findViewById(R.id.logon_et_nextUsername);
        this.mLogonTvNextDob = views.get(1).findViewById(R.id.logon_tv_nextDob);
        this.mLogonTvNextDob.setOnClickListener(this);
        this.mLogonEtNextHome = views.get(1).findViewById(R.id.logon_et_nextHome);
        this.mLogonEtYzm = views.get(1).findViewById(R.id.logon_et_yzm);
        this.mLogonBtnYzm = views.get(1).findViewById(R.id.logon_btn_yzm);
        this.mLogonBtnYzm.setOnClickListener(this);
        this.mLogonRgRbSexBoy = views.get(1).findViewById(R.id.logon_rg_rbSex_Boy);
        this.mLogonRgRbSexGirl = views.get(1).findViewById(R.id.logon_rg_rbSex_Girl);

        this.mLoginBtnLogin = findViewById(R.id.login_btn_login);
        this.mLoginBtnLogin.setOnClickListener(this);
        this.mLoginBtnLogon = findViewById(R.id.login_btn_logon);
        this.mLoginBtnLogon.setOnClickListener(this);
        this.mLoginLlTitleIvBreak = findViewById(R.id.login_ll_title_iv_break);
        this.mLoginLlTitleIvBreak.setOnClickListener(this);

//        设置cookie
        CookieManager manager = new CookieManager();
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        CookieHandler.setDefault(manager);

        getCpatcha(CAPTCHA_IMAGE,"image");
    }
    private void initDate(){
        appData = (AppData) getApplication();
        if (appData.isPhoneSms()){
            mService = appData.getmService();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_iv_yzm:
                getCpatcha(CAPTCHA_IMAGE,"image");
                break;
            case R.id.login_ll_title_iv_break:
                finish();
                break;
            case R.id.login_btn_login:
                mLogin_viewPager.setCurrentItem(0);
                if (mLoginBtnCkSum-- == 0) {
                    login();
                }
                if (mLoginBtnCkSum < 0) {
                    mLoginBtnCkSum = 0;
                }
                break;
            case R.id.login_btn_logon:
                mLogin_viewPager.setCurrentItem(1);
                if (mLoginBtnCkSum++ == 1) {
                    logon();
                }
                if (mLoginBtnCkSum > 1) {
                    mLoginBtnCkSum = 1;
                }
                break;
            case R.id.logon_tv_nextDob:
                setDate();
                break;
            case R.id.logon_iv_avatar:
                showChoosePicDialog();
                break;
            case R.id.logon_btn_yzm:
                if (appData.isPhoneSms()){
                    break;
                }
                if (mLogonEtTel.getText().toString().length() < 11){
                    viewTools.myToast(1,"请填写正确的手机号码",LoginActivity.this);
                    break;
                }
                setPhoneSmsTime();
                getCpatcha(CAPTCHA_SENDSMS + mLogonEtTel.getText().toString(),"sendsms");
                appData.setPhoneSms(true);
                break;
            default:break;
        }
    }
    /**
     * 打开相册
     */
    private void openPhones(){
        Intent intent = new Intent(Intent.ACTION_PICK);  //跳转到 ACTION_IMAGE_CAPTURE
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PICTURE);
    }
    /**
     * 打开相机
     */
    private void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(getExternalFilesDir(null),"temp.jpg")));
        startActivityForResult(intent,TAKE_PICTURE);
    }
    /**
     * 剪裁头像
     * @param uri
     */
    private void clipImage(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }
    /**
     * 添加头像对话框
     */
    private void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("添加图片");
        String[] items;
//        判断是否带有相机
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            items = new String[]{"选择本地照片"};
        } else {
            items = new String[]{"选择本地照片"};
        }
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        openPhones();
                        break;
                    case TAKE_PICTURE: // 拍照
                        openCamera();
                        break;
                    default:break;
                }
            }
        });
        builder.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == LoginActivity.RESULT_OK){
            switch (requestCode){
                case CHOOSE_PICTURE://相册
                    Uri uri = data.getData();
                    clipImage(uri);
                    break;
                case TAKE_PICTURE://相机
                    break;
                case CROP_SMALL_PICTURE://剪裁
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        //在这里获得了剪裁后的Bitmap对象，可以用于上传
                        Bitmap image = bundle.getParcelable("data");
                        //设置到ImageView上
                        mLogonIvAvatar.setImageBitmap(viewTools.getRoundedCornerBitmap(image,100));
                    }
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * DatePickerDialog 日期选择器
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setDate(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mYear = year;
                        mMonth = month;
                        mDay = dayOfMonth;
                    }
                },
                mYear, mMonth, mDay);
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mLogonTvNextDob.setText(year + "-" + month + "-" + dayOfMonth);
            }
        });
        datePickerDialog.show();
    }
    /**
     * 发送短信验证码后需等待60秒后可重试
     */
    private void setPhoneSmsTime(){
        mService = Executors.newScheduledThreadPool(appData.getPhoneSmsTime());
        mService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int time = appData.getPhoneSmsTime() - 1;
                        appData.setPhoneSmsTime(time);
                        mLogonBtnYzm.setText(String.valueOf(appData.getPhoneSmsTime()));
                        if (appData.getPhoneSmsTime() < 0) {
                            appData.setPhoneSmsTime(60);
                            appData.setPhoneSms(false);
                            mLogonBtnYzm.setText("发送验证码");
                            mService.shutdown();
                        }
                    }
                });
            }
        }, 1, 1, TimeUnit.SECONDS);
    }
    /**
     * 登录逻辑 判断登录条件是否成立
     * @return 是否登录成功
     */
    private void login(){
        String path = OKCAPTCHA_PATH + "image/" + mLoginEtYzm.getText().toString();
        if (mLoginEtNickname.getText().length() > 0
                && mLoginEtPassword.getText().length() > 0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tel", mLoginEtNickname.getText().toString());
            jsonObject.put("pwdmd5", strToMd5(mLoginEtPassword.getText().toString()));
            isCaptcha(path,"image");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (imageCaptcha){
                loginPost(jsonObject, LOGIN_PATH + jsonObject.get("tel"));
            } else {
                viewTools.myToast(1,"验证码错误",LoginActivity.this);
            }
        } else {
            viewTools.myToast(1,"用户名密码不能为空",this);
        }
    }
    /**
     * 注册逻辑 判断注册条件是否成立
     * @return 是否注册成功
     */
    private void logon(){
        JSONObject jsonObject;
        Date date = new Date();
        UserBean userBean = new UserBean();
        String path = OKCAPTCHA_PATH + "sendsms/" + mLogonEtYzm.getText().toString();
        userBean.setNickname(mLogonEtNickname.getText().toString());
        userBean.setPwdmd5(strToMd5(mLogonEtPassword.getText().toString()));
        userBean.setTel(mLogonEtTel.getText().toString());
        userBean.setEmail(mLogonEtEmail.getText().toString());
        userBean.setDob(date);
        userBean.setHome(mLogonEtNextHome.getText().toString());
        userBean.setUsername(mLogonEtNextUsername.getText().toString());
        if (mLogonRgRbSexBoy.isChecked()){
            userBean.setSex("男");
        }else{
            userBean.setSex("女");
        }
        isCaptcha(path,"sendsms");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (userBean.getNickname().length() > 0
                && userBean.getPwdmd5().length() > 0
                && mLogonEtOkpassword.getText().length() > 0
                && userBean.getTel().length() > 0
                && mLogonIvAvatar.getDrawable() != null
                && sendCaptcha){
            if (!mLogonEtPassword.getText().toString().equals(mLogonEtOkpassword.getText().toString())){
                viewTools.myToast(1,"请检查密码",this);
            } else {
                jsonObject = JSON.parseObject(JSON.toJSONString(userBean));
                logonPost(LOGON_PATH,jsonObject);
            }
        } else {
            viewTools.myToast(1,"信息请填写完整",LoginActivity.this);
        }
    }
    /**
     * 登录
     * @param jsonObject
     * @param path
     */
    private void loginPost(JSONObject jsonObject, String path){
        try {
            URL url = new URL(path);
            generalThread = new GeneralThread(url,jsonObject,"jsonObject",0,null,"POST");
            generalThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 注册
     * @param path
     * @param jsonObject
     */
    private void logonPost(String path, JSONObject jsonObject){
        try {
            URL url = new URL(path);
            generalThread = new GeneralThread(url,jsonObject,"jsonObject",1,null,"POST");
            generalThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取头像
     * @param path
     */
    private void getHead(String path){
        try {
            URL url = new URL(path);
            generalThread = new GeneralThread(url,null,null,6,null,"POST");
            generalThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 上传头像
     * @param path
     */
    private void setHead(String path,byte[] bytes){
        try {
            URL url = new URL(path);
            generalThread = new GeneralThread(url,bytes,"byte",2,null,"POST");
            generalThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Bitmap转byte[]
     * @param bm
     * @return
     */
    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }
    /**
     * byte[]转Bitmap
     * @param b
     * @return
     */
    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
    /**
     * 获取验证码
     */
    private void getCpatcha(String path,String type){
        try {
            URL url = new URL(path);
            generalThread = new GeneralThread(url,null,null,3,type,"GET");
            generalThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 验证验证码
     * @param path
     */
    private void isCaptcha(String path,String type){
        try {
            URL url = new URL(path);
            GeneralThread generalThread;
            generalThread = new GeneralThread(url,null,null,4,type,"GET");
            generalThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 通用Connection
     * 0:登录
     * 1:注册
     * 2:上传头像
     * 3:发送验证码
     * 4:验证验证码
     * 5:注销
     * 6:获取头像
     */
    private class GeneralThread extends Thread{
//        路径
        private URL url;
//        数据
        private Object object;
//        连接类型
        private int connType;
//        验证码类型
        private String captchaType;
//        数据类型
        private String dataType;
//        访问方式
        private String method;
        private JSONObject jsonObject;
        private byte[] bytes;
        private StringBuilder strber;
        private InputStream is;
        private BufferedReader br;
        private HttpURLConnection conn;
        GeneralThread(URL url,Object object,String dataType,int connType,String captchaType,String method){
            this.connType = connType;
            this.captchaType = captchaType;
            this.dataType = dataType;
            this.object = object;
            this.url = url;
            this.method = method;
        }
        @Override
        public void run() {
            super.run();
            try {
                Log.e("Url", String.valueOf(url));
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(1000);
                conn.setReadTimeout(2500);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setRequestMethod(method);
                conn.setRequestProperty("Charsert", "UTF-8");
                conn.setRequestProperty("Content-Type", "application/json");
                if (dataType!=null){
                    switch (dataType){
                        case "byte":
                            bytes = (byte[]) object;
                            //设置DataOutputStream
                            DataOutputStream dsDataOutputStream = new DataOutputStream(conn.getOutputStream());
//                dsDataOutputStream.writeBytes("Content-Disposition:form-data;" + "name=\"" + key + "\";filename=\"" + "11.jpg\"" + endString);
//                dsDataOutputStream.writeBytes(endString);
                            //取得文件的FileInputStream
                            dsDataOutputStream.write(bytes, 0, bytes.length);
                            System.out.println(bytes.length);
                            dsDataOutputStream.close();
                            break;
                        case "jsonObject":
                            jsonObject = (JSONObject) object;
                            conn.getOutputStream().write(String.valueOf(jsonObject).getBytes());
                            System.out.println(jsonObject);
                            conn.connect();
                            break;
                        default:break;
                    }
                } else {
                    conn.connect();
                }
                int responseCode = conn.getResponseCode();
                if (responseCode == 200){
                    is = conn.getInputStream();
                    if (connType != 3){
                        br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                        strber = new StringBuilder();
                        String line = null;
                        while ((line=br.readLine()) != null) {
                            strber.append(line).append("\n");
                        }
                        result = strber.toString();
                        jsonObject = JSONObject.parseObject(result);
                    }
                    switch (connType){
                        case 0:
                            //登录
                            switch (jsonObject.getInteger("msg")){
                                case ErrorTools.SUCCESS:
                                    if (imageCaptcha){
                                        appData.setUserBean(JSON.parseObject(jsonObject.toJSONString(), new TypeReference<UserBean>(){}));
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("uuid", appData.getUserBean().getUuid());
                                        editor.putString("nickname", appData.getUserBean().getNickname());
                                        editor.putString("email", appData.getUserBean().getEmail());
                                        editor.putString("tel", appData.getUserBean().getTel());
                                        editor.putString("username", appData.getUserBean().getUsername());
                                        editor.putString("sex", appData.getUserBean().getSex());
                                        editor.putString("dob", simpleDateFormat.format(appData.getUserBean().getDob()));
                                        editor.putString("home", appData.getUserBean().getHome());
                                        editor.apply();//写入
                                        Intent intent = new Intent(LoginActivity.this, NavActivity.class);
                                        setResult(RESULT_OK, intent);
                                        finish();
                                    }
                                    break;
                                case ErrorTools.IAP:
                                    viewTools.myToast(1,"账号或密码错误",LoginActivity.this);
                                    break;
                                default:break;
                            }
                            break;
                        case 1:
                            //注册
                            switch (jsonObject.getInteger("msg")){
                                case ErrorTools.FAILURE:
                                    viewTools.myToast(2,"注册失败",LoginActivity.this);
                                    break;
                                case ErrorTools.SUCCESS:
                                    setHead(LOGON_SETAVATAR_PATH + mLogonEtTel.getText().toString(),
                                            Bitmap2Bytes(((BitmapDrawable)mLoginIvAvatar.getDrawable()).getBitmap()));
                                    viewTools.myToast(0,"注册成功",LoginActivity.this);
                                    break;
                                default:break;
                            }
                            break;
                        case 2:
                            //上传头像
                            break;
                        case 3:
                            //获取验证码
                            switch (captchaType){
                                case "image":
                                    bitmap = viewTools.getRoundedCornerBitmap(BitmapFactory.decodeStream(is),25);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mLoginIvYzm.setImageBitmap(bitmap);
                                        }
                                    });
                                    break;
                                case "sendsms":
                                    break;
                                default:break;
                            }
                            break;
                        case 4:
                            //验证验证码
                            switch (captchaType){
                                case "image":
                                    imageCaptcha = jsonObject.getInteger("msg") == ErrorTools.SUCCESS;
                                    break;
                                case "sendsms":
                                    sendCaptcha = jsonObject.getInteger("msg") == ErrorTools.SUCCESS;
                                    break;
                                default:break;
                            }
                            break;
                        case 5:
                            //注销
                            break;
                        default:break;
                    }
                    is.close();
                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                viewTools.myToast(2,"连接服务器失败",LoginActivity.this);
                e.printStackTrace();
            }
        }
    }
}