package com.alibaba.uilearning.view;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.uilearning.R;
import com.alibaba.uilearning.data.DataCategoryInfo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

/**
 * 自动添加按钮控件 传入参数为： 数据源(必填),每行btn个数，btn之间距离
 * 
 * @author shuai.qi
 */
public class WidgetCategoryButtonAuto extends LinearLayout {
	private Context mContext = null;

	/* 数据源 */
	public  List<DataCategoryInfo> list = null;
	/* 每行button个数 */
	public static int mDefaultRowButtonCount = 4;
	/* 间距 */
	public static int mMarginButton = 20;

	private ArrayList<Button> buttonList = new ArrayList<Button>();

	private OnCategoryBtnWidgetClickListener mListener = null;
	
    LayoutParams lpMatchLayout = new LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT);
    LayoutParams lpNormalBtn = new LayoutParams(LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT);
    LayoutParams lpLastBtn = new LayoutParams(LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT);
    Drawable topDrawable = getResources()
            .getDrawable(R.drawable.button_clicked);

	public WidgetCategoryButtonAuto(Context context) {
		super(context);
		init(context);
	}

	public WidgetCategoryButtonAuto(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void setListener(OnCategoryBtnWidgetClickListener listener) {
		this.mListener = listener;
	}

	private void init(Context context) {
		mContext = context;
		setOrientation(VERTICAL);

		initView();
		
	}
	
	public void show(List<DataCategoryInfo> list){
		this.list = list;
		
		addButtons();
		addImageViews();
	}

	private void initView() {
		// 按钮右边用margin
		lpNormalBtn.weight = 1.0f;
		lpNormalBtn.rightMargin = mMarginButton;

		// 按钮无magin
		lpLastBtn.weight = 1.0f;

		// TODO 图片、文档、背景动态显示、是否显示小红点
		topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(),
				topDrawable.getMinimumHeight());

	}

	/**
	 * 循环添加button按钮
	 */
	private void addButtons() {
		// add button view
		LinearLayout tempLinearLayout = null;
		// button total count
		int buttonTotalCount = 0;
		for (int i = 0; i < list.size(); i++) {
			DataCategoryInfo info = list.get(i);

			if (info.type == 0) { // button 按钮
				// 是否超过每行最多个数,超过需要换行
				if (buttonTotalCount == 0
						|| (buttonTotalCount % mDefaultRowButtonCount == 0)) {
					tempLinearLayout = new LinearLayout(mContext);
					tempLinearLayout.setOrientation(HORIZONTAL);
					addView(tempLinearLayout, lpMatchLayout);
				}

				Button btn = new Button(mContext);

				// 布局: 最后一个button,不需要margin
				if (buttonTotalCount == (mDefaultRowButtonCount - 1)) {
					btn.setLayoutParams(lpLastBtn);
				} else {
					btn.setLayoutParams(lpNormalBtn);
				}

				btn.setText(info.name);
				final int position = i;
				btn.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (mListener != null) {
							mListener.onClickListener(v, position);
							// for (Button button : buttonList) {
							// button.setBackgroundColor(getResources()
							// .getColor(R.color.blue));
							// }
							// v.setBackgroundColor(getResources().getColor(
							// R.color.red));
							//
						}

					}
				});

				tempLinearLayout.addView(btn);
				buttonList.add(btn);
				buttonTotalCount++;
			}
		}

		// 如果button不够一行，为保证对齐格式，需要填充至一行
		addNeedButtons(tempLinearLayout, buttonTotalCount);
	}

	/**
	 * 自动补全button，便于对齐
	 * 
	 * @param tempLinearLayout
	 * @param buttonTotalCount
	 */
	private void addNeedButtons(LinearLayout tempLinearLayout,
			int buttonTotalCount) {
		int needButtonNumber = 0;
		if (buttonTotalCount < mDefaultRowButtonCount) {
			needButtonNumber = mDefaultRowButtonCount - buttonTotalCount;
		} else if (buttonTotalCount > mDefaultRowButtonCount) {
			needButtonNumber = mDefaultRowButtonCount - buttonTotalCount
					% mDefaultRowButtonCount;
		}

		for (int j = 0; j < needButtonNumber; j++) {
			Button btn = new Button(mContext);
			// 最后一个button，不需要margin
			if (j == (needButtonNumber - 1)) {
				btn.setLayoutParams(lpLastBtn);
			} else {
				btn.setLayoutParams(lpNormalBtn);
			}

			btn.setVisibility(View.INVISIBLE);

			tempLinearLayout.addView(btn);
		}

	}

	/**
	 * 自动补全ImageView，便于对齐
	 * 
	 * @param tempLinearLayout
	 * @param buttonTotalCount
	 */
	private void addNeedImageViews(LinearLayout tempLinearLayout,
			int buttonTotalCount) {
		int needButtonNumber = 0;
		if (buttonTotalCount < mDefaultRowButtonCount) {
			needButtonNumber = mDefaultRowButtonCount - buttonTotalCount;
		} else if (buttonTotalCount > mDefaultRowButtonCount) {
			needButtonNumber = mDefaultRowButtonCount - buttonTotalCount
					% mDefaultRowButtonCount;
		}

		for (int j = 0; j < needButtonNumber; j++) {
			// 每一项
			FrameLayout tempFrameLayout = new FrameLayout(mContext);
			TextView tv = new TextView(mContext);
			tv.setCompoundDrawables(null, topDrawable, null, null);
			tv.setGravity(Gravity.CENTER);
			tempFrameLayout.addView(tv);

			// 最后一个button，不需要margin
			if (j == (needButtonNumber - 1)) {
				tempFrameLayout.setLayoutParams(lpLastBtn);
			} else {
				tempFrameLayout.setLayoutParams(lpNormalBtn);
			}

			tempFrameLayout.setVisibility(View.INVISIBLE);

			tempLinearLayout.addView(tempFrameLayout);
		}

	}

	/**
	 * 添加imageviews
	 */
	private void addImageViews() {
		// add button view
		LinearLayout tempLinearLayout = null;
		// button total count
		int buttonTotalCount = 0;

		android.widget.FrameLayout.LayoutParams lpRedTip = new android.widget.FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lpRedTip.rightMargin = 5;
		lpRedTip.topMargin = 2;
		lpRedTip.gravity = Gravity.RIGHT;

		for (int i = 0; i < list.size(); i++) {
			DataCategoryInfo info = list.get(i);
			if (info.type == 1) { // image view button
				// 是否超过每行最多个数,超过需要换行
				if (buttonTotalCount == 0
						|| (buttonTotalCount % mDefaultRowButtonCount == 0)) {
					tempLinearLayout = new LinearLayout(mContext);
					tempLinearLayout.setOrientation(HORIZONTAL);
					addView(tempLinearLayout, lpMatchLayout);
				}

				// 每一项
				FrameLayout tempFrameLayout = new FrameLayout(mContext);
				tempFrameLayout.setBackgroundColor(getResources().getColor(
						R.color.blue));

				// 布局: 最后一个button,不需要margin
				if (buttonTotalCount == (mDefaultRowButtonCount - 1)) {
					tempFrameLayout.setLayoutParams(lpLastBtn);
				} else {
					tempFrameLayout.setLayoutParams(lpNormalBtn);
				}

				TextView redTipIv = new TextView(mContext);
				redTipIv.setLayoutParams(lpRedTip);
				redTipIv.setGravity(Gravity.CENTER);
				redTipIv.setBackgroundResource(R.drawable.myprofile_push_tip_img);
				if (!info.isRedTip) {
					redTipIv.setVisibility(View.GONE);
				}

				TextView tv = new TextView(mContext);
				tv.setCompoundDrawables(null, topDrawable, null, null);
				tv.setGravity(Gravity.CENTER);
				tv.setText(info.name);

				final int position = i;
				tempFrameLayout.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (mListener != null) {
							mListener.onClickListener(v, position);
						}
					}
				});

				tempFrameLayout.addView(tv);
				tempFrameLayout.addView(redTipIv);
				tempLinearLayout.addView(tempFrameLayout);

				buttonTotalCount++;
			}
		}

		addNeedImageViews(tempLinearLayout, buttonTotalCount);
	}

	/**
	 * 计算布局行数
	 * 
	 * @param rowButtonCount
	 * @param sourceValuesLength
	 * @return
	 */
	private int getRowCount(int rowButtonCount, int sourceValuesLength) {
		if (rowButtonCount <= 0 || sourceValuesLength <= 0) {
			return 0;
		}
		int tempRemainder = sourceValuesLength % rowButtonCount;
		int tempResult = sourceValuesLength / rowButtonCount;
		if (tempRemainder != 0) {
			tempResult += 1;
		}

		return tempResult;
	}

	public static interface OnCategoryBtnWidgetClickListener {
		public void onClickListener(View v, int position);
	}

}
