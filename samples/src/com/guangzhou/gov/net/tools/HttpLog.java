package com.guangzhou.gov.net.tools;

import android.util.Log;

import com.guangzhou.gov.BuildConfig;

/**
 * 
 * @ClassName: HttpLog
 * @author chenjianping
 * @date 2014-11-9
 * 
 */
public class HttpLog {
    public final static boolean LOG_ENABLE = BuildConfig.DEBUG;
    private static final String DEFAULT_LOG_TAG = "Http";

    public static int v(String tag, String msg)
    {
        if (tag == null) {
            tag = DEFAULT_LOG_TAG;
        }
        return LOG_ENABLE ? Log.v(tag, msg) : 0;
    }

    public static int v(String tag, String msg, Throwable tr)
    {
        if (tag == null) {
            tag = DEFAULT_LOG_TAG;
        }
        return LOG_ENABLE ? Log.v(tag, msg, tr) : 0;
    }

    public static int d(String tag, String msg)
    {
        if (tag == null) {
            tag = DEFAULT_LOG_TAG;
        }
        return LOG_ENABLE ? Log.d(tag, msg) : 0;
    }

    public static int d(String tag, Exception e)
    {
        if (tag == null) {
            tag = DEFAULT_LOG_TAG;
        }
        return LOG_ENABLE ? Log.d(tag, e.toString()) : 0;
    }

    public static int d(String tag, String msg, boolean force)
    {
        if (tag == null) {
            tag = DEFAULT_LOG_TAG;
        }
        return force ? Log.d(tag, msg) : 0;
    }

    public static int d(String tag, String msg, Throwable tr)
    {
        if (tag == null) {
            tag = DEFAULT_LOG_TAG;
        }
        return LOG_ENABLE ? Log.d(tag, msg, tr) : 0;
    }

    public static int i(String msg)
    {
        return LOG_ENABLE ? Log.i(DEFAULT_LOG_TAG, msg) : 0;
    }

    public static int i(String tag, String msg)
    {
        if (tag == null) {
            tag = DEFAULT_LOG_TAG;
        }
        return LOG_ENABLE ? Log.i(tag, msg) : 0;
    }

    public static int i(String tag, String msg, Throwable tr)
    {
        if (tag == null) {
            tag = DEFAULT_LOG_TAG;
        }
        return LOG_ENABLE ? Log.i(tag, msg, tr) : 0;
    }

    public static int w(String tag, String msg)
    {
        if (tag == null) {
            tag = DEFAULT_LOG_TAG;
        }
        return LOG_ENABLE ? Log.w(tag, msg) : 0;
    }

    public static int w(String tag, String msg, Throwable tr)
    {
        if (tag == null) {
            tag = DEFAULT_LOG_TAG;
        }
        return LOG_ENABLE ? Log.w(tag, msg, tr) : 0;
    }

    public static int w(String tag, Throwable tr)
    {
        if (tag == null) {
            tag = DEFAULT_LOG_TAG;
        }
        return LOG_ENABLE ? Log.w(tag, tr) : 0;
    }

    public static int e(String tag, String msg)
    {
        if (tag == null) {
            tag = DEFAULT_LOG_TAG;
        }
        return LOG_ENABLE ? Log.e(tag, msg) : 0;
    }

    public static int e(String tag, String msg, Throwable tr)
    {
        if (tag == null) {
            tag = DEFAULT_LOG_TAG;
        }
        return LOG_ENABLE ? Log.e(tag, msg, tr) : 0;
    }
}
