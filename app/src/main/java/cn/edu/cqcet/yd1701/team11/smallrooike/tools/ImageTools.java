package cn.edu.cqcet.yd1701.team11.smallrooike.tools;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;

import cn.edu.cqcet.yd1701.team11.smallrooike.activity.LoginActivity;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/23 19:28 星期四
 */
public class ImageTools {
    private Uri uriClipUri;
    /**
     * 图片的裁剪和压缩
     * @param uri
     */
    public void startPhotoZoom(Uri uri,int TAKEPAHTO,Context context,Activity activity) {
        Log.e("uri=====", "" + uri);
        //com.android.camera.action.CROP，这个action是调用系统自带的图片裁切功能
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");//裁剪的图片uri和图片类型
        intent.putExtra("crop", "true");//设置允许裁剪，如果不设置，就会跳过裁剪的过程，还可以设置putExtra("crop", "circle")
        intent.putExtra("aspectX", 1);//裁剪框的 X 方向的比例,需要为整数
        intent.putExtra("aspectY", 1);//裁剪框的 Y 方向的比例,需要为整数
        intent.putExtra("outputX", 60);//返回数据的时候的X像素大小。
        intent.putExtra("outputY", 60);//返回数据的时候的Y像素大小。
        //uritempFile为Uri类变量，实例化uritempFile
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (TAKEPAHTO == 1) {//如果是7.0的拍照
                //开启临时访问的读和写权限
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                //针对7.0以上的操作
                intent.setClipData(ClipData.newRawUri(MediaStore.EXTRA_OUTPUT, uri));
                uriClipUri = uri;
            } else {//如果是7.0的相册
                //设置裁剪的图片地址Uri
                uriClipUri = Uri.parse("file://" + "/" + context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath() + "/" + "clip.jpg");
            }
        } else {
            uriClipUri = Uri.parse("file://" + "/" + context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath() + "/" + "clip.jpg");
        }
        Log.e("uriClipUri=====", "" + uriClipUri);
        //Android 对Intent中所包含数据的大小是有限制的，一般不能超过 1M，否则会使用缩略图 ,所以我们要指定输出裁剪的图片路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriClipUri);
        intent.putExtra("return-data", false);//是否将数据保留在Bitmap中返回
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出格式，一般设为Bitmap格式及图片类型
        intent.putExtra("noFaceDetection", true);//人脸识别功能
        activity.startActivityForResult(intent, LoginActivity.CROP_SMALL_PICTURE);//裁剪完成的标识
    }
    /**
     * 图片压缩的方法(只是内存减少，避免oom，图片本身在disk盘体积不变)
     * 显示的Bitmap占用的内存少一点，还是需要去设置加载的像素长度和宽度（变成缩略图）
     */
    public Bitmap compressPhto(File mFile){
//        BitmapFactory这个类就提供了多个解析方法（decodeResource、decodeStream、decodeFile等）用于创建Bitmap。
//        比如如果图片来源于网络，就可以使用decodeStream方法；
//        如果是sd卡里面的图片，就可以选择decodeFile方法；
//        如果是资源文件里面的图片，就可以使用decodeResource方法等
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 获取当前图片的边界大小
        //BitmapFactory.decodeResource(getResources(), R.drawable.bg, options);
        BitmapFactory.decodeFile(mFile.getAbsolutePath(),options);
        int outHeight = options.outHeight; //获取图片本身的高像素
        int outWidth = options.outWidth;//获取图片本身的宽的像素
        String outMimeType = options.outMimeType;
        options.inJustDecodeBounds = false;
        //inSampleSize的作用就是可以把图片的长短缩小inSampleSize倍，所占内存缩小inSampleSize的平方
        //对于inSampleSize值的大小有要求，最好是整数且2的倍数
        options.inSampleSize = caculateSampleSize(options, 500, 500);
        //etPath()得到的是构造file的时候的路径。getAbsolutePath()得到的是全路径
        String path =mFile.getPath();
        String absPath=mFile.getAbsolutePath();
        return BitmapFactory.decodeFile(absPath,options);
//        ivUserPhoto.setImageBitmap(bitmap);
        //尺寸压缩结果
//        ivSize.setImageBitmap(bitmap);
    }
    /**
     * 计算出所需要压缩的大小
     * @param options
     * @param reqWidth  希望的图片宽大小
     * @param reqHeight 希望的图片高大小
     * @return
     */
    private int caculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int sampleSize = 1;
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        if (picWidth > reqWidth || picHeight > reqHeight) {
            int halfPicWidth = picWidth / 2;
            int halfPicHeight = picHeight / 2;
            while (halfPicWidth / sampleSize > reqWidth || halfPicHeight / sampleSize > reqHeight) {
                sampleSize *= 2;
            }
        }
        return sampleSize;
    }
}
