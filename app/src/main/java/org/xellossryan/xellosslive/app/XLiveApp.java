package org.xellossryan.xellosslive.app;

import android.app.ActivityManager;
import android.app.Application;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

import org.xellossryan.xellosslive.BuildConfig;

/**
 * Created by Liang on 2016/11/18.
 */
public class XLiveApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XLog.init(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE);
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    }
}
