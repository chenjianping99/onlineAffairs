package com.guangzhou.gov.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.util.LruCache;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guangzhou.gov.R;
import com.guangzhou.gov.util.Constant;
import com.guangzhou.gov.util.LogUtils;
import com.guangzhou.gov.view.GalleryActivity.GalleryAdapter.Photo;

public class GalleryActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	private RelativeLayout mRoot;

	private GridView mGridView;
	private GalleryAdapter mGalleryAdapter;
	private static LruCache<String, Bitmap> sBitmapCache;

	private PreviewView mPreviewView;
	private int mNum = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNum = getIntent().getIntExtra(ShengbanPhotoActivity.ADD_PHOTO_NUM, 1);
		initViews();
	}

	private void initViews() {
		mRoot = new RelativeLayout(this);
		setContentView(mRoot);

		// bottom
		FrameLayout bottom = new FrameLayout(this);
		bottom.setId(3);
		bottom.setBackgroundColor(0xff282828);
		RelativeLayout.LayoutParams bottomParams = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, ViewUtils.getPXByHeight(168));
		bottomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		mRoot.addView(bottom, bottomParams);

		TextView preview = new TextView(this);
		preview.setId(103);
		preview.setOnClickListener(this);
		preview.setText("预览");
		preview.setTextColor(Color.WHITE);
		preview.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				ViewUtils.getPXByWidth(48));
		FrameLayout.LayoutParams previewParams = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.CENTER);
		//previewParams.leftMargin = ViewUtils.getPXByWidth(50);
		bottom.addView(preview, previewParams);

		// top
		FrameLayout titleView = new FrameLayout(this);
		titleView.setId(1);
		titleView.setBackgroundColor(0xff282828);
		mRoot.addView(titleView, LayoutParams.MATCH_PARENT,
				ViewUtils.getPXByHeight(168));

		TextView titleText = new TextView(this);
		titleText.setText("相册");
		titleText.setTextColor(Color.WHITE);
		titleText.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				ViewUtils.getPXByHeight(60));
		titleView.addView(titleText, new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.CENTER_VERTICAL | Gravity.LEFT));
		titleText.setPadding(ViewUtils.getPXByWidth(168), 0, 0, 0);
		
		ImageView back = new ImageView(this);
		back.setId(101);
		back.setImageResource(R.drawable.login_title_icon);
		back.setOnClickListener(this);
		FrameLayout.LayoutParams backParams = new FrameLayout.LayoutParams(
				ViewUtils.getPXByWidth(108), ViewUtils.getPXByWidth(108),
				Gravity.LEFT | Gravity.CENTER_VERTICAL);
		backParams.leftMargin = OnlineAffairsView.LEFT_MARGIN;
		titleView.addView(back, backParams);

		ImageView done = new ImageView(this);
		done.setOnClickListener(this);
		done.setId(102);
		done.setImageResource(R.drawable.done);
		int w = ViewUtils.getPXByWidth(62);
		int h = ViewUtils.getPXByWidth(48);
		FrameLayout.LayoutParams doneParams = new FrameLayout.LayoutParams(
				w * 2, h * 2,
				Gravity.RIGHT | Gravity.CENTER_VERTICAL);
		doneParams.rightMargin = backParams.leftMargin;
		done.setPadding(w / 2, h / 2, w / 2, h / 2);
		titleView.addView(done, doneParams);

		// center
		mGridView = new GridView(this);
		mGridView.setBackgroundColor(0xff1b1b1b);
		mGridView.setNumColumns(4);
		//int padding = ViewUtils.getPXByHeight(8);
		//mGridView.setPadding(padding, padding, padding, padding);
		mGalleryAdapter = new GalleryAdapter();
		mGridView.setAdapter(mGalleryAdapter);
		mGridView.setOnItemClickListener(this);
		RelativeLayout.LayoutParams grid = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		grid.addRule(RelativeLayout.BELOW, 1);
		grid.addRule(RelativeLayout.ABOVE, 3);
		// mGridView.setOnScrollListener(mGalleryAdapter);
		mRoot.addView(mGridView, grid);

		mPreviewView = new PreviewView(this);
		mPreviewView.setVisibility(View.GONE);
		RelativeLayout.LayoutParams pre = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		pre.addRule(RelativeLayout.BELOW, 1);
		pre.addRule(RelativeLayout.ABOVE, 3);
		mRoot.addView(mPreviewView, pre);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 101:// back
			if (mPreviewView.getVisibility() == View.VISIBLE) {
				mPreviewView.recycle();
				mPreviewView.setVisibility(View.GONE);
				mRoot.findViewById(103).setVisibility(View.VISIBLE);
			} else {
				finish();
			}
			break;
		case 102:// done
			if (mSelectList.size() > 0) {
				StringBuffer sb = new StringBuffer();
				for (Photo path : mSelectList) {
					sb.append(path.mPath);
					sb.append(",");
				}
				Intent intent = new Intent(Constant.ACTION_PHOTO);
				intent.putExtra(Constant.TAG_PHOTO, sb.toString());
				sendBroadcast(intent);
			}
			finish();
			break;
		case 103:// preview
			mPreviewView.setList(mSelectList);
			v.setVisibility(View.GONE);
			break;
		}
	}

	private List<Photo> mSelectList = new ArrayList<Photo>();

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		ImageView select = (ImageView) view.findViewWithTag("select");
		if (select != null) {
			Photo photo = (Photo) parent.getAdapter().getItem(position);

			if (photo.mSelect) {
				//select.setVisibility(View.INVISIBLE);
				if (mSelectList.contains(photo)) {
					mSelectList.remove(photo);
				}
				select.setImageResource(R.drawable.notchosen);
				photo.mSelect = false;
			} else {
				if (mSelectList.size() >= mNum) {
					/*Toast.makeText(this, "最多只能选择" + mNum + "张图片", Toast.LENGTH_SHORT)
							.show();*/
					MyToast.showToast(this, "最多只能选择" + mNum + "张图片");
					return;
				}
				//select.setVisibility(View.VISIBLE);
				if (!mSelectList.contains(photo)) {
					mSelectList.add(photo);
				}
				select.setImageResource(R.drawable.chosen);
				photo.mSelect = true;
			}
		}
	}

	@Override
	protected void onDestroy() {
		if (sBitmapCache != null) {
			sBitmapCache.evictAll();
		}
		mPreviewView.recycle();
		super.onDestroy();
	}

	class GalleryAdapter extends BaseAdapter implements Runnable {

		final Uri IMAGE_URI = Uri
				.parse("content://media/external/images/media");

		class Photo {
			String mPath;
			int mOrientation;
			boolean mSelect;
		}

		private List<Photo> mPhotoList;
		Object[] mLock = new Object[3];

		public GalleryAdapter() {
			for (int i = 0; i < mLock.length; i++) {
				mLock[i] = new Object();
			}
			Constant.initMetrics(getApplicationContext());
			mPhotoList = new ArrayList<GalleryActivity.GalleryAdapter.Photo>();
			int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
			int cacheSize = maxMemory / 8;
			if (sBitmapCache == null) {
				sBitmapCache = new LruCache<String, Bitmap>(cacheSize);
			}
			new Thread(this).start();
		}

		@Override
		public void run() {
			Cursor cursor = getContentResolver().query(
					IMAGE_URI,
					new String[] { MediaStore.Images.Media.DATA,
							MediaStore.Images.Media.ORIENTATION }, null, null,
					null);
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				do {
					String path = cursor.getString(cursor
							.getColumnIndex(MediaStore.Images.Media.DATA));
					if (path.contains(Constant.sPicturePath)
							|| path.toLowerCase().contains("camera")) {
						int orientation = cursor
								.getInt(cursor
										.getColumnIndex(MediaStore.Images.Media.ORIENTATION)); 
						Photo p = new Photo();
						p.mPath = path;
						p.mOrientation = orientation;
						mPhotoList.add(p);
					}
				} while (cursor.moveToNext());
				runOnUiThread(new Runnable() {
					public void run() {
						notifyDataSetChanged();
					}
				});
				cursor.close();
			}
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mPhotoList.size();
		}

		@Override
		public Object getItem(int position) {
			try {
				return mPhotoList.get(position);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = new FrameLayout(getBaseContext());
				int width = Constant.sRealWidth / 4;
				AbsListView.LayoutParams params = new AbsListView.LayoutParams(
						width, width);
				convertView.setLayoutParams(params);

				ImageView iv = new ImageView(getBaseContext());
				iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
				iv.setTag("image");
				int padding = ViewUtils.getPXByWidth(6);
				FrameLayout.LayoutParams ivParams = new FrameLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER);
				ivParams.setMargins(padding, padding, padding, padding);
				((ViewGroup) convertView).addView(iv, ivParams);

				ImageView select = new ImageView(getBaseContext());
				//select.setVisibility(View.INVISIBLE);
				//select.setImageResource(R.drawable.notchosen);
				select.setScaleType(ImageView.ScaleType.CENTER_CROP);
				select.setTag("select");
				FrameLayout.LayoutParams selectParams = new FrameLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
						Gravity.TOP | Gravity.RIGHT);
				selectParams.setMargins(padding, padding, padding, padding);
				((ViewGroup) convertView).addView(select, selectParams);

			}
			Photo photo = mPhotoList.get(position);

			ImageView iv = (ImageView) convertView.findViewWithTag("image");
			iv.setImageBitmap(null);
			setImage(iv, photo, position);
			iv.setId(2000 + position);

			ImageView select = (ImageView) convertView
					.findViewWithTag("select");
			if (photo.mSelect) {
				//select.setVisibility(View.VISIBLE);
				select.setImageResource(R.drawable.chosen);
			} else {
				//select.setVisibility(View.INVISIBLE);
				select.setImageResource(R.drawable.notchosen);
			}

			return convertView;
		}

		private void setImage(final ImageView v, final Photo photo,
				final int position) {
			if (photo == null) {
				return;
			}
			final Bitmap cache = sBitmapCache.get(photo.mPath);
			if (cache != null) {
				v.setImageBitmap(cache);
				return;
			}
			new Thread() {
				public void run() {
					Object o = mLock[position % mLock.length];
					synchronized (o) {
						Bitmap bitmap = ViewUtils.getBitmapByPath(
								getApplicationContext(), photo.mPath, 15);
						LogUtils.log(null, "width : " + bitmap.getWidth() + "photo.mOrientation =" + photo.mOrientation);
						if (photo.mOrientation != 0) {
							bitmap = ViewUtils.rotaingBitmap(
									photo.mOrientation, bitmap);
						}
						sBitmapCache.put(photo.mPath, bitmap);
						runOnUiThread(new Runnable() {
							public void run() {
								if (position >= mGridView
										.getFirstVisiblePosition()
										&& position <= mGridView
												.getLastVisiblePosition())
									v.setImageBitmap(sBitmapCache
											.get(photo.mPath));
							}
						});
					}
				}
			}.start();
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			onClick(findViewById(101));
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
