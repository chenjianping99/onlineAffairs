package com.guangzhou.gov.net.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.guangzhou.gov.GovApplication;

/**
 * 如果需要缓存，可通过sharedpreferences 缓存json数据 缓存数据进行加密处理
 * 
 * @ClassName: JSONCache
 * @author chenjianping
 * @date 2014-11-9
 * 
 */
public class JSONCache {
    public static final String KEY_USER_LOGIN_INFO = "user_login_info";


    public static void saveCache(Context context, String key, String value)
    {
        if (value == null) return;
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(GovApplication.getInstance());
        Editor editor = pref.edit();
        try {
            editor.putString(KeepMD5.getMD5Hex(key), KeepMD5.encrypt(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.commit();
    }

    public static String readCache(Context context, String key)
    {
        if (context == null) return "";
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(GovApplication.getInstance());
        String s = pref.getString(KeepMD5.getMD5Hex(key), "");
        if (!TextUtils.isEmpty(s)) {
            try {
                return KeepMD5.decrypt(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static void removCache(Context context, String key)
    {
        if (context == null) return;
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(GovApplication.getInstance());
        pref.edit().remove(KeepMD5.getMD5Hex(key));
    }

}
