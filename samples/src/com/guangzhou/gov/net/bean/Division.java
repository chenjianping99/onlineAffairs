package com.guangzhou.gov.net.bean;

/**
 * 
 * @ClassName: Division
 * @Description: 行政区划
 * @author chenjianping
 * @date 2014-11-13
 * 
 */
public class Division extends BaseBean {

    private static final long serialVersionUID = 4967065264978486346L;

    /**
     * 分厅编码
     */
    private String divisionCode;

    /**
     * 分厅名称
     */
    private String divisionName; //

    /**
     * 上级分厅编码
     */
    private String parentDivisionCode; //

    /**
     * 创建（更新）时间
     */
    private String createDate; //

    public String getDivisionCode()
    {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode)
    {
        this.divisionCode = divisionCode;
    }

    public String getDivisionName()
    {
        return divisionName;
    }

    public void setDivisionName(String divisionName)
    {
        this.divisionName = divisionName;
    }

    public String getParentDivisionCode()
    {
        return parentDivisionCode;
    }

    public void setParentDivisionCode(String parentDivisionCode)
    {
        this.parentDivisionCode = parentDivisionCode;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }



}
