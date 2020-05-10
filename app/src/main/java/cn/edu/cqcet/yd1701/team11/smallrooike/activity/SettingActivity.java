package cn.edu.cqcet.yd1701.team11.smallrooike.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import cn.edu.cqcet.yd1701.team11.smallrooike.R;
import cn.edu.cqcet.yd1701.team11.smallrooike.AppData;
import cn.edu.cqcet.yd1701.team11.smallrooike.tools.ViewTools;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/29 16:29
 */
public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button settingSwitchUser;
    private ViewTools viewTools = new ViewTools();
    private ImageView settingLlTitleBack;
    private AppData appData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initData();
    }
    private void initView(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        settingSwitchUser = findViewById(R.id.setting_switch_user);
        settingSwitchUser.setOnClickListener(this);
        settingLlTitleBack = findViewById(R.id.setting_ll_title_back);
        settingLlTitleBack.setOnClickListener(this);
    }
    private void initData(){
        appData = (AppData) getApplication();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_switch_user:
                SharedPreferences sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();
                appData.setUserBean(null);
                viewTools.myToast(0,"已退出",SettingActivity.this);
                Intent intent = new Intent(SettingActivity.this, NavActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.setting_ll_title_back:
                finish();
                break;
            default:break;
        }
    }
}
