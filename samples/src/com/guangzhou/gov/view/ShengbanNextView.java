package com.guangzhou.gov.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.guangzhou.gov.R;
import com.guangzhou.gov.net.bean.BaseBean;
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
public class ShengbanNextView extends FrameLayout implements OnClickListener, HttpListener
{
	private View mThis;
	private Context mContext;
	public ShengbanNextView(Context context)
	{
		super(context);
		mContext = context;
		mThis = this;
		setBackgroundColor(Constant.sTitle3BgColor);
		addmTitle();
		addCailiaoLy();
		
		addComfirmBtn();
		registerPhotoBs();
	}
	
	private ArrayList<String> mPhotoAddr = new ArrayList<String>();
	private ArrayList<String> mPhotoCompressAddr = new ArrayList<String>();
	private BroadcastReceiver mPhotoInfoBs;
	private void registerPhotoBs() {
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Constant.ACTION_PHOTO);
		
		mPhotoInfoBs = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				LogUtils.log(null, "intent = " + intent.getAction());
				String[] path  = intent.getStringExtra(Constant.TAG_PHOTO).split(",");
				
				//压缩上传
				compressBitmapAndStore(path);
				
				for (String string : path) {
					mPhotoAddr.add(string);
					LogUtils.log("Http", "string = " +string);
				}
				
