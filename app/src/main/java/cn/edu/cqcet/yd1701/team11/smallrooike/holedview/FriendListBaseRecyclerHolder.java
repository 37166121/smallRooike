package cn.edu.cqcet.yd1701.team11.smallrooike.holedview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.edu.cqcet.yd1701.team11.smallrooike.beans.UserBean;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/24 2:38 星期五
 */
public class FriendListBaseRecyclerHolder extends ViewHolder {
    private View mConvertView;
    private SparseArray<View> mViews;
    public FriendListBaseRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        mConvertView = itemView;
        this.mViews = new SparseArray<>();
    }
    public static FriendListBaseRecyclerHolder get(Context context, ViewGroup parent, int layoutId, int position) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new FriendListBaseRecyclerHolder(view);
    }
    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    public View getConvertView() {
        return mConvertView;
    }
}
