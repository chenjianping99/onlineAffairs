package com.guangzhou.gov.manager;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.guangzhou.gov.GovApplication;
import com.guangzhou.gov.net.bean.Userlogin;
import com.guangzhou.gov.net.cache.JSONCache;

/**
 * 
 * @ClassName: UserManager
 * @author chenjianping
 * @date 2014-11-10
 * 
 */
public class UserManager {
    public Userlogin mUser;

    private UserManager() {
        init();
    }

    public static final UserManager getInstance()
    {
        return SingletonHolder.sINSTANCE;
    }

    /**
     * 
     * @ClassName: SingletonHolder
     * @author chenjianping
     * @date 2014-11-10
     * 
     */
    static class SingletonHolder {
        static UserManager sINSTANCE = new UserManager();

    }

    public void init()
    {
        String json = JSONCache.readCache(GovApplication.getInstance(), JSONCache.KEY_USER_LOGIN_INFO);
        if (!TextUtils.isEmpty(json)) {
            mUser = new Gson().fromJson((JsonObject) new JsonParser().parse(json), Userlogin.class);
        } else {
            mUser = null;
        }
    }

    public Userlogin getUser()
    {
        return this.mUser;
    }

}
