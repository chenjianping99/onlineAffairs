package com.guangzhou.gov.net.bean;

/**
 * 
 * @ClassName: ApplyInfo
 * @Description: 申办信息
 * @author chenjianping
 * @date 2014-11-10
 * 
 */
public class ApplyInfo extends BaseBean {


    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -1625967955293082561L;

    /**
     * 步骤名称,recheck 确认回执（保存、更新时调用）,success已受理（提交调用）。
     */
    private String operateStep;

    /**
     * true：暂存办件（保存、更新时调用） false:提交办件（提交调用）。
     */
    private String isSaveTemp;

    /**
     * 传值：dosave。
     */
    private String actionFlag;

    /**
     * 
     */
    private String approveItem;

    /**
     * 
     */
    private String user_code;

    /**
     * 用户类型,个人--“gr”，企业--“qy”。
     */
    private String uTypeShortName;

    /**
     * 企业名称,申请人为企业时不可为空。
     */
    private String cust_name;

    /**
     * 企业（申请人）地址,第一次申请时不可为空。
     */
    private String cust_addr;

    /**
     * 经办人（申请人）姓名,第一次申请时不可为空。
     */
    private String cust_contact_person;

    /**
     * 手机,第一次申请时不可为空。
     */
    private String custMobile;

    /**
     * 固话,第一次申请时不可为空。
     */
    private String cust_contact_way;

    /**
     * 申请人身份证,申请人为个人时不可为空。
     */
    private String paper_code;

    /**
     * 经办人身份证,申请人为企业时不可为空。
     */
    private String cust_contact_person_id;

    /**
     * 法定代表人,申请人为企业时不可为空。
     */
    private String corporation_name;

    /**
     * 法人身份证,申请人为企业时不可为空。
     */
    private String corporation_id;

    /**
     * 企业类型,申请人为企业时不可为空。
     */
    private String company_type;

    /**
     * 项目名称，不可为空
     */
    private String prj_name;

    public String getPrj_name()
    {
        return prj_name;
    }

    public void setPrj_name(String prj_name)
    {
        this.prj_name = prj_name;
    }

    public String getOperateStep()
    {
        return operateStep;
    }

    public void setOperateStep(String operateStep)
    {
        this.operateStep = operateStep;
    }

    public String getIsSaveTemp()
    {
        return isSaveTemp;
    }

    public void setIsSaveTemp(String isSaveTemp)
    {
        this.isSaveTemp = isSaveTemp;
    }

    public String getActionFlag()
    {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag)
    {
        this.actionFlag = actionFlag;
    }

    public String getApproveItem()
    {
        return approveItem;
    }

    public void setApproveItem(String approveItem)
    {
        this.approveItem = approveItem;
    }

    public String getUser_code()
    {
        return user_code;
    }

    public void setUser_code(String user_code)
    {
        this.user_code = user_code;
    }

    public String getuTypeShortName()
    {
        return uTypeShortName;
    }

    public void setuTypeShortName(String uTypeShortName)
    {
        this.uTypeShortName = uTypeShortName;
    }

    public String getCust_name()
    {
        return cust_name;
    }

    public void setCust_name(String cust_name)
    {
        this.cust_name = cust_name;
    }

    public String getCust_addr()
    {
        return cust_addr;
    }

    public void setCust_addr(String cust_addr)
    {
        this.cust_addr = cust_addr;
    }

    public String getCust_contact_person()
    {
        return cust_contact_person;
    }

    public void setCust_contact_person(String cust_contact_person)
    {
        this.cust_contact_person = cust_contact_person;
    }

    public String getCustMobile()
    {
        return custMobile;
    }

    public void setCustMobile(String custMobile)
    {
        this.custMobile = custMobile;
    }

    public String getCust_contact_way()
    {
        return cust_contact_way;
    }

    public void setCust_contact_way(String cust_contact_way)
    {
        this.cust_contact_way = cust_contact_way;
    }

    public String getPaper_code()
    {
        return paper_code;
    }

    public void setPaper_code(String paper_code)
    {
        this.paper_code = paper_code;
    }

    public String getCust_contact_person_id()
    {
        return cust_contact_person_id;
    }

    public void setCust_contact_person_id(String cust_contact_person_id)
    {
        this.cust_contact_person_id = cust_contact_person_id;
    }

    public String getCorporation_name()
    {
        return corporation_name;
    }

    public void setCorporation_name(String corporation_name)
    {
        this.corporation_name = corporation_name;
    }

    public String getCorporation_id()
    {
        return corporation_id;
    }

    public void setCorporation_id(String corporation_id)
    {
        this.corporation_id = corporation_id;
    }

    public String getCompany_type()
    {
        return company_type;
    }

    public void setCompany_type(String company_type)
    {
        this.company_type = company_type;
    }



}
