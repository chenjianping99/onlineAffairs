package com.guangzhou.gov.net.http;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;

import com.guangzhou.gov.net.http.HttpListener.Transformer;
import com.guangzhou.gov.net.parsers.HttpJsonResponse;
import com.guangzhou.gov.net.tools.HttpLog;
import com.guangzhou.gov.net.tools.HttpTool;

/**
 * 
* @ClassName: HttpInternal 
* @Description: 网络请求方式
* @author chenjianping
* @date 2014-11-17 
* 
* @param <T>
 */
public class HttpInternal<T> {
    public Transformer mTrasformer;

    public HttpInternal(Transformer tras) {
        this.mTrasformer = tras;
    }

    // request module is get
    public T doGet(Context mContext, String url, HttpStatus httpStatus)
    {
        return doGet(mContext, url, null, httpStatus);
    }

    // request module is get
    public T doGet(Context mContext, String url, HashMap<String, Object> values, HttpStatus httpStatus)
    {
        if (mTrasformer != null) {
            if (values != null) {
                url += "?";
                int i = values.size();
                StringBuilder sbd = new StringBuilder();
                for (Map.Entry<String, Object> entry : values.entrySet()) {
                    sbd.append(entry.getKey()).append("=").append(TextUtils.isEmpty(entry.getValue().toString()) ? "" : entry.getValue().toString().trim());
                    if (i != 1) sbd.append("&");
                    i--;
                }
                url += sbd.toString();
            }
            HttpLog.d(null, "doGet ======> url = " + url);
            return mTrasformer.onTransformer(new HttpJsonResponse(HttpTool.getString(mContext, url)), httpStatus);
        }
        return null;
    }

    // request module is post and body is key＝value
    public T doPost(Context mContext, String url, HashMap<String, String> values, HttpStatus httpStatus)
    {
        HttpLog.d(null, "doPost ======> url = " + url + " & values = " + values);
        if (mTrasformer != null) {
            return mTrasformer.onTransformer(new HttpJsonResponse(HttpTool.getPostString(mContext, url, values)), httpStatus);
        }
        return null;
    }

    // request module is post and body is json
    public T doPostString(Context mContext, String url, String values, HttpStatus httpStatus)
    {

        HttpLog.d(null, "doPostString ======> url = " + url + " & values = " + values);
        if (mTrasformer != null) {
            return mTrasformer.onTransformer(new HttpJsonResponse(HttpTool.postString(mContext, url, values)), httpStatus);
        }
        return null;
    }

    // request module is post and body key=value(string and file)
    public T doPostStream(Context mContext, String url, HashMap<String, Object> values, HttpStatus httpStatus)
    {
        HttpLog.d(null, "doPostStream ======> url = " + url + " & values = " + values);
        if (mTrasformer != null) {
            return mTrasformer.onTransformer(new HttpJsonResponse(HttpTool.postStream(mContext, url, values)), httpStatus);
        }
        return null;
    }

}
