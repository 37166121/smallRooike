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
 * @create 2019/11/19 16:30
 */
public class MineViewPagerPagerAdapter extends PagerAdapter {
    private List<View> mMineListViews;
    public MineViewPagerPagerAdapter(List<View> mNavListViews){
        this.mMineListViews = mNavListViews;
    }
    @Override
    public int getCount() {
        return mMineListViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mMineListViews.get(position));
        return mMineListViews.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mMineListViews.get(position));
    }
}
