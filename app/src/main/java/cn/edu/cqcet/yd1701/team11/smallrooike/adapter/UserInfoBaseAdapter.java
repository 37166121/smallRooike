package cn.edu.cqcet.yd1701.team11.smallrooike.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.cqcet.yd1701.team11.smallrooike.R;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/21 3:21 星期二
 */
public class UserInfoBaseAdapter extends BaseAdapter {
    private List<String> title = new ArrayList<>();
    public List<String> userBean = new ArrayList<>();
    private Context context;
    public UserInfoBaseAdapter(List<String> title,List<String> userBean, Context context){
        this.title = title;
        this.userBean = userBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int position) {
        return title.get(position) + ":" + userBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.user_info_item,null);
            viewHolder.userInfoContentTvTitle = convertView.findViewById(R.id.user_info_item_tv_title);
            viewHolder.userInfoContentTvContent = convertView.findViewById(R.id.user_info_item_tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.userInfoContentTvTitle.setText(title.get(position));
        viewHolder.userInfoContentTvContent.setText(userBean.get(position));
        return convertView;
    }
    private static class ViewHolder {
        TextView userInfoContentTvTitle;
        TextView userInfoContentTvContent;
    }
    private ViewHolder getViewHold(View view){
        if (view.getTag() == null){
            return getViewHold((View) view.getParent());
        }
        return (ViewHolder)view.getTag();
    }
}
