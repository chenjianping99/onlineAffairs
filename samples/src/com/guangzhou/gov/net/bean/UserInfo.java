package com.guangzhou.gov.net.bean;

/**
 * 用户详细信息
 * 
 * @ClassName: UserInfo
 * @author chenjianping
 * @date 2014-11-9
 * 
 */
public class UserInfo extends BaseBean {

    private static final long serialVersionUID = 8606996193556408127L;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 
     */
    private String bar_code;

    /**
     * 
     */
    private String ca_sn;

    /**
     * 是否通过验证 2为通过 默认为2
     */
    private String check_state;

    /**
     * 统一认证唯一标示
     */
    private String code;

    /**
     * 法定代表人（企业）
     */
    private String corporation;

    /**
     * 法定代表人身份证（企业）
     */
    private String corporation_id;

    /**
     * 邮箱
     */
    private String e_mail;

    /**
     * 
     */
    private String e_uid;

    /**
     * 
     */
    private String exchange_status;

    /**
     * 经办人身份证（企业）
     */
    private String handler_id;

    /**
     * 经办人身份证（企业）
     */
    private String handler_name;

    /**
     * 
     */
    private String ip_Number;

    /**
     * 是否新设立企业，0为否，1为是，默认0
     */
    private String is_new_establish;

    /**
     * 营业执照号
     */
    private String license;

    /**
     * 登录ip
     */
    private String loginIP;

    /**
     * 登录网卡地址
     */
    private String loginMac;

    /**
     * 用户手机
     */
    private String mobile_phone;

    /**
     * 用户名称（个人／企业）
     */
    private String name;

    /**
     * 组织机构代码（企业）
     */
    private String org_code;

    /**
     * 用户身份证号码
     */
    private String paper_code;

    /**
     * 证件类型（1为身份证）
     */
    private String paper_type;

    /**
     * 密保答案
     */
    private String pwd_answer;

    /**
     * 密保问题
     */
    private String pwd_question;

    /**
     *
     */
    private String pwd_type;

    /**
     * 固定电话（个人／企业）
     */
    private String tel_phone;

    /**
     * 如果是个人用户：p1为男,p0为女。 如果是企业用户：e1国有,e2民营,e3外资,e4港澳台资,e5其他
     */
    private String type;

    /**
     * 流水号
     */
    private String u_id;

    /**
     * 用户账号
     */
    private String u_name;

    /**
     * 用户密码
     */
    private String u_pwd;

    /**
     * 用户类型，1为企业用户，2为个人用户，默认为2个人用户（个人/企业）
     */
    private String u_type;

    /**
     * 用户类型：1 会员卡用户, 2 CA用户, 3 普通用户,默认为3
     */
    private String u_type2;

    /**
     * 
     */
    private String unit_code;


    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getBar_code()
    {
        return bar_code;
    }

    public void setBar_code(String bar_code)
    {
        this.bar_code = bar_code;
    }

    public String getCa_sn()
    {
        return ca_sn;
    }

    public void setCa_sn(String ca_sn)
    {
        this.ca_sn = ca_sn;
    }

    public String getCheck_state()
    {
        return check_state;
    }

