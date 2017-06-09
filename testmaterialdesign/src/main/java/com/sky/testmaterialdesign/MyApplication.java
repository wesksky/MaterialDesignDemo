package com.sky.testmaterialdesign;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by sky on 2017/6/9.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
