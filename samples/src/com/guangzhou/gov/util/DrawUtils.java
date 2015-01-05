package com.guangzhou.gov.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewConfiguration;
import android.view.WindowManager;

/**
 * 绘制工具类
 * 
 * @author luopeihuan
 * 
 */
public class DrawUtils {
	public static float sDensity = 1.0f;
	public static int sDensityDpi;
	public static int sWidthPixels;
	public static int sHeightPixels;
	public static float sFontDensity;
	public static int sTouchSlop = 15; // 点击的最大识别距离，超过即认为是移动

	public static int sStatusHeight; // 平板中底边的状态栏高度
	private static Class sClass = null;
	private static Method sMethodForWidth = null;
	private static Method sMethodForHeight = null;
	public static int sTopStatusHeight;

	public static int sNavBarLocation;
	private static int sRealWidthPixels;
	private static int sRealHeightPixels;
	private static int sNavBarWidth; // 虚拟键宽度
	private static int sNavBarHeight; // 虚拟键高度
	public static final int NAVBAR_LOCATION_RIGHT = 1;
	public static final int NAVBAR_LOCATION_BOTTOM = 2;

	// 在某些机子上存在不同的density值，所以增加两个虚拟值
	public static float sVirtualDensity = -1;
	public static float sVirtualDensityDpi = -1;

	/**
	 * dip/dp转像素
	 * 
	 * @param dipValue
	 *            dip或 dp大小
	 * @return 像素值
	 */
	public static int dip2px(float dipVlue) {
		return (int) (dipVlue * sDensity + 0.5f);
	}

	/**
	 * 像素转dip/dp
	 * 
	 * @param pxValue
	 *            像素大小
	 * @return dip值
	 */
	public static int px2dip(float pxValue) {
		final float scale = sDensity;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * sp 转 pxpublic static Bitmap getGaussinaBlur(Context context, Bitmap
	 * sentBitmap, int radius) { try { return getGaussina(context, sentBitmap,
	 * radius); } catch (OutOfMemoryError oom) { // TODO: handle exception
	 * System.gc(); } catch (Exception e) { // TODO: handle exception } return
	 * sentBitmap;
	 * 
	 * }
	 * 
	 * @param spValue
	 *            sp大小
	 * @return 像素值
	 */
	public static int sp2px(float spValue) {
		final float scale = sDensity;
		return (int) (scale * spValue);
	}

	/**
	 * px转sp
	 * 
	 * @param pxValue
	 *            像素大小
	 * @return sp值
	 */
	public static int px2sp(float pxValue) {
		final float scale = sDensity;
		return (int) (pxValue / scale);
	}

	public static void resetDensity(Context context) {
		if (context != null && null != context.getResources()) {
			DisplayMetrics metrics = context.getResources().getDisplayMetrics();
			sDensity = metrics.density;
			sFontDensity = metrics.scaledDensity;
			sWidthPixels = metrics.widthPixels;
			sHeightPixels = metrics.heightPixels;
			sDensityDpi = metrics.densityDpi;

			try {
				final ViewConfiguration configuration = ViewConfiguration
						.get(context);
				if (null != configuration) {
					sTouchSlop = configuration.getScaledTouchSlop();
				}
				getStatusBarHeight(context);
			} catch (Error e) {
				Log.i("DrawUtils", "resetDensity has error" + e.getMessage());
			}
		}
		resetNavBarHeight(context);
	}

	private static void resetNavBarHeight(Context context) {
		if (context != null) {
			WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			try {
				if (sClass == null) {
					sClass = Class.forName("android.view.Display");
				}
				Point realSize = new Point();
				Method method = sClass.getMethod("getRealSize", Point.class);
				method.invoke(display, realSize);
				sRealWidthPixels = realSize.x;
				sRealHeightPixels = realSize.y;
				sNavBarWidth = realSize.x - sWidthPixels;
				sNavBarHeight = realSize.y - sHeightPixels;
			} catch (Exception e) {
				sRealWidthPixels = sWidthPixels;
				sRealHeightPixels = sHeightPixels;
				sNavBarHeight = 0;
			}
		}
		sNavBarLocation = getNavBarLocation();
	}

	public static boolean isPad() {
		if (sDensity >= 1.5 || sDensity <= 0) {
			return false;
		}
		if (sWidthPixels < sHeightPixels) {
			if (sWidthPixels > 480 && sHeightPixels > 800) {
				return true;
			}
		} else {
			if (sWidthPixels > 800 && sHeightPixels > 480) {
				return true;
			}
		}
		return false;
	}

	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0;
		int top = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			top = context.getResources().getDimensionPixelSize(x);
			sTopStatusHeight = top;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return top;
	}

	public static int getRealWidth() {
		if (Build.VERSION.SDK_INT >= 19) {
			return sRealWidthPixels;
		}
		return sWidthPixels;
	}

	public static int getRealHeight() {
		if (Build.VERSION.SDK_INT >= 19) {
			return sRealHeightPixels;
		}
		return sHeightPixels;
	}

	/**
	 * 虚拟键在下面时
	 * 
	 * @return
	 */
	public static int getNavBarHeight() {
		if (Build.VERSION.SDK_INT >= 19) {
			return sNavBarHeight;
		}
		return 0;
	}

	/**
	 * 横屏，虚拟键在右边时
	 * 
	 * @return
	 */
	public static int getNavBarWidth() {
		if (Build.VERSION.SDK_INT >= 19) {
			return sNavBarWidth;
		}
		return 0;
	}

	public static int getNavBarLocation() {
		if (sRealWidthPixels > sWidthPixels) {
			return NAVBAR_LOCATION_RIGHT;
		}
		return NAVBAR_LOCATION_BOTTOM;
	}
}
