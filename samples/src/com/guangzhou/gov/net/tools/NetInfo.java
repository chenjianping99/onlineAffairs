package com.guangzhou.gov.net.tools;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 网络信息 区分网络
 * 
 * @ClassName: NetInfo
 * @author chenjianping
 * @date 2014-11-9
 * 
 */
public class NetInfo {

    public static final String CTWAP = "ctwap";
    public static final String CMWAP = "cmwap";
    public static final String WAP_3G = "3gwap";
    public static final String UNIWAP = "uniwap";
    public static final String proxy172 = "10.0.0.172";
    public static final String proxy200 = "10.0.0.200";

    /**
     * 获取当前网络类型，wifi或者mobile
     * 
     * @see ConnectivityManager#TYPE_WIFI
     * @see ConnectivityManager#TYPE_MOBILE
     * @param context
     * @return 返回当前网络类型
     */
    public static final int getNetType(Context context)
    {
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connec == null) return -1;
        NetworkInfo ni = connec.getActiveNetworkInfo();
        if (ni != null) return ni.getType();
        return -1;
    }

    /**
     * 获取当前网络类型，wifi或者mobile
     * 
     * @param context
     * @return 返回当前网络类型
     */
    public static final String getNetTypeName(Context context)
    {
        String nettype = "";
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connec == null) return nettype;
        NetworkInfo ni = connec.getActiveNetworkInfo();
        if (ni == null) return nettype;
        nettype = ni.getTypeName();
        if (nettype == null) nettype = "";
        return nettype;
    }

    public static final int getMobileType(Context context)
    {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) return TelephonyManager.NETWORK_TYPE_UNKNOWN;
        return tm.getNetworkType();
    }

    /**
     * 获取移动网络类型，gprs,edge,cdma等
     * 
     * @param context
     * @return 返回当前移动网络类型
     */
    public static final String getMobileTypeName(Context context)
    {
        int type = getMobileType(context);
        switch (type)
        {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return "CDMA-1xRTT";
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "CDMA";
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "EDGE";
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return "CDMA-EvDorev.0";
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return "CDMA-EvDorev.A";
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return "GPRS";
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return "HSDPA";
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return "HSPA";
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return "HSUPA";
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return "UMTS";
            default:
                return "UNKNOWN";
        }
    }

    /**
     * 当前使用的apn，cmwap,3gwap,ctwap等
     */
    private static String apn;// 使用的apn接入点

    public static final String getApn(Context context)
    {
        getNetInfo(context);
        if (apn == null) apn = "";
        return apn;
    }

    private static boolean isWap;

    /**
     * 判断当前网络是否需要设置代理
     * 
     * @return 返回是否需要设置
     */
    public static final boolean isWap(Context context)
    {
        getNetInfo(context);
        return isWap;
    }

    private static String wapProxy;

    /**
     * 获取当前的代理服务器地址
     * 
     * @return 返回地址
     */
    public static final String getWapProxy(Context context)
    {
        getNetInfo(context);
        if (wapProxy == null) wapProxy = "";
        return wapProxy;
    }

    /**
     * 判断当前网络是否可用
     * 
     * @param context
     * @return 返回结果
     */
    public static final boolean isNetworkAvailable(Context context)
    {
        if (context == null) return false;
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connec != null) {
            NetworkInfo info = connec.getActiveNetworkInfo();
            if (info == null || !info.isAvailable()) {
                return false;
            } else
                return true;
        }
        return false;
    }

    /**
     * 获取网络信息
     * 
     * @param context
     */
    public static final void getNetInfo(Context context)
    {
        if (context == null) return;
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = null;
        NetworkInfo.State wifi = NetworkInfo.State.UNKNOWN;
        NetworkInfo.State mobile = NetworkInfo.State.UNKNOWN;
        if (connec != null) {
            try {
                info = connec.getActiveNetworkInfo();
                wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
                mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (info != null) {
                apn = info.getExtraInfo();// cmwap/cmnet/wifi/uniwap/uninet
            }
            if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
                isWap = false;
            } else if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
                NetworkInfo mobileInfo = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (mobileInfo != null) {
                    String currentAPN = mobileInfo.getExtraInfo();
                    if (currentAPN != null) {
                        currentAPN = currentAPN.toLowerCase();
                        if (currentAPN.equals(CMWAP) || currentAPN.equals(UNIWAP) || currentAPN.equals(WAP_3G)) {
                            isWap = true;
                            wapProxy = proxy172;
                            apn = currentAPN;
                        } else if (currentAPN.equals(CTWAP)) {
                            isWap = true;
                            wapProxy = proxy200;
                            apn = currentAPN;
                        }
                    } else {
                        Cursor c = context.getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), null, null, null, null);
                        if (c != null) {
                            if (c.getCount() > 0) {
                                c.moveToFirst();
                                String user = c.getString(c.getColumnIndex("user"));
                                if (!TextUtils.isEmpty(user)) {
                                    user = user.toLowerCase();
                                    if (user.startsWith(CTWAP)) {
                                        isWap = true;
                                        wapProxy = proxy200;
                                        apn = CTWAP;
                                    } else if (user.startsWith(CMWAP) || user.startsWith(UNIWAP) || user.startsWith(WAP_3G)) {
                                        isWap = true;
                                        wapProxy = proxy172;
                                        if (user.startsWith(CMWAP))
                                            apn = CMWAP;
                                        else if (user.startsWith(UNIWAP))
                                            apn = UNIWAP;
                                        else if (user.startsWith(WAP_3G)) apn = WAP_3G;
                                    }
                                }
                            }
                            c.close();
                        }
                    }
                }
            }
        }
    }
}
