package com.guangzhou.gov.net.bean;

/**
 * 
 * @ClassName: ServiceWindow
 * @Description: 服务事项窗口
 * @author chenjianping
 * @date 2014-11-14
 * 
 */
public class ServiceWindow extends BaseBean {
    private static final long serialVersionUID = 6608843861470475136L;
    
    /**
     * 窗口代码。标识属性
     */
    private String window_code;
    
    /**
     * 窗口名称
     */
    private String name;
    
    /**
     * 联系电话
     */
    private String phone;
    
    /**
     * 窗口地址
     */
    private String address;
    
    /**
     * 办公时间
     */
    private String office_hour;
    
    /**
     * 交通指引
     */
    private String traffic_guide;
    
    /**
     * 所属机构代码
     */
    private String org_code;
    
    /**
     * 创建者
     */
    private String creator;
    
    /**
     * 创建时间
     */
    private String creation_time;
    
    /**
     * 最后修改者
     */
    private String last_modificator;
    
    /**
     * 最后修改时间
     */
    private String last_modification_time;

    public String getWindow_code()
    {
        return window_code;
    }

    public void setWindow_code(String window_code)
    {
        this.window_code = window_code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getOffice_hour()
    {
        return office_hour;
    }

    public void setOffice_hour(String office_hour)
    {
        this.office_hour = office_hour;
    }

    public String getTraffic_guide()
    {
        return traffic_guide;
    }

    public void setTraffic_guide(String traffic_guide)
    {
        this.traffic_guide = traffic_guide;
    }

    public String getOrg_code()
    {
        return org_code;
    }

    public void setOrg_code(String org_code)
    {
        this.org_code = org_code;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getCreation_time()
    {
        return creation_time;
    }

    public void setCreation_time(String creation_time)
    {
        this.creation_time = creation_time;
    }

    public String getLast_modificator()
    {
        return last_modificator;
    }

    public void setLast_modificator(String last_modificator)
    {
        this.last_modificator = last_modificator;
    }

    public String getLast_modification_time()
    {
        return last_modification_time;
    }

    public void setLast_modification_time(String last_modification_time)
    {
        this.last_modification_time = last_modification_time;
    }


}
