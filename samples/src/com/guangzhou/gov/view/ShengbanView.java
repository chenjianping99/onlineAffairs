package com.guangzhou.gov.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guangzhou.gov.R;
import com.guangzhou.gov.manager.UserManager;
import com.guangzhou.gov.net.bean.ApplyInfo;
import com.guangzhou.gov.net.bean.BaseBean;
import com.guangzhou.gov.net.bean.request.Request;
import com.guangzhou.gov.net.http.Http;
import com.guangzhou.gov.net.http.HttpListener;
import com.guangzhou.gov.net.http.HttpStatus;
import com.guangzhou.gov.util.Constant;
import com.guangzhou.gov.util.LogUtils;

/**
 * 
 * @author chenjianping
 *
 */
public class ShengbanView extends FrameLayout implements OnClickListener, HttpListener
{
	private VerificationActivity mContext;
	private ShengbanNextView mShengbanNextView;
	public ShengbanView(VerificationActivity context, ShengbanNextView v)
	{
		super(context);
		mContext = context;
		mShengbanNextView = v;
		setBackgroundColor(Color.WHITE);
		addmTitle();
		addInfoView();
	}
	
	public static final int TITLE_H = ViewUtils.getPXByHeight(126);
	private TextView mTitle;
	private void addmTitle() {
		mTitle = new TextView(mContext);
		mTitle.setTextColor(Constant.sBlack54Color);
		mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(48));
		mTitle.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		mTitle.setText(R.string.shengbanren_info);
		Drawable a = getResources().getDrawable(R.drawable.head);
		a.setBounds(0, 0, ViewUtils.getPXByHeight(168), ViewUtils.getPXByHeight(126));
		mTitle.setCompoundDrawables(a, null, null, null);
		//mTitle.setCompoundDrawablePadding(OnlineAffairsView.LEFT_MARGIN);
		//mTitle.setPadding(OnlineAffairsView.LEFT_MARGIN, 0, 0, 0);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, TITLE_H, Gravity.TOP);
        mTitle.setBackgroundColor(Constant.sTitle3BgColor);
        addView(mTitle, params);
        
