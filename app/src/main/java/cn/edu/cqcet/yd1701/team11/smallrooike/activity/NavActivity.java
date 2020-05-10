package cn.edu.cqcet.yd1701.team11.smallrooike.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import cn.edu.cqcet.yd1701.team11.smallrooike.R;
import cn.edu.cqcet.yd1701.team11.smallrooike.adapter.*;
import cn.edu.cqcet.yd1701.team11.smallrooike.beans.*;
import cn.edu.cqcet.yd1701.team11.smallrooike.AppData;
import cn.edu.cqcet.yd1701.team11.smallrooike.customclicklistener.CustomTouchListener;
import cn.edu.cqcet.yd1701.team11.smallrooike.tools.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/10/30 19:40
 */
public class NavActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mNavIvHome;
    private ImageView mNavIvMore;
    private ImageView mNavIvFriend;
    private ImageView mNavIvMine;
    private ImageView mNavIvMenu;
    private ImageView mNavLlTitleIvAvatar;
    private ImageView mMoreIvCall;
    private TextView mNavTvName;
    private TextView mNavTvHome;
    private TextView mNavTvMore;
    private TextView mNavTvFriend;
    private TextView mNavTvMine;
    private TextView mHomeTvLogin;
    private TextView mHomeTvLookHistory;
    private TextView mMine_tv_signature;
    private TextView mMine_tv_nickname;
    private LinearLayout mNavLlHome;
    private LinearLayout mNavLlMore;
    private LinearLayout mNavLlFriend;
    private LinearLayout mNavLlMine;
    private LinearLayout mHomeLlLogin;
    private ViewPager mNavViewpager;
    private LinearLayout mHomeLlPickup;
    private LinearLayout mHomeLlDelivery;
    private LinearLayout mHhomeLlLogin;
    private List<View> mNavListViews = new ArrayList<View>();
    private ListView mHomeLvLogisticsNews;
    private ListView mMine_lv_more;
    private RecyclerView mFriendRv;
    private TextView mFriendTvSearch;
    private List<LogisticsNewsBean> mLogisticsNewsBeans = new ArrayList<>();
    private LogisticsNewsBean mLogisticsNewsBean = new LogisticsNewsBean();
    private int mRecordImgId;
    private List<String> mGroupList;
    private List<FriendBean> itemList = new ArrayList<>();
    private List<List<FriendBean>> mItemList = new ArrayList<>();
    private FriendBean mfriendBean;
    private AppData appData;
    private ViewTools viewTools = new ViewTools();
    private List<MineListMapBean> mineListMapBeanList = new ArrayList<>();
    private String[] mineListMapBeanName = {"关于","帮助与反馈","分享小菜鸟"};
    private int[] mineListMapBeanImage = {R.drawable.exclamation307xxblack,
            R.drawable.question207xxblack,
            R.drawable.share440black};
    private long mExitTime;
    private static final int REQUEST_CODE_ACTIVITY = 1;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
//        StatusBarUtil.setTranslucent(this, 64);
        initView();
        initData();
        mHomeLlPickup.setOnTouchListener(new CustomTouchListener() {
            @Override
            protected void onSingleClick() {
                if (appData.getUserBean() != null){
                    Intent intent;
                    intent = new Intent(NavActivity.this,PostTakeActivity.class);
                    startActivity(intent);
                } else {
                    new ViewTools().myToast(1,"请登录后再试",NavActivity.this);
                }
            }

            @Override
            protected void onFastClick() {

            }
        });
        mHomeLlDelivery.setOnTouchListener(new CustomTouchListener() {
            @Override
            protected void onSingleClick() {
                if (appData.getUserBean() != null){
                    Intent intent;
                    intent = new Intent(NavActivity.this,PostTakeActivity.class);
                    startActivity(intent);
                } else {
                    new ViewTools().myToast(1,"请登录后再试",NavActivity.this);
                }
            }

            @Override
            protected void onFastClick() {

            }
        });
        mMoreIvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });
        final HomeNewsListViewBaseAdapter homeListViewBaseAdapter = new HomeNewsListViewBaseAdapter(mLogisticsNewsBeans,NavActivity.this);
        mHomeLvLogisticsNews.setAdapter(homeListViewBaseAdapter);
        mHomeLvLogisticsNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(homeListViewBaseAdapter.getItem(position));
            }
        });
//        测试数据
        mLogisticsNewsBean.setmAddress("重庆");
        mLogisticsNewsBean.setmStatu("未签收");
        mLogisticsNewsBean.setmQuantity(99);
        mLogisticsNewsBean.setmPlatform("京东快递");
