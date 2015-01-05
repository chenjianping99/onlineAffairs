package com.guangzhou.gov.net.bean;

/**
 * 
 * @ClassName: ServiceAttachment
 * @Description: 事项附件
 * @author chenjianping
 * @date 2014-11-14
 * 
 */
public class ServiceAttachment extends BaseBean {

    private static final long serialVersionUID = 439306690411421008L;

    /**
     * 附件代码
     */
    private String attachment_code;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 对应服务事项的代码
     */
    private String service_code;

    /**
     * 附件类型（代码值）
     */
    private String attachment_type;

    /**
     * 原始文件名
     */
    private String file_name;

    /**
     * 文件类型。允许的类型常见的文档、表单 和图片，如doc、docx、pdf、xls、xlsx、bmp、 jpg、jpeg、gif、png等。
     */
    private String file_type;

    /**
     * 文件链接。此属性在上传数据时不需要赋值，附件上传后由服务端自动生成一个相对于{SITE_URL}的路径。{SITE_URL}加file_url就是完整的URL。详见附录：
     * 事项附件URL地址说明
     */
    private String file_url;

    /**
     * 排序序号
     */
    private String sort_order;

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

    public String getAttachment_code()
    {
        return attachment_code;
    }

    public void setAttachment_code(String attachment_code)
    {
        this.attachment_code = attachment_code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getService_code()
    {
        return service_code;
    }

    public void setService_code(String service_code)
    {
        this.service_code = service_code;
    }

    public String getAttachment_type()
    {
        return attachment_type;
    }

    public void setAttachment_type(String attachment_type)
    {
        this.attachment_type = attachment_type;
    }

    public String getFile_name()
    {
        return file_name;
    }

    public void setFile_name(String file_name)
    {
        this.file_name = file_name;
    }

    public String getFile_type()
    {
        return file_type;
    }

    public void setFile_type(String file_type)
    {
        this.file_type = file_type;
    }

    public String getFile_url()
    {
        return file_url;
    }

    public void setFile_url(String file_url)
    {
        this.file_url = file_url;
    }

    public String getSort_order()
    {
        return sort_order;
    }

    public void setSort_order(String sort_order)
    {
        this.sort_order = sort_order;
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
