package cn.edu.cqcet.yd1701.team11.smallrooike.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.cqcet.yd1701.team11.smallrooike.R;
import cn.edu.cqcet.yd1701.team11.smallrooike.beans.LogisticsNewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/17 12:44
 */
public class HomeNewsListViewBaseAdapter extends BaseAdapter {
    private List<LogisticsNewsBean> mLogisticsNewsBeans = new ArrayList<LogisticsNewsBean>();
    private Context context;
    public HomeNewsListViewBaseAdapter(List<LogisticsNewsBean> mLogisticsNewsBeans,Context context){
        this.mLogisticsNewsBeans = mLogisticsNewsBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.mLogisticsNewsBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mLogisticsNewsBeans.get(position).toString();
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
            convertView = View.inflate(context, R.layout.activity_home_lv_content,null);
            viewHolder.mHomeLvContentImage = convertView.findViewById(R.id.home_lv_content_image);
            viewHolder.mHomeLvContentAddress = convertView.findViewById(R.id.home_lv_content_address);
            viewHolder.mHomeLvContentNumber = convertView.findViewById(R.id.home_lv_content_number);
            viewHolder.mHomeLvContentPlatform = convertView.findViewById(R.id.home_lv_content_platform);
            viewHolder.mHomeLvContentStatus = convertView.findViewById(R.id.home_lv_content_status);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LogisticsNewsBean mLogisticsNewsBean = this.mLogisticsNewsBeans.get(position);
        viewHolder.mHomeLvContentNumber.setText("共"+mLogisticsNewsBean.getmQuantity()+"件商品");
        viewHolder.mHomeLvContentStatus.setText(mLogisticsNewsBean.getmStatu());
        viewHolder.mHomeLvContentPlatform.setText(mLogisticsNewsBean.getmPlatform());
        viewHolder.mHomeLvContentAddress.setText(mLogisticsNewsBean.getmAddress());
//        viewHolder.mHomeLvContentImage.setImageBitmap(new ViewUtil().getRoundedCornerBitmap(Bitmap.createBitmap(Color.red()),5);
        return convertView;
    }
    private static class ViewHolder {
        TextView mHomeLvContentNumber, mHomeLvContentStatus, mHomeLvContentPlatform, mHomeLvContentAddress;
        ImageView mHomeLvContentImage;
    }
    private ViewHolder getViewHold(View view){
        if (view.getTag() == null){
            return getViewHold((View) view.getParent());
        }
        return (ViewHolder)view.getTag();
    }
}