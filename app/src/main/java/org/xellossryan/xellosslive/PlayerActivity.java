package org.xellossryan.xellosslive;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;

import com.elvishew.xlog.XLog;

import org.xellossryan.playerlib.widgets.IMediaController;
import org.xellossryan.playerlib.widgets.IjkVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 播放器
 *
 * @Author Xelloss
 * @Date 2016-11-18
 */
public class PlayerActivity extends AppCompatActivity {
    private static final String TAG = "VideoActivity";

    private String mVideoPath;
    private Uri mVideoUri;
    private org.xellossryan.playerlib.widgets.IjkVideoView myplayer;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_player);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP);

        this.myplayer = (IjkVideoView) findViewById(R.id.myplayer);
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        Intent intent = getIntent();
        String intentAction = intent.getAction();
        XLog.d(intentAction);
        if (!TextUtils.isEmpty(intentAction)) {
            if (intentAction.equals(Intent.ACTION_VIEW)) {
                mVideoPath = intent.getDataString();
            } else if (intentAction.equals(Intent.ACTION_SEND)) {
                mVideoUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    String scheme = mVideoUri.getScheme();
                    if (TextUtils.isEmpty(scheme)) {
                        Log.e(TAG, "Null unknown scheme\n");
                        finish();
                        return;
                    }
                    if (scheme.equals(ContentResolver.SCHEME_ANDROID_RESOURCE)) {
                        mVideoPath = mVideoUri.getPath();
                    } else if (scheme.equals(ContentResolver.SCHEME_CONTENT)) {
                        Log.e(TAG, "Can not resolve content below Android-ICS\n");
                        finish();
                        return;
                    } else {
                        Log.e(TAG, "Unknown scheme " + scheme + "\n");
                        finish();
                        return;
                    }
                }
            }
        }
        myplayer.setMediaController(new IMediaController() {
            @Override
            public void hide() {
                getSupportActionBar().hide();
            }

            @Override
            public boolean isShowing() {
                return getSupportActionBar().isShowing();
            }

            @Override
            public void setAnchorView(View view) {

            }

            @Override
            public void setEnabled(boolean enabled) {

            }

            @Override
            public void setMediaPlayer(MediaController.MediaPlayerControl player) {

            }

            @Override
            public void show(int timeout) {
                getSupportActionBar().show();
            }

            @Override
            public void show() {

            }

            @Override
            public void showOnce(View view) {

            }
        });
        if (mVideoPath != null)
            myplayer.setVideoPath(mVideoPath);
        else if (mVideoUri != null)
            myplayer.setVideoURI(mVideoUri);
        else {
            Log.e(TAG, "Null Data Source\n");
            finish();
            return;
        }
        myplayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        IjkMediaPlayer.native_profileEnd();
        if (myplayer != null) {
            myplayer.stopPlayback();
            myplayer.stopBackgroundPlay();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
