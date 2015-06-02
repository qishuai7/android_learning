package com.alibaba.uilearning.activity;

import com.alibaba.uilearning.R;
import com.alibaba.uilearning.data.Students;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 测试序列化对象
 * 
 * @author shuai.qi
 *
 */
public class ParcelActivity extends Activity{
    public static final String PARCEL_KEY = "students";
    private TextView mPracelObjTv = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel);
        
        mPracelObjTv = (TextView)findViewById(R.id.parcel_obj_tv);
        
        Intent intent = getIntent();
        if(intent != null){
           Students student = intent.getParcelableExtra(PARCEL_KEY);
           mPracelObjTv.setText(student.id + " " + student.name + " " + student.sex);
        }
        
    }
}
