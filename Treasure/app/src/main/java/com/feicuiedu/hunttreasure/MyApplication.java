package com.feicuiedu.hunttreasure;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

/**
 * Created by Administrator on 2017/1/4.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
