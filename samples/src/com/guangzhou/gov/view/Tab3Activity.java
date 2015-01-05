package com.guangzhou.gov.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.guangzhou.gov.R;
import com.guangzhou.gov.manager.UserManager;
import com.guangzhou.gov.net.http.Http;
import com.guangzhou.gov.net.http.HttpListener;
import com.guangzhou.gov.net.http.HttpStatus;
import com.guangzhou.gov.net.tools.HttpLog;
import com.guangzhou.gov.util.Constant;

/**
 * 
* @ClassName: IndexActivity2 
* @author chenjianping
* @date 2014-11-7 
*
 */
public class Tab3Activity extends AbstractBaseActivity implements OnClickListener, HttpListener {
	
	public static final String CLOSE_TAG = "close";
	public static final String LOGIN_TAG = "login";
	private boolean mShouldClose = false;
	private boolean mShouldLogin = false;
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mContext = this;
        mShouldClose = getIntent().getBooleanExtra(CLOSE_TAG, false);
        mShouldLogin = getIntent().getBooleanExtra(LOGIN_TAG, false);
        checkIsLogin();
    }
	
	private Http mHttp;
	private void checkIsLogin() {
		if (!mShouldLogin && UserManager.getInstance().mUser != null &&
				UserManager.getInstance().mUser.getUserInfo() != null) {
			// already login, show person
			addMyinfoPage();
		} else {
			 mHttp = new Http(this);
			 mHttp.setHttpListener(this);
			// show login pager
			 addLoginView();
		}
	}
	
	private MyInfoPageView myInfoPageView;
	private void addMyinfoPage() {
		myInfoPageView = new MyInfoPageView(mContext);
		setContentView(myInfoPageView);
	}
    
    private Context mContext;
    private FrameLayout mContentView;
    private void addLoginView() {
    	mContentView = new FrameLayout(mContext);
    	LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
				Gravity.CENTER);
    	mContentView.setLayoutParams(params);
    	setContentView(mContentView);
    	mContentView.setBackgroundColor(Color.WHITE);
    	addmTitle();
    	
    	addLoginBody();
    	addLine();
	}

	private void addLine() {
		View v = new View(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM);
        v.setBackgroundColor(0xffd6d6d6);
        mContentView.addView(v, params);
	}

    /*private TextView mTitle;
	private void addmTitle() {
		mTitle = new TextView(mContext);
		mTitle.setTextColor(Color.WHITE);
		mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(60));
		//mTitle.setTypeface(CircleContainer.sTypeface);
		mTitle.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		mTitle.setText(R.string.login_title);
		Drawable a = getResources().getDrawable(R.drawable.login_title_icon);
		a.setBounds(0, 0, ViewUtils.getPXByHeight(108), ViewUtils.getPXByHeight(108));
		mTitle.setCompoundDrawables(a, null, null, null);
		mTitle.setCompoundDrawablePadding(OnlineAffairsView.LEFT_MARGIN);
		mTitle.setPadding(OnlineAffairsView.LEFT_MARGIN, 0, 0, 0);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, OnlineAffairsView.TITLE_H, Gravity.TOP);
        mTitle.setBackgroundColor(Constant.sBlueColor);
        mContentView.addView(mTitle, params);
        mTitle.setOnClickListener(this);
	}*/
	
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
        mContentView.addView(mTitle, params);
        
        
        mSearch = new ImageView(mContext);
        mSearch.setImageResource(R.drawable.title_search);
        int w = ViewUtils.getPXByHeight(53);
		LayoutParams mSearchParams = new LayoutParams(w, w, Gravity.TOP | Gravity.RIGHT);
		mSearchParams.topMargin = (TITLE_H - w) / 2;
		mSearchParams.rightMargin = ViewUtils.getPXByWidth(80);
		mContentView.addView(mSearch, mSearchParams);
	}
	
	private EditText mTextName, mPassword;
	private Button mLoginBtn;	
	private LayoutInflater mInflater;
	private void addLoginBody() {
		mInflater = LayoutInflater.from(this);
		View mLogin = mInflater.inflate(R.layout.login, mContentView, false);
		mTextName = (EditText) mLogin.findViewById(R.id.et_zh);
		mPassword = (EditText) mLogin.findViewById(R.id.et_mima);
		mLoginBtn = (Button) mLogin.findViewById(R.id.btn_login);
		int w = ViewUtils.getPXByWidth(850);
		LayoutParams mParams = new LayoutParams(w, w, Gravity.CENTER);
		mContentView.addView(mLogin, mParams);
		mLoginBtn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		if (v == mLoginBtn) {
			String userNameValue = mTextName.getText().toString();
			String passwordValue = mPassword.getText().toString();
			if (mHttp != null && userNameValue.length() > 0 && userNameValue.length() > 0) {
				mHttp.doUserLogin("登录中...", userNameValue, passwordValue);
				//mHttp.doUserLogin("zhangzhang", "abc123456");
			} else {
				/*Toast toast = Toast.makeText(mContext,
					     "用户名和密码不能为空" , Toast.LENGTH_LONG);
					   toast.setGravity(Gravity.CENTER, 0, 0);
					   toast.show();*/
				MyToast.showToast(mContext,  "用户名和密码不能为空" );
			}
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
    public void onHttpError(HttpStatus mHs)
    {
        HttpLog.d(null, "Tab3Activity onHttpError");
        /*Toast toast = Toast.makeText(mContext,
        		"请检查您的网络连接" + mHs.error, Toast.LENGTH_LONG);
			   toast.setGravity(Gravity.CENTER, 0, 0);
			   toast.show();*/
        MyToast.showToast(mContext,  "请检查您的网络连接" + mHs.error );
    }

    @Override
    public void onSuccess(Object parent, HttpStatus mHttpStatus)
    {
    	if (UserManager.getInstance().mUser != null &&
				UserManager.getInstance().mUser.getUserInfo() != null) {
    		/*Toast toast = Toast.makeText(mContext,
      			     "登录成功", Toast.LENGTH_LONG);
      			   toast.setGravity(Gravity.CENTER, 0, 0);
      			   toast.show();*/
    		MyToast.showToast(mContext,  "登录成功");
    		
            if (mShouldClose) {
            	finish();
            } else {
            	addMyinfoPage();
            }
            
            HttpLog.d(null, "UserInfo = " + UserManager.getInstance().mUser.getUserInfo().toString());
    	} else {
    		/*Toast toast = Toast.makeText(mContext,
   			     "用户名或密码错误: ", Toast.LENGTH_LONG);
   			   toast.setGravity(Gravity.CENTER, 0, 0);
   			   toast.show();*/
    		MyToast.showToast(mContext,  "用户名或密码错误");
    	}
        HttpLog.d(null, "Tab3Activity onSuccess");
        
        
        
    }

    @Override
    public void onFail(HttpStatus mHs)
    {
    	String msg = mHs.error;
		if (mHs.error.length() < 1) {
			msg = "登录失败，请检查网络";
		}

		MyToast.showToast(mContext, msg);
    }
    
    @Override
    public void onBackPressed() {
    	if (myInfoPageView != null) {
    		if (myInfoPageView.ismIsFirstView()) {
    			super.onBackPressed();
    		}
    	} else {
    		super.onBackPressed();
    	}
    }
}
