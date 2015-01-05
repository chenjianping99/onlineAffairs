package com.guangzhou.gov.net.http.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.protocol.HTTP;

/**
 * 
 * @ClassName: JsonEntity
 * @author chenjianping
 * @date 2014-11-12
 * 
 */
public class JsonEntity extends AbstractHttpEntity implements Cloneable {


    protected final byte[] content;

    public JsonEntity(final String s, String charset) throws UnsupportedEncodingException {
        super();
        if (s == null) {
            throw new IllegalArgumentException("Source string may not be null");
        }
        if (charset == null) {
            charset = HTTP.DEFAULT_CONTENT_CHARSET;
        }
        this.content = s.getBytes(charset);
        setContentType("application/json" + HTTP.CHARSET_PARAM + charset);
    }

    public JsonEntity(final String s) throws UnsupportedEncodingException {
        this(s, null);
    }

    public boolean isRepeatable()
    {
        return true;
    }

    public long getContentLength()
    {
        return this.content.length;
    }

    public InputStream getContent() throws IOException
    {
        return new ByteArrayInputStream(this.content);
    }

    public void writeTo(final OutputStream outstream) throws IOException
    {
        if (outstream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        outstream.write(this.content);
        outstream.flush();
    }

    /**
     * Tells that this entity is not streaming.
     * 
     * @return <code>false</code>
     */
    public boolean isStreaming()
    {
        return false;
    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
