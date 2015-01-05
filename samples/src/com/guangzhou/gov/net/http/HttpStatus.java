package com.guangzhou.gov.net.http;

import com.guangzhou.gov.net.parsers.HttpJsonResponse;

/**
 * 
 * @ClassName: HttpStatus
 * @Description: 状体封装
 * @author chenjianping
 * @date 2014-11-17
 * 
 */
public class HttpStatus {
    public static final int STATUS_SUCCESS = 0; // 成功
    public static final int STATUS_FAIL = 1; // 失败
    public static final int STATUS_NET_ERROE = 2; // 网络异常
    public int mStatus;
    public String mErrorMessage;
    public String mCode;
    public String mRequestMethod; // 请求的方法名

    public String request;
    public String error;
    public String error_code;

    public boolean isSuccess()
    {
        return this.mStatus == STATUS_SUCCESS;
    }
    
    public void setError(HttpJsonResponse json) {
        this.request = json.getAsString("request");
        this.error = json.getAsString("error");
        this.error_code = json.getAsString("error_code");
    }

    public static enum ERROR_CODE {
    	SystemEerror(100001), //系统错误
    	ServiceUnavailable(100002), //服务暂停
    	RemoteServiceError(100003), //远程服务错误
    	ParamError(100004), //参数错误，请参考API文档
    	JobExpired(100005), //任务超时
    	IllegalRequest(100006),  //非法请求
    	MissRrequiredParameter(100007),   // 缺失必选参数 (%s)，请参考API文档
    	ParameterValueInvalid(100008), // 参数值非法，需为 (%s)，实际为 (%s)，请参...
    	RequestApiNotFound(100009),  //接口不存在
    	HTTPMethodNotSuported(100010), //请求的HTTP METHOD不支持，请检查是否选择...
    	LoginExpired(100011); // 登录过期

    	private final int id;
    	ERROR_CODE(int id) { this.id = id; }
        public int getValue() { return id; }
    }
    
}
