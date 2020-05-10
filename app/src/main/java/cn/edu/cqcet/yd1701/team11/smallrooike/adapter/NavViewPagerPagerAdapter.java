package cn.edu.cqcet.yd1701.team11.smallrooike.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/17 12:38
 */
public class NavViewPagerPagerAdapter extends PagerAdapter {
    private List<View> mNavListViews;
    public NavViewPagerPagerAdapter(List<View> mNavListViews){
        this.mNavListViews = mNavListViews;
    }
    @Override
    public int getCount() {
        return mNavListViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mNavListViews.get(position));
        return mNavListViews.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mNavListViews.get(position));
    }
}