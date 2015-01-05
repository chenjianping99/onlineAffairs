package com.guangzhou.gov.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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
import com.guangzhou.gov.manager.UserManager;
import com.guangzhou.gov.util.Constant;
import com.guangzhou.gov.util.LogUtils;

/**
 * 
 * @author chenjianping
 *
 */
public class MyInfoPageView extends FrameLayout implements OnClickListener
{
	private Context mContext;
	public MyInfoPageView(Context context)
	{
		super(context);
		mContext = context;
		FrameLayout.LayoutParams thisParams = new FrameLayout.LayoutParams(
				Constant.sRealWidth, Constant.sRealHeight - Constant.sBottomH, Gravity.TOP);
		this.setLayoutParams(thisParams);
		setBackgroundColor(Constant.sTitle3BgColor);
		
		addmTitle();
		addmPerson();
		addListView();
		addLine();
	}

	private void addLine() {
		View v = new View(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM);
        v.setBackgroundColor(Constant.sBlack10Color);
        addView(v, params);
	}

	private static final int TITLE_H = ViewUtils.getPXByHeight(168);
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
	}
	
	private TextView mPerson;
	private FrameLayout mPersonView;
	private static final int PERSON_H = ViewUtils.getPXByHeight(264);
	private static final int TIP_H = ViewUtils.getPXByHeight(120);
	private void addmPerson() {
		mPersonView = new FrameLayout(mContext);
		LayoutParams mParams = new LayoutParams(LayoutParams.MATCH_PARENT, 
				PERSON_H, Gravity.TOP);
		mParams.topMargin = TITLE_H;
		mPersonView.setPadding(OnlineAffairsView.LEFT_MARGIN, 0, OnlineAffairsView.LEFT_MARGIN, 0);
		mPersonView.setBackgroundColor(0xffffffff);
		addView(mPersonView, mParams);
		
		mPerson = new TextView(mContext);
		mPerson.setTextColor(Constant.sBlack87Color);
		mPerson.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(50));
		mPerson.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		Drawable a = getResources().getDrawable(R.drawable.person_avatar);
		a.setBounds(0, 0, ViewUtils.getPXByHeight(166), ViewUtils.getPXByHeight(164));
		mPerson.setCompoundDrawables(a, null, null, null);
		int padding = ViewUtils.getPXByWidth(55);
		mPerson.setCompoundDrawablePadding(padding);
		mPerson.setLineSpacing(1.2f, 1.2f);
        LayoutParams paramsp = new LayoutParams(LayoutParams.WRAP_CONTENT, 
        		LayoutParams.WRAP_CONTENT, Gravity.LEFT | Gravity.CENTER_VERTICAL);
        mPersonView.addView(mPerson, paramsp);
        
        View rightTip = new View(mContext);
		rightTip.setBackgroundResource(R.drawable.right_tip);
		LayoutParams rightTipP = new LayoutParams(
				ViewUtils.getPXByWidth(20), ViewUtils.getPXByWidth(36));
		rightTipP.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
		mPersonView.addView(rightTip, rightTipP);
        
		if (UserManager.getInstance().mUser.getUserInfo() != null) {
			setNumber(UserManager.getInstance().mUser.getUserInfo().getU_name(), UserManager.getInstance().mUser.getUserInfo().getMobile_phone());
		}
        /*View mTips = new View(mContext);
        mTips.setBackgroundColor(0xfff3f3f3);
        LayoutParams mTipParams = new LayoutParams(LayoutParams.MATCH_PARENT, TIP_H, Gravity.TOP);
        mTipParams.topMargin = TITLE_H + PERSON_H;
        addView(mTips, mTipParams);
        
        View v = new View(mContext);
		LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT, 2, Gravity.TOP);
		params2.topMargin = TITLE_H + PERSON_H + TIP_H;
        v.setBackgroundColor(0xffd6d6d6);
        addView(v, params2);*/
	}
	
	private void setNumber(String name, String phone) {
    	SpannableString ss =  new SpannableString(name + "\n" + phone);
    	 //ss.setSpan(new RelativeSizeSpan(0.75f), name.length(), ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // set size
    	 ss.setSpan(new ForegroundColorSpan(Constant.sBlack54Color), name.length(), ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // set color
    	 mPerson.setText(ss);
    }
	
	private ListView mListView;
	private ArrayList<String> mDepartMentsList = new ArrayList<String>();
	private MyAdapter mMyAdapter;
	private void addListView() {
		int margins = ViewUtils.getPXByHeight(0);
		mListView = new ListView(getContext());
		LayoutParams pager = new LayoutParams(
				Constant.sRealWidth, LayoutParams.WRAP_CONTENT, Gravity.TOP);
		pager.topMargin = TITLE_H + PERSON_H + TIP_H + margins;
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
		mListView.setDivider(new ColorDrawable(0xffd6d6d6));
		mListView.setDividerHeight(ViewUtils.getPXByHeight(2));
		
		mDepartMentsList.clear();
		for (int i = 0; i < DATA.length; i++) {
			mDepartMentsList.add(i, DATA[i]);
		}
		
		initAboutView();
	}
		
	private final static String[] DATA = {
		"进度状态查询",  "服务水平评议",  "推荐好友使用",  "关于"
	};
	
	@Override
	public void onClick(View v)
	{

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
						ViewUtils.getPXByHeight(180));
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
				
				
				View rightTip = new View(c);
				rightTip.setBackgroundResource(R.drawable.right_tip);
				FrameLayout.LayoutParams rightTipP = new FrameLayout.LayoutParams(
						ViewUtils.getPXByWidth(20), ViewUtils.getPXByWidth(36), Gravity.CENTER_VERTICAL | Gravity.RIGHT);
				itemView.addView(rightTip, rightTipP);
				
				if (position == DATA.length - 1) {
					LogUtils.log(null, "position = " + position);
					itemView.setOnClickListener(this);
				}
				
			} else {
				itemView = (FrameLayout) convertView;
			}
			
			return itemView;
		}

		 
		@Override
		public void onClick(View v) {
			if (mMyInfoPageAboutView != null) {
				mMyInfoPageAboutView.setVisibility(VISIBLE);
				mIsFirstView = false;
			}
		}
	}
	
	private MyInfoPageAboutView mMyInfoPageAboutView;
	private void initAboutView() {
		mMyInfoPageAboutView = new MyInfoPageAboutView(mContext);
		addView(mMyInfoPageAboutView);
		mMyInfoPageAboutView.setVisibility(INVISIBLE);
	}
	
	public static boolean mIsFirstView = true;
	public boolean ismIsFirstView() {
		return mIsFirstView;
	}

}
