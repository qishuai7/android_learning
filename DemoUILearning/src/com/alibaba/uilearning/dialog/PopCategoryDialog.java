
package com.alibaba.uilearning.dialog;

import java.util.List;

import com.alibaba.uilearning.R;
import com.alibaba.uilearning.data.DataCategoryInfo;
import com.alibaba.uilearning.view.WidgetCategoryButtonAuto;
import com.alibaba.uilearning.view.WidgetCategoryButtonAuto.OnCategoryBtnWidgetClickListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class PopCategoryDialog implements View.OnClickListener {
    private Context mContext = null;
    private AlertDialog mDialog = null;
     private CategoryClickListener mCategoryClickListener = null;
    private Window mWindow = null;
    private View mSelectView = null;
    private LinearLayout mCategoryOverLayout; // 消失时显示
    private int y = -1;

    public PopCategoryDialog(Context context) {
        mContext = context;
    }

    
     public void setCategoryClickListener(
             CategoryClickListener categoryClickListener) {
             this.mCategoryClickListener = categoryClickListener;
     }

    /**
     * 计算位置
     * 
     * @param activity
     * @param tv
     */
    public void setLocation(Activity activity, TextView tv,
            LinearLayout categoryOverLayout) {
        int[] location = new int[2];
        tv.getLocationInWindow(location);
        Rect outRect = new Rect();
        activity.getWindow().getDecorView()
                .getWindowVisibleDisplayFrame(outRect);
        int topPx = dipToPixel(mContext, 2);

        this.y = location[1] - outRect.top - topPx;

        mCategoryOverLayout = categoryOverLayout;
    }

    public static int dipToPixel(Context context, int dipValue) {
        if (context == null) {
            return dipValue; // 原值返回
        }
        try {
            float pixelFloat = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, dipValue, context
                            .getResources().getDisplayMetrics());
            return (int) pixelFloat;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dipValue;
    }

    /**
     * 弹出事件
     */
    public void show(List<DataCategoryInfo> list) {
        if (mDialog == null) {
            mDialog = new AlertDialog.Builder(mContext).create();
        }

        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface v) {
                dissmiss();
            }
        });

        mDialog.setCancelable(true);

        mDialog.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ScaleAnimation sa = new ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f,
                        Animation.RELATIVE_TO_SELF, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);
                sa.setDuration(200);
                sa.setFillAfter(true);
                if (mSelectView != null) {
                    mSelectView.startAnimation(sa);
                }
            }
        });
        mDialog.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                ScaleAnimation sa = new ScaleAnimation(1.0f, 0.1f, 1.0f, 0.1f,
                        Animation.RELATIVE_TO_SELF, 0.97f,
                        Animation.RELATIVE_TO_SELF, 0f);
                sa.setDuration(300);
                sa.setFillAfter(true);

                if (mSelectView != null) {
                    mSelectView.startAnimation(sa);
                }
            }
        });

        mDialog.show();
        mWindow = mDialog.getWindow();
        mWindow.setContentView(R.layout.pop_category_dialog);
        mSelectView = mWindow.findViewById(R.id.pop_dialog);
        mWindow.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams wl = mWindow.getAttributes();
        wl.gravity = Gravity.TOP | Gravity.RIGHT;
        wl.y = y;
        // 设置显示位置
        mDialog.onWindowAttributesChanged(wl);

        // 动态添加布局
        WidgetCategoryButtonAuto widgetCategoryButtonAuto = (WidgetCategoryButtonAuto) mWindow
                .findViewById(R.id.widget_button_auto);
        widgetCategoryButtonAuto.setListener(new OnCategoryBtnWidgetClickListener() {

            @Override
            public void onClickListener(View v, int position) {
                if(mCategoryClickListener != null){
                    mCategoryClickListener.OnClickListener(v, position);
                }

            }
        });
        
        widgetCategoryButtonAuto.show(list);

        // mWindow.findViewById(R.id.all_category_btn).setOnClickListener(this);
        // mWindow.findViewById(R.id.door_category_btn)
        // .setOnClickListener(this);
        // mWindow.findViewById(R.id.phone_category_btn).setOnClickListener(
        // this);
        // mWindow.findViewById(R.id.address_category_btn).setOnClickListener(
        // this);
        // mWindow.findViewById(R.id.watery_category_btn).setOnClickListener(
        // this);
        // mWindow.findViewById(R.id.navi_category_btn).setOnClickListener(
        // this);
        // mWindow.findViewById(R.id.map_pop_layout).setOnClickListener(this);
        //
        // initBackgroud();
    }

    public void dissmiss() {
        mCategoryOverLayout.setVisibility(View.VISIBLE);

        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public boolean isShowing() {
        if (mDialog != null) {
            return mDialog.isShowing();
        }

        return false;
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub

    }
    
    public static interface CategoryClickListener {
        public void OnClickListener(View v, int position);
    }
}
