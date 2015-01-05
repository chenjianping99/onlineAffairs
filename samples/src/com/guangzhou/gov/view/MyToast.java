package com.guangzhou.gov.view;

import android.content.Context;
import android.widget.Toast;

public class MyToast {

	private static String mOldMsg;
	protected static Toast mToast = null;
	private static long oneTime = 0;
	private static long twoTime = 0;
	private static final int LONG_DELAY = 3500; // 3.5 seconds
	private static final int SHORT_DELAY = 2000; // 2 seconds

	public static void showToast(Context context, String s) {
		if (mToast == null) {
			mToast = Toast.makeText(context, s, Toast.LENGTH_LONG);
			mToast.show();
			oneTime = System.currentTimeMillis();
		} else {
			twoTime = System.currentTimeMillis();
			if (s.equals(mOldMsg)) {
				if (twoTime - oneTime > LONG_DELAY) {
					mToast.show();
				}
			} else {
				mOldMsg = s;
				mToast.setText(s);
				mToast.show();
			}
		}
		oneTime = twoTime;
	}

	public static void showToast(Context context, int resId) {
		showToast(context, context.getString(resId));
	}

}
