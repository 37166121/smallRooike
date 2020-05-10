package cn.edu.cqcet.yd1701.team11.smallrooike.tools;

import android.annotation.SuppressLint;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/12 10:03
 */
public class TypeConversionTools {
    /**
     * String转MD5
     * @param str 需要转MD5的字符串
     * @return str
     */
    public static String strToMd5(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (byte b : hash) {
                if ((0xff & b) < 0x10) {
                    hexString.append("0").append(Integer.toHexString((0xFF & b)));
                } else {
                    hexString.append(Integer.toHexString(0xFF & b));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }

    /**
     * Date转String
     * @param date 需要转换的日期
     * @return 转换后的日期 yyyy-mm-dd
     */
    public static String dateToStr(Date date){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        ParsePosition pos = new ParsePosition(8);
        return formatter.parse(dateString, pos).toString();
    }
}
