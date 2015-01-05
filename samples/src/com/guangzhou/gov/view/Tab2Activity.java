package com.guangzhou.gov.view;

import android.os.Bundle;

/**
 * @author chenjianping
 */
public class Tab2Activity extends AbstractBaseActivity {
	private GovDepartmentsView mView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mView = new GovDepartmentsView(this);
        setContentView(mView);
    }

}