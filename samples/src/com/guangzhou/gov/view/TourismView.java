package com.guangzhou.gov.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
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
public class TourismView extends FrameLayout implements OnClickListener
{
	private Context mContext;
	private int mIndex;
	public TourismView(Context context)
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
	}
	
	public void setIndex(int index) {
		mIndex = index;
		mDepartMentsList.clear();
		int t = mapping(index);
		if (mTitle != null) {
			mTitle.setText(TITLE_TEXT[t]);
		}
		for (int i = 0; i < DATA[t].length; i++) {
			mDepartMentsList.add(i, DATA[t][i]);
		}
		mMyAdapter.notifyDataSetChanged();
	}
	
	private int mapping(int index) {
		int ret = 0;
		switch (index) {
		case GovDepartmentsView.mTourismIndex:
			ret = 0;
			break;
		case GovDepartmentsView.mDizhengIndex:
			ret = 1;
			break;
		case GovDepartmentsView.mShangwuIndex:
			ret = 2;
			break;
		default:
			break;
		}
		return ret;
	}
	
	public static final String[] TITLE_TEXT = {"广东省旅游局", "广东省地震局", "广东省商务厅"};

	private void addLine() {
		View v = new View(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM);
        v.setBackgroundColor(0xffd6d6d6);
        addView(v, params);
	}

	private static final int TIP_H = ViewUtils.getPXByHeight(120);
	private TextView mTitle;
	private TextView mRightText;
	private void addmTitle() {
		mTitle = new TextView(mContext);
		mTitle.setTextColor(Color.WHITE);
		mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(60));
		//mTitle.setTypeface(CircleContainer.sTypeface);
		mTitle.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		mTitle.setText(R.string.tourism_name);
		Drawable a = getResources().getDrawable(R.drawable.login_title_icon);
		a.setBounds(0, 0, ViewUtils.getPXByHeight(108), ViewUtils.getPXByHeight(108));
		mTitle.setCompoundDrawables(a, null, null, null);
		mTitle.setCompoundDrawablePadding(OnlineAffairsView.LEFT_MARGIN);
		mTitle.setPadding(OnlineAffairsView.LEFT_MARGIN, 0, 0, 0);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, OnlineAffairsView.TITLE_H, Gravity.TOP);
        mTitle.setBackgroundColor(Constant.sBlueColor);
        mTitle.setOnClickListener(this);
        addView(mTitle, params);
        
        mRightText = new TextView(mContext);
        mRightText.setTextColor(Color.WHITE);
        mRightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(42));
        mRightText.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        mRightText.setText(R.string.province_name);
		Drawable d = getResources().getDrawable(R.drawable.right_white_tip);
		d.setBounds(0, 0, ViewUtils.getPXByHeight(20), ViewUtils.getPXByHeight(36));
		mRightText.setCompoundDrawables(null, null, d, null);
		mRightText.setCompoundDrawablePadding(OnlineAffairsView.LEFT_MARGIN / 2);
		mRightText.setPadding(0, 0, OnlineAffairsView.LEFT_MARGIN, 0);
        LayoutParams rightTextP = new LayoutParams(LayoutParams.WRAP_CONTENT, 
        		OnlineAffairsView.TITLE_H, Gravity.TOP | Gravity.RIGHT);
        addView(mRightText, rightTextP);
        mRightText.setOnClickListener(this);

        TextView mTips = new TextView(mContext);
        mTips.setTextColor(Constant.sBlack54Color);
        mTips.setBackgroundColor(0xfffafafa);
        mTips.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(45));
        mTips.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        mTips.setText(R.string.affairs_tip);
        LayoutParams mTipParams = new LayoutParams(LayoutParams.MATCH_PARENT, TIP_H, Gravity.TOP);
        mTipParams.topMargin = OnlineAffairsView.TITLE_H;
        mTips.setPadding(OnlineAffairsView.LEFT_MARGIN, 0, 0, 0);
        addView(mTips, mTipParams);
        
        View v = new View(mContext);
		LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT, 2, Gravity.TOP);
		params2.topMargin = OnlineAffairsView.TITLE_H + TIP_H;
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
		pager.topMargin = OnlineAffairsView.TITLE_H + TIP_H + margins;
		addView(mListView, pager);
		
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
				if (position == mFuTaiIndex && mIndex == GovDepartmentsView.mTourismIndex) {
					Intent intent = new Intent();
					intent.setClass(mContext, VerificationActivity.class);
					intent.putExtra(GovDepartmentsView.GOV_INDEX, GovDepartmentsView.mTourismIndex);
					mContext.startActivity(intent);
				} else if (position == mDiZhengShengbanIndex && mIndex == GovDepartmentsView.mDizhengIndex) {
					Intent intent = new Intent();
					intent.setClass(mContext, VerificationActivity.class);
					intent.putExtra(GovDepartmentsView.GOV_INDEX, GovDepartmentsView.mDizhengIndex);
					mContext.startActivity(intent);
				} else if (position == mShangwuQicheShengbanIndex && mIndex == GovDepartmentsView.mShangwuIndex) {
					Intent intent = new Intent();
					intent.setClass(mContext, VerificationActivity.class);
					intent.putExtra(GovDepartmentsView.GOV_INDEX, GovDepartmentsView.mShangwuIndex);
					mContext.startActivity(intent);
				} else {
					//没有做
				}
			}
		});
		
	}
		
	private int mFuTaiIndex = 8;
	private int mDiZhengShengbanIndex = 2;
	private int mShangwuQicheShengbanIndex = 13;
	private final static String[][] DATA = {
		// 省旅游局
		{"144小时便利措施名单表核发",
		 "赴台旅游领队证核发",	
		 "全国导游人员资格考试",
		 "旅游投诉与咨询",
		 /*"港澳资旅行社试点经营广东省居民赴港澳团队旅游审核" ,*/
		    "澳门特别行政区的旅游经营者试点经营广东省居民赴港澳团队旅游审核",
		    "香港特别行政区的旅游经营者试点经营广东省居民赴港澳团队旅游审核",
		 "外资旅行社设立审核",
		 "经营出境游业务审核"	,
		"大陆居民赴台旅游名单表审验"	,
		"出境旅游签证专办员卡审核"	,
		"出境游组团社授权人签章备案"	,
		"港澳服务提供者在粤设立旅行社审批"	,
		"澳门特别行政区服务提供者在粤设立旅行社审批",
		"香港特别行政区服务提供者在粤设立旅行社审批"},
		
		// 省地震局
		{"甲级地震安全性评价单位资质审查",
			"乙级地震安全性评价单位资质审查",
			"建设工程地震安全性评价结果审定及抗震设防要求确定",
		 },
		 
		// 省商务厅
		{   "设立典当行及分支机构审批",	
			 "二手车交易市场经营者备案",	
			 //"设立拍卖企业及分公司核准 ",	
			   "设立拍卖企业分公司核准",
			   "设立拍卖企业核准",
			 "报废汽车回收（拆解）企业资格核准	",
			 "商业特许经营备案",	
			 "对外劳务合作经营资格核准",
			 "申请直销企业服务网点方案确认",
			 "自动进口许可证核发",
			 "钨品、锑品国营贸易出口企业资格初审",	
			 "直销企业产品说明重大变更审批",
			 "《最终用户和最终用途说明》初审",
			 "机电产品进口许可初审",	
			 "汽车、摩托车出口资质初审",	
			 "对外援助物资项目实施企业资格初审",	
			 "国际货运代理企业备案",	
			 "进出境货运车辆检查场开设、关闭及调整审批",	
			 "进出口商品配额指标分配",	
			 "外资研发中心采购设备免、退税资格审核认定",	
			//"进出口许可证核发",	
			  "进口许可证核发",
			    "出口许可证核发",
			//"限制类商品进出口经营资质初审",	
				   "限制类商品进口经营资质初审",
				   "限制类商品出口经营资质初审",
			// "对协议国家的纺织品出口原产地证核发",
			    "输土耳其纺织品原产地证核发",
			    "输土耳其丝麻原产地证核发",
			//"国家鼓励发展的外商投资项目确认书及外商投资企业进口更新设备、技术及配备件证明核发",	
			    "国家鼓励发展的外商投资项目确认书核发",
			    "外商投资企业进口更新设备、技术及配备件证明核发",
			//"限制类技术进出口许可",	
			 "限制类技术进口许可",
			    "限制类技术出口许可",
			//"属国家审批权限的外商投资企业设立、变更、终止审核",	
			    "属国家审批权限的外商投资企业设立审核",
			    "属国家审批权限的外商投资企业变更审核",
			    "属国家审批权限的外商投资企业终止审核",
			//"广东省沿海砂石出口作业点和港澳籍小型船舶进出砂石出口作业点作业许可",	
			    "广东省沿海砂石出口作业点许可",
			    "港澳籍小型船舶进出砂石出口作业点作业许可",
			//"输港澳劳务合作项目立项审核",	
			    "输香港劳务合作项目立项审核",
			    "输澳门劳务合作项目立项审核",
			//"对外承包工程经营资格审批",	
			    "对外承包工程经营资格审批",
			    "对外承包工程经营资格换证审核",
			//"两用物项和技术进出口许可",	
			    "敏感物项和技术出口许可",
			    "易制毒化学品进口许可",
			    "易制毒化学品出口许可",
			//"境外投资许可",
			   "境外投资一般核准",
			   "境外投资重点核准",
			   "境外机构一般核准",
		 },
	};
	
	@Override
	public void onClick(View v)
	{
		this.setVisibility(INVISIBLE);
	}

	/**
	 * 
	 * @author chenjianping
	 * 
	 */
	class MyAdapter extends BaseAdapter {

		SimpleDateFormat	mDateFormat;
		private int margins = ViewUtils.getPXByWidth(30);
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
						ViewUtils.getPXByHeight(198));
				itemView.setLayoutParams(params);
				itemView.setPadding(OnlineAffairsView.LEFT_MARGIN, 0, 0, 0);

				TextView department = new TextView(c);
				department.setTag("department");
				department.setTextSize(TypedValue.COMPLEX_UNIT_PX,
						ViewUtils.getPXByWidth(48));
				//department.setTypeface(null, Typeface.BOLD);
				/*Drawable locationIcon = getResources().getDrawable(R.drawable.cal_tip);
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
			} else {
				itemView = (LinearLayout) convertView;
			}
			TextView department = (TextView) itemView.findViewWithTag("department");
			 department.setText(mDepartMentsList.get(position));
			 if (position == mFuTaiIndex && mIndex == GovDepartmentsView.mTourismIndex) {
					department.setTextColor(Color.BLUE);
				} else if (position == mDiZhengShengbanIndex && mIndex == GovDepartmentsView.mDizhengIndex) {
					department.setTextColor(Color.BLUE);
				} else if (position == mShangwuQicheShengbanIndex && mIndex == GovDepartmentsView.mShangwuIndex) {
					department.setTextColor(Color.BLUE);
				} else {
					department.setTextColor(Constant.sBlack87Color);
				}
			return itemView;
		}
	}
}
