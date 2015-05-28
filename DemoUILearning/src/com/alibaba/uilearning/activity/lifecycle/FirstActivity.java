package com.alibaba.uilearning.activity.lifecycle;

import com.alibaba.uilearning.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends Activity {
	public static final String TAG = "FirstActivity";
	private Context mContext = null;
	
	private EditText mMsgEt = null;
	private TextView mMsgTv = null;
	public final String MSGTEMP = "msg_temp";

	private String mMsg = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		mContext = this;
		
		Log.i(TAG, "onCreate()  msg变量 = " + mMsg);
		
		mMsgEt = (EditText)findViewById(R.id.first_start_et);
		mMsgTv = (TextView)findViewById(R.id.first_start_change_me_tv);
		
		initClick();
		
		mMsgEt.addTextChangedListener(new TextWatcher() {
            
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                
            }
            
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                
            }
            
            @Override
            public void afterTextChanged(Editable paramEditable) {
                String keyWord = paramEditable.toString();
                mMsgTv.setText(keyWord);
                mMsgTv.setBackgroundResource(R.color.red);
                
                mMsg = keyWord;
            }
        });
		
		findViewById(R.id.first_start_crash).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                int[] a = {1,2};
                a[5] = 8;
            }
        });
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(TAG, "onRestart()");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "onStart()");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume()");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause()");
		
		Toast.makeText(mContext, "onPause", Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop()");
		Toast.makeText(mContext, "onStop", Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy()");
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    Log.i(TAG, "onSaveInstanceState()");
	    Toast.makeText(mContext, "onSaveInstanceState", Toast.LENGTH_LONG).show();
	    
	    if(outState == null){
	        return;
	    }
	    
	    if(mMsgEt.getText() == null || mMsgEt.getText().toString().equals("")){
	        return ;
	    }
	    
	    outState.putString(MSGTEMP, mMsgEt.getText().toString());
	}


	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    super.onRestoreInstanceState(savedInstanceState);
	    Log.i(TAG, "onRestoreInstanceState()");
	    
	    if(savedInstanceState == null){
	        return ;
	    }
	    
	    String msg = savedInstanceState.getString(MSGTEMP);
	    if(msg != null){
	        mMsgEt.setText(msg);
	    }
	    
	    
	}
	
	private void initClick(){
		findViewById(R.id.first_start_second_btn).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext,SecondActivity.class);
				startActivity(intent);
			}
		});
		
		findViewById(R.id.first_start_dialog_btn).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "start dialog ...");
				AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
				builder.setMessage("my title ");
				builder.create().show();;
			}
		});
		
		
		findViewById(R.id.first_start_dialog_act_btn).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext,DialogActivity.class);
				startActivity(intent);
			}
		});
	}
}
