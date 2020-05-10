package cn.edu.cqcet.yd1701.team11.smallrooike.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import cn.edu.cqcet.yd1701.team11.smallrooike.R;
import cn.edu.cqcet.yd1701.team11.smallrooike.adapter.MineViewPagerPagerAdapter;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/19 10:23
 */
public class MineItemActivity extends AppCompatActivity implements View.OnClickListener{
    private List<View> mMineListViews = new ArrayList<View>();
    private ViewPager mMineViewPager;
    private ImageView mMineContentLlTitleIvBreak;
    private TextView mMineContentLlTitleTvName;
    private Intent data;
    private int page = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_content);
//        StatusBarUtil.setTranslucent(this, 64);
        initview();
    }
    private void initview(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        mMineListViews.add(layoutInflater.inflate(R.layout.activity_about,null));
        mMineListViews.add(layoutInflater.inflate(R.layout.activity_help,null));
        this.mMineViewPager = findViewById(R.id.mine_content_viewpager);
        this.mMineViewPager.setAdapter(new MineViewPagerPagerAdapter(mMineListViews));
        this.mMineContentLlTitleIvBreak = findViewById(R.id.mine_content_ll_title_iv_break);
        this.mMineContentLlTitleIvBreak.setOnClickListener(this);
        this.mMineContentLlTitleTvName = findViewById(R.id.mine_content_ll_title_tv_name);
        this.data = getIntent();
        this.page = this.data.getIntExtra("page",0);
        switch (this.page){
            case 0:
                mMineContentLlTitleTvName.setText("关于");
                break;
            case 1:
                mMineContentLlTitleTvName.setText("帮助与反馈");
                break;
            default:
                break;
        }
        this.mMineViewPager.setCurrentItem(page);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_content_ll_title_iv_break:
                finish();
                break;
            default:
                break;
        }
    }
}