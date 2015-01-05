package com.guangzhou.gov.view;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.guangzhou.gov.R;
import com.guangzhou.gov.util.Constant;

/**
 * 
 * @author chenjianping
 *
 */
public class ShengbanPhotoView extends FrameLayout {
	private Context mContext;
	public ShengbanPhotoView(Context context)
	{
		super(context);
		mContext = context;
		setBackgroundColor(0x44000000);
		addComfirmBtn();
	}

	private Button mCamera, mPhoto, mCancle;
	public static final int CAMERA_ID = 0x00100;
	private void addComfirmBtn() {
		LinearLayout mBottomLy = new LinearLayout(mContext);
		mBottomLy.setOrientation(LinearLayout.VERTICAL);
		mBottomLy.setGravity(Gravity.CENTER);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 
				ViewUtils.getPXByHeight(610 + 120), Gravity.BOTTOM);
		addView(mBottomLy, params);
		mBottomLy.setPadding(OnlineAffairsView.LEFT_MARGIN, OnlineAffairsView.LEFT_MARGIN,
				OnlineAffairsView.LEFT_MARGIN, OnlineAffairsView.LEFT_MARGIN);
		mBottomLy.setBackgroundColor(Color.WHITE);
		
		int w = ViewUtils.getPXByWidth(700);
		int h = ViewUtils.getPXByHeight(160);
		mCamera = new Button(mContext);
		mCamera.setId(CAMERA_ID);
		mCamera.setBackgroundResource(R.drawable.white_btn_indicator);
		LinearLayout.LayoutParams mCameraParams = new LinearLayout.LayoutParams(w, h);
		mCameraParams.gravity = Gravity.CENTER;
		mCameraParams.topMargin = OnlineAffairsView.LEFT_MARGIN / 2;
		mCameraParams.bottomMargin = mCameraParams.topMargin;
		mCamera.setText(R.string.paizhao);
		mCamera.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				ViewUtils.getPXByWidth(48));
		mCamera.setTextColor(Constant.sYellowBtn);
		mBottomLy.addView(mCamera, mCameraParams);
		
		mPhoto = new Button(mContext);
		mPhoto.setId(CAMERA_ID + 1);
		mPhoto.setBackgroundColor(Color.TRANSPARENT);
		mPhoto.setBackgroundResource(R.drawable.white_btn_indicator);
		//mPhoto.setOnClickListener(this);
		LinearLayout.LayoutParams mPhotoPara = new LinearLayout.LayoutParams(w, h);
		mPhotoPara.gravity = Gravity.CENTER;
		mPhotoPara.topMargin = OnlineAffairsView.LEFT_MARGIN / 2;
		mPhotoPara.bottomMargin = mPhotoPara.topMargin;
		mPhoto.setText(R.string.xiangce);
		mPhoto.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				ViewUtils.getPXByWidth(48));
		mPhoto.setTextColor(Constant.sYellowBtn);
		mBottomLy.addView(mPhoto, mPhotoPara);
		
		mCancle = new Button(mContext);
		mCancle.setId(CAMERA_ID + 2);
		mCancle.setBackgroundResource(R.drawable.white_btn_indicator);
		
		LinearLayout.LayoutParams mCanclePara = new LinearLayout.LayoutParams(w, h);
		mCanclePara.gravity = Gravity.CENTER;
		mCanclePara.topMargin = OnlineAffairsView.LEFT_MARGIN / 2;
		mCanclePara.bottomMargin = mCanclePara.topMargin;
		mCancle.setText(R.string.cancle);
		mCancle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				ViewUtils.getPXByWidth(48));
		mCancle.setTextColor(Constant.sYellowBtn);
		mBottomLy.addView(mCancle, mCanclePara);
	}
}
