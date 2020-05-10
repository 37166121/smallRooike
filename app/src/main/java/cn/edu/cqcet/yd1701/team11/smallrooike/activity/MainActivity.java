package cn.edu.cqcet.yd1701.team11.smallrooike.activity;
/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/10/30 19:40
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import cn.edu.cqcet.yd1701.team11.smallrooike.R;
import cn.edu.cqcet.yd1701.team11.smallrooike.beans.UserBean;
import cn.edu.cqcet.yd1701.team11.smallrooike.AppData;
import cn.edu.cqcet.yd1701.team11.smallrooike.tools.IpTools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static cn.edu.cqcet.yd1701.team11.smallrooike.customvalue.CustomValue.ADMOB_AD_UNIT_ID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mainBtnSkip;
//    倒计时
    private static int TIME = 5;
//    定时器
    private ScheduledExecutorService mService = Executors.newScheduledThreadPool(TIME);
//    google原生广告
    private UnifiedNativeAd nativeAd;
    private AppData appData;
    private IpTools ipTools = new IpTools();
    private Handler handler;
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
        //等待时间一秒，停顿时间一秒
        mService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //从闪屏界面跳转到首界面
                // UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TIME--;
                        mainBtnSkip.setText("跳过 " + TIME);
                        if (TIME < 0) {
                            mainBtnSkip.setVisibility(View.GONE);//倒计时到0隐藏字体
                        }
                    }
                });
            }
        }, 1, 1, TimeUnit.SECONDS);
        /**
         * 正常情况下不点击跳过
         */
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, NavActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME*1000);//延迟5S后发送handler信息
    }
    private void initDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        appData = (AppData) getApplication();
        appData.setIp(ipTools.getIPAddress(this));
        SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        if (preferences.contains("uuid")){
            appData.setUserBean(new UserBean());
            appData.getUserBean().setUuid(preferences.getString("uuid",""));
            appData.getUserBean().setNickname(preferences.getString("nickname",""));
            appData.getUserBean().setEmail(preferences.getString("email",""));
            appData.getUserBean().setTel(preferences.getString("tel",""));
            appData.getUserBean().setUsername(preferences.getString("username",""));
            appData.getUserBean().setSex(preferences.getString("sex",""));
            try {
                appData.getUserBean().setDob(simpleDateFormat.parse(preferences.getString("dob","")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            appData.getUserBean().setHome(preferences.getString("home",""));
        }
        googleAdMob();
    }
    private void initView(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        this.mainBtnSkip = findViewById(R.id.main_btn_skip);
        mainBtnSkip.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_btn_skip) {
            Intent intent = new Intent(MainActivity.this, NavActivity.class);
            startActivity(intent);
            finish();
            if (runnable != null) {
                handler.removeCallbacks(runnable);
            }
        }
    }

    /**
     * 获取广告
     */
    private void googleAdMob(){
//        我的ID：ca-app-pub-9731120967538789/6608474210
//        Google广告测试ID：
//        横幅广告	ca-app-pub-3940256099942544/6300978111
//        插页式广告	ca-app-pub-3940256099942544/1033173712
//        插页式视频广告	ca-app-pub-3940256099942544/8691691433
//        激励视频广告	ca-app-pub-3940256099942544/5224354917
//        原生高级广告	ca-app-pub-3940256099942544/2247696110
//        原生高级视频广告	ca-app-pub-3940256099942544/1044960115
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        refreshAd();
    }

    /**
     * 重新获取广告
     */
    private void refreshAd() {
        AdLoader.Builder builder = new AdLoader.Builder(this, ADMOB_AD_UNIT_ID);
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            // OnUnifiedNativeAdLoadedListener implementation.
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                // You must call destroy on old ads when you are done with them,
                // otherwise you will have a memory leak.
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                nativeAd = unifiedNativeAd;
                FrameLayout frameLayout =
                        findViewById(R.id.fl_adplaceholder);
                UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.my_ad_layout, null);
                populateUnifiedNativeAdView(unifiedNativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
                style();
            }

        });

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(MainActivity.this, "Failed to load native ad: "
                        + errorCode, Toast.LENGTH_SHORT).show();
            }
        }).build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

    /**
     * 展示广告
     * @param nativeAd
     * @param adView
     */
    private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
        // Set the media view.
        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));

        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
//        VideoController vc = nativeAd.getVideoController();
//
//        // Updates the UI to say whether or not this ad has a video asset.
//        if (vc.hasVideoContent()) {
//            videoStatus.setText(String.format(Locale.getDefault(),
//                    "Video status: Ad contains a %.2f:1 video asset.",
//                    vc.getAspectRatio()));
//
//            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
//            // VideoController will call methods on this object when events occur in the video
//            // lifecycle.
//            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
//                @Override
//                public void onVideoEnd() {
//                    // Publishers should allow native ads to complete video playback before
//                    // refreshing or replacing them with another ad in the same UI location.
//                    refresh.setEnabled(true);
//                    videoStatus.setText("Video status: Video playback has ended.");
//                    super.onVideoEnd();
//                }
//            });
//        } else {
//            videoStatus.setText("Video status: Ad does not contain a video asset.");
//            refresh.setEnabled(true);
//        }
    }

    /**
     * 弹出广告时改变样式
     */
    private void style(){
        ConstraintLayout constraintLayout = findViewById(R.id.main_cl);
        LinearLayout linearLayout = findViewById(R.id.main_ll_appconfig);
        ImageView mMainIv1 = findViewById(R.id.main_iv_1);
        TextView mMainTvAppname = findViewById(R.id.main_tv_appname);
        TextView mMainTvTitle = findViewById(R.id.main_tv_title);

        constraintLayout.setBackgroundColor(this.getResources().getColor(R.color.colorWhite));
        linearLayout.setBackgroundColor(this.getResources().getColor(R.color.colorDefault));
        mainBtnSkip.setTextColor(this.getResources().getColor(R.color.colorDefault));
        mMainIv1.setBackgroundColor(this.getResources().getColor(R.color.colorWhite));
        mMainTvAppname.setTextColor(this.getResources().getColor(R.color.colorWhite));
        mMainTvTitle.setVisibility(View.GONE);
    }
}