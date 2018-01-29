package com.sk.lgtea;


import android.app.Application;
import android.content.Context;

import com.aspsine.multithreaddownload.DownloadConfiguration;
import com.aspsine.multithreaddownload.DownloadManager;
import com.github.androidtools.SPUtils;
import com.github.baseclass.view.Loading;
import com.github.retrofitutil.NetWorkManager;
import com.sk.lgtea.tools.download.CrashHandler;

/**
 * Created by administartor on 2017/8/8.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
//        SpeechUtility.createUtility(this, "appid=" + Config.xunfei_app_id);
        super.onCreate();

        NetWorkManager.getInstance(getApplicationContext(),"http://121.40.186.118:1554/",BuildConfig.DEBUG).complete();
        Loading.setLoadView(R.layout.loading_view);
        initDownloader();
        CrashHandler.getInstance(getApplicationContext());
        //二维码
//        ZXingLibrary.initDisplayOpinion(this);
//        ZXingLibrary.initDisplayOpinion(this);
//        SDKInitializer.initialize(getApplicationContext());

//        PlatformConfig.setWeixin(Config.weixing_id, Config.weixing_AppSecret);
//        PlatformConfig.setQQZone(Config.qq_id, Config.qq_key);
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");

//        UMShareAPI.get(this);
//        huanXin();
    }

   /* private void huanXin() {
        ChatClient.Options options = new ChatClient.Options();
        options.setAppkey(Config.hx_appKey);//必填项，appkey获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“AppKey”
        options.setTenantId(Config.hx_tenantId);//必填项，tenantId获取地址：kefu.easemob.com，“管理员模式 > 设置 > 企业信息”页面的“租户ID”

        // Kefu SDK 初始化
        if (!ChatClient.getInstance().init(this, options)){
            return;
        }
        // Kefu EaseUI的初始化
        UIProvider.getInstance().init(this);
        //后面可以设置其他属性
    }*/
   private void initDownloader() {
       DownloadConfiguration configuration = new DownloadConfiguration();
       configuration.setMaxThreadNum(10);
       configuration.setThreadNum(3);
       DownloadManager.getInstance().init(getApplicationContext(), configuration);
   }
   //经度
   public static double longitude;//=121.432986;
    //纬度
   public static double latitude;//=31.229504;

    /**
     * 经度
     * @param context
     * @return
     */
    public static double getJingDu(Context context){
        if(longitude==0){
            longitude=SPUtils.getPrefFloat(context,Config.longitude,0);
            return longitude;
        }else{
            return longitude;
        }
    }

    /**
     * 纬度
     * @param context
     * @return
     */
    public static double getWeiDu(Context context){
        if(latitude==0){
            latitude=SPUtils.getPrefFloat(context,Config.latitude,0);
            return latitude;
        }else{
            return latitude;
        }
    }
    /**
     * 经度
     * @param context
     * @return
     */
    public static double Lng(Context context){
        if(longitude==0){
            longitude=SPUtils.getPrefFloat(context,Config.longitude,0);
            return longitude;
        }else{
            return longitude;
        }
    }
    /**
     * 纬度
     * @param context
     * @return
     */
    public static double Lat(Context context){
        if(latitude==0){
            latitude=SPUtils.getPrefFloat(context,Config.latitude,0);
            return latitude;
        }else{
            return latitude;
        }
    }
}
