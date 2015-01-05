package com.guangzhou.gov.view;

import android.app.ActivityGroup;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.guangzhou.gov.R;

/**
 * 
 * @ClassName: GovActivity
 * @Description: 政府项目main activity
 * @author chenjianping
 * @date 2014-11-7
 * 
 */
public class GovActivity extends ActivityGroup {
    public static final String TAB1_TAG = "首页";
    public static final String TAB2_TAG = "产品中心";
    public static final String TAB3_TAG = "新闻动态";

    private LayoutInflater mInflater;
    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_gov);
        mInflater = LayoutInflater.from(this);
        
        setTabs();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    private void setTabs()
    {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        mTabHost.setup(this.getLocalActivityManager());

        mTabHost.addTab(mTabHost.newTabSpec(TAB1_TAG).setIndicator(createTabIndicator(R.string.navi_1, createTabDrawable(R.drawable.home_indicator)))
                .setContent(new Intent(this, Tab1Activity.class)));
        mTabHost.addTab(mTabHost.newTabSpec(TAB2_TAG).setIndicator(createTabIndicator(R.string.navi_2, createTabDrawable(R.drawable.products_indicator)))
                .setContent(new Intent(this, Tab2Activity.class)));
        mTabHost.addTab(mTabHost.newTabSpec(TAB3_TAG).setIndicator(createTabIndicator(R.string.navi_3, createTabDrawable(R.drawable.news_indicator)))
            .setContent(new Intent(this, Tab3Activity.class)));

        mTabHost.setCurrentTabByTag(TAB1_TAG);

    }

    private View createTabIndicator(int labelId, Drawable drawable)
    {
        View tabIndicator = mInflater.inflate(R.layout.tab_indicator, mTabHost.getTabWidget(), false);

        TextView txtTitle = (TextView) tabIndicator.findViewById(R.id.text_view_tab_title);
        txtTitle.setText(labelId);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) txtTitle.getLayoutParams();
        txtTitle.setLayoutParams(params);
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(36));
        ImageView imgIcon = (ImageView) tabIndicator.findViewById(R.id.image_view_tab_icon);
        imgIcon.setImageDrawable(drawable);

        return tabIndicator;
    }
    
    /*private View createTabIndicator(int labelId, Drawable drawable) {
    	FrameLayout mView = new FrameLayout(this);
        LayoutParams params = new LayoutParams(Constant.sRealWidth / 3, ViewUtils.getPXByHeight(162));
        mView.setLayoutParams(params);

        TextView textView = new TextView(this);
        textView.setTextColor(R.drawable.tab_text_indicator);
        drawable.setBounds(0, 0, ViewUtils.getPXByHeight(84), ViewUtils.getPXByHeight(84));
		textView.setCompoundDrawables(null, drawable, null, null);
		textView.setCompoundDrawablePadding(ViewUtils.getPXByHeight(5));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewUtils.getPXByWidth(40));
        textView.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
        textView.setGravity(Gravity.CENTER);
        textView.setSingleLine(true);
        mView.addView(textView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        View v = new View(this);
        v.setBackgroundColor(getResources().getColor(R.color.tab_line));
        
        mView.addView(v, Constant.sRealWidth / 3, 2);
        return mView;
    }*/

    private Drawable createTabDrawable(int resId)
    {
        Resources res = getResources();
        Drawable states = res.getDrawable(resId);
        states.setBounds(0, 0, ViewUtils.getPXByHeight(84), ViewUtils.getPXByHeight(84));
        return states;
    }

}
