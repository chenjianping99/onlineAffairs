/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.guangzhou.gov.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guangzhou.gov.R;
import com.guangzhou.gov.util.Constant;

/**
 * A grid that displays a set of framed photos.
 *
 */
public class GridAffairs extends FrameLayout {

	private int mIndex;
	public GridAffairs(Context context, int index) {
		super(context);
		
		mIndex = index;
		GridView g = new GridView(context) {
			/*private float mStartX, mDisX;
			private float mStartY, mDisY;
			@Override
			public boolean onTouchEvent(MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					mStartX = event.getRawX();
					mStartY = event.getRawY();
					mDisX = 0;
					break;
				case MotionEvent.ACTION_MOVE:
					mDisX = event.getRawX() - mStartX;
					mDisY = event.getRawY() - mStartY;
					if (Math.abs(mDisX) > ViewUtils.getPXByWidth(10)
							&& Math.abs(mDisX) > Math.abs(mDisY) ) {
						return false;
					}
				}
				return super.onTouchEvent(event);
			}*/
		};
		g.setNumColumns(4);
		g.setGravity(Gravity.CENTER);
		int w = ViewUtils.getPXByWidth(20);
		g.setVerticalSpacing(w);
		//g.setHorizontalSpacing(w);
		g.setPadding(0, ViewUtils.getPXByHeight(48), 0, ViewUtils.getPXByHeight(48));
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
				Gravity.CENTER);
		addView(g, params);
        g.setAdapter(new ImageAdapter(context));
        //g.setFadingEdgeLength(0);
		//g.setSelector(new ColorDrawable(Color.TRANSPARENT));
		//g.setCacheColorHint(Color.TRANSPARENT);
		g.setVerticalFadingEdgeEnabled(false);
		g.setVerticalScrollBarEnabled(false);
		g.setFadingEdgeLength(0);
		
	}

	

	/**
	 * 
	 * @author chenjianping
	 *
	 */
	public class ImageAdapter extends BaseAdapter implements OnClickListener {
		int mMaginTop = ViewUtils.getPXByHeight(48);
        public ImageAdapter(Context c) {
            mContext = c;
            mWidth = ViewUtils.getPXByWidth(168);
    		mHeight = ViewUtils.getPXByWidth(250);
    		mPicH = ViewUtils.getPXByWidth(168);
        }

        public int getCount() {
            return mIcon[mIndex].length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
        	LinearLayout itemView = null;
            if (convertView == null) {
            	itemView = new LinearLayout(mContext);
				itemView.setOrientation(LinearLayout.VERTICAL);
				itemView.setGravity(Gravity.CENTER_VERTICAL);
				GridView.LayoutParams params = new GridView.LayoutParams(mWidth, mHeight);
				itemView.setLayoutParams(params);
								
                TextView textView = new TextView(mContext);
                textView.setTag("department");
                /*int h = mHeight;
                LogUtils.log("grid", "position = " + position);
                if (position < 4) {
                	h = mHeight + mMaginTop;
                	//LogUtils.log("grid", "h: position = " + position);
                }
                
                if (position < 4) {
                	textView.setPadding(0, mMaginTop, 0, 0);
                } */
                
                LinearLayout.LayoutParams timeParams = new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				itemView.addView(textView, timeParams);
               
            } else {
            	itemView = (LinearLayout) convertView;
            }
            
            TextView view = (TextView) itemView.findViewWithTag("department");
            if (view != null) {
	            view.setText(mName[mIndex][position]);
	            view.setTextColor(Constant.sBlack87Color);
	            Drawable mDrawable = getResources().getDrawable(mIcon[mIndex][position]);
	    		mDrawable.setBounds(0, 0, mWidth, mPicH);
	    		view.setCompoundDrawables(null, mDrawable, null, null);
	    		view.setCompoundDrawablePadding(ViewUtils.getPXByWidth(5));
	            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(40));
	            view.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
	            view.setGravity(Gravity.CENTER);
	            view.setSingleLine(true);
	            view.setOnClickListener(this);
            }
            return itemView;
        }

        private Context mContext;
        private int mWidth, mHeight, mPicH;
  
        private int[] mThumbIds = {
              R.drawable.personl_1, R.drawable.personl_2, R.drawable.personl_3, R.drawable.personl_4,
              R.drawable.personl_5, R.drawable.personl_6, R.drawable.personl_7, R.drawable.personl_8,
              R.drawable.personl_9, R.drawable.personl_10, R.drawable.personl_11, R.drawable.personl_12,
              R.drawable.personl_13, R.drawable.personl_14, R.drawable.personl_15, R.drawable.personl_16, 
              R.drawable.personl_17, R.drawable.personl_18, R.drawable.personl_19, R.drawable.personl_20,
              R.drawable.personl_21, R.drawable.personl_22, 
        };
        
        private int[] mThumbStr = {
                R.string.personal_affairs_1, R.string.personal_affairs_2, R.string.personal_affairs_3, R.string.personal_affairs_4,
                R.string.personal_affairs_5, R.string.personal_affairs_6, R.string.personal_affairs_7, R.string.personal_affairs_8,
                R.string.personal_affairs_9, R.string.personal_affairs_10, R.string.personal_affairs_11, R.string.personal_affairs_12,
                R.string.personal_affairs_13, R.string.personal_affairs_14, R.string.personal_affairs_15, R.string.personal_affairs_16,
                R.string.personal_affairs_17, R.string.personal_affairs_18, R.string.personal_affairs_19, R.string.personal_affairs_20,
                R.string.personal_affairs_21, R.string.personal_affairs_22
          };
        
        private int[] mComThumbIds = {
                R.drawable.company_1, R.drawable.company_2, R.drawable.company_3, R.drawable.company_4,
                R.drawable.company_5, R.drawable.company_6, R.drawable.company_7, R.drawable.company_8,
                R.drawable.company_9, R.drawable.company_10, R.drawable.company_11, R.drawable.company_12,
                R.drawable.company_13, R.drawable.company_14, R.drawable.company_15, R.drawable.company_16,
                R.drawable.company_17, R.drawable.company_18, R.drawable.company_19, R.drawable.company_20,
                R.drawable.company_21, R.drawable.company_22, R.drawable.company_23, R.drawable.company_24,
                R.drawable.company_25, R.drawable.company_26, 
          };
          
          private int[] mComThumbStr = {
                  R.string.company_affairs_1, R.string.company_affairs_2, R.string.company_affairs_3, R.string.company_affairs_4,
                  R.string.company_affairs_5, R.string.company_affairs_6, R.string.company_affairs_7, R.string.company_affairs_8,
                  R.string.company_affairs_9, R.string.company_affairs_10, R.string.company_affairs_11, R.string.company_affairs_12,
                  R.string.company_affairs_13, R.string.company_affairs_14, R.string.company_affairs_15, R.string.company_affairs_16,
                  R.string.company_affairs_17, R.string.company_affairs_18, R.string.company_affairs_19, R.string.company_affairs_20,
                  R.string.company_affairs_21, R.string.company_affairs_22, R.string.company_affairs_23, R.string.company_affairs_24,
                  R.string.company_affairs_25, R.string.company_affairs_26, 
            };
          
         private int[][] mIcon = {mThumbIds, mComThumbIds};
         private int[][] mName = {mThumbStr, mComThumbStr};
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + getResources().getString(R.string.website)));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getContext().startActivity(intent);
		}
         
        /*private String[] mThumbStr= {
        		"生育收养", "户籍", "教育", "兵役", 
        		"就业", "纳税", "社保", "婚姻", 
        		"医疗", "出入境", "社会救助", "住房",
        		"司法公证", "死亡殡葬", "职业资格",  "消费维权", 
        		"交通",  "文化体育",  "知识产权",  "民族宗教",  
        		"专项资金",  "其他"
          };*/
    }
    

}
