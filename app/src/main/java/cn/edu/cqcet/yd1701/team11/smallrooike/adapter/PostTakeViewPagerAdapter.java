package cn.edu.cqcet.yd1701.team11.smallrooike.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/30 2:00 星期四
 */
public class PostTakeViewPagerAdapter extends PagerAdapter {
    private List<View> postTakeViews = new ArrayList<>();
    public PostTakeViewPagerAdapter(List<View> postTakeViews){
        this.postTakeViews = postTakeViews;
    }
    @Override
    public int getCount() {
        return postTakeViews.size();
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(postTakeViews.get(position));
        return postTakeViews.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(postTakeViews.get(position));
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
