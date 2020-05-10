package cn.edu.cqcet.yd1701.team11.smallrooike.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.cqcet.yd1701.team11.smallrooike.R;
import cn.edu.cqcet.yd1701.team11.smallrooike.beans.MineListMapBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/18 15:46
 */
public class MineListViewBaseAdapter extends BaseAdapter {
    private List<MineListMapBean> hashMap = new ArrayList<>();
    private Context context;
    public MineListViewBaseAdapter(List<MineListMapBean> hashMap, Context context){
        this.hashMap = hashMap;
        this.context = context;
    }
    @Override
    public int getCount() {
        return hashMap.size();
    }

    @Override
    public Object getItem(int position) {
        return hashMap.get(position);
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
            convertView = View.inflate(context, R.layout.activity_mine_lv_content,null);
            viewHolder.mineIvContentImage = convertView.findViewById(R.id.mine_iv_content_image);
            viewHolder.mineTvContentName = convertView.findViewById(R.id.mine_tv_content_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MineListMapBean mineListMapBean = this.hashMap.get(position);
        viewHolder.mineIvContentImage.setImageBitmap(mineListMapBean.getImage());
        viewHolder.mineTvContentName.setText(mineListMapBean.getName());
        return convertView;
    }
    private static class ViewHolder{
        private ImageView mineIvContentImage;
        private TextView mineTvContentName;
    }

    private ViewHolder getViewHold(View view){
        if (view.getTag() == null){
            return getViewHold((View) view.getParent());
        }
        return (ViewHolder)view.getTag();
    }
}
