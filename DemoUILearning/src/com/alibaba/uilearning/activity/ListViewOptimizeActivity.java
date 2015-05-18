
package com.alibaba.uilearning.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.alibaba.uilearning.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * listView性能优化
 * ListView滚动速度优化主要可以应用以下几点方法来实现：
1、使用Adapter提供的convertView
convertView是Adapter提供的视图缓存机制，当第一次显示数据的时候，adapter会创建n个（n等于页面可见的item的数目）convertView，
当下次需要显示新的item的时候，adapter会循环利用这些已经创建的convertView，减少再次创建convertView所带来的开销，从而达到性能的提升。
2、使用自定义的视图缓存类
就是自定义一个视图缓存类，在这个类中保存我们在item中使用到的视图的引用，通过convertView的setTag方法和getTag方法来存储这个视图缓存类引用和
重新获取这个视图缓存类引用，其目的也是为了减少重复创建视图时的开销。
3、减少不必要的视图更新
ListView在滚动时会请求重新获取item，来显示不同内容的item，而如果在获取item时比较耗时就会造成在滚动时出现卡顿的现象。
那我们可以通过监听ListView的滚动事件来使ListView处于不同的滚动状态时做不同的事情，比如在ListView处于滚动过程中加载少量的显示数据，当ListView处于空闲的状态时再加载所有的数据，这样就可以减少ListView在滚动过程中的开销，从而提供ListView的滚动速度。
 * @author shuai.qi
 */
public class ListViewOptimizeActivity extends Activity {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        lv = (ListView) findViewById(R.id.lv);
        MyAdapter mAdapter = new MyAdapter(this);// 得到一个MyAdapter对象
        lv.setAdapter(mAdapter);// 为ListView绑定Adapter
        /** 为ListView添加点击事件 */
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Log.v("MyListViewBase", "你点击了ListView条目" + arg2);// 在LogCat中输出信息
            }
        });
    }

    /** 添加一个得到数据的方法，方便使用 */
    private ArrayList<HashMap<String, Object>> getDate() {
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        /** 为动态数组添加数据 */
        for (int i = 0; i < 30; i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemText", "这是第" + i + "行");
            listItem.add(map);
        }
        return listItem;
    }

    /**
     * 新建一个类继承BaseAdapter，实现视图与数据的绑定
     */
    private class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局

        /** 构造函数 */
        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return getDate().size();// 返回数组的长度
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /** 书中详细解释该方法 */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            // 观察convertView随ListView滚动情况
            Log.v("MyListViewBase", "getView " + position + " " + convertView);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.activity_listview_item, null);
                holder = new ViewHolder();
                Log.v("MyListViewBase", "getView  new ViewHolder() = " + position );
                /** 得到各个控件的对象 */
                holder.title = (TextView) convertView.findViewById(R.id.item_title);
                holder.text = (TextView) convertView.findViewById(R.id.item_text);
                holder.bt = (Button) convertView.findViewById(R.id.item_button);
                convertView.setTag(holder);// 绑定ViewHolder对象
            }
            else {
                holder = (ViewHolder) convertView.getTag();// 取ViewHolder对象
            }
            /** 设置TextView显示的内容，即我们存放在动态数组中的数据 */
            holder.title.setText(getDate().get(position).get("ItemTitle").toString());
            holder.text.setText(getDate().get(position).get("ItemText").toString());

            /** 为Button添加点击事件 */
            holder.bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("MyListViewBase", "你点击了按钮" + position);// 打印Button的点击信息
                }
            });

            return convertView;
        }

    }

    /** 存放控件 */
    public final class ViewHolder {
        public TextView title;
        public TextView text;
        public Button bt;
    }
}
