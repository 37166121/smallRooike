package cn.edu.cqcet.yd1701.team11.smallrooike.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import cn.edu.cqcet.yd1701.team11.smallrooike.R;
import cn.edu.cqcet.yd1701.team11.smallrooike.beans.UserBean;
import cn.edu.cqcet.yd1701.team11.smallrooike.tools.ViewTools;

import static android.graphics.Bitmap.Config.RGB_565;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/24 2:05 星期五
 */
public class FriendListBaseAdapter extends RecyclerView.Adapter<FriendListBaseAdapter.ViewHolder> {
    private List<UserBean> userBeans = new ArrayList<>();
    private Context context;
    protected LayoutInflater mInflater;
    private int mItemLayoutId;
    private ViewTools viewTools;
    public FriendListBaseAdapter(List<UserBean> userBeans,Context context){
        this.userBeans = userBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendListBaseAdapter.ViewHolder holder, int position) {
        UserBean userBean = userBeans.get(position);
        Bitmap bitmap = Bitmap.createBitmap(50, 50,Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(context.getResources().getColor(R.color.colorok));
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint .setTextSize(100);
        paint .setColor(Color.GREEN);
        paint .setFlags(1);
        paint .setStyle(Paint.Style.FILL);
        canvas.drawText("", 100, 100, paint);
        viewTools = new ViewTools();
        holder.friendContentNickname.setText(userBean.getNickname());
        holder.friendContentHome.setText(userBean.getHome());

        holder.friend_content_status.setImageBitmap(viewTools.getRoundedCornerBitmap(bitmap,25));
    }

    @Override
    public int getItemCount() {
        return userBeans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView friendContentNickname;
        TextView friendContentHome;
        ImageView friend_content_head;
        ImageView friend_content_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            friendContentNickname=itemView.findViewById(R.id.friend_content_nickname);
            friendContentHome=itemView.findViewById(R.id.friend_content_home);
            friend_content_head = itemView.findViewById(R.id.friend_content_head);
            friend_content_status = itemView.findViewById(R.id.friend_content_status);
        }
    }
}
