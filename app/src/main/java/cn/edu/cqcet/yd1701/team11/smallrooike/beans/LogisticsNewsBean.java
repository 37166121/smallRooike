package cn.edu.cqcet.yd1701.team11.smallrooike.beans;
/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/10/30 19:40
 */

import android.graphics.Bitmap;

/**
 * This is mHome_lv_LogisticsNews's Beans
 */
public class LogisticsNewsBean {
//    图片
    private Bitmap mImage;
//    数量
    private int mQuantity;
//    签收状态
    private String mStatu;
//    平台
    private String mPlatform;
//    地址
    private String mAddress;

    public LogisticsNewsBean(Bitmap mImage, int mQuantity, String mStatu, String mPlatform, String mAddress) {
        this.mImage = mImage;
        this.mQuantity = mQuantity;
        this.mStatu = mStatu;
        this.mPlatform = mPlatform;
        this.mAddress = mAddress;
    }

    public LogisticsNewsBean() {

    }

    public Bitmap getmImage() {
        return mImage;
    }

    public void setmImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmStatu() {
        return mStatu;
    }

    public void setmStatu(String mStatu) {
        this.mStatu = mStatu;
    }

    public String getmPlatform() {
        return mPlatform;
    }

    public void setmPlatform(String mPlatform) {
        this.mPlatform = mPlatform;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    @Override
    public String toString() {
        return "LogisticsNewsBean{" +
                "mImage=" + mImage +
                ", mQuantity=" + mQuantity +
                ", mStatu='" + mStatu + '\'' +
                ", mPlatform='" + mPlatform + '\'' +
                ", mAddress='" + mAddress + '\'' +
                '}';
    }
}
