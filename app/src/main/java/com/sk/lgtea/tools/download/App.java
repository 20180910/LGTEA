package com.sk.lgtea.tools.download;

import android.app.Application;
import android.content.Context;

import com.aspsine.multithreaddownload.DownloadConfiguration;
import com.aspsine.multithreaddownload.DownloadManager;

/**
 * Created by Aspsine on 2015/4/20.
 */
public class App extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        initDownloader();
        CrashHandler.getInstance(sContext);
    }

    private void initDownloader() {
        DownloadConfiguration configuration = new DownloadConfiguration();
        configuration.setMaxThreadNum(10);
        configuration.setThreadNum(1);
        DownloadManager.getInstance().init(getApplicationContext(), configuration);
    }

    public static Context getContext() {
        return sContext;
    }


}
