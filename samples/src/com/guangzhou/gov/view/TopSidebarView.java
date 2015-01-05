package com.guangzhou.gov.view;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.guangzhou.gov.util.Constant;
/**
 * 
 * @author chenjianping
 *
 */
public class TopSidebarView extends FrameLayout {

	private int mW, mH;
	public TopSidebarView(Context context, int w, int h, int name) {
		super(context);

		mW = w;
		mH = h;
		mNameStr = name;
		initTop3Icon(context);
		setBackgroundColor(Color.WHITE);
	}
	
	private int mNameStr;		
	private TextView mName, mViewClick;
	private void initTop3Icon(Context context) {
		mName = new TextView(context);
		mName.setTextColor(0x88000000);
		mName.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(48));
		//mApp.setTypeface(CircleContainer.sTypeface);
		mName.setGravity(Gravity.CENTER);
		
		mName.setText(mNameStr);
		addView(mName, mW, mH);
	
		mViewClick = new TextView(context);
		mViewClick.setBackgroundColor(Constant.sYellowColor);
		LayoutParams params = new LayoutParams(mW, mH / 20, Gravity.BOTTOM);
		addView(mViewClick, params);
		mViewClick.setVisibility(INVISIBLE);
	}
	
	public void setFocus(boolean isSelect) {
		if (!isSelect) {
			mName.setTextColor(0x88000000);
			mViewClick.setVisibility(INVISIBLE);
		} else {
			mName.setTextColor(Constant.sYellowColor);
			mViewClick.setVisibility(VISIBLE);
		}
	}
	
}
