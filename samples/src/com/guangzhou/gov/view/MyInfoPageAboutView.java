package com.guangzhou.gov.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.guangzhou.gov.R;
import com.guangzhou.gov.util.Constant;

/**
 * 
 * @author chenjianping
 *
 */
public class MyInfoPageAboutView extends FrameLayout implements OnClickListener
{
	private Context mContext;
	public MyInfoPageAboutView(Context context)
	{
		super(context);
		mContext = context;
		FrameLayout.LayoutParams thisParams = new FrameLayout.LayoutParams(
				Constant.sRealWidth, Constant.sRealHeight - Constant.sBottomH, Gravity.TOP);
		this.setLayoutParams(thisParams);
		setBackgroundColor(Constant.sTitle3BgColor);
		
		addmTitle();
		addListView();
		//addLine();
		
		String versionName = mContext.getResources().getString(R.string.version_name);
		if (versionName != null && versionName.length() > 0) {
			DATA_VALUE[0] = versionName;
		}
	}

	private void addLine() {
		View v = new View(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM);
        v.setBackgroundColor(Constant.sBlack10Color);
        //addView(v, params);
	}

	private TextView mTitle;
	private static final int PIC_H = ViewUtils.getPXByWidth(546);
	private void addmTitle() {
		mTitle = new TextView(mContext);
		mTitle.setTextColor(Color.WHITE);
		mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(60));
		//mTitle.setTypeface(CircleContainer.sTypeface);
		mTitle.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		mTitle.setText("关于");
		Drawable a = getResources().getDrawable(R.drawable.login_title_icon);
		a.setBounds(0, 0, ViewUtils.getPXByHeight(108), ViewUtils.getPXByHeight(108));
		mTitle.setCompoundDrawables(a, null, null, null);
		
		mTitle.setCompoundDrawablePadding(OnlineAffairsView.LEFT_MARGIN);
		mTitle.setPadding(OnlineAffairsView.LEFT_MARGIN, 0, 0, 0);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 
        		OnlineAffairsView.TITLE_H, Gravity.TOP);
        mTitle.setBackgroundColor(Constant.sBlueColor);
        addView(mTitle, params);
        mTitle.setOnClickListener(this);
              
        ImageView mPic = new ImageView(mContext);
        mPic.setBackgroundResource(R.drawable.tu);
        //mPic.setImageResource(R.drawable.tu);
		LayoutParams mSearchParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				PIC_H, Gravity.TOP);
		mSearchParams.topMargin = OnlineAffairsView.TITLE_H;
        addView(mPic, mSearchParams);
	}
	
	private ListView mListView;
	private ArrayList<String> mDepartMentsList = new ArrayList<String>();
	private MyAdapter mMyAdapter;
	private void addListView() {
		mListView = new ListView(getContext());
		LayoutParams pager = new LayoutParams(
				Constant.sRealWidth, LayoutParams.WRAP_CONTENT, Gravity.TOP);
		pager.topMargin = OnlineAffairsView.TITLE_H + PIC_H;
		mListView.setBackgroundColor(Color.WHITE);
		addView(mListView, pager);
		
		/*View v = new View(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.TOP);
        v.setBackgroundColor(0xffd6d6d6);
        params.topMargin = pager.topMargin + ViewUtils.getPXByHeight(180) * 4;
        addView(v, params);*/
		
		mMyAdapter = new MyAdapter();
		mListView.setAdapter(mMyAdapter);
		mListView.setFadingEdgeLength(0);
		mListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		mListView.setCacheColorHint(Color.TRANSPARENT);
		mListView.setVerticalFadingEdgeEnabled(false);
		mListView.setVerticalScrollBarEnabled(false);
		mListView.setFadingEdgeLength(0);
		mListView.setFooterDividersEnabled(false);
		mListView.setDivider(new ColorDrawable(Constant.sBlack10Color));
		mListView.setDividerHeight(ViewUtils.getPXByHeight(2));
		
		mDepartMentsList.clear();
		for (int i = 0; i < DATA_NAME.length; i++) {
			mDepartMentsList.add(i, DATA_NAME[i]);
		}
	}
		
	private final static String[] DATA_NAME = {
		"手机版本",  "主办单位",  "承办单位",  "建设单位",  "网站地址",  "客服邮箱", 
	};
	private final static String[] DATA_VALUE = {
		"v2.0 for Android",  "广东省人民政府办公厅",  "广东省经济和信息化委员会",  "广东省信息中心",  
		"www.gdbs.gov.cn",  "gdbs@gd.gov.cn", 
	};
	
	@Override
	public void onClick(View v)
	{
		if (mTitle == v) {
			this.setVisibility(INVISIBLE);
			MyInfoPageView.mIsFirstView = true;
		}
	}
	

	/**
	 * 
	 * @author chenjianping
	 * 
	 */
	class MyAdapter extends BaseAdapter implements OnClickListener {

		SimpleDateFormat	mDateFormat;
		@Override
		public int getCount() {
			int count = mDepartMentsList.size();
			
			return count;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mDepartMentsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Context c = getContext();
			FrameLayout itemView = null;
			if (convertView == null) {
				itemView = new FrameLayout(c);
				//itemView.setOrientation(LinearLayout.HORIZONTAL);
				AbsListView.LayoutParams params = new AbsListView.LayoutParams(Constant.sRealWidth, 
						ViewUtils.getPXByHeight(144));
				itemView.setLayoutParams(params);
				//itemView.setBackgroundColor(0x33000000);
				itemView.setPadding(OnlineAffairsView.LEFT_MARGIN, 0, OnlineAffairsView.LEFT_MARGIN, 0);

				TextView department = new TextView(c);
				department.setTextSize(TypedValue.COMPLEX_UNIT_PX,
						ViewUtils.getPXByWidth(48));
				FrameLayout.LayoutParams timeParams = new FrameLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL | Gravity.LEFT);
				department.setTextColor(Constant.sBlack87Color);
				department.setText(mDepartMentsList.get(position));
				itemView.addView(department, timeParams);
				
				TextView value = new TextView(c);
				value.setTextSize(TypedValue.COMPLEX_UNIT_PX,
						ViewUtils.getPXByWidth(48));
				FrameLayout.LayoutParams valueParams = new FrameLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL | Gravity.RIGHT);
				value.setTextColor(Constant.sBlack54Color);
				value.setText(DATA_VALUE[position]);
				itemView.addView(value, valueParams);
				
			} else {
				itemView = (FrameLayout) convertView;
			}

			return itemView;
		}

		@Override
		public void onClick(View v) {
			
		}
	}
	
	
	
}
