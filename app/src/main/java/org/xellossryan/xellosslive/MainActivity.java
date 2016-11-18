package org.xellossryan.xellosslive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.xellossryan.playerlib.widgets.IjkVideoView;

public class MainActivity extends AppCompatActivity {

    private org.xellossryan.playerlib.widgets.IjkVideoView myplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.myplayer = (IjkVideoView) findViewById(R.id.myplayer);

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
