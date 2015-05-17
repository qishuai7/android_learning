
package com.alibaba.uilearning.activity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.uilearning.R;
import com.alibaba.uilearning.activity.lifecycle.FirstActivity;
import com.alibaba.uilearning.data.DataCategoryInfo;
import com.alibaba.uilearning.dialog.PopCategoryDialog;
import com.alibaba.uilearning.dialog.PopCategoryDialog.CategoryClickListener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 主界面
 * 
 * @author shuai.qi
 *
 */
public class MainActivity extends FragmentActivity {
	private static final String TAG = "MainActivity";
	private Context mContext = null;
    
    List<DataCategoryInfo> list = new ArrayList<DataCategoryInfo>();
    
    /* 分类 */
    private LinearLayout mCategoryOverLayout;
    private TextView mCategoryOverTv;
    
    /* buttons */
    private Button mActLifeCycleBtn = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initClick();
        initData();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Log.d(TAG, "onPause()");
    }
    
    
    private void initView(){
    	mContext = this;
    	
        /* 分类 */
        mCategoryOverLayout = (LinearLayout) this
                .findViewById(R.id.category_over_layout);
        mCategoryOverTv = (TextView) this.findViewById(R.id.category_over_tv);

        /* buttons */
        mActLifeCycleBtn = (Button)findViewById(R.id.main_btn_act_lifecycle);
        		
    }
    
    private void initClick(){
    	mActLifeCycleBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext,FirstActivity.class);
				startActivity(intent);
			}
		});
    	
    	/* 分类事件 */
        mCategoryOverLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
            	 PopCategoryDialog p =  new PopCategoryDialog(MainActivity.this);
                 p.setLocation(MainActivity.this, mCategoryOverTv, mCategoryOverLayout);
                 p.setCategoryClickListener(new CategoryClickListener() {
                     
                     @Override
                     public void OnClickListener(View v, int position) {
                         Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
                         
                     }
                 });
                 p.show(list);
            }
        });

    }

    
    private void initData(){

    	DataCategoryInfo d1 = new DataCategoryInfo();
		d1.type = 0;
		d1.name = "门脸";
		d1.isRedTip = false;
		d1.picId = R.drawable.button_clicked;
		d1.redTipNumber = 1;

		DataCategoryInfo d2 = new DataCategoryInfo();
		d2.type = 0;
		d2.name = "电话";

		DataCategoryInfo d3 = new DataCategoryInfo();
		d3.type = 0;
		d3.name = "地址";

		DataCategoryInfo d4 = new DataCategoryInfo();
		d4.type = 0;
		d4.name = "地址2";

		DataCategoryInfo d5 = new DataCategoryInfo();
		d5.type = 1;
		d5.name = "水牌";
		d5.picId = R.drawable.button_clicked;
		d5.isRedTip = true;

		DataCategoryInfo d6 = new DataCategoryInfo();
		d6.type = 1;
		d6.name = "充电桩";
		d6.isRedTip = false;

		list.add(d1);
		list.add(d2);
		list.add(d3);
		list.add(d4);
		list.add(d5);
		list.add(d6);
    }
}
