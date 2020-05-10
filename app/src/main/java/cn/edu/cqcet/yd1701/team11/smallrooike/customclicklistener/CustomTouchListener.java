package cn.edu.cqcet.yd1701.team11.smallrooike.customclicklistener;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/30 1:49 星期四
 */
public abstract class CustomTouchListener implements View.OnTouchListener {
    private long mLastClickTime;
    private long timeInterval = 1000L;

    public CustomTouchListener() {

    }

    public CustomTouchListener(long interval) {
        this.timeInterval = interval;
    }

    protected abstract void onSingleClick();
    protected abstract void onFastClick();

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        long nowTime = System.currentTimeMillis();
        if (nowTime - mLastClickTime > timeInterval) {
            // 单次点击事件
            onSingleClick();
            mLastClickTime = nowTime;
        } else {
            // 快速点击事件
            onFastClick();
        }
        return false;
    }
}
