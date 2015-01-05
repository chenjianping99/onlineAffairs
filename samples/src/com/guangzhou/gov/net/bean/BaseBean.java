package com.guangzhou.gov.net.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: BaseBean
 * @author chenjianping
 * @date 2014-11-9
 * 
 */
public class BaseBean implements Serializable {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -4688003172610751734L;
    private String message;
    private String success;

    /**
     * 办件流水号,第一次可为空。
     */
    private String controlSeq;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getSuccess()
    {
        return success;
    }

    public void setSuccess(String success)
    {
        this.success = success;
    }

    public String getControlSeq()
    {
        return controlSeq;
    }

    public void setControlSeq(String controlSeq)
    {
        this.controlSeq = controlSeq;
    }

}
