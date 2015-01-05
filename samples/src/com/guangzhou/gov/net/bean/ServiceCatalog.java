package com.guangzhou.gov.net.bean;

/**
 * 
 * @ClassName: ServiceCatalog
 * @Description: 事项分类
 * @author chenjianping
 * @date 2014-11-14
 * 
 */
public class ServiceCatalog extends BaseBean {

    private static final long serialVersionUID = 5895740857887206692L;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 创建时间
     */
    private String creationTime;

    /**
     * 上级分类代码
     */
    private String parentCode;

    /**
     * 分类图片url
     */
    private String iconUrl;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 服务分类代码
     */
    private String catalogCode;

    /**
     * 最后修改者
     */
    private String lastModificator;

    /**
     * 最后修改时间
     */
    private String lastModificationTime;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCreationTime()
    {
        return creationTime;
    }

    public void setCreationTime(String creationTime)
    {
        this.creationTime = creationTime;
    }

    public String getParentCode()
    {
        return parentCode;
    }

    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }

    public String getIconUrl()
    {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl)
    {
        this.iconUrl = iconUrl;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getCatalogCode()
    {
        return catalogCode;
    }

    public void setCatalogCode(String catalogCode)
    {
        this.catalogCode = catalogCode;
    }

    public String getLastModificator()
    {
        return lastModificator;
    }

    public void setLastModificator(String lastModificator)
    {
        this.lastModificator = lastModificator;
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