        View v = new View(mContext);
		LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.TOP);
		params1.topMargin = TITLE_H;
        v.setBackgroundColor(Constant.sBlack10Color);
        addView(v, params1);
	}
	
	private LinearLayout mInfoLy;
	private LayoutInflater mInflater;
	private void addInfoView() {
		mInflater = LayoutInflater.from(mContext);
		View mGallery = mInflater.inflate(R.layout.post_data_ly, this, false);
		mInfoLy = (LinearLayout) mGallery.findViewById(R.id.info_ly);
		LayoutParams mParams = new LayoutParams(Constant.sRealWidth - OnlineAffairsView.LEFT_MARGIN * 2, 
				ViewUtils.getPXByHeight(1110), Gravity.TOP | Gravity.CENTER_HORIZONTAL);
		mParams.topMargin = TITLE_H;
		addView(mGallery, mParams);
		//mGallery.setBackgroundColor(0x33000000);
		
		initUserData();
		addInfo2InfoLy();
		addComfirmBtn();
	}
	
	private EditText[] mEditTexts;
	private void addInfo2InfoLy() {
		mEditTexts = new EditText[DATA_NAME.length];
		for (int i = 0; i < DATA_NAME.length; i++) {
			LinearLayout itemView = new LinearLayout(mContext);
			itemView.setOrientation(LinearLayout.HORIZONTAL);
			itemView.setGravity(Gravity.CENTER_VERTICAL);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
					ViewUtils.getPXByHeight(144));
			itemView.setLayoutParams(params);
			itemView.setPadding(OnlineAffairsView.LEFT_MARGIN, OnlineAffairsView.LEFT_MARGIN / 2, 
					OnlineAffairsView.LEFT_MARGIN, OnlineAffairsView.LEFT_MARGIN / 2);
			mInfoLy.addView(itemView, params);

			TextView department = new TextView(mContext);
			department.setTag("department");
			department.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					ViewUtils.getPXByWidth(42));
			department.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			
			LinearLayout.LayoutParams timeParams = new LinearLayout.LayoutParams(
					ViewUtils.getPXByWidth(220), LayoutParams.WRAP_CONTENT);
			timeParams.gravity = Gravity.FILL_HORIZONTAL | Gravity.CENTER_VERTICAL;
			itemView.addView(department, timeParams);
			department.setTextColor(Constant.sBlack54Color);
			department.setText(DATA_NAME[i]);

			mEditTexts[i] = new EditText(mContext);
			mEditTexts[i].setTag("info");
			mEditTexts[i].setTextSize(TypedValue.COMPLEX_UNIT_PX,
					ViewUtils.getPXByWidth(48));
			mEditTexts[i].setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			mEditTexts[i].setBackgroundResource(R.drawable.login_edittext_indicator);
			int padding = ViewUtils.getPXByWidth(15);
			mEditTexts[i].setPadding(padding, 0, padding, 0);
			LinearLayout.LayoutParams infoParams = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			infoParams.leftMargin = padding;
			itemView.addView(mEditTexts[i], infoParams);
		
			if (i < COULD_MODIFY_INDEX) {
				mEditTexts[i].setFocusableInTouchMode(false);
				mEditTexts[i].setFocusable(false);
				mEditTexts[i].setTextColor(Constant.sBlack54Color);
			} else {
				mEditTexts[i].setFocusableInTouchMode(true);
				mEditTexts[i].setFocusable(true);
				mEditTexts[i].setTextColor(Constant.sBlack87Color);
				if (i == COULD_MODIFY_INDEX + 1 ||
						i == COULD_MODIFY_INDEX + 2) {
					mEditTexts[i].setInputType(InputType.TYPE_CLASS_PHONE);
				}
			}
		
			if (sUserInfo != null) {
				mEditTexts[i].setText(sUserInfo[i]);
			}
		}
	}
			
	private Button mConfirm;
	public static final int BTN_H = ViewUtils.getPXByHeight(280);
	private void addComfirmBtn() {
		LinearLayout mBottomLy = new LinearLayout(mContext);
		mBottomLy.setOrientation(LinearLayout.HORIZONTAL);
		mBottomLy.setGravity(Gravity.CENTER);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 
				BTN_H, Gravity.BOTTOM);
		addView(mBottomLy, params);
		mBottomLy.setBackgroundColor(Constant.sTitle3BgColor);
		
		mConfirm = new Button(mContext);
		mConfirm.setBackgroundResource(R.drawable.yellow_btn_indicator);
		mConfirm.setOnClickListener(this);
		LinearLayout.LayoutParams timeParams = new LinearLayout.LayoutParams(
				ViewUtils.getPXByWidth(500), ViewUtils.getPXByHeight(126));
		timeParams.gravity = Gravity.CENTER;
		mConfirm.setText("下一步");
		mConfirm.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				ViewUtils.getPXByWidth(48));
		mConfirm.setTextColor(Color.WHITE);
		mBottomLy.addView(mConfirm, timeParams);
		
	}

	private Http mHttp;
	@Override
	public void onClick(View v)
	{
		//test for network error
		/*this.setVisibility(INVISIBLE);
		mShengbanNextView.setVisibility(VISIBLE);*/
		
		if (mConfirm == v && mShengbanNextView != null) {
			for (int i = 0; i < DATA_NAME.length; i++) {
				sUserInfo[i] = mEditTexts[i].getText().toString();
				LogUtils.log(null, "sUserInfo[i] = " + sUserInfo[i]);
			}
			for (int i = COULD_MODIFY_INDEX; i < DATA_NAME.length; i++) {
				if (sUserInfo[i].length() <= 0) {
					/*Toast toast = Toast.makeText(mContext, DATA_NAME[i] + 
						     "不能为空" , Toast.LENGTH_LONG);
						   toast.setGravity(Gravity.CENTER, 0, 0);
						   toast.show();*/
					MyToast.showToast(mContext, DATA_NAME[i] + 
						     "不能为空");
					return;
				}
			}
			
			mHttp = new Http(mContext);
			mHttp.setHttpListener(this);
			mHttp.doCommitDoc("正在提交...", ShengbanNextView.APPOVE_ITEM_STUFF_ID[VerificationActivity.sIndex][0][0],
					sUserInfo[0], sUserInfo[1], sUserInfo[2], 
					sUserInfo[3], sUserInfo[4 ], sUserInfo[5], sUserInfo[6]);
		}
	}
	
	public static Request getOuterNetDataInfo(String[] commitInfo)
    {
        Request r = new Request();
        ApplyInfo info = new ApplyInfo();
        info.setOperateStep("recheck");
        info.setIsSaveTemp("true");
        info.setActionFlag("dosave");
        info.setApproveItem("4711");
        if (UserManager.getInstance().mUser != null) {
            info.setUser_code(UserManager.getInstance().mUser.getUserInfo().getU_name());
        }

        info.setCust_contact_person(commitInfo[COULD_MODIFY_INDEX + 0]);
        info.setCustMobile(commitInfo[COULD_MODIFY_INDEX + 1]);
        info.setCust_contact_way(commitInfo[COULD_MODIFY_INDEX + 2]);
        info.setCust_name(commitInfo[COULD_MODIFY_INDEX + 3]);
        r.setOuterNetDataInfo(info);
        return r;
    }

	private static final int COULD_MODIFY_INDEX = 3;
	private final static String[] DATA_NAME = {
		"企业名称:", "企业地址:", "企业类型:", 
		"经办人:", "手机:",  "电话:", "项目名称:", 
	};
	
	public static String[] sUserInfo;
	private void initUserData() {
		sUserInfo = new String[DATA_NAME.length];
		if (UserManager.getInstance().mUser != null && UserManager.getInstance().mUser.getUserInfo() != null) {
			/*1.企业类型，需要转义：
			"type":"p1",type，--如果是个人用户：p1为男,p0为女。 如果是企业用户：e1国有,e2民营,e3外资,e4港澳台资,e5其他
			2.经办人姓名：handler_name
			3.经办人手机：mobile_phone*/
			sUserInfo[0] = UserManager.getInstance().mUser.getUserInfo().getName();
			sUserInfo[1] = UserManager.getInstance().mUser.getUserInfo().getAddress();
			sUserInfo[2] = translateType(UserManager.getInstance().mUser.getUserInfo().getType());
			sUserInfo[3] = UserManager.getInstance().mUser.getUserInfo().getHandler_name();
			sUserInfo[4] = UserManager.getInstance().mUser.getUserInfo().getMobile_phone();
			sUserInfo[5] = UserManager.getInstance().mUser.getUserInfo().getTel_phone();
			//sUserInfo[6] = UserManager.getInstance().mUser.getUserInfo().getName();
		}
	}
	
	private String translateType(String type) {
		/*1.企业类型，需要转义：
		"type":"p1",type，--如果是个人用户：p1为男,p0为女。 如果是企业用户：e1国有,e2民营,e3外资,e4港澳台资,e5其他*/
		String ret = "其他";
		if (type.equalsIgnoreCase("p1")) {
			ret = "男";
		} else if (type.equalsIgnoreCase("p0")) {
			ret = "女";
		} else if (type.equalsIgnoreCase("e1")) {
			ret = "国有";
		} else if (type.equalsIgnoreCase("e2")) {
			ret = "民营";
		} else if (type.equalsIgnoreCase("e3")) {
			ret = "外资";
		} else if (type.equalsIgnoreCase("e4")) {
			ret = "港澳台资";
		} else  if (type.equalsIgnoreCase("e5")) {
			ret = "其他";
		}
		
		return ret;
	}
	
	@Override
	public void onSuccess(Object parent, HttpStatus mStatus) {
		LogUtils.log(null, "Shengban Step1: onSuccess");
		
		//判断流水号
		if (((BaseBean)parent).getControlSeq() != null && ((BaseBean)parent).getControlSeq().length() > 0)
		{
			this.setVisibility(INVISIBLE);
			mShengbanNextView.setVisibility(VISIBLE);
			mShengbanNextView.setBaseBean((BaseBean)parent);
		} else {
			/*Toast toast = Toast.makeText(mContext, "流水号错误, 请重试 ",
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();*/
			MyToast.showToast(mContext, "流水号错误, 请重试 ");
		}
	}

	@Override
	public void onHttpError(HttpStatus mHs) {

		/*Toast toast = Toast.makeText(mContext, "请检查您的网络连接" + mHs.error,
				Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();*/
		MyToast.showToast(mContext,  "请检查您的网络连接" + mHs.error);
	}

	@Override
	public void onFail(HttpStatus mHs) {

		String msg = mHs.error;
		if (mHs.error.length() < 1) {
			msg = "提交失败，请检查网络";
		}
		MyToast.showToast(mContext,  msg);
		LogUtils.log(null, "HttpStatus.ERROR_CODE.LoginExpired =" + HttpStatus.ERROR_CODE.LoginExpired.getValue());
		 if (mHs.error_code.equals("" + HttpStatus.ERROR_CODE.LoginExpired.getValue())) {
			 Intent intent = new Intent();
				intent.putExtra(Tab3Activity.CLOSE_TAG, true);
				intent.putExtra(Tab3Activity.LOGIN_TAG, true);
				intent.setClass(mContext, Tab3Activity.class);
				mContext.startActivity(intent);
				mContext.finish();
		 }
	}

}