				if (mBaseBean == null || mPhotoAddr == null || mPhotoAddr.size() <= 0) {
					mConfirm.setEnabled(false);
				} else {
					mConfirm.setEnabled(true);
				}
				updateTakePhoto();
			}
		};
		getContext().registerReceiver(mPhotoInfoBs, intentFilter);
	}
	
	
	private void compressBitmapAndStore(String[] path) {
		int inSampleSize = 2;
		for (int i = 0; i < path.length; i++) {
			Bitmap b = ViewUtils.getBitmapByPath(mContext, path[i], inSampleSize);
			String p= ViewUtils.storeImage(b, Constant.sPictureCompressPath);
			mPhotoCompressAddr.add(p);
			LogUtils.log("Http", "p = " +p);
		}
		
	}

	private TextView mTitle;
	private void addmTitle() {
		mTitle = new TextView(mContext);
		mTitle.setTextColor(Constant.sBlack54Color);
		mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(60));
		mTitle.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		mTitle.setText(R.string.xiangguan_cailiao);
		Drawable a = getResources().getDrawable(R.drawable.cailiao_icon);
		a.setBounds(0, 0, ViewUtils.getPXByHeight(108), ViewUtils.getPXByHeight(108));
		mTitle.setCompoundDrawables(a, null, null, null);
		mTitle.setCompoundDrawablePadding(OnlineAffairsView.LEFT_MARGIN);
		mTitle.setPadding(OnlineAffairsView.LEFT_MARGIN, 0, 0, 0);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, ShengbanView.TITLE_H, Gravity.TOP);
        //mTitle.setBackgroundColor(Constant.sTitle3BgColor);
        addView(mTitle, params);
        
        
	}
	
	private LinearLayout mCailiaoLy;
	private void addCailiaoLy() {
		mCailiaoLy = new LinearLayout(mContext);
		mCailiaoLy.setOrientation(LinearLayout.VERTICAL);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.WRAP_CONTENT, Gravity.TOP);
		params.topMargin = ShengbanView.TITLE_H ;
        addView(mCailiaoLy, params);
        addListView();
		addGalleryView();
        
        View v = new View(mContext);
		LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.TOP);
		params1.topMargin = ShengbanView.TITLE_H;
        v.setBackgroundColor(Constant.sBlack10Color);
        addView(v, params1);
	}
		
	
	
	
	private LinearLayout mGalleryLy;
	private LayoutInflater mInflater;
	private ImageView mAddPic;
	private static final int PHOTO_H = ViewUtils.getPXByHeight(204);
	private void addGalleryView() {
		mInflater = LayoutInflater.from(mContext);
		View mGallery = mInflater.inflate(R.layout.gallery_item, this, false);
		mGallery.setBackgroundColor(Color.WHITE);
		mGalleryLy = (LinearLayout) mGallery.findViewById(R.id.galleryLinearLayout);
		LayoutParams mParams = new LayoutParams(Constant.sRealWidth, 
				PHOTO_H + ViewUtils.getPXByHeight(36), Gravity.TOP | Gravity.CENTER_HORIZONTAL);

		mParams.topMargin = (int) (ShengbanView.TITLE_H * (DATA[VerificationActivity.sIndex].length + 2));
		mCailiaoLy.addView(mGallery/*, mParams*/);
		//mGallery.setBackgroundColor(0x33000000);

		mGallery.setVerticalFadingEdgeEnabled(false);
		mGallery.setVerticalScrollBarEnabled(false);
		mGallery.setFadingEdgeLength(0);
		
		updateTakePhoto();
	}
	
	private static final int TAG = 0x001024;
	private Bitmap[] mPhotoBitmap;
	public void updateTakePhoto() {
		if (mGalleryLy != null) {
			mGalleryLy.removeAllViews();
			if (mPhotoAddr != null) {
				mPhotoBitmap = new Bitmap[mPhotoAddr.size()];
				for (int i = 0; i < mPhotoAddr.size(); i++) {
					LogUtils.log(null, "mPhotoAddr[i] = " + mPhotoAddr.get(i));
					if (mPhotoAddr.get(i).length() > 0) {
						ImageView v = new ImageView(mContext);
						v.setId(TAG + i);
						ViewUtils.recycleBitmap(mPhotoBitmap[i]);
						mPhotoBitmap[i] = ViewUtils.getBitmapByPath(mContext, mPhotoAddr.get(i), 10);
						int rotate = ViewUtils.handleRotateBitmap(mPhotoAddr.get(i));
						if (rotate != 0) {
							mPhotoBitmap[i] = ViewUtils.rotaingBitmap(
									rotate, mPhotoBitmap[i]);
						}
						//v.setBackgroundDrawable(new BitmapDrawable(mPhotoBitmap[i]));
						v.setImageDrawable(new BitmapDrawable(mPhotoBitmap[i]));
						LinearLayout.LayoutParams vParams = new LinearLayout.LayoutParams(PHOTO_H,  
								PHOTO_H);
						vParams.gravity = Gravity.TOP;
						vParams.leftMargin = OnlineAffairsView.LEFT_MARGIN;
						mGalleryLy.addView(v, vParams);
						v.setOnLongClickListener(mOnLongClickListener);
					} else {
						mPhotoAddr.remove(i);
					}
				}
			}
			
			mAddPic = new ImageView(mContext);
			mAddPic.setBackgroundResource(R.drawable.add_photo_btn);
			LinearLayout.LayoutParams addParams = new LinearLayout.LayoutParams(PHOTO_H,  
					PHOTO_H);
			addParams.gravity = Gravity.TOP;
			addParams.leftMargin = OnlineAffairsView.LEFT_MARGIN;
			addParams.rightMargin = OnlineAffairsView.LEFT_MARGIN;
			mGalleryLy.addView(mAddPic, addParams);
			mAddPic.setOnClickListener(this);
		}
	}
	
	
	
	private OnLongClickListener mOnLongClickListener = new OnLongClickListener() {
		@Override
		public boolean onLongClick(final View v) {
			final AlertDialog alert = new AlertDialog.Builder(mContext).create();
			alert.show();
			alert.getWindow().setContentView(new MyAlertDialogView(mContext, "确定要删除这张图片吗?\n(不会删除手机原图)"));  
			alert.getWindow()  
                .findViewById(MyAlertDialogView.COMFIRM_ID)  
                .setOnClickListener(new View.OnClickListener() {  
                @Override  
                public void onClick(View view) {  
                	alert.dismiss();
					mGalleryLy.removeView(v);
					mGalleryLy.invalidate();
					int index = v.getId() - TAG;
					mPhotoAddr.remove(index);
					mPhotoCompressAddr.remove(index);
                }  
            });  
			alert.getWindow()  
            .findViewById(MyAlertDialogView.CANCLE_ID)  
            .setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View view) {  
            	alert.dismiss();
            }  
        });  
			return true;
		}
	};
	
				
	private Button mConfirm;
	private void addComfirmBtn() {
		LinearLayout mBottomLy = new LinearLayout(mContext);
		mBottomLy.setOrientation(LinearLayout.HORIZONTAL);
		mBottomLy.setGravity(Gravity.CENTER);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 
				ShengbanView.BTN_H, Gravity.BOTTOM);
		addView(mBottomLy, params);
		mBottomLy.setBackgroundColor(Constant.sTitle3BgColor);
		
		mConfirm = new Button(mContext);
		mConfirm.setBackgroundResource(R.drawable.yellow_btn_indicator);
		mConfirm.setOnClickListener(this);
		LinearLayout.LayoutParams timeParams = new LinearLayout.LayoutParams(
				ViewUtils.getPXByWidth(500), ViewUtils.getPXByHeight(126));
		timeParams.gravity = Gravity.CENTER;
		mConfirm.setText("确认提交");
		mConfirm.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				ViewUtils.getPXByWidth(48));
		mConfirm.setTextColor(Color.WHITE);
		mBottomLy.addView(mConfirm, timeParams);
		if (mBaseBean == null || mPhotoAddr == null || mPhotoAddr.size() <=0) {
			mConfirm.setEnabled(false);
		}
	}
	
	public void onDestory() {
		LogUtils.log(null, "ShengbanNextView onDestory()" );
		getContext().unregisterReceiver(mPhotoInfoBs);
		if (mPhotoBitmap != null) {
			for (int i = 0; i < mPhotoBitmap.length; i++) {
				ViewUtils.recycleBitmap(mPhotoBitmap[i]);
			}
		}
		
		ViewUtils.deleteFileByDir(Constant.sPictureCompressPath);
		mPhotoAddr.clear();
		mPhotoCompressAddr.clear();
		mParent = null;
	}
	
	private BaseBean mBaseBean;
	public void setBaseBean(BaseBean parent) {
		mBaseBean = parent;
	}
		
	private Http mHttp;
	@Override
	public void onClick(View v)
	{
		if (mConfirm == v) {
			if (mBaseBean != null && mPhotoCompressAddr.size() == DATA[VerificationActivity.sIndex].length) {
				final AlertDialog alert = new AlertDialog.Builder(mContext)
						.create();
				alert.show();
				alert.getWindow().setContentView(
						new MyAlertDialogView(mContext,
								"请注意:申报提交后, 将不能再做修改, 是否确认提交?"));
				alert.getWindow().findViewById(MyAlertDialogView.COMFIRM_ID)
						.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								alert.dismiss();
								handleUpload();
							}
						});
				alert.getWindow().findViewById(MyAlertDialogView.CANCLE_ID)
						.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								alert.dismiss();
							}
						});
			} else {
				/*Toast toast = Toast.makeText(mContext,
					     "请根据材料列表选择" + DATA[VerificationActivity.sIndex].length + "张对应图片", 
					     Toast.LENGTH_LONG);
					   toast.setGravity(Gravity.CENTER, 0, 0);
					   toast.show();*/
				MyToast.showToast(mContext, "请根据材料列表选择" + DATA[VerificationActivity.sIndex].length + "张对应图片");
			}
		} else if (mAddPic == v) {
			int num = PHOTO_NUM[VerificationActivity.sIndex] - mPhotoAddr.size();
			if (num > 0) {
				Intent intent = new Intent();
				intent.putExtra(ShengbanPhotoActivity.ADD_PHOTO_NUM, num);
				intent.setClass(mContext, ShengbanPhotoActivity.class);
				mContext.startActivity(intent);
			} else {
				/*Toast toast = Toast.makeText(mContext,
					     "已经达到最大上传照片数量\n, 您可以长按图片删除重选", Toast.LENGTH_LONG);
					   toast.setGravity(Gravity.CENTER, 0, 0);
					   toast.show();*/
				MyToast.showToast(mContext, "已经达到最大上传照片数量\n, 您可以长按图片删除后重选");
			}
		}
	}

	private void handleUpload() {
		mHttp = new Http(mContext);
		mHttp.setHttpListener(this);
		/*
		 * 测试环境：
		《大陆居民赴台旅游名单表审验》
		approveItem：4711
		“申请领取《大陆居民赴台湾地区旅游团名单表》备案登记表”的 stuffId：4176
		
		生产环境：
		*/
		mHttp.doCommitFile("上传中...", mPhotoCompressAddr, mBaseBean.getControlSeq(), 
				APPOVE_ITEM_STUFF_ID[VerificationActivity.sIndex]);
	}

	@Override
	public void onSuccess(Object parent, HttpStatus status) {

		final AlertDialog alert = new AlertDialog.Builder(mContext).create();
		alert.show();
		alert.getWindow().setContentView(
				new MyAlertDialogView(mContext, "您的办理提交成功!\n办证编号为:" + ((BaseBean)parent).getControlSeq()));
		alert.getWindow().findViewById(MyAlertDialogView.COMFIRM_ID)
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						alert.dismiss();
						if (mParent != null) {
							mParent.handleCommitSuccess();
							mThis.setVisibility(INVISIBLE);
							mPhotoAddr.clear();
							mPhotoCompressAddr.clear();
							updateTakePhoto();
						}
					}
				});
		alert.getWindow().findViewById(MyAlertDialogView.CANCLE_ID).setVisibility(GONE);
				
	}

	@Override
	public void onHttpError(HttpStatus mHs) {
		/*Toast toast = Toast.makeText(mContext,
			   "请检查您的网络连接" + mHs.error, Toast.LENGTH_LONG);
			   toast.setGravity(Gravity.CENTER, 0, 0);
			   toast.show();*/
		MyToast.showToast(mContext, "请检查您的网络连接" + mHs.error);
	}
	
	@Override
	public void onFail(HttpStatus mHs) {

		String msg = mHs.error;
		if (mHs.error.length() < 1) {
			msg = "上传失败，请检查网络";
		}
		/*Toast toast = Toast.makeText(mContext,
				msg, Toast.LENGTH_LONG);
			   toast.setGravity(Gravity.CENTER, 0, 0);
			   toast.show();*/
		MyToast.showToast(mContext, msg);
	}
	
	
	//登记表
	private ListView mListView;
	private ArrayList<String> mDepartMentsList = new ArrayList<String>();
	private MyAdapter mMyAdapter;
	private void addListView() {
		mListView = new ListView(getContext());
		LogUtils.log(null, "VerificationActivity.sIndex=" + VerificationActivity.sIndex 
				+ "DATA[VerificationActivity.sIndex].length = " + DATA[VerificationActivity.sIndex].length);
		LayoutParams pager = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, Gravity.TOP);
		pager.topMargin = ShengbanView.TITLE_H;
		mCailiaoLy.addView(mListView/*, pager*/);
		mDepartMentsList.clear();
		int i = 0;
		for (; i < DATA[VerificationActivity.sIndex].length; i++) {
			mDepartMentsList.add(i, DATA[VerificationActivity.sIndex][i]);
		}
		if (DATA[VerificationActivity.sIndex].length > 1) {
			mDepartMentsList.add(i, "(注意: 照片按顺序对应上述材料，请您依次选择上传)");
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
		mListView.setBackgroundColor(Color.WHITE);
	}

	private final static String[][] DATA = {
		{"《大陆居民赴台湾地区旅游团名单表》备案登记表"},
		
		{"1. 建设工程地震安全性评价结果审定及抗震设防要求确定行政许可申请表", 
			"2. 地震安全性评价报告",  /*"2. 地震安全性评价报告（7-15份）", */
			"3. 地震安全性评价委托合同", 
			"4. 承担地震安全性评价工作单位的资质证书", 
			"5. 承担地震安全性评价工作的单位受工程建设单位委托提出申请的应当提供建设单位签署的委托书"},
			
			{"1.《符合申领汽车出口许可证条件企业申请表》或《符合申领摩托车出口许可证条件企业申请表》", 
				"2. 海关报关单复印件等出口证明材料", 
				"3. 企业境外售后维修服务网点总体建设及变动情况"}
	};
	
	private final static int[] PHOTO_NUM = {
		1, 5, 3
	};
	
	public final static String[][][] APPOVE_ITEM_STUFF_ID = {
		{{"4500", "3837"}},
		{{"3980", "17867"}, {"3980", "17868"}, {"3980", "17869"}, {"3980", "17870"}, {"3980", "17902"}},
		{{"4536", "3886"}, {"4536", "3887"}, {"4536", "3888"}}
		/*
		 * 《大陆居民赴台旅游名单表审验》
		approveItem：4500
		“申请领取《大陆居民赴台湾地区旅游团名单表》备案登记表”的 stuffId：3837
		
		 * 建设工程地震安全性评价结果审定及抗震设防要求确定
		approveItem:3980
		材料staffid:
		建设工程地震安全性评价结果审定及抗震设防要求确定行政许可申请表-staffid:17867
		地震安全性评价报告-17868
		地震安全性评价委托合同-17869
		承担地震安全性评价工作单位的资质证书-17870
		承担地震安全性评价工作的单位受工程建设单位委托提出申请的，应当提供建设单位签署的委托书-17902
		
		汽车、摩托车出口资质初审
		approveItem:4536
		《符合申领汽车出口许可证条件企业申请表》或《符合申领摩托车出口许可证条件企业申请表》-3886
		海关报关单复印件等出口证明材料-3887
		企业境外售后维修服务网点总体建设及变动情况-3888*/
	};
	
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
						ViewUtils.getPXByWidth(42));
				department.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
				
				LinearLayout.LayoutParams timeParams = new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				department.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
				itemView.addView(department, timeParams);
				department.setTextColor(Constant.sBlack87Color);
				
			} else {
				itemView = (LinearLayout) convertView;
			}
			
			TextView department = (TextView) itemView.findViewWithTag("department");
			 department.setText(mDepartMentsList.get(position));
			return itemView;
		}
	}
	
	
	private VerificationView mParent;
	public void addParentListener(VerificationView v) {
		mParent = v;
	}
	
	
}
