package com.guangzhou.gov.net.http;

import com.guangzhou.gov.net.parsers.HttpJsonResponse;

/**
 * 网络请求接口
 * 
 * @ClassName: HttpInterface
 * @author chenjianping
 * @date 2014-11-9
 * 
 */
public interface HttpListener {
    public void onHttpError(HttpStatus mHs);

    public void onSuccess(Object parent, HttpStatus mStatus);

    public void onFail(HttpStatus mHs);

    public interface HttpCallable<V> {
        public V onCall(HttpStatus mHs) throws Exception;
    }
    public interface HttpCallback<T> {
        public void onCallback(final T result);
    }

    public interface Transformer {
        public <T> T onTransformer(HttpJsonResponse json, HttpStatus httpStatus);
    }

}
