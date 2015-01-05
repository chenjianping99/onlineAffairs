package com.guangzhou.gov.net.tools;

import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import android.content.Context;

/**
 * 上层封装的Http工具类
 * 
 * @ClassName: HttpTool
 * @author chenjianping
 * @date 2014-11-9
 * 
 */
public class HttpTool {

    /**
     * 通过get方法访问一个http地址，获取其返回的字符串内容
     * 
     * @param context
     * @param contentUrl http地址
     * @return 返回获取到的字符串，可能为null
     */
    public static final String getString(Context context, String contentUrl)
    {
        String data = null;
        HttpGet get = HttpHelper.get(contentUrl);
        if (get != null) {
            HttpResponse resp = HttpHelper.processHttpRequest(context, get);
            if (resp != null) {
                StatusLine statusLine = resp.getStatusLine();
                int code = statusLine.getStatusCode();
                if (code == HttpStatus.SC_OK) {
                    data = HttpHelper.parserHttpResponseToString(resp);
                }
            }
        }
        HttpHelper.abortRequest(get);
        return data;
    }


    /**
     * 通过post方法访问一个http地址，获取其返回的字符串内容
     * 
     * @param context
     * @param httpUrl http地址
     * @param values post的数据
     * @return 返回获取到的字符串，可能为null
     */
    public static final String getPostString(Context context, String httpUrl, HashMap<String, String> values)
    {
        String data = null;
        HttpPost post = HttpHelper.post(httpUrl, values);
        if (post != null) {
            HttpResponse resp = HttpHelper.processHttpRequest(context, post);
            if (resp != null) {
                StatusLine statusLine = resp.getStatusLine();
                int code = statusLine.getStatusCode();
                if (code == HttpStatus.SC_OK) {
                    data = HttpHelper.parserHttpResponseToString(resp);
                }
            }
        }
        HttpHelper.abortRequest(post);
        return data;
    }



    /**
     * 通过post方法访问一个http地址，获取其返回的http状态码和字符串内容
     * 
     * @param context
     * @param returnData byte数组缓存，获取到的数据将存放到该缓存中
     * @param httpUrl http地址
     * @param postData post的数据
     */
    public static final String postString(Context context, String httpUrl, String postData)
    {
        String data = null;
        HttpPost post = HttpHelper.post(httpUrl, postData);
        if (post != null) {
            HttpResponse resp = HttpHelper.processHttpRequest(context, post);
            if (resp != null) {
                StatusLine statusLine = resp.getStatusLine();
                int code = statusLine.getStatusCode();
                if (code == HttpStatus.SC_OK) {
                    data = HttpHelper.parserHttpResponseToString(resp);
                }
            }
        }
        HttpHelper.abortRequest(post);
        return data;
    }

    /**
     * 上传文件到服务端。
     * 
     * @param httpUrl
     * @param values
     * @return
     * @throws UploadException
     */
    public static final String postStream(Context context, String httpUrl, HashMap<String, Object> values)
    {
        String returnStr = null;
        HttpPost post = HttpHelper.postStream(httpUrl, values);

        HttpResponse response = HttpHelper.processHttpRequest(context, post);
        StatusLine statusLine = response.getStatusLine();
        int code = statusLine.getStatusCode();
        if (code == HttpStatus.SC_OK) {
            returnStr = HttpHelper.parserHttpResponseToString(response);
        }
        HttpHelper.abortRequest(post);
        return returnStr;
    }

}
