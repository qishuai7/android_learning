
package com.alibaba.uilearning.activity.service;

import java.io.FileDescriptor;
import java.io.IOException;

import com.alibaba.uilearning.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * 播放音乐的服务
 */
public class PlayerService extends Service {

    public static final String TAG = "PlayerService";

    private MediaPlayer mplayer;

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "service onbind");
        if (mplayer == null) {
            // 方法一说明
            // 此方法实例化播放器的同时指定音乐数据源 ,若用此方法在，mplayer.start() 之前不需再调用mplayer.prepare()
            // 官方文档有说明 ：On success, prepare() will already have been called and must not be called again.
            // 译文：一旦create成功，prepare已被调用，勿再调用 。查看源代码可知create方法内部已经调用prepare方法。
            // 方法一开始
            // mplayer = MediaPlayer.create(this, R.raw.lost);
            // 方法一结束

            // 方法二说明
            // 若用此方法，在mplayer.start() 之前需要调用mplayer.prepare()
            // 方法二开始
            mplayer = new MediaPlayer();
            try {
                FileDescriptor fd = getResources().openRawResourceFd(R.raw.lost).getFileDescriptor(); // 获取音乐数据源
                mplayer.setDataSource(fd); // 设置数据源
                mplayer.setLooping(true); // 设为循环播放
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 方法二结束
            Log.i(TAG, "player created");
        }
        return mBinder;
    }

    // 实现aidl文件中定义的接口
    private IBinder mBinder = new IRemoteService.Stub() {

        @Override
        public void stop() throws RemoteException {
            try {
                if (mplayer.isPlaying()) {
                    mplayer.stop();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void play() throws RemoteException {
            try {
                if (mplayer.isPlaying()) {
                    return;
                }
                // start之前需要prepare。
                // 如果前面实例化mplayer时使用方法一，则第一次play的时候直接start，不用prepare。
                // 但是stop一次之后,再次play就需要在start之前prepare了。
                // 前面使用方法二 这里就简便了， 不用判断各种状况
                mplayer.prepare();
                mplayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public boolean onUnbind(Intent intent) {
        if (mplayer != null) {
            mplayer.release();
        }
        Log.i(TAG, "service onUnbind");
        return super.onUnbind(intent);
    }
}
