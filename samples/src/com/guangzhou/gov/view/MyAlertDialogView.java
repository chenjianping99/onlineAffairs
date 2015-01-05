package com.guangzhou.gov.view;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guangzhou.gov.R;
import com.guangzhou.gov.util.Constant;

public class MyAlertDialogView extends LinearLayout {

	private Context mContext;
	private String mTitleStr;
	private int mW = ViewUtils.getPXByWidth(930);
	//private int mH = ViewUtils.getPXByWidth(500);
	public MyAlertDialogView(Context context, String msg) {
		super(context);
		setOrientation(LinearLayout.VERTICAL);
		mContext = context;
		mTitleStr = msg; 
		 LayoutParams params = new LayoutParams(mW, LayoutParams.WRAP_CONTENT);
	     this.setLayoutParams(params);
	    setBackgroundResource(R.drawable.alert_dialog_bg);
		addTitle();
		addLineBlack(0);
		addComfirmBtn();
	}

	private TextView mTitle;
	private void addTitle() {
		mTitle = new TextView(mContext);
		mTitle.setTextColor(Constant.sBlack87Color);
		//mTitle.setBackgroundColor(0x33000000);
		mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(48));
		mTitle.setGravity(Gravity.CENTER);
		mTitle.setText(mTitleStr);
		mTitle.setLineSpacing(1.2f, 1.2f);
		int padding = OnlineAffairsView.LEFT_MARGIN * 2;
		mTitle.setPadding(padding, padding, padding, padding / 2);
        LayoutParams params = new LayoutParams(mW, 
        		LayoutParams.WRAP_CONTENT/* - ViewUtils.getPXByWidth(144)*//*, Gravity.TOP*/);
        addView(mTitle, params);
	}
	
	public static final int CANCLE_ID = 0x110110;
	public static final int COMFIRM_ID = 0x110111;
	private Button mConfirm, mCancle;
	private void addComfirmBtn() {
		LinearLayout mBottomLy = new LinearLayout(mContext);
		mBottomLy.setOrientation(LinearLayout.HORIZONTAL);
		mBottomLy.setGravity(Gravity.CENTER);
		LayoutParams params = new LayoutParams(mW, 
				ViewUtils.getPXByWidth(144)/*, Gravity.BOTTOM*/);
		addView(mBottomLy, params);
		
		mCancle = new Button(mContext);
		mCancle.setId(CANCLE_ID);
		mCancle.setBackgroundColor(Color.TRANSPARENT);
		LinearLayout.LayoutParams cancleParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		cancleParams.gravity = Gravity.CENTER;
		mCancle.setText("取消");
		mCancle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				ViewUtils.getPXByWidth(54));
		mCancle.setTextColor(0xffe98c52);
		mBottomLy.addView(mCancle, cancleParams);
		
		View v = new View(mContext);
		LayoutParams params1 = new LayoutParams(1, LayoutParams.MATCH_PARENT);
		v.setBackgroundColor(Constant.sBlack10Color);
		mBottomLy.addView(v, params1);

		mConfirm = new Button(mContext);
		mConfirm.setId(COMFIRM_ID);
		mConfirm.setBackgroundColor(Color.TRANSPARENT);
		LinearLayout.LayoutParams timeParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		timeParams.gravity = Gravity.CENTER;
		mConfirm.setText("确认");
		mConfirm.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				ViewUtils.getPXByWidth(54));
		mConfirm.setTextColor(0xff51c755);
		mBottomLy.addView(mConfirm, timeParams);
		
	}
	
	private void addLineBlack(int topMargin) {
		View v = new View(mContext);
		LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
		params1.topMargin = topMargin;
		v.setBackgroundColor(Constant.sBlack10Color);
        addView(v, params1);
	}

}
