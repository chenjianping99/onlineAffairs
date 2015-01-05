package com.guangzhou.gov.net.http;

/**
 * 
 * @ClassName: HttpException
 * @Description: http 异常定义
 * @author chenjianping
 * @date 2014-11-17
 * 
 */
@Deprecated
public class HttpException extends Exception {

    private static final long serialVersionUID = 3854772125385537971L;

    public String message;
    public String url;
    public long time;

    /**
     * @param code
     * @param message
     */
    public HttpException(String message) {
        this.message = message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "HttpException [" + (message != null ? "message=" + message + ", " : "") + (url != null ? "url=" + url + ", " : "") + "time=" + time + " " + "]";
    }



}
