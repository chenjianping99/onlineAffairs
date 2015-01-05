package com.guangzhou.gov.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

import com.guangzhou.gov.R;
import com.guangzhou.gov.util.Constant;

/**
 * @author chenjianping
 */
public class StartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Constant.sIsfullscreen = (getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
        Constant.initMetrics(this);
        addmStartBg();
    }

    private FrameLayout mStartBg;
	private void addmStartBg() {
		mStartBg = new FrameLayout(this);
		mStartBg.setBackgroundResource(R.drawable.bg);
		LayoutParams params = new LayoutParams(Constant.sRealWidth, Constant.sRealHeight, Gravity.TOP);
		
		View logo = new View(this);
		logo.setBackgroundResource(R.drawable.logo);
		int w = ViewUtils.getPXByWidth(408);
		LayoutParams logoparams = new LayoutParams(w, w, Gravity.CENTER);
		logoparams.bottomMargin = ViewUtils.getPXByHeight(480);
		mStartBg.addView(logo, logoparams);
		
		View name = new View(this);
		name.setBackgroundResource(R.drawable.app_name);
		LayoutParams nameparams = new LayoutParams(ViewUtils.getPXByWidth(663), ViewUtils.getPXByWidth(78), Gravity.CENTER);
		nameparams.bottomMargin = ViewUtils.getPXByHeight(140);
		mStartBg.addView(name, nameparams);
		
		TextView website = new TextView(this);
		website.setTextColor(Color.WHITE);
		website.setTypeface(null, Typeface.BOLD);
		website.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(45));
		website.setGravity(Gravity.CENTER);
		website.setText(R.string.website);
        LayoutParams websiteparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        websiteparams.bottomMargin = ViewUtils.getPXByHeight(60);
        mStartBg.addView(website, websiteparams);
        
        TextView copyright = new TextView(this);
        copyright.setTextColor(Color.WHITE);
        copyright.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(35));
        copyright.setGravity(Gravity.CENTER);
        copyright.setText(R.string.copy_right);
        LayoutParams copyrightparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 
        		Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        copyrightparams.bottomMargin = ViewUtils.getPXByHeight(40);
        mStartBg.addView(copyright, copyrightparams);
        
		setContentView(mStartBg, params);
	}
		
	private Handler mHandler = new Handler();
	private Runnable mR = new Runnable() {
		public void run() {
			Intent intent = new Intent();
			intent.setClass(StartActivity.this, GovActivity.class);
			startActivity(intent);
			finish();
		}
	};

	@Override
	protected void onResume() {
		mHandler.postDelayed(mR, 1000);
		super.onResume();
	}

}
