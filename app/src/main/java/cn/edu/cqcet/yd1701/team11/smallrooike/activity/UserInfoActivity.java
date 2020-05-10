package cn.edu.cqcet.yd1701.team11.smallrooike.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.edu.cqcet.yd1701.team11.smallrooike.R;
import cn.edu.cqcet.yd1701.team11.smallrooike.adapter.UserInfoBaseAdapter;
import cn.edu.cqcet.yd1701.team11.smallrooike.AppData;
import cn.edu.cqcet.yd1701.team11.smallrooike.customclicklistener.CustomTouchListener;
import cn.edu.cqcet.yd1701.team11.smallrooike.tools.ViewTools;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView userLlTitleBack;//返回
    private TextView userLlTitleTvSave;//保存
    private TextView userLlTitleTvName;//界面标题
    private ImageView userInfoIvHead;
    private TextView userInfoTvUsername;
    private ListView userInfolistView;
    private AppData appData;
    private Calendar ca = Calendar.getInstance();
    private int  mYear = ca.get(Calendar.YEAR);
    private int  mMonth = ca.get(Calendar.MONTH);
    private int  mDay = ca.get(Calendar.DAY_OF_MONTH);
    private UserInfoBaseAdapter userInfoBaseAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initData();
        initView();
        setListener();
    }
    private void initView(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<String> userBeanList = new ArrayList<>();
        List<String> title = new ArrayList<>();
        userLlTitleTvName = findViewById(R.id.user_ll_title_tv_name);
        userLlTitleTvName.setText("个人资料");
        userLlTitleBack = findViewById(R.id.user_ll_title_back);
        userLlTitleBack.setVisibility(View.VISIBLE);
        userLlTitleTvSave = findViewById(R.id.user_ll_title_save);
        userLlTitleTvSave.setVisibility(View.VISIBLE);
        userInfoIvHead = findViewById(R.id.user_info_iv_head);
        userInfoTvUsername = findViewById(R.id.user_info_tv_username);
        userInfolistView = findViewById(R.id.user_info_list);
        userInfolistView.setOnItemClickListener(this);
        userInfoTvUsername.setText(appData.getUserBean().getNickname());
        userBeanList.add(appData.getUserBean().getUsername());
        userBeanList.add(appData.getUserBean().getSex());
        userBeanList.add(appData.getUserBean().getTel());
        userBeanList.add(appData.getUserBean().getEmail());
        userBeanList.add(simpleDateFormat.format(appData.getUserBean().getDob()));
        userBeanList.add(appData.getUserBean().getHome());
        title = Arrays.asList(getResources().getStringArray(R.array.user_info_list));
        userInfoBaseAdapter = new UserInfoBaseAdapter(title,userBeanList,this);
        userInfolistView.setAdapter(userInfoBaseAdapter);
    }
    private void initData() {
        appData = (AppData) getApplication();
    }
    private void setListener() {
        userLlTitleBack.setOnTouchListener(new CustomTouchListener() {
            @Override
            protected void onSingleClick() {

            }

            @Override
            protected void onFastClick() {

            }
        });
        userLlTitleTvSave.setOnTouchListener(new CustomTouchListener() {
            @Override
            protected void onSingleClick() {

            }

            @Override
            protected void onFastClick() {

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_ll_title_back:
                this.finish();
                break;
            case R.id.user_ll_title_save:
                new ViewTools().myToast(0,"修改成功",UserInfoActivity.this);
                this.finish();
                break;
            default:
                break;
        }
    }

    /**
     * @param sex 性别弹出框
     */
    private void sexDialog(String sex) {
        int sexFlag = 0;
        if("男".equals(sex)){
            sexFlag = 0;
        }else if("女".equals(sex)){
            sexFlag = 1;
        }
        final String[] items = {"男","女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//获取构造器
        builder.setTitle("性别");
        builder.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(UserInfoActivity.this,items[which],Toast.LENGTH_SHORT).show();
//                setSex(items[which]);
            }
        });
        builder.create().show();
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
                userInfoBaseAdapter.userBean.set(4,year + "-" + month + "-" + dayOfMonth);
            }
        });
        datePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                setDate();
                break;
            case 5:
                break;
            default:break;
        }
    }
}
