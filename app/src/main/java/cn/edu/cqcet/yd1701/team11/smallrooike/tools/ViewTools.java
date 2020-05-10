package cn.edu.cqcet.yd1701.team11.smallrooike.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.*;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.cqcet.yd1701.team11.smallrooike.R;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/8 17:22
 */
public class ViewTools {
    /**
     * 自定义Toast
     * @param code 信息码 0 成功 1 警告 2 严重
     * @param value toast窗口内容
     */
    public void myToast(int code, String value,Context context){
        try{
            toast(code,value,context);
        } catch (Exception e){
            Looper.prepare();
            toast(code,value,context);
            Looper.loop();
        }
    }
    private void toast(int s, String value,Context context){
        Toast toast=new Toast(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") View view =inflater.inflate(R.layout.activity_custom_toast,null);
        ImageView imageView=view.findViewById(R.id.custom_toast_iv_image);
        TextView textView=view.findViewById(R.id.custom_toast_tv_text);
        switch (s){
            case 0:
                imageView.setImageResource(R.drawable.satisfied249green);
                textView.setTextColor(context.getResources().getColor(R.color.colorok));
                break;
            case 1:
                imageView.setImageResource(R.drawable.satisfied250blue);
                textView.setTextColor(context.getResources().getColor(R.color.colorDefault));
                break;
            case 2:
                imageView.setImageResource(R.drawable.satisfied251red);
                textView.setTextColor(context.getResources().getColor(R.color.colorerror));
                break;
            default:
                break;
        }
        textView.setText(value);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
    /**
     * 图片圆角
     * @param bitmap 图片
     * @param roundPx 弧度
     * @return bitmap
     */
    public Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, w, h);
        final RectF rectf = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectf, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
