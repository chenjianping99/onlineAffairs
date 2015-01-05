package com.guangzhou.gov.net.bean;

/**
 * 
 * @ClassName: ServiceOrg
 * @Description: 服务机构
 * @author chenjianping
 * @date 2014-11-14
 * 
 */
public class ServiceOrg extends BaseBean {
    private static final long serialVersionUID = 5726730153463227700L;

    /**
     * 机构地址
     */
    private String address; //
    /**
     * 机构名称
     */
    private String name; //

    /**
     * 机构类型。一级政府、政府部门
     */
    private String type; //

    /**
     * 备注说明
     */
    private String description; //

    /**
     * 创建时间
     */
    private String creationTime; //

    /**
     * 机构简称
     */
    private String shortName; //

    /**
     * 顺序号，用于界面显示排序
     */
    private String sortOrder; //

    /**
     * 创建者
     */
    private String creator; //

    /**
     * 组织机构代码(9位)，标识属性
     */
    private String orgCode; //

    /**
     * 机构职能
     */
    private String duties; //

    /**
     * 上级机构
     */
    private String parentOrg; //

    /**
     * 机构标签
     */
    private String label; //

    /**
     * 联系电话
     */
    private String phone; //

    /**
     * 行政区划名称
     */
    private String division; //

    /**
     * 扩展属性
     */
    private String extendProps; //

    /**
     * 最后修改者
     */
    private String lastModificator; //

    /**
     * 行政系统代码(预留)
     */
    private String adminSysCode; //

    /**
     * 行政区划代码。只读属性
     */
    private String divisionCode; //

    /**
     * 最后修改时间
     */
    private String lastModificationTime; //

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCreationTime()
    {
        return creationTime;
    }

    public void setCreationTime(String creationTime)
    {
        this.creationTime = creationTime;
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    public String getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getOrgCode()
    {
        return orgCode;
    }

    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    public String getDuties()
    {
        return duties;
    }

    public void setDuties(String duties)
    {
        this.duties = duties;
    }

    public String getParentOrg()
    {
        return parentOrg;
    }

    public void setParentOrg(String parentOrg)
    {
        this.parentOrg = parentOrg;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getDivision()
    {
        return division;
    }

    public void setDivision(String division)
    {
        this.division = division;
    }

    public String getExtendProps()
    {
        return extendProps;
    }

    public void setExtendProps(String extendProps)
    {
        this.extendProps = extendProps;
    }

    public String getLastModificator()
    {
        return lastModificator;
    }

    public void setLastModificator(String lastModificator)
    {
        this.lastModificator = lastModificator;
    }

    public String getAdminSysCode()
    {
        return adminSysCode;
    }

    public void setAdminSysCode(String adminSysCode)
    {
        this.adminSysCode = adminSysCode;
    }

    public String getDivisionCode()
    {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode)
    {
        this.divisionCode = divisionCode;
    }

    public String getLastModificationTime()
    {
        return lastModificationTime;
    }

    public void setLastModificationTime(String lastModificationTime)
    {
        this.lastModificationTime = lastModificationTime;
    }


}
