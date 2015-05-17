package com.alibaba.uilearning.activity.lifecycle;

import com.alibaba.uilearning.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FirstActivity extends Activity {
	public static final String TAG = "FirstActivity";
	private Context mContext = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		mContext = this;
		
		Log.i(TAG, "onCreate()");
		
		initClick();
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
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop()");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy()");
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
