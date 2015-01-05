package com.guangzhou.gov.view;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.guangzhou.gov.R;
import com.guangzhou.gov.util.Constant;

/**
 * 
 * @author shenyaobin 调用系统相机拍照，并拿到拍到的图片
 */

public class CameraActivity extends Activity implements OnClickListener {

	private final static int ACTION_TAKE_PHOTO_B = 2014;

	private ImageView mPictureView;
	private Bitmap mBitmap;
	private String mCurrentPhotoPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViews();
		dispatchTakePictureIntent(ACTION_TAKE_PHOTO_B);
	}

	private void initViews() {
		FrameLayout root = new FrameLayout(this);
		setContentView(root);

		mPictureView = new ImageView(this);
		root.addView(mPictureView);

		FrameLayout titleView = new FrameLayout(this);
		titleView.setBackgroundColor(Constant.sBlueColor);
		root.addView(titleView, LayoutParams.MATCH_PARENT,
				ViewUtils.getPXByHeight(168));

		TextView titleText = new TextView(this);
		titleText.setText("相册");
		titleText.setTextColor(Color.WHITE);
		titleText.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				ViewUtils.getPXByHeight(60));
		titleView.addView(titleText, new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.CENTER));

		ImageView back = new ImageView(this);
		back.setId(101);
		back.setImageResource(R.drawable.login_title_icon);
		back.setOnClickListener(this);
		FrameLayout.LayoutParams backParams = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.LEFT | Gravity.CENTER_VERTICAL);
		backParams.leftMargin = OnlineAffairsView.LEFT_MARGIN;
		titleView.addView(back, backParams);

		ImageView done = new ImageView(this);
		done.setId(102);
		done.setImageResource(R.drawable.done);
		done.setOnClickListener(this);
		FrameLayout.LayoutParams doneParams = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.RIGHT | Gravity.CENTER_VERTICAL);
		doneParams.rightMargin = backParams.leftMargin;
		titleView.addView(done, doneParams);
	}
	
	private void setPic() {

		/* There isn't enough memory to open up more than a couple camera photos */
		/* So pre-scale the target bitmap into which the file is decoded */

		/* Get the size of the ImageView */
		int targetW = Constant.sRealWidth;
		int targetH = Constant.sRealHeight;

		/* Get the size of the image */
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;
		
		/* Figure out which way needs to be reduced less */
		int scaleFactor = 1;
		if ((targetW > 0) || (targetH > 0)) {
			scaleFactor = Math.min(photoW/targetW, photoH/targetH);	
		}

		/* Set bitmap options to scale the image decode target */
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		/* Decode the JPEG file into a Bitmap */
		Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		
		int rotate = ViewUtils.handleRotateBitmap(mCurrentPhotoPath);
		if (rotate != 0) {
			bitmap = ViewUtils.rotaingBitmap(
					rotate, bitmap);
		}
		/* Associate the Bitmap to the ImageView */
		mPictureView.setImageBitmap(bitmap);
	}

	private void galleryAddPic() {
	   /* Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
		File f = new File(mCurrentPhotoPath);
	    Uri contentUri = Uri.fromFile(f);
	    mediaScanIntent.setData(contentUri);
	    this.sendBroadcast(mediaScanIntent);*/
	}
	
	private void dispatchTakePictureIntent(int actionCode) {

		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		switch(actionCode) {
		case ACTION_TAKE_PHOTO_B:
			File f = null;
			try {
				f = ViewUtils.getOutputMediaFile(Constant.sPicturePath);
				if (f != null) {
					mCurrentPhotoPath = f.getAbsolutePath();
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
				} else {
					MyToast.showToast(this, "SD卡不存在, 无法保存图片");
				}
			} catch (IOException e) {
				e.printStackTrace();
				f = null;
				mCurrentPhotoPath = null;
			}
			break;
		default:
			break;			
		} // switch

		startActivityForResult(takePictureIntent, actionCode);
	}

	private void handleBigCameraPhoto() {
		if (mCurrentPhotoPath != null) {
			setPic();
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 101:// back
			finish();
			break;
		case 102:// done
			if (!TextUtils.isEmpty(mCurrentPhotoPath)) {
				Intent intent = new Intent(Constant.ACTION_PHOTO);
				intent.putExtra(Constant.TAG_PHOTO, mCurrentPhotoPath);
				sendBroadcast(intent);
			}
			finish();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 此处进行处理
		
		switch (requestCode) {
			case ACTION_TAKE_PHOTO_B: {
				if (resultCode == RESULT_OK) {
					handleBigCameraPhoto();
				}
				break;
			} 
		}

		super.onActivityResult(requestCode, resultCode, data);
	}
	
	

	@Override
	protected void onDestroy() {
		if (mBitmap != null) {
			mBitmap.recycle();
			mBitmap = null;
		}
		super.onDestroy();
	}

}
