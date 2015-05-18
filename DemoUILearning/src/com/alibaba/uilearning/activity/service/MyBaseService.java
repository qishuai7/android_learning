
package com.alibaba.uilearning.activity.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBaseService extends Service {
    public static final String TAG = "MyBaseService";

    // bind专用
    private MyBinder myBinder = new MyBinder();

    public class MyBinder extends Binder {
        public MyBaseService getService() {
            return MyBaseService.this;
        }
    }
    
    public void testBindMethod(){
        Log.i(TAG, "testBindMethod()");
    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.i(TAG, "onBind()");
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "onStart()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG, "onRebind()");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
        super.onDestroy();
    }
}
