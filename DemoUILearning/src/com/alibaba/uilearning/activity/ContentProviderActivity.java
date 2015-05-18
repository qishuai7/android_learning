
package com.alibaba.uilearning.activity;

import com.alibaba.uilearning.R;
import com.alibaba.uilearning.activity.contentprovider.DBlite;
import com.alibaba.uilearning.activity.contentprovider.RuiXin;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class ContentProviderActivity extends Activity {
    /** Called when the activity is first created. */
    private DBlite dBlite1 = new DBlite(this);;
    private ContentResolver contentResolver;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        
        // 先对数据库进行添加数据
        //dBlite1.add(email, username, date, sex);
        dBlite1.add("qi@163.com", "张帅", "20150606", "男");
        
        // 通过contentResolver进行查找
        contentResolver = ContentProviderActivity.this.getContentResolver();
        Cursor cursor = contentResolver.query(
                RuiXin.CONTENT_URI, new String[] {
                        RuiXin.EMAIL, RuiXin.USERNAME,
                        RuiXin.DATE, RuiXin.SEX
                }, null, null, null);
        
        while (cursor.moveToNext()) {
            Toast.makeText(
                    ContentProviderActivity.this,
                    cursor.getString(cursor.getColumnIndex(RuiXin.EMAIL))
                            + " "
                            + cursor.getString(cursor.getColumnIndex(RuiXin.USERNAME))
                            + " "
                            + cursor.getString(cursor.getColumnIndex(RuiXin.DATE))
                            + " "
                            + cursor.getString(cursor.getColumnIndex(RuiXin.SEX)),
                    Toast.LENGTH_SHORT).show();
        }
        startManagingCursor(cursor); // 查找后关闭游标
    }
}
