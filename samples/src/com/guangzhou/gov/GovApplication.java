package com.guangzhou.gov;

import android.app.Application;

import com.guangzhou.gov.manager.UserManager;

/**
 * gov Application
 * 
 * @author chenjianping
 * 
 */
public class GovApplication extends Application {
    public static final String TAG = GovApplication.class.getSimpleName();
    public static GovApplication sINSTANCE;

    /**
     * 提供系统调用的构造函数，
     */
    public GovApplication() {
        sINSTANCE = this;
    }

    /**
     * 获得application实例
     * 
     * @return
     */
    public static GovApplication getInstance()
    {
        if (sINSTANCE == null) {
            sINSTANCE = new GovApplication();
        }
        return sINSTANCE;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        UserManager.getInstance().init();
    }

}
