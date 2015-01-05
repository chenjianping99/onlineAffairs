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
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.guangzhou.gov.R;
import com.guangzhou.gov.util.Constant;

/**
 * 
 * @author chenjianping
 *
 */
public class GovDepartmentsView extends FrameLayout
{
	private Context mContext;
	public GovDepartmentsView(Context context)
	{
		super(context);
		mContext = context;
		FrameLayout.LayoutParams thisParams = new FrameLayout.LayoutParams(
				Constant.sRealWidth, Constant.sRealHeight - Constant.sBottomH, Gravity.TOP);
		this.setLayoutParams(thisParams);
		setBackgroundColor(Color.WHITE);
		
		addmTitle();
		addListView();
		addLine();
		addTourismView();
	}

	private void addLine() {
		View v = new View(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM);
        v.setBackgroundColor(Constant.sBlack10Color);
        addView(v, params);
	}

	private static final int TIP_H = ViewUtils.getPXByHeight(120);
	private static final int TITLE_H = ViewUtils.getPXByHeight(168);
	private static final int LEFT_MARGIN = ViewUtils.getPXByWidth(50);
	private ImageView mSearch;
	private TextView mTitle;
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
		mTitle.setCompoundDrawablePadding(OnlineAffairsView.LEFT_MARGIN);
		mTitle.setPadding(OnlineAffairsView.LEFT_MARGIN, 0, 0, 0);
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

        TextView mTips = new TextView(mContext);
        mTips.setTextColor(Constant.sBlack54Color);
        mTips.setBackgroundColor(Constant.sTitle2BgColor);
        mTips.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(45));
        mTips.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        mTips.setText(R.string.departments_choose_tip);
        LayoutParams mTipParams = new LayoutParams(LayoutParams.MATCH_PARENT, TIP_H, Gravity.TOP);
        mTipParams.topMargin = TITLE_H;
        mTips.setPadding(LEFT_MARGIN, 0, 0, 0);
        addView(mTips, mTipParams);
        
        View v = new View(mContext);
		LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT, 2, Gravity.TOP);
		params2.topMargin = TITLE_H + TIP_H;
        v.setBackgroundColor(Constant.sBlack10Color);
        addView(v, params2);
	}
	
	private ListView mListView;
	private ArrayList<String> mDepartMentsList = new ArrayList<String>();
	private MyAdapter mMyAdapter;
	private void addListView() {
		int margins = ViewUtils.getPXByHeight(0);
		mListView = new ListView(getContext());
		LayoutParams pager = new LayoutParams(
				LayoutParams.MATCH_PARENT, OnlineAffairsView.BODY_H, Gravity.TOP);
		pager.topMargin = TITLE_H + TIP_H + margins;
		addView(mListView, pager);
		mDepartMentsList.clear();
		for (int i = 0; i < DATA.length; i++) {
			mDepartMentsList.add(i, DATA[i]);
		}
		
		mMyAdapter = new MyAdapter();
		mListView.setAdapter(mMyAdapter);
		mListView.setFadingEdgeLength(0);
		mListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		mListView.setCacheColorHint(Color.TRANSPARENT);
		mListView.setVerticalFadingEdgeEnabled(false);
		mListView.setVerticalScrollBarEnabled(false);
		mListView.setFooterDividersEnabled(false);
		mListView.setDivider(new ColorDrawable(Constant.sBlack10Color));
		mListView.setDividerHeight(ViewUtils.getPXByHeight(2));
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == mTourismIndex) {
					mTourismView.setIndex(mTourismIndex);
					mTourismView.setVisibility(VISIBLE);
				} else if (position == mDizhengIndex) {
					mTourismView.setIndex(mDizhengIndex);
					mTourismView.setVisibility(VISIBLE);
				} else if (position == mShangwuIndex) {
					mTourismView.setIndex(mShangwuIndex);
					mTourismView.setVisibility(VISIBLE);
				} else {
					//没有做
				}
			}
		});
	}
		
	public static final String GOV_INDEX = "gov_index";
	public static final int mTourismIndex = 4;
	public static final int mDizhengIndex = 5;
	public static final int mShangwuIndex = 6;
	private final static String[] DATA = {
		"省编办",  "省发展改革委",  "省经济和信息化委",  "省教育厅",  "省旅游局", "省地震局",   "省商务厅",  
		"省科技厅",  "省民族宗教委",  "省公安厅",  "省安全厅",  "省民政厅",
		"省司法厅",  "省财政厅", "省人力资源社会保障厅, 省国土资源厅",   "省环境保护厅",  
		"省住房城乡建设厅",   "省交通运输厅",   "省水利厅", "省农业厅",   "省林业厅",   
		 "省文化厅",   "省卫生计生委",   "省审计厅",   "省国资委",  
		"省地税局",   "省体育局",   "省统计局",   "省工商局",   "省新闻出版广电局	",  
		"省海洋渔业局",   "省质监局",   "省安全监管局",   "省知识产权局",   "省法制办",  
		"省金融办",   "省食品药品监管局	",   "省残联	",   "省社保基金管理局", 
		"省气象局",   "省档案局",   "省人防办（省民防办）",   "省盐务局",   "省中医药局"
	};
	

	private TourismView mTourismView;
	private void addTourismView() {
		mTourismView = new TourismView(mContext);
		mTourismView.setIndex(mTourismIndex);
		addView(mTourismView);
		mTourismView.setVisibility(INVISIBLE);
	}

	/**
	 * 
	 * @author chenjianping
	 * 
	 */
	class MyAdapter extends BaseAdapter {

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
			LinearLayout itemView = null;
			if (convertView == null) {
				itemView = new LinearLayout(c);
				itemView.setOrientation(LinearLayout.VERTICAL);
				itemView.setGravity(Gravity.CENTER_VERTICAL);
				AbsListView.LayoutParams params = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, 
						ViewUtils.getPXByHeight(144));
				itemView.setLayoutParams(params);
				itemView.setPadding(OnlineAffairsView.LEFT_MARGIN, 0, 0, 0);

				TextView department = new TextView(c);
				department.setTag("department");
				department.setTextSize(TypedValue.COMPLEX_UNIT_PX,
						ViewUtils.getPXByWidth(48));
				/*department.setTypeface(null, Typeface.BOLD);
				Drawable locationIcon = getResources().getDrawable(R.drawable.cal_tip);
				int w = ViewUtils.getPXByHeight(37);
				int h = ViewUtils.getPXByHeight(50);
				locationIcon.setBounds(0, 0, w, h);
				time.setCompoundDrawables(locationIcon, null, null, null);
				time.setCompoundDrawablePadding(ViewUtils.getPXByWidth(10));*/
				department.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
				
				LinearLayout.LayoutParams timeParams = new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				department.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
				itemView.addView(department, timeParams);
				department.setTextColor(Constant.sBlack87Color);
				
				/*if (position == mTourismIndex) {
					itemView.setOnClickListener(this);
				}*/
				
			} else {
				itemView = (LinearLayout) convertView;
			}
			
			TextView department = (TextView) itemView.findViewWithTag("department");
			 department.setText(mDepartMentsList.get(position));
			return itemView;
		}
	}
	
}
