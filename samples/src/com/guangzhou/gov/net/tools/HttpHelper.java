package com.guangzhou.gov.net.tools;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.text.TextUtils;

import com.guangzhou.gov.net.http.entity.JsonEntity;

/**
 * 
 * @ClassName: HttpHelper
 * @Description: 组装和产生实际请求
 * @author chenjianping
 * @date 2014-11-9
 * 
 */
public class HttpHelper {

    /**
     * 放弃一个request，不再响应返回的数据
     * 
     * @see HttpRequestBase#abort()
     * @param request
     * @return 返回是否放弃成功
     */
    public static final boolean abortRequest(HttpRequestBase request)
    {
        if (request != null) {
            request.abort();
            return true;
        }
        return false;
    }

    /**
     * 解析HttpResponse响应为字符串
     * 
     * @param resp
     * @return 返回解析出来的字符串，UTF-8格式，可能为空
     */
    public static String parserHttpResponseToString(HttpResponse resp)
    {
        byte[] data = parserHttpResponseToByte(resp);
        return byteToString(data);
    }

    /**
     * 转换字节数组为utf8字符串
     * 
     * @param data 要转换的字节数组
     * @return 返回的字符串，转换失败返回null
     */
    public static final String byteToString(byte[] data)
    {
        String temp = null;
        if (data != null) {
            try {
                temp = new String(data, HTTP.UTF_8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    /**
     * 解析HttpResponse响应为byte数组
     * 
     * @param resp
     * @return 返回解析出来的byte数组，可能为null
     */
    public static byte[] parserHttpResponseToByte(HttpResponse resp)
    {
        if (resp == null) return null;
        HttpEntity entity = resp.getEntity();
        if (entity == null) return null;
        try {
            return EntityUtils.toByteArray(entity);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过一个http的url生成一个HttpGet
     * 
     * @param httpUrl url地址
     * @return 返回生成的HttpGet，可能为null
     */
    public static HttpGet get(String httpUrl)
    {
        if (TextUtils.isEmpty(httpUrl)) return null;
        HttpGet get = new HttpGet(ChinaEncode.encodeURI(httpUrl));
        return get;
    }

    /**
     * 通过一个http的url生成一个HttpPost
     * 
     * @param httpUrl url地址
     * @param postData 需要post的数据
     * @return 返回生成的HttpPost，可能为null
     */
    public static HttpPost post(String httpUrl, String postData)
    {
        if (TextUtils.isEmpty(httpUrl)) return null;
        if (TextUtils.isEmpty(postData)) return null;
        HttpPost post = new HttpPost(httpUrl);
        try {
            JsonEntity se = new JsonEntity(postData, HTTP.UTF_8);
            post.setEntity(se);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return post;
    }

    public static HttpPost post(String httpUrl, HashMap<String, String> values)
    {
        if (TextUtils.isEmpty(httpUrl)) return null;
        if (values == null) return null;
        HttpPost post = new HttpPost(httpUrl);
        post.addHeader("Content-Type", "application/json");
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            Iterator<Entry<String, String>> iter = values.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, String> entry = iter.next();
                String key = entry.getKey();
                String val = entry.getValue();
                params.add(new BasicNameValuePair(key, val));
            }
            post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return post;
    }

    public static HttpPost postStream(String httpUrl, HashMap<String, Object> values)
    {
        if (TextUtils.isEmpty(httpUrl)) return null;
        if (values == null) return null;
        HttpPost post = new HttpPost(httpUrl);
        MultipartEntity entity = new MultipartEntity();

        Iterator<Entry<String, Object>> iter = values.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, Object> entry = iter.next();
            if (entry.getValue() instanceof String) {
                try {
                    entity.addPart(entry.getKey(), new StringBody(entry.getValue().toString(), "application/json", Charset.forName(org.apache.http.protocol.HTTP.UTF_8)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (entry.getValue() instanceof File) {
                entity.addPart(entry.getKey(), new FileBody((File) entry.getValue()));
            }

        }
        post.setEntity(entity);
        return post;
    }

    /**
     * 处理HttpRequest，返回HttpResponse，已经考虑到apn接入点、wifi等所有的情况了
     * 
     * @param context
     * @param request 需要处理的request
     * @return 返回HttpResponse响应，可能为null
     */
    public static HttpResponse processHttpRequest(Context context, HttpRequestBase request)
    {
        if (request == null) return null;

        HttpClient httpClient = null;
        try {
            httpClient = new DefaultHttpClient();
            if (NetInfo.isWap(context)) {
                HttpHost proxy = new HttpHost(NetInfo.getWapProxy(context), 80);
                httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
            }
            HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 60 * 1000);
            HttpConnectionParams.setSoTimeout(httpClient.getParams(), 60 * 1000);
            request.addHeader("Charset", HTTP.UTF_8);
            request.addHeader("Connection", "Keep-Alive");

            return httpClient.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