    public void setCheck_state(String check_state)
    {
        this.check_state = check_state;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCorporation()
    {
        return corporation;
    }

    public void setCorporation(String corporation)
    {
        this.corporation = corporation;
    }

    public String getCorporation_id()
    {
        return corporation_id;
    }

    public void setCorporation_id(String corporation_id)
    {
        this.corporation_id = corporation_id;
    }

    public String getE_mail()
    {
        return e_mail;
    }

    public void setE_mail(String e_mail)
    {
        this.e_mail = e_mail;
    }

    public String getE_uid()
    {
        return e_uid;
    }

    public void setE_uid(String e_uid)
    {
        this.e_uid = e_uid;
    }

    public String getExchange_status()
    {
        return exchange_status;
    }

    public void setExchange_status(String exchange_status)
    {
        this.exchange_status = exchange_status;
    }

    public String getHandler_id()
    {
        return handler_id;
    }

    public void setHandler_id(String handler_id)
    {
        this.handler_id = handler_id;
    }

    public String getHandler_name()
    {
        return handler_name;
    }

    public void setHandler_name(String handler_name)
    {
        this.handler_name = handler_name;
    }

    public String getIp_Number()
    {
        return ip_Number;
    }

    public void setIp_Number(String ip_Number)
    {
        this.ip_Number = ip_Number;
    }

    public String getIs_new_establish()
    {
        return is_new_establish;
    }

    public void setIs_new_establish(String is_new_establish)
    {
        this.is_new_establish = is_new_establish;
    }

    public String getLicense()
    {
        return license;
    }

    public void setLicense(String license)
    {
        this.license = license;
    }

    public String getLoginIP()
    {
        return loginIP;
    }

    public void setLoginIP(String loginIP)
    {
        this.loginIP = loginIP;
    }

    public String getLoginMac()
    {
        return loginMac;
    }

    public void setLoginMac(String loginMac)
    {
        this.loginMac = loginMac;
    }

    public String getMobile_phone()
    {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone)
    {
        this.mobile_phone = mobile_phone;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getOrg_code()
    {
        return org_code;
    }

    public void setOrg_code(String org_code)
    {
        this.org_code = org_code;
    }

    public String getPaper_code()
    {
        return paper_code;
    }

    public void setPaper_code(String paper_code)
    {
        this.paper_code = paper_code;
    }

    public String getPaper_type()
    {
        return paper_type;
    }

    public void setPaper_type(String paper_type)
    {
        this.paper_type = paper_type;
    }

    public String getPwd_answer()
    {
        return pwd_answer;
    }

    public void setPwd_answer(String pwd_answer)
    {
        this.pwd_answer = pwd_answer;
    }

    public String getPwd_question()
    {
        return pwd_question;
    }

    public void setPwd_question(String pwd_question)
    {
        this.pwd_question = pwd_question;
    }

    public String getPwd_type()
    {
        return pwd_type;
    }

    public void setPwd_type(String pwd_type)
    {
        this.pwd_type = pwd_type;
    }

    public String getTel_phone()
    {
        return tel_phone;
    }

    public void setTel_phone(String tel_phone)
    {
        this.tel_phone = tel_phone;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getU_id()
    {
        return u_id;
    }

    public void setU_id(String u_id)
    {
        this.u_id = u_id;
    }

    public String getU_name()
    {
        return u_name;
    }

    public void setU_name(String u_name)
    {
        this.u_name = u_name;
    }

    public String getU_pwd()
    {
        return u_pwd;
    }

    public void setU_pwd(String u_pwd)
    {
        this.u_pwd = u_pwd;
    }

    public String getU_type()
    {
        return u_type;
    }

    public void setU_type(String u_type)
    {
        this.u_type = u_type;
    }

    public String getU_type2()
    {
        return u_type2;
    }

    public void setU_type2(String u_type2)
    {
        this.u_type2 = u_type2;
    }

    public String getUnit_code()
    {
        return unit_code;
    }

    public void setUnit_code(String unit_code)
    {
        this.unit_code = unit_code;
    }

	@Override
	public String toString() {
		return "UserInfo [address=" + address + ", bar_code=" + bar_code
				+ ", ca_sn=" + ca_sn + ", check_state=" + check_state
				+ ", code=" + code + ", corporation=" + corporation
				+ ", corporation_id=" + corporation_id + ", e_mail=" + e_mail
				+ ", e_uid=" + e_uid + ", exchange_status=" + exchange_status
				+ ", handler_id=" + handler_id + ", handler_name="
				+ handler_name + ", ip_Number=" + ip_Number
				+ ", is_new_establish=" + is_new_establish + ", license="
				+ license + ", loginIP=" + loginIP + ", loginMac=" + loginMac
				+ ", mobile_phone=" + mobile_phone + ", name=" + name
				+ ", org_code=" + org_code + ", paper_code=" + paper_code
				+ ", paper_type=" + paper_type + ", pwd_answer=" + pwd_answer
				+ ", pwd_question=" + pwd_question + ", pwd_type=" + pwd_type
				+ ", tel_phone=" + tel_phone + ", type=" + type + ", u_id="
				+ u_id + ", u_name=" + u_name + ", u_pwd=" + u_pwd
				+ ", u_type=" + u_type + ", u_type2=" + u_type2
				+ ", unit_code=" + unit_code + "]";
	}


}
