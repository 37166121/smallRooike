package cn.edu.cqcet.yd1701.team11.smallrooike.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import cn.edu.cqcet.yd1701.team11.smallrooike.R;
import cn.edu.cqcet.yd1701.team11.smallrooike.adapter.PostTakeViewPagerAdapter;
import cn.edu.cqcet.yd1701.team11.smallrooike.customview.NoScrollViewPager;

public class PostTakeActivity extends AppCompatActivity implements View.OnClickListener {
    private NoScrollViewPager viewPager;
    private List<View> views = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_take);
        initView();
    }
    private void initView(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        views.add(LayoutInflater.from(this).inflate(R.layout.activity_post,null));
        views.add(LayoutInflater.from(this).inflate(R.layout.activity_take,null));
        viewPager = findViewById(R.id.post_take_viewpager);
        viewPager.setAdapter(new PostTakeViewPagerAdapter(views));
    }
    private void initDate(){

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pt_ll_title_iv_break:
                finish();
                break;
        }
    }
}
