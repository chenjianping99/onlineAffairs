package com.guangzhou.gov.net.bean;

/**
 * 
 * @ClassName: ApplyAttach
 * @Description 申办材料
 * @author chenjianping
 * @date 2014-11-10
 * 
 */
public class ApplyAttach extends BaseBean {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 2380812659800955391L;
    
    /**
     * 用户账号
     */
    private String user_code;

    /**
     * 材料ID
     */
    private String stuffId;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 附件内容
     */
    private String fileContent;

    public String getUser_code()
    {
        return user_code;
    }

    public void setUser_code(String user_code)
    {
        this.user_code = user_code;
    }
   

    public String getStuffId()
    {
        return stuffId;
    }

    public void setStuffId(String stuffId)
    {
        this.stuffId = stuffId;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileContent()
    {
        return fileContent;
    }

    public void setFileContent(String fileContent)
    {
        this.fileContent = fileContent;
    }


}
