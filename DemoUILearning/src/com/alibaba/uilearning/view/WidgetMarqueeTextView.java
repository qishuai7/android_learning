
package com.alibaba.uilearning.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 跑马灯
 * 
 * @author shuai.qi
 *
 */
public class WidgetMarqueeTextView extends TextView {
    public WidgetMarqueeTextView(Context context) {
        super(context);
    }

    public WidgetMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetMarqueeTextView(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
