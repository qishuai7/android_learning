package com.alibaba.uilearning.activity;

import com.alibaba.uilearning.R;
import com.alibaba.uilearning.view.WidgetAutoTextView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ScrollLightActivity extends Activity implements OnClickListener {
    private TextView mScrollLightTv = null;
    private TextView mMyScrollLightTv = null;
    private Button   mScrollLightBtn = null;
    
    private Button mBtnNext;
    private Button mBtnPrev;
    private WidgetAutoTextView mTextView02 = null;
    private static int sCount = 10;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_light);
        
        mScrollLightTv = (TextView)findViewById(R.id.scroll_light_tv);
        mScrollLightBtn = (Button)findViewById(R.id.scroll_light_btn);
        mMyScrollLightTv = (TextView)findViewById(R.id.my_scroll_light_tv);
        
        mScrollLightTv.setText("111111112222222222222333333333334");
        mMyScrollLightTv.setText("8888888888888888999999999999999999");
        
        mScrollLightBtn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                mScrollLightTv.setText("广东省广大法国大师法撒旦法士大夫地方上东方四大防盗锁");
                mMyScrollLightTv.setText("广东省广大法国大师法撒旦法士大夫地方上东方四大防盗锁");
            }
        });
        
        
     init();   
    }
    
    
    private void init() {
        // TODO Auto-generated method stub
        mBtnNext = (Button) findViewById(R.id.next);
        mBtnPrev = (Button) findViewById(R.id.prev);
        mTextView02 = (WidgetAutoTextView) findViewById(R.id.switcher02);
        mTextView02.setText("Hello world!");
        mBtnPrev.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.next:
                mTextView02.next();
                sCount++;
                break;
            case R.id.prev:
                mTextView02.previous();
                sCount--;
                break;
            }
            mTextView02.setText(sCount%2==0 ? 
                    sCount+"AAFirstAA" :
                    sCount+"BBBBBBB");
            System.out.println("getH: ["+mTextView02.getHeight()+"]");
        
    }
}
