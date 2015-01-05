package com.guangzhou.gov.view;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
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
import com.guangzhou.gov.manager.UserManager;
import com.guangzhou.gov.util.Constant;
import com.guangzhou.gov.util.LogUtils;

/**
 * 
 * @author chenjianping
 *
 */
public class VerificationView extends FrameLayout implements OnClickListener
{
	private VerificationActivity mContext;
	public VerificationView(VerificationActivity context)
	{
		super(context);
		mContext = context;
		FrameLayout.LayoutParams thisParams = new FrameLayout.LayoutParams(
				Constant.sRealWidth, Constant.sRealHeight - Constant.sBottomH, Gravity.TOP);
		this.setLayoutParams(thisParams);
		setBackgroundColor(Color.WHITE);
		
		addmTitle();
		addListView();
	}

	private void addLine() {
		View v = new View(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM);
        v.setBackgroundColor(0xffd6d6d6);
        addView(v, params);
	}

	private TextView mTitle, mRightText;
	//private View mBack;
	private void addmTitle() {
		mTitle = new TextView(mContext);
		mTitle.setTextColor(Color.WHITE);
		mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(60));
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
        
        /*mBack = new View(mContext);
        mBack.setBackgroundResource(R.drawable.login_title_icon);
        int w = ViewUtils.getPXByWidth(108);
        LayoutParams paramsmBack = new LayoutParams(w, w, Gravity.TOP | Gravity.LEFT);
        paramsmBack.topMargin = (OnlineAffairsView.TITLE_H - w) / 2;
        paramsmBack.leftMargin = OnlineAffairsView.LEFT_MARGIN;
        addView(mBack, paramsmBack);
        mBack.setOnClickListener(this);
        mBack.setVisibility(INVISIBLE);*/
        
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
	}
	
	private String mLine2Str, mLine3Str;
	public void setName(String title, String line2Name, String line3Name) {
		if (mTitle != null) {
			mTitle.setText(title);
		}
		mLine2Str = line2Name;
		mLine3Str = line3Name;
		LogUtils.log(null, "mLine2Str =" + mLine2Str + "mLine3Str = " + mLine3Str);
		addLine2();
	}
	
	public static final int TIP_H = ViewUtils.getPXByHeight(120);
	private void addLine2() {
		TextView mTips = new TextView(mContext);
		mTips.setTextColor(Constant.sBlack87Color);
        mTips.setBackgroundColor(Constant.sTitle2BgColor);
        mTips.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(45));
        mTips.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        mTips.setText(mLine2Str);
        LayoutParams mTipParams = new LayoutParams(LayoutParams.MATCH_PARENT, TIP_H, Gravity.TOP);
        mTipParams.topMargin = OnlineAffairsView.TITLE_H;
        mTips.setPadding(OnlineAffairsView.LEFT_MARGIN, 0, 0, 0);
        addView(mTips, mTipParams);        
		addLine3();
	}
	
	private void addLine3() {
		TextView mTips = new TextView(mContext);
        mTips.setTextColor(Constant.sBlack87Color);
        mTips.setBackgroundColor(Constant.sTitle2BgColor);
        mTips.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(45));
        mTips.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        mTips.setText(mLine3Str);
        LayoutParams mTipParams = new LayoutParams(LayoutParams.MATCH_PARENT, TIP_H, Gravity.TOP);
        mTipParams.topMargin = OnlineAffairsView.TITLE_H + TIP_H;
        mTips.setPadding(OnlineAffairsView.LEFT_MARGIN, 0, 0, 0);
        addView(mTips, mTipParams);

        addLine4();
	}
	
	private TextView[] mBanShi;
	private int [] mBanshiRes = {R.string.shengban, R.string.jindu, R.string.jieguo, R.string.zixun, R.string.pingyi};
	private static final int LINE4_H = ViewUtils.getPXByHeight(200);
	private LinearLayout mLine4Ly;
	private void addLine4() {
		mLine4Ly = new LinearLayout(mContext);
		mLine4Ly.setOrientation(LinearLayout.HORIZONTAL);
		mLine4Ly.setGravity(Gravity.CENTER_VERTICAL);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 
				LINE4_H, Gravity.TOP);
		params.topMargin = OnlineAffairsView.TITLE_H + TIP_H * 2;
		mLine4Ly.setLayoutParams(params);
		mLine4Ly.setPadding(OnlineAffairsView.LEFT_MARGIN / 2, 0, OnlineAffairsView.LEFT_MARGIN / 2, 0);
		mBanShi = new TextView[5];
		for (int i = 0; i < mBanShi.length; i++) {
			mBanShi[i] = new TextView(mContext);
			mBanShi[i].setBackgroundResource(R.drawable.banshi_btn);
			mBanShi[i].setText(mBanshiRes[i]);
			mBanShi[i].setTextColor(getResources().getColor(R.color.shengban_text_color));
			mBanShi[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(48));
			mBanShi[i].setGravity(Gravity.CENTER);
			int h = ViewUtils.getPXByWidth(108);
			LinearLayout.LayoutParams prams = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT,  h, 1);
			prams.leftMargin = OnlineAffairsView.LEFT_MARGIN / 2;
			prams.rightMargin = OnlineAffairsView.LEFT_MARGIN / 2;
			mLine4Ly.addView(mBanShi[i], prams);
			mBanShi[i].setOnClickListener(this);
		}
		addView(mLine4Ly, params);
		
		addLineBlack(OnlineAffairsView.TITLE_H + TIP_H);
		addLineBlack(OnlineAffairsView.TITLE_H + TIP_H * 2);
		//addLineBlack(OnlineAffairsView.TITLE_H + TIP_H * 2 + LINE4_H);
	}
	
	private void addLineBlack(int topMargin) {
		View v = new View(mContext);
		LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.TOP);
		params1.topMargin = topMargin;
		v.setBackgroundColor(Constant.sBlack10Color);
        addView(v, params1);
	}
	
	private View mViewLine3;
	private void addmViewLine3(int topMargin) {
		mViewLine3 = new View(mContext);
		LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.TOP);
		params1.topMargin = topMargin;
		mViewLine3.setBackgroundColor(Constant.sBlack10Color);
        addView(mViewLine3, params1);
	}
	
	private ListView mListView;
	private ArrayList<String[]> mDepartMentsList = new ArrayList<String[]>();
	private MyAdapter mMyAdapter;
	private void addListView() {
		mListView = new ListView(getContext());
		int topMargin = OnlineAffairsView.TITLE_H + TIP_H * 2 + LINE4_H;
		LayoutParams pager = new LayoutParams(
				LayoutParams.MATCH_PARENT, Constant.sRealHeight - topMargin, Gravity.TOP);
		pager.topMargin = topMargin;
		addView(mListView, pager);
		addmViewLine3(OnlineAffairsView.TITLE_H + TIP_H * 2 + LINE4_H);
		
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
					if (VerificationActivity.sIndex < 2 && position == ADDR_INDEX) {
						
						String uri = "geo:"
								+ GEO[VerificationActivity.sIndex][0] + "," 
								+ GEO[VerificationActivity.sIndex][1] + "," 
								+  DATA[VerificationActivity.sIndex][ADDR_INDEX][1];
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
						try {
							//打开地图跳转
							mContext.startActivity(intent);
						} catch (Exception e) {
							/*Toast toast = Toast.makeText(mContext,
								     "安装地图应用可打开地址 ", Toast.LENGTH_LONG);
								   toast.setGravity(Gravity.CENTER, 0, 0);
								   toast.show();*/
							MyToast.showToast(mContext, "安装地图应用可浏览该地址");
						}
						
					} else if (VerificationActivity.sIndex < 2 && position ==  PHONE_INDEX) {
						//打开电话
						Intent intent = new Intent(Intent.ACTION_DIAL);
						intent.setData(Uri.parse("tel:" + DATA[VerificationActivity.sIndex][PHONE_INDEX][1]));
						mContext.startActivity(intent); 
					}
			}
		});

		mDepartMentsList.clear();
		for (int i = 0; i < DATA[VerificationActivity.sIndex].length; i++) {
			mDepartMentsList.add(i, DATA[VerificationActivity.sIndex][i]);
		}
		initShengbanNext();
	}
		
	
	private ShengbanView mShengban;
	private void initShengban() {
		mShengban = new ShengbanView(mContext, mShengbanNextView);
		int topMargin = OnlineAffairsView.TITLE_H + TIP_H * 2;
		LayoutParams pager = new LayoutParams(
				LayoutParams.MATCH_PARENT, Constant.sRealHeight - topMargin, Gravity.TOP);
		pager.topMargin = topMargin;
		addView(mShengban, pager);
		mShengban.setVisibility(INVISIBLE);
	}
	
	private ShengbanNextView mShengbanNextView;
	private void initShengbanNext() {
		mShengbanNextView = new ShengbanNextView(mContext);
		mShengbanNextView.addParentListener(this);
		int topMargin = OnlineAffairsView.TITLE_H + TIP_H * 2;
		LayoutParams pager = new LayoutParams(
				LayoutParams.MATCH_PARENT, Constant.sRealHeight - topMargin, Gravity.TOP);
		pager.topMargin = topMargin;
		addView(mShengbanNextView, pager);
		mShengbanNextView.setVisibility(INVISIBLE);
		
		initShengban();
	}
	
	private boolean mIsFirstView = true;
	public boolean isFirstView() {
		return mIsFirstView;
	}
	
	
	
	@Override
	public void onClick(View v)
	{
		if (v == mBanShi[0]) {
			if (UserManager.getInstance().mUser == null ||
					UserManager.getInstance().mUser.getUserInfo() == null) {
				Intent intent = new Intent();
				intent.putExtra(Tab3Activity.CLOSE_TAG, true);
				intent.setClass(mContext, Tab3Activity.class);
				mContext.startActivity(intent);
				mContext.finish();
			} else {
				mTitle.setText("申办");
				mShengban.setVisibility(VISIBLE);
				mShengbanNextView.setVisibility(INVISIBLE);
				//mBack.setVisibility(VISIBLE);
				mListView.setVisibility(INVISIBLE);
				mLine4Ly.setVisibility(INVISIBLE);
				mViewLine3.setVisibility(INVISIBLE);
				mIsFirstView = false;
			}
		} else if (v == mTitle) {
			if (mIsFirstView) {
				mContext.finish();
			} else	if (!mShengbanNextView.isShown()) {
				handleCommitSuccess();
			} else {
				mShengban.setVisibility(VISIBLE);
				mShengbanNextView.setVisibility(INVISIBLE);
				mListView.setVisibility(INVISIBLE);
				mLine4Ly.setVisibility(INVISIBLE);
				mViewLine3.setVisibility(INVISIBLE);
				mIsFirstView = false;
			}
		}
	}

	public void handleCommitSuccess() {
		mTitle.setText(R.string.tourism_name);
		mShengban.setVisibility(INVISIBLE);
		mListView.setVisibility(VISIBLE);
		mLine4Ly.setVisibility(VISIBLE);
		mViewLine3.setVisibility(VISIBLE);
		mIsFirstView = true;
		this.invalidate();
	}
	
	/**
	 * 
	 * @author chenjianping
	 * 
	 */
	class MyAdapter extends BaseAdapter {

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
						LayoutParams.WRAP_CONTENT);
				itemView.setLayoutParams(params);
				itemView.setPadding(OnlineAffairsView.LEFT_MARGIN, OnlineAffairsView.LEFT_MARGIN, 
						OnlineAffairsView.LEFT_MARGIN, OnlineAffairsView.LEFT_MARGIN);

				TextView department = new TextView(c);
				department.setTag("department");
				/*department.setTextSize(TypedValue.COMPLEX_UNIT_PX,
						ViewUtils.getPXByWidth(48));
				Drawable locationIcon = getResources().getDrawable(RES_ID[position]);
				LogUtils.log(null, "position = " + position);
				int w = ViewUtils.getPXByHeight(108);
				int h = ViewUtils.getPXByHeight(108);
				locationIcon.setBounds(0, 0, w, h);
				department.setCompoundDrawables(locationIcon, null, null, null);
				department.setCompoundDrawablePadding(OnlineAffairsView.LEFT_MARGIN);
				department.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);*/
				
				LinearLayout.LayoutParams timeParams = new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				itemView.addView(department, timeParams);
				department.setTextColor(Constant.sBlack87Color);
				
			} else {
				itemView = (LinearLayout) convertView;
			}
			TextView department = (TextView) itemView.findViewWithTag("department");
			if (department != null) {
				department.setTextSize(TypedValue.COMPLEX_UNIT_PX,
						ViewUtils.getPXByWidth(48));
				Drawable locationIcon = getResources().getDrawable(RES_ID[position]);
				LogUtils.log(null, "position = " + position);
				int w = ViewUtils.getPXByHeight(108);
				int h = ViewUtils.getPXByHeight(108);
				locationIcon.setBounds(0, 0, w, h);
				department.setCompoundDrawables(locationIcon, null, null, null);
				department.setCompoundDrawablePadding(OnlineAffairsView.LEFT_MARGIN);
				department.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
				setNumber(department, mDepartMentsList.get(position)[0], mDepartMentsList.get(position)[1]);
			}
			return itemView;
		}
		
		private void setNumber(TextView v, String name, String phone) {
			int color = Constant.sBlack54Color;
	    	 if (VerificationActivity.sIndex < 2 && name.equals(mDepartMentsList.get(ADDR_INDEX)[0])) {
	    		 v.setId(ADDR_INDEX);
	    		 color = Color.BLUE;
	    	 } else  if (VerificationActivity.sIndex < 2 && name.equals(mDepartMentsList.get(PHONE_INDEX)[0])) {
	    		 v.setId(PHONE_INDEX);
	    		 color = Color.BLUE;
	    	 }
	    	 
	    	 SpannableString ss =  new SpannableString(name + "\n" + phone);
	    	 ss.setSpan(new RelativeSizeSpan(0.85f), name.length(), ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // set size
	    	 ss.setSpan(new ForegroundColorSpan(color), name.length(), ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // set color
	    	 v.setText(ss);
	    	 v.setLineSpacing(1.2f, 1.2f);
	    }

	}
	
	public void onDestory() {
		LogUtils.log(null, "Virification onDestory()" );
		if (mShengbanNextView != null) {
			mShengbanNextView.onDestory();
		}
	}
	
	
	private static final int ADDR_INDEX = 8;
	private static final int PHONE_INDEX = 9;
	private final static String[][][] DATA = {
		{	{ "办理对象", "符合办理条件企业" },
			{ "办理条件", "已取得许可经营大陆居民赴台湾地区旅游业务的合法旅行社" },
			{
					"所需材料",
					"(一) 提交书面申请. 内容包括: 法人授权委托证明书(需加盖旅行社公章),"
							+ "《企业法人营业执照》副本复印件、《旅行社业务经营许可证》副本复印件等相关材料;"
							+ "（二）申请领取《大陆居民赴台湾地区旅游团名单表》备案登记表及台湾游名单表使用汇总表 " },
			{
					"窗口办理流程",
					"（一）旅行社委托指定备案人员携带申请书及相关证明文件（加盖旅行社公章），"
							+ "并填写申请领取《大陆居民赴台湾地区旅游团名单表》备案登记表及台湾游名单表使用汇总表后，"
							+ "前往省旅游局领取大陆居民赴台湾地区旅游团名单表"
							+ "（二）工作人员验证其身份，按需求发放名单表，并做好登记工作" },
			{ "网上办理流程", "在线申办-网上预受理-受理-处理-办结" },
			{ "办理时限", "法定期限： 5个工作日\n承诺期限：5个工作日" },
			{ "办事窗口", "广东省旅游局行业管理处" },
			{ "工作时间", "逢星期一至星期五（上午8：30－12：00，下午：14：00－17：00），法定假日除外" },
			{ "地址", "广州市黄埔大道西463号7楼708" },
			{ "联系电话", "020-87513677" },
			{
					"交通指引",
					" 1、从广州内环（或东风路）到我单位：黄埔大道出口下，沿黄埔大道一直向东3000米，到华威达酒店交通灯掉头，西行100米。\n"
							+ "2、从华南快速干线到我单位：黄埔大道出口下，沿黄埔大道一直向西500米（经暨南大学、华威达酒店）。\n"
							+ " 3、地铁三号线（番禺广场-天河客运站）岗顶站C出入口下，石牌西路向黄埔大道西方向走约980米，途径广州市海关缉私局大楼、天晟明苑。\n"
							+ "4、公交车线路(石牌村站）：23路、53路、63路、137路、140路、227路、243路、245路、261路、、278路、292路、517路、518路、542路、540路、541路、545路、562路、882路、884路、大学城4线――站点名称：石牌村。 附近的标志性建筑：华威达酒店、国防大厦、丰田汽车销售部。两个“石牌”牌坊（中间）。对面为勤建大厦、东田大厦、赛马场和珠江新城。" },
			{ "收费标准", "不收费" }, 
			{ "办理依据", "《大陆居民赴台湾地区旅游团名单表》管理办法" },
			{ "备注", "无" }
		},
		
		//省地震局
		{	{ "办理对象", "企业、个人等" },
			{ "办理条件", "1、工程项目属于依法应开展地震安全性评价的范围\n" 
				+"2、承担地震安全性评价工作的单位应具有相应的地震安全性评价资质" },
			{
					"所需材料",
					"1、建设工程地震安全性评价结果审定及抗震设防要求确定行政许可申请表\n"
					+"2、地震安全性评价报告（7-15份）\n"
					+"3、地震安全性评价委托合同（复印件）\n"
					+"4、承担地震安全性评价工作单位的资质证书（复印件）\n"
					+"5、承担地震安全性评价工作的单位受工程建设单位委托提出申请的应当提供建设单位签署的委托书"},
			{
					"窗口办理流程",
					"提交材料—现场审查—确认受理—后台承办（专家评审）—处长审核—局长签发—发文办结"},
			{ "网上办理流程", "提交材料—网上审查—确认受理—后台承办（专家评审）—处长审核—局长签发—发文办结" },
			{ "办理时限", "法定期限： 15个工作日\n承诺期限：15个工作日" },
			{ "办事窗口", "省地震安全性评价行政许可窗口" },
			{ "工作时间", "逢星期一至星期五（上午8：30－12：00，下午：14：00－17：00），法定假日除外" },
			{ "地址", "广州市越秀区先烈中路81号洪都大厦A座2409房" },
			{ "联系电话", "02037656311" },
			{
					"交通指引",
					" 先烈中路黄花岗站，乘公交16路、74路、85路、201路、219路、220路、223路、285路、535路、833路、862路可达。" },
			{ "收费标准", "不收费" }, 
			{ "办理依据", "《中华人民共和国防震减灾法》\n"
					+ "《地震安全性评价管理条例》\n"
					+ "《广东省防震减灾条例》第二十一条规定，下列建设工程应当进行地震安全性评价，并按照经审定的地震安全性评价报告所确定的抗震设防要求进行抗震设防：\n"
					+ "（一）核电站和其他核设施，易燃、易爆和剧毒物质的生产、贮存及输送管道（网）等可能发生严重次生灾害的建设工程；\n"
					+"（二）公路、城市道路、铁路干线的单孔跨径超过一百五十米的特大桥梁和大型隧道，Ⅰ级铁路干线的重要车站与铁路枢纽的主要建筑工程，城市轨道交通工程，Ⅱ类以上机场，年吞吐量二百万吨以上的大型港口；\n"
					+"（三）大型水库的大坝和城市上游的Ⅰ级挡水坝，装机容量一百万千瓦以上的热电厂三十万千瓦以上的水电厂及其变电站，五百千伏以上的枢纽变电站；\n"
					+"（四）省、市二百千瓦以上大功率广播发射台和电视台，通信枢纽的程控机主楼；\n"
					+"（五）大中城市主要供电、供水、供气、输油管（网）的调度控制工程；\n"
					+"（六）大型工矿企业，大型粮油加工厂，大中型化工厂、炼油厂，大型海洋平台，二百万吨以上大型船坞项目，高度超过一百米（地震基本烈度Ⅶ度和Ⅷ度区中软、软弱场地高度超过八十米）的建设工程；\n"
					+"（七）位于地震基本烈度Ⅵ度以上（含Ⅵ度）分界线两侧各八公里范围内或者位于地震动参数区划图峰值加速度分区分界线两侧各四公里地区内，占地范围跨越不同地质构造和工程地质单元的建设工程；\n"
					+"（八）法律、法规规定和省人民政府确定的其他需要进行地震安全性评价的工程。\n"
					+"见业务表格：《开展地震安全性评价的建设工程范围界定表》" },
			{ "备注", "无" }
		},
		
		//省商务厅
		{	{ "办理对象", "符合办理条件企业" },
			{ "办理条件", "申报出口资质的生产企业应具备的条件：\n" 
			+"1.汽车、摩托车生产企业应列入工业和信息化部《车辆生产企业及产品公告》；具备有效的国家强制性产品认证（CCC认证）。\n" 
			+"2.低速汽车生产企业应列入工业和信息化部《车辆生产企业及产品公告》。\n" 
			+"3.非公路用两轮摩托车生产企业应具备有效的ISO9000企业质量管理体系认证；获得国家推行的自愿性产品认证或相关国际认证。\n" 
			+"4.全地形车生产企业应具备有效的ISO9000企业质量管理体系认证；获得相关国际认证。\n" 
			+"5.所有产品类别的生产企业须具备与出口保有量相适应的维修服务能力。\n" },
			
			{
					"所需材料",	"1.申请表。\n"
					+"2.海关报关单复印件等出口证明材料。\n"
					+"3.企业境外售后维修服务网点总体建设及变动情况。\n"},
			{
					"窗口办理流程",
					"符合条件的生产企业须于每年9月10日前，将申请表、海关报关单复印件等出口证明材料、企业境外售后维修服务网点总体建设及变动情况，报至各市（含顺德区，不含深圳）机电办汇总后报我办，我办初审后报商务部。"},
			{ "网上办理流程", "无" },
			{ "办理时限", "法定期限：符合条件的生产企业须于每年9月10日前，将申请表（见附件）、海关报关单复印件等出口证明材料、企业境外售后维修服务网点总体建设及变动情况，报至各市（含顺德区，不含深圳）机电办汇总后报我办。我办初审后9月30日前报商务部。商务部会同工业和信息化部、海关总署、质检总局、国家认监委于每年10月公示下一年度《符合申领汽车和摩托车出口许可证条件企业名单》，并于12月正式发布。\n" 
				+"承诺期限：符合条件的生产企业须于每年9月10日前，将申请表（见附件）、海关报关单复印件等出口证明材料、企业境外售后维修服务网点总体建设及变动情况，报至各市（含顺德区，不含深圳）机电办汇总后报我办。我办初审后9月30日前报商务部。商务部会同工业和信息化部、海关总署、质检总局、国家认监委于每年10月公示下一年度《符合申领汽车和摩托车出口许可证条件企业名单》，并于12月正式发布。" },
			{ "办事窗口", "无" },
			
			{ "收费标准", "不收费" }, 
			{ "办理依据", "《关于进一步规范汽车和摩托车产品出口秩序的通知》（商产发[2012]318号）"},
			{ "备注", "无" }
		},
	};

	private final int[] RES_ID = {R.drawable.verification_1, R.drawable.verification_2, R.drawable.verification_3, R.drawable.verification_4,
			R.drawable.verification_5, R.drawable.verification_6, R.drawable.verification_7, R.drawable.verification_8,
			R.drawable.verification_9, R.drawable.verification_10, R.drawable.verification_11, R.drawable.verification_12,
			R.drawable.verification_13, R.drawable.verification_14};
	
	private final float[][] GEO = {
			{23.1326580000f, 113.3471040000f},
			{23.1464900000f,113.3041360000f},
			};
	
}
