package com.guangzhou.gov.net.bean;

/**
 * 
 * @ClassName: ClientConfig
 * @Description: 客户端事项配置
 * @author chenjianping
 * @date 2014-11-14
 * 
 */
public class ClientConfig extends BaseBean {

    private static final long serialVersionUID = 3333135183187037109L;
    
    /**
     * 事项编码
     */
    private String serviceCode;

    /**
     * 是否可以手机申办
     */
    private String ifClientSubmit;

    /**
     * 所属系统编码
     */
    private String belongSysCode;

    /**
     * 对端系统的事项业务编码
     */
    private String belongServiceCode;

    /**
     * 申办实现方式
     */
    private String submitType;

    /**
     * 移动客户端申办地址
     */
    private String submitUrl;

    /**
     * 办理对象
     */
    private String userType;

    public String getServiceCode()
    {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode)
    {
        this.serviceCode = serviceCode;
    }

    public String getIfClientSubmit()
    {
        return ifClientSubmit;
    }

    public void setIfClientSubmit(String ifClientSubmit)
    {
        this.ifClientSubmit = ifClientSubmit;
    }

    public String getBelongSysCode()
    {
        return belongSysCode;
    }

    public void setBelongSysCode(String belongSysCode)
    {
        this.belongSysCode = belongSysCode;
    }

    public String getBelongServiceCode()
    {
        return belongServiceCode;
    }

    public void setBelongServiceCode(String belongServiceCode)
    {
        this.belongServiceCode = belongServiceCode;
    }

    public String getSubmitType()
    {
        return submitType;
    }

    public void setSubmitType(String submitType)
    {
        this.submitType = submitType;
    }

    public String getSubmitUrl()
    {
        return submitUrl;
    }

    public void setSubmitUrl(String submitUrl)
    {
        this.submitUrl = submitUrl;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }



}
