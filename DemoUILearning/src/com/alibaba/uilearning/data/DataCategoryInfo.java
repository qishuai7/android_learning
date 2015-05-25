package com.alibaba.uilearning.data;

import com.alibaba.uilearning.R;


/**
 * 分类info
 * 
 * @author shuai.qi
 *
 */
public class DataCategoryInfo {
    public int type = 0;                            // 0: button按钮 ; 1: TextView(带图片、小红点)
    public String name = "";                        // 名称
    public int  picId = R.drawable.button_clicked;  // 图片
    public boolean isRedTip = false;
    public int     redTipNumber = 0;
    public boolean isDefault = false;
}
