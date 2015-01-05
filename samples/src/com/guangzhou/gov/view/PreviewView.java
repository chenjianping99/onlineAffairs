package com.guangzhou.gov.view;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.guangzhou.gov.view.GalleryActivity.GalleryAdapter.Photo;

public class PreviewView extends ViewPager {

	private List<Photo> mPreviewList;
	private static LruCache<String, Bitmap> sBitmapCache;
	PreviewAdapter mPreviewAdapter;

	public PreviewView(Context context) {
		super(context);
		
	}

	public void setList(List<Photo> list) {
		mPreviewList = list;
		if (mPreviewList != null && mPreviewList.size() > 0) {
			setVisibility(View.VISIBLE);
			if (sBitmapCache == null) {
				int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
				int cacheSize = maxMemory / 8;
				sBitmapCache = new LruCache<String, Bitmap>(cacheSize);
			}
			mPreviewAdapter = new PreviewAdapter();
			setAdapter(mPreviewAdapter);
		}

	}

	/**
	 * 
	 * @author shenyaobin
	 *
	 */
	class PreviewAdapter extends PagerAdapter {

		public PreviewAdapter() {
		}

		public int getCount() {
			return mPreviewList.size();
		}

		public Photo getItem(int position) {
			return mPreviewList.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container)
					.removeView(container.findViewById(position));
		}

		@Override
		public Object instantiateItem(ViewGroup view, final int position) {
			FrameLayout page = new FrameLayout(getContext());
			page.setId(position);
			view.addView(page, LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			ImageView v = new ImageView(getContext());
			page.addView(v, new FrameLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
					Gravity.CENTER));
			page.setBackgroundColor(Color.WHITE);
			setImage(v, getItem(position));
			return page;
		}

		private void setImage(final ImageView v, final Photo photo) {
			if (photo == null) {
				return;
			}
			new Thread() {
				public void run() {
					final Bitmap cache = sBitmapCache.get(photo.mPath);
					if (cache != null) {
						runOnUiThread(new Runnable() {
							public void run() {
								v.setImageBitmap(cache);
							}
						});
					} else {
						Bitmap bitmap = ViewUtils.getBitmapByPath(getContext(),
								photo.mPath, 5);
						if (photo.mOrientation != 0) {
							bitmap = ViewUtils.rotaingBitmap(
									photo.mOrientation, bitmap);
						}
						sBitmapCache.put(photo.mPath, bitmap);
						runOnUiThread(new Runnable() {
							public void run() {
								v.setImageBitmap(sBitmapCache.get(photo.mPath));
							}
						});
					}
				}
			}.start();
		}

		private Handler mHandler;

		private void runOnUiThread(Runnable r) {
			if (mHandler == null) {
				mHandler = new Handler(getContext().getMainLooper());
			}
			if (mHandler != null) {
				mHandler.post(r);
			}
		}

	}

	public void recycle() {
		if (sBitmapCache != null) {
			sBitmapCache.evictAll();
		}
	}

}
