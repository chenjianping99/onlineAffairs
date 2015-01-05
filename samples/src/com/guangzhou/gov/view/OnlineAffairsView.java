package com.guangzhou.gov.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.guangzhou.gov.R;
import com.guangzhou.gov.util.Constant;
import com.guangzhou.gov.util.LogUtils;
/**
 * 
 * @author chenjianping
 *
 */
public class OnlineAffairsView extends FrameLayout implements OnClickListener {

	private Context mContext;
	public OnlineAffairsView(Context context) {
		super(context);
		setBackgroundColor(0xfffafafa);
		Constant.sBottomH = Constant.dpToPx(context, 60);
		LogUtils.log(null, "mBottomH = " + Constant.sBottomH);
		BODY_H = Constant.sRealHeight - TITLE_H  - TOP_BOTTON_H - Constant.sBottomH;
		mContext = context;
		addmTitle();
		addTopSidebarView();
		initViewPager();
		addLine();
	}

	private void addLine() {
		View v = new View(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM);
        v.setBackgroundColor(0xffd6d6d6);
        addView(v, params);
	}
	private TextView mTitle;
	private ImageView mSearch;
	public static final int TITLE_H = ViewUtils.getPXByHeight(168);
	public static final int LEFT_MARGIN = ViewUtils.getPXByWidth(30);
	private void addmTitle() {
		mTitle = new TextView(mContext);
		mTitle.setTextColor(Color.WHITE);
		mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(60));
		//mTitle.setTypeface(CircleContainer.sTypeface);
		mTitle.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		mTitle.setText(R.string.title);
		Drawable a = getResources().getDrawable(R.drawable.name_icon);
		a.setBounds(0, 0, ViewUtils.getPXByHeight(108), ViewUtils.getPXByHeight(108));
		mTitle.setCompoundDrawables(a, null, null, null);
		mTitle.setCompoundDrawablePadding(LEFT_MARGIN);
		mTitle.setPadding(LEFT_MARGIN, 0, 0, 0);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, TITLE_H, Gravity.TOP);
        mTitle.setBackgroundColor(Constant.sBlueColor);
        addView(mTitle, params);
        
        
        mSearch = new ImageView(mContext);
        mSearch.setImageResource(R.drawable.title_search);
        int w = ViewUtils.getPXByHeight(53);
		LayoutParams mSearchParams = new LayoutParams(w, w, Gravity.TOP | Gravity.RIGHT);
		mSearchParams.topMargin = (TITLE_H - w) / 2;
		mSearchParams.rightMargin = ViewUtils.getPXByWidth(80);
        addView(mSearch, mSearchParams);
	}
	
	private TopSidebarView mPersonAffairs, mCompanyAffairs;
	private static final int TOP_BOTTON_H = ViewUtils.getPXByHeight(144);
	private void addTopSidebarView() {
		mPersonAffairs = new TopSidebarView(mContext, Constant.sRealWidth / 2, TOP_BOTTON_H, R.string.personal_affairs);
		LayoutParams params = new LayoutParams(Constant.sRealWidth / 2, TOP_BOTTON_H, Gravity.TOP | Gravity.LEFT);
		params.topMargin = TITLE_H;
		addView(mPersonAffairs, params);
		mPersonAffairs.setFocus(true);
		
		mCompanyAffairs = new TopSidebarView(mContext, Constant.sRealWidth / 2, TOP_BOTTON_H, R.string.company_affairs);
		LayoutParams params2 = new LayoutParams(Constant.sRealWidth / 2, TOP_BOTTON_H, Gravity.TOP | Gravity.RIGHT);
		params2.topMargin = TITLE_H;
		addView(mCompanyAffairs, params2);
		mCompanyAffairs.setFocus(false);
		
		mPersonAffairs.setOnClickListener(this);
		mCompanyAffairs.setOnClickListener(this);
	}
	
	private ViewPager mViewPager;
	private ViewPageAdapter mPageAdapter;
	private List<View> mListViews;
	
	public static int BODY_H;
	private void initViewPager() {
		mViewPager = new ViewPager(mContext);
		mViewPager.setHorizontalFadingEdgeEnabled(false);

		if (Integer.parseInt(Build.VERSION.SDK) >= 9) {
			mViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
		}
		FrameLayout.LayoutParams thisParams = new FrameLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, BODY_H, Gravity.TOP);
		thisParams.topMargin = TITLE_H + TOP_BOTTON_H;
		addView(mViewPager, thisParams);

		mListViews = new ArrayList<View>();
		GridAffairs mGridPersonal = new GridAffairs(mContext, 0);
		GridAffairs mGridPersonal2 = new GridAffairs(mContext, 1);
		mListViews.add(mGridPersonal);
		mListViews.add(mGridPersonal2);

		mPageAdapter = new ViewPageAdapter(mListViews);	
		mViewPager.setAdapter(mPageAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int index) {
				if (index == 0) {
					mPersonAffairs.setFocus(true);
					mCompanyAffairs.setFocus(false);
					mViewPager.setCurrentItem(0);
				} else if (index == 1) {
					mCompanyAffairs.setFocus(true);
					mPersonAffairs.setFocus(false);
					mViewPager.setCurrentItem(1);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	@Override
	public void onClick(View v) {
		if (v == mPersonAffairs) {
			mPersonAffairs.setFocus(true);
			mCompanyAffairs.setFocus(false);
			mViewPager.setCurrentItem(0);
		} else if (v == mCompanyAffairs) {
			mCompanyAffairs.setFocus(true);
			mPersonAffairs.setFocus(false);
			mViewPager.setCurrentItem(1);
		}
	}
}