//        mLogisticsNewsBean.setmImage();
        for (int i = 0 ; i < 10 ; i++){
            mLogisticsNewsBeans.add(mLogisticsNewsBean);
        }
        for (int i = 0 ; i < mineListMapBeanName.length ; i++){
            MineListMapBean mineListMapBean = new MineListMapBean();
            mineListMapBean.setName(mineListMapBeanName[i]);
            mineListMapBean.setImage(BitmapFactory.decodeResource(getResources(), mineListMapBeanImage[i], null));
            mineListMapBeanList.add(mineListMapBean);
        }
        mMine_lv_more.setAdapter(new MineListViewBaseAdapter(mineListMapBeanList,NavActivity.this));
        mMine_lv_more.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NavActivity.this, MineItemActivity.class);
                switch (position){
                    case 0:
                        intent.putExtra("page",0);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("page",1);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "www.aliyunm.top\n小菜鸟");
                        shareIntent = Intent.createChooser(shareIntent, "小菜鸟");
                        startActivity(shareIntent);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    /**
     * 查找好友
     */
//    private class ConnectToServer extends Thread{
//        private URL url;
//        private String uuid;
//        ConnectToServer(){}
//        ConnectToServer(URL url){
//            url = url;
//        }
//        @Override
//        public void run() {
//            super.run();
//            try {
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////                超时
//                conn.setConnectTimeout(5000);
////                接受输入
////                conn.setDoInput(true);
////                接受输出
//                conn.setDoOutput(true);
////                缓存
//                conn.setUseCaches(false);
////                连接方式
//                conn.setRequestMethod("POST");
//                conn.connect();
//                InputStream is = conn.getInputStream();
//                InputStreamReader isr = new InputStreamReader(is, "utf-8");
//                BufferedReader br = new BufferedReader(isr);
//                StringBuffer buffer = new StringBuffer();
//                String line;
//                while ((line = br.readLine()) != null) {
//                    buffer.append(line);
//                }
//                String str = buffer.toString();
//
//                JSONArray jsonArray = JSON.parseArray(str);
//                for (int i = 0 ; i < jsonArray.size() ; i++){
//                    str = jsonArray.get(i).toString();
//                    JSONObject jsonObject = JSONObject.parseObject(str);
//                    mfriendBean = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<FriendBean>() {
//                    });
//                    itemList.add(mfriendBean);
//                    Log.e(String.valueOf(i),itemList.get(i).toString());
//                    System.out.println(mfriendBean.getFriendtype());
//                    switch (mfriendBean.getFriendtype()){
//                        case 0:
//                            mItemList.add(0,itemList);
//                            break;
//                        case 1:
//                            mItemList.add(1,itemList);
//                            break;
//                        case 2:
//                            mItemList.add(2,itemList);
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    @SuppressLint("InflateParams")
    private void initView(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        mNavListViews.add(layoutInflater.inflate(R.layout.activity_home,null));
        mNavListViews.add(layoutInflater.inflate(R.layout.activity_more,null));
        mNavListViews.add(layoutInflater.inflate(R.layout.activity_friend,null));
        mNavListViews.add(layoutInflater.inflate(R.layout.activity_mine,null));

        mNavViewpager = findViewById(R.id.nav_viewpager);
        mNavViewpager.setAdapter(new NavViewPagerPagerAdapter(mNavListViews));
        mHomeLlPickup = mNavListViews.get(0).findViewById(R.id.home_ll_pickup);
        mHomeLlDelivery = mNavListViews.get(0).findViewById(R.id.home_ll_delivery);
        mHhomeLlLogin = mNavListViews.get(0).findViewById(R.id.home_ll_login);
        mHomeLvLogisticsNews = mNavListViews.get(0).findViewById(R.id.home_lv_LogisticsNews);
        mHomeTvLookHistory = mNavListViews.get(0).findViewById(R.id.home_tv_LookHistory);
        mHomeTvLookHistory.setOnClickListener(this);

        mMoreIvCall = mNavListViews.get(1).findViewById(R.id.more_iv_call);

        mFriendRv = mNavListViews.get(2).findViewById(R.id.friend_rv);
        mFriendTvSearch = mNavListViews.get(2).findViewById(R.id.friend_tv_search);
        mFriendTvSearch.setOnClickListener(this);

        mMine_lv_more = mNavListViews.get(3).findViewById(R.id.mine_lv_more);
        mMine_tv_nickname = mNavListViews.get(3).findViewById(R.id.mine_tv_nickname);
        mMine_tv_signature = mNavListViews.get(3).findViewById(R.id.mine_tv_signature);

        mNavLlHome = findViewById(R.id.nav_ll_home);
        mNavLlHome.setOnClickListener(this);
        mNavLlMore = findViewById(R.id.nav_ll_more);
        mNavLlMore.setOnClickListener(this);
        mNavLlFriend = findViewById(R.id.nav_ll_friend);
        mNavLlFriend.setOnClickListener(this);
        mNavLlMine = findViewById(R.id.nav_ll_mine);
        mNavLlMine.setOnClickListener(this);
        mNavIvMenu = findViewById(R.id.nav_ll_title_iv_menu);
        mNavIvMenu.setOnClickListener(this);
        mNavIvHome = findViewById(R.id.nav_iv_home);
        mNavIvMore = findViewById(R.id.nav_iv_more);
        mNavIvFriend = findViewById(R.id.nav_iv_friend);
        mNavIvMine = findViewById(R.id.nav_iv_mine);
        mNavLlTitleIvAvatar = findViewById(R.id.nav_ll_title_iv_avatar);
        mNavLlTitleIvAvatar.setOnClickListener(this);
        mNavTvName = findViewById(R.id.nav_ll_title_tv_name);
        mNavTvHome = findViewById(R.id.nav_tv_home);
        mNavTvMore = findViewById(R.id.nav_tv_more);
        mNavTvFriend = findViewById(R.id.nav_tv_friend);
        mNavTvMine = findViewById(R.id.nav_tv_mine);

        setNavToHome();
        mRecordImgId = mNavIvHome.getId();
    }
    private void initData() {
        appData = (AppData) getApplication();
        if (appData.getUserBean() != null){
            setInfo();
        }
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.nav_ll_home:
                if (mRecordImgId == v.getId()){ break;}
                setNavToHome();
                mNavViewpager.setCurrentItem(0);
                mRecordImgId = v.getId();
                break;
            case R.id.nav_ll_more:
                if (mRecordImgId == v.getId()){ break;}
                setNavToMore();
                mNavViewpager.setCurrentItem(1);
                mRecordImgId = v.getId();
                break;
            case R.id.nav_ll_friend:
                if (appData.getUserBean() == null){
                    intent = new Intent(NavActivity.this,LoginActivity.class);
                    startActivityForResult(intent,REQUEST_CODE_ACTIVITY);
                } else {
                    if (mRecordImgId == v.getId()){ break;}
                    setNavToFriend();
                    mNavViewpager.setCurrentItem(2);
                    mRecordImgId = v.getId();
                }
                break;
            case R.id.nav_ll_mine:
                if (appData.getUserBean() == null){
                    intent = new Intent(NavActivity.this,LoginActivity.class);
                    startActivityForResult(intent,REQUEST_CODE_ACTIVITY);
                } else {
                    if (mRecordImgId == v.getId()){ break;}
                    setNavToMine();
                    mNavViewpager.setCurrentItem(3);
                    mRecordImgId = v.getId();
                }
                break;
            case R.id.mine_iv_avatar:
                    intent = new Intent(NavActivity.this,UserInfoActivity.class);
                    startActivity(intent);
                break;
            case R.id.nav_ll_title_iv_avatar:
                if (appData.getUserBean() != null){
                    intent = new Intent(NavActivity.this,UserInfoActivity.class);
                    startActivity(intent);
                } else {
                    new ViewTools().myToast(1,"请登录后再试",NavActivity.this);
                }
                break;
            case R.id.nav_ll_title_iv_menu:
                switch (mNavViewpager.getCurrentItem()){
                    case 2:
                        break;
                    case 3:
                        intent = new Intent(NavActivity.this,SettingActivity.class);
                        startActivity(intent);
                        break;
                    default:break;
                }
                break;
            case R.id.home_ll_login:
                intent = new Intent(NavActivity.this,LoginActivity.class);
                startActivityForResult(intent,REQUEST_CODE_ACTIVITY);
                break;
            case R.id.home_tv_LookHistory:
                new ViewTools().myToast(1,"没有历史物流消息",NavActivity.this);
                break;
            case R.id.friend_tv_search:
                intent = new Intent(NavActivity.this,SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    /**
     * 快递通讯录对话框
     */
    private void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NavActivity.this);
        builder.setTitle("快递通讯录");
        List<String> stringList = callJson();
        String[] items = stringList.toArray(new String[stringList.size()]);
        builder.setNegativeButton("关闭", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewTools.myToast(1,stringList.get(which),NavActivity.this);
            }
        });
        builder.show();
    }
    private List<String> callJson(){
        List<String> callList = new ArrayList<>();
        String call;
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        //读取流
        InputStreamReader inputStreamReader_jsonFile;
        BufferedReader bufferedReader_json;
        StringBuilder stringBuilder_json = new StringBuilder();
        try {
            inputStreamReader_jsonFile = new InputStreamReader(getAssets().open("快递通讯录.json"),"UTF-8");
            bufferedReader_json = new BufferedReader(inputStreamReader_jsonFile);
            while((call = bufferedReader_json.readLine()) != null) {
                stringBuilder_json.append(call);
            }
            bufferedReader_json.close();
            inputStreamReader_jsonFile.close();
            jsonArray = JSONArray.parseArray(stringBuilder_json.toString());
            for (int i = 0 ; i < jsonArray.size() ; i++){
                CallBean callBean = new CallBean();
                jsonObject = jsonArray.getJSONObject(i);
                callBean.setName(jsonObject.getString("name"));
                callBean.setTel(jsonObject.getString("tel"));
                callBean.setUrl(jsonObject.getString("url"));
                callList.add(callBean.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return callList;
    }
    /**
     * 调用拨号界面
     * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    /**
     * 登录后返回的信息
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case REQUEST_CODE_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    if(intent!=null){
                        //获取登录界面传递的数据并显示出来
                        new ViewTools().myToast(0,"登录成功",NavActivity.this);
                        setInfo();
                        mMine_tv_nickname.setText(appData.getUserBean().getNickname());
                        mMine_tv_signature.setText(appData.getUserBean().getHome());
                        mHhomeLlLogin.setVisibility(View.GONE);
                        mHomeTvLookHistory.setVisibility(View.VISIBLE);
                        mHomeLvLogisticsNews.setVisibility(View.VISIBLE);
                    }
                }
                break;
            default:
                break;
        }
    }
    /**
     * 设置用户信息
     */
    private void setInfo(){
        List<UserBean> userBeans = new ArrayList<>();
        for (int i = 0 ; i < 3 ; i++){
            userBeans.add(appData.getUserBean());
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mFriendRv.setLayoutManager(layoutManager);
        mFriendRv.setAdapter(new FriendListBaseAdapter(userBeans,this));
        mMine_tv_nickname.setText(appData.getUserBean().getNickname());
        mMine_tv_signature.setText(appData.getUserBean().getHome());
        mHhomeLlLogin.setVisibility(View.GONE);
        mHomeTvLookHistory.setVisibility(View.VISIBLE);
        mHomeLvLogisticsNews.setVisibility(View.VISIBLE);
    }
    /**
     * 导航栏被点击后改变样式
     */
    private void reset(){
        mNavIvMenu.setVisibility(View.INVISIBLE);
        mNavIvHome.setImageDrawable(getDrawable(R.drawable.home204xxblue));
        mNavTvHome.setTextColor(getResources().getColor(R.color.colorBlack));
        mNavIvMore.setImageDrawable(getDrawable(R.drawable.applications30xxblue));
        mNavTvMore.setTextColor(getResources().getColor(R.color.colorBlack));
        mNavIvFriend.setImageDrawable(getDrawable(R.drawable.mission228blue));
        mNavTvFriend.setTextColor(getResources().getColor(R.color.colorBlack));
        mNavIvMine.setImageDrawable(getDrawable(R.drawable.users224xxblue));
        mNavTvMine.setTextColor(getResources().getColor(R.color.colorBlack));
    }
    /**
     * 切换页面
     */
    private void setNavToHome(){
        reset();
        mNavIvHome.setImageDrawable(getDrawable(R.drawable.home204blue));
        mNavTvHome.setTextColor(getResources().getColor(R.color.colorDefault));
        mNavTvName.setText(getString(R.string.home));
    }
    private void setNavToMore(){
        reset();
        mNavIvMore.setImageDrawable(getDrawable(R.drawable.applications30blue));
        mNavTvMore.setTextColor(getResources().getColor(R.color.colorDefault));
        mNavTvName.setText(getString(R.string.more));
    }
    private void setNavToFriend(){
        reset();
        mNavIvFriend.setImageDrawable(getDrawable(R.drawable.characters226blue));
        mNavIvMenu.setImageDrawable(getDrawable(R.drawable.registration227white));
        mNavIvMenu.setVisibility(View.VISIBLE);
        mNavTvFriend.setTextColor(getResources().getColor(R.color.colorDefault));
        mNavTvName.setText(getString(R.string.friend));
    }
    private void setNavToMine(){
        reset();
        mNavIvMine.setImageDrawable(getDrawable(R.drawable.users224blue));
        mNavIvMenu.setImageDrawable(getDrawable(R.drawable.more215white));
        mNavIvMenu.setVisibility(View.VISIBLE);
        mNavTvMine.setTextColor(getResources().getColor(R.color.colorDefault));
        mNavTvName.setText(getString(R.string.mine));
    }
    /**
     * 2秒内点击两次返回键退出APP
     */
    private void exit() {
        int time = 2000;
        if ((System.currentTimeMillis() - mExitTime) > time) {
            new ViewTools().myToast(1,"再按一次退出",NavActivity.this);
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
    /**
     * 对返回键进行监听
     * @param keyCode 返回键ID
     * @param event
     * @return boolean
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}