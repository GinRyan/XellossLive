package org.xellossryan.xellosslive.app;

import android.app.ActivityManager;
import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

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
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
