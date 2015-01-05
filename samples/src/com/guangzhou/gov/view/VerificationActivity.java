package com.guangzhou.gov.view;

import android.os.Bundle;

import com.guangzhou.gov.R;
import com.guangzhou.gov.util.LogUtils;

/**
 * @author chenjianping
 */
public class VerificationActivity extends AbstractBaseActivity {

	public static int sIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
        int index = getIntent().getIntExtra(GovDepartmentsView.GOV_INDEX, GovDepartmentsView.mTourismIndex);
        sIndex = mapping(index);
        
        mView = new VerificationView(this);
        String line2Name = getResources().getString(R.string.zhuguan_bumen) + "  " + 
        		TITLE_ARRAY[sIndex][1];
        String line3Name = getResources().getString(R.string.shixiang_name) + "  " + 
        		TITLE_ARRAY[sIndex][2];
        mView.setName(TITLE_ARRAY[sIndex][0], line2Name, line3Name);
        setContentView(mView);
        
    }

    private VerificationView mView;

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
    	LogUtils.log(null, "VirificationActivity onDestory()" );
        if (mView != null) {
        	mView.onDestory();
        }
        super.onDestroy();
    }

   @Override
	public void onBackPressed() {
		if (mView != null) {
			if (mView.isFirstView()) {
				super.onBackPressed();
			}
		}
	}
    
   private static final String[][] TITLE_ARRAY = {
	   {"广东省旅游局", "广东省旅游局", "大陆居民赴台旅游名单表审验"},
	   {"广东省地震局", "广东省地震局", "地震安全性评价结果审定及抗震要求确定"},
	   {"广东省商务厅", "广东省商务厅", "汽车、摩托车出口资质初审"},
   };
   
   private int mapping(int index) {
		int ret = 0;
		switch (index) {
		case GovDepartmentsView.mTourismIndex:
			ret = 0;
			break;
		case GovDepartmentsView.mDizhengIndex:
			ret = 1;
			break;
		case GovDepartmentsView.mShangwuIndex:
			ret = 2;
			break;
		default:
			break;
		}
		return ret;
	}
   
}
