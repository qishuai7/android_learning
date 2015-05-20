
package com.alibaba.uilearning.activity;

import com.alibaba.uilearning.R;
import com.alibaba.uilearning.activity.service.IRemoteService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

/**
 * 远程调用service(AIDL)
 * 
 * @author shuai.qi
 */
public class ClicetPlayerActivity extends Activity {
    public static final String TAG = "ClicetPlayerActivity";

    // 服务端 AndroidManifest.xml中的intent-filter action声明的字符串
    public static final String ACTION = "com.alibaba.uilearning.activity.service.PlayerService";

    private IRemoteService mService;

    private boolean isBinded = false;

    private Button mStartPlayerBtn = null;
    private Button mStopPlayerBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_service);

        initView();
        doBind();
    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBinded = false;
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IRemoteService.Stub.asInterface(service);
            isBinded = true;
        }
    };
    
    public void doBind() {
        Intent intent = new Intent(ACTION);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    public void doUnbind() {
        if (isBinded) {
            unbindService(conn);
            mService = null;
            isBinded = false;
        }
    }
    
    

    private void initView() {
        mStartPlayerBtn = (Button) findViewById(R.id.dialog_loading_default_btn);
        mStopPlayerBtn = (Button) findViewById(R.id.dialog_loading_with_cancle_btn);

        mStartPlayerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    mService.play();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        mStopPlayerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    mService.stop();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}
