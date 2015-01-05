package com.guangzhou.gov.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author chenjianping
 */
public class ShengbanPhotoActivity extends AbstractBaseActivity implements
		OnClickListener {
	private ShengbanPhotoView mView;
	private int mPhotoNum = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE); 
		mPhotoNum = getIntent().getIntExtra(ADD_PHOTO_NUM, 1);
		
		mView = new ShengbanPhotoView(this);
		setContentView(mView);
		
		mCamera = (Button) mView.findViewById(ShengbanPhotoView.CAMERA_ID);
		mPhoto = (Button) mView.findViewById(ShengbanPhotoView.CAMERA_ID + 1);
		mCancle = (Button) mView.findViewById(ShengbanPhotoView.CAMERA_ID + 2);

		mCamera.setOnClickListener(this);
		mPhoto.setOnClickListener(this);
		mCancle.setOnClickListener(this);
	}

	private Button mCamera, mPhoto, mCancle;
	public static final String ADD_PHOTO_NUM = "add_photo_num";
	@Override
	public void onClick(View v) {
		if (mCamera == v) {
			Intent intent = new Intent(this, CameraActivity.class);
			//intent.putExtra(ADD_PHOTO_NUM, mPhotoNum);
			startActivity(intent);
		} else if (mPhoto == v) {
			Intent intent = new Intent(this, GalleryActivity.class);
			intent.putExtra(ADD_PHOTO_NUM, mPhotoNum);
			startActivity(intent);
		} 
		
		finish();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
	}
}

