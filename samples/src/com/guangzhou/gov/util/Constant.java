package com.guangzhou.gov.util;

import java.lang.reflect.Field;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 
 * @author zhangjie
 * 
 */
public class Constant {

	public static final String PKG_NAME = "com.guangzhou.gov";
	public static final String ACTION_PHOTO = "com.jiubang.goscreenlock.action.getphoto";
	public static final String TAG_PHOTO = "photo";
	
	/*-------------------------------------设计师的切图分辨率－－－－－－－－－－－－－－－－－－－－－－－－－－－*/
	public static final int S_DEFAULT_WIDTH = 1080;
	public static final int S_DEFAULT_HEIGHT = 1920;

	public static int sRealWidth = 720;
	public static int sRealHeight = 1280;
	public static int sStatusBarHeight = 0;
	
	public static boolean sIsfullscreen = false; // 是否全屏
	public static boolean sIsScreenOn = false;
	
	public static int sYellowBtn = 0xffe98c52;
	public static int sYellowColor = 0xfff88532;
	public static int sBlueColor = 0xff0a82f5;
	public static int sTitle2BgColor = 0xfffafafa;
	public static int sTitle3BgColor = 0xfff3f3f3;
	public static int sBlack10Color = 0x1a000000;
	public static int sBlack54Color = 0x8a000000;
	public static int sBlack87Color = 0xa4000000;
	public static int sGreyLightColor = 0xfffafafa;
	
	public static final String sPicturePath = "/DCIM";
	public static final String sPictureCompressPath = "/com.guangzhou.gov/files/upload_pictures";

	public static int getStatusBarHeight(Context context) {
		if (sStatusBarHeight > 0) {
			return sStatusBarHeight;
		}
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sStatusBarHeight = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sStatusBarHeight;
	}

	public static float sXRate = 1;
	public static float sYRate = 1;
	public static int sTextColor = 0xfffff6ea;
	public static int sCustomBg = 0;
	public static int sBottomH = 0;
	
	
	public static void initMetrics(Context c) {
		DisplayMetrics mDm = c.getResources().getDisplayMetrics();

		sRealWidth = mDm.widthPixels;
		if (sIsfullscreen) {
			sRealHeight = mDm.heightPixels;
		} else {
			sRealHeight = mDm.heightPixels - getStatusBarHeight(c);
		}
//		if (Build.VERSION.SDK_INT >= 19) {
//			DrawUtils.resetDensity(c);
//			sRealHeight = Global.sScreenHeight - getStatusBarHeight(c);
//		}
		
		sXRate = (float) sRealWidth / (float) Constant.S_DEFAULT_WIDTH;
		sYRate = (float) sRealHeight / (float) Constant.S_DEFAULT_HEIGHT;
		LogUtils.log("ddd", "sYRate =" + sYRate + "Constant.sRealHeight =" + Constant.sRealHeight);
	}
	
	public static int dpToPx(Context c, int dp) {
	    DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();
	    int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));       
	    return px;
	}

	public static int pxToDp(Context c, int px) {
	    DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();
	    int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
	    return dp;
	}
}
