package com.senierr.demo;

import android.app.Application;

import com.senierr.push.PushManager;

/**
 * @author zhouchunjie
 * @date 2018/6/12
 */
public class PushApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PushManager.register(this);
    }
}
