
package com.alibaba.uilearning.activity;

import com.alibaba.uilearning.R;
import com.alibaba.uilearning.activity.service.MyBaseService;
import com.alibaba.uilearning.activity.service.MyBaseService.MyBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/**
 * 测试service
 * @author shuai.qi
 */
public class ServiceActivity extends Activity {
    public static final String TAG = "ServiceActivity";
    
    private Button mStartServiceBtn = null;
    private Button mStopServiceBtn = null;
    
    private boolean isBindServiceFlag = false;
    private Button mBindServiceBtn = null;
    private Button mUnbindServiceBtn = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        initView();
        initClick();
    }

    private void initView(){
        mStartServiceBtn = (Button)findViewById(R.id.service_btn_start_Service);
        mStopServiceBtn = (Button)findViewById(R.id.service_btn_stop_Service);
        mBindServiceBtn = (Button)findViewById(R.id.service_btn_bind_Service);
        mUnbindServiceBtn = (Button)findViewById(R.id.service_btn_unbind_Service);
    }
    
    @Override
    protected void onDestroy() {
        myUnbindService();
        super.onDestroy();
    }
    
    private void initClick(){
        final Intent intentService = new Intent(this, MyBaseService.class);
        
        mStartServiceBtn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                startService(intentService);
            }
        });
        
        mStopServiceBtn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                stopService(intentService);
            }
        });
        
        mBindServiceBtn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                myBindService();
            }
        });
        
        mUnbindServiceBtn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                myUnbindService();
            }
        });
    }
    
    //-------------bind service----------------------
    private void myBindService(){
        Intent intent = new Intent(ServiceActivity.this,MyBaseService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }
    
    private void myUnbindService(){
        if(isBindServiceFlag){
            unbindService(conn);
        }
        isBindServiceFlag = false;
    }
    
    private ServiceConnection conn = new ServiceConnection() {
        
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected...");
        }
        
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected...");
            MyBinder binder = (MyBinder) service;
            MyBaseService bindService = binder.getService();
            bindService.testBindMethod();
            
            isBindServiceFlag = true;
        }
    };
    
 
}
