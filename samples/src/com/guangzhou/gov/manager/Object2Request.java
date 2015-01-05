package com.guangzhou.gov.manager;

import com.guangzhou.gov.net.bean.ApplyAttach;
import com.guangzhou.gov.net.bean.ApplyInfo;
import com.guangzhou.gov.net.bean.Userlogin;
import com.guangzhou.gov.net.bean.request.Request;

public class Object2Request {

    /**
     * 
     * @Description: 构建登陆参数
     * @param name
     * @param @param pwd
     * @return Request
     */
    public static Request split2LoginReq(String name, String pwd)
    {
        Request r = new Request();
        Userlogin o = new Userlogin();
        o.setUser_code(name);
        o.setPassword(pwd);
        r.setOuterNetDataInfo(o);
        return r;
    }

    /**
     * 
     * @Description: 构建登陆参数
     * @param name
     * @param @param pwd
     * @return Request
     */
    public static Request split2LogoutReq(String name)
    {
        Request r = new Request();
        Userlogin o = new Userlogin();
        o.setUser_code(name);
        r.setOuterNetDataInfo(o);
        return r;
    }

    /**
     * 
     * @Description: 构建提交文档参数
     * @param @param approveItem
     * @param @param cust_name 企业名称
     *  @param @param cust_addr 企业地址
     *  @param @param company_type  企业类型
     * @param jbr 经办人
     * @param mobile 手机
     * @param phone 座机
     * @param prj_name 项目名称
     * @return Request
     */
    public static Request split2commitDoc(final String approveItem, final String cust_name, final String cust_addr, final String company_type, String jbr, String mobile, String phone, String prj_name)
    {
        Request r = new Request();
        ApplyInfo info = new ApplyInfo();
        info.setOperateStep("recheck");
        info.setIsSaveTemp("true");
        info.setActionFlag("dosave");
        info.setApproveItem(approveItem);
        if (UserManager.getInstance().mUser != null) {
            info.setUser_code(UserManager.getInstance().mUser.getUserInfo().getU_name());
            String type = UserManager.getInstance().mUser.getUserInfo().getU_type();
            //用户类型,个人--“gr”，企业--“qy”。
            if (type.equals("1")) {
            	info.setuTypeShortName("qy");
            } else {
            	info.setuTypeShortName("gr");
            }
            String paper_code = UserManager.getInstance().mUser.getUserInfo().getPaper_code();
            if (paper_code.length() > 0) {
            	info.setPaper_code(paper_code);
            } else {
            	 info.setPaper_code("362524198888889999");
            }
           
            String person_id = UserManager.getInstance().mUser.getUserInfo().getHandler_id();
            if (person_id.length() > 0) {
            	info.setCust_contact_person_id(person_id);
            } else {
            	info.setCust_contact_person_id("362524198888889999");
            }
            info.setCorporation_name(UserManager.getInstance().mUser.getUserInfo().getCorporation());
            info.setCorporation_id(UserManager.getInstance().mUser.getUserInfo().getCorporation_id());
        }
        info.setControlSeq("");
        info.setCust_name(cust_name);
        info.setCust_addr(cust_addr);
        info.setCompany_type(company_type);
        info.setCust_contact_person(jbr);
        info.setCustMobile(mobile);
        info.setCust_contact_way(phone);
        info.setPrj_name(prj_name);

        r.setOuterNetDataInfo(info);
        return r;
    }

    /**
     * 
     * @Description: 构建文件上传请求
     * @param fileName
     * @param dir
     * @param controlSeq
     * @param stuffId
     * @return Request
     */
    public static Request split2commitFile(String fileName, String dir, String controlSeq, String stuffId)
    {
        Request r = new Request();
        ApplyAttach info = new ApplyAttach();
        if (UserManager.getInstance().mUser != null) {
            info.setUser_code(UserManager.getInstance().mUser.getUserInfo().getU_name());
        } else {}
        info.setControlSeq(controlSeq);
        info.setStuffId(stuffId);
        info.setFileName(fileName);
        r.setOuterNetDataInfo(info);
        return r;
    }

    /**
     * 
     * @Description: TODO
     * @param controlSeq 流水号
     * @param approveItem
     * @param @return
     * @return Request
     */
    public static Request split2commitConfirm(String controlSeq, String approveItem)
    {
        Request r = new Request();
        ApplyInfo info = new ApplyInfo();
        info.setOperateStep("success");
        info.setIsSaveTemp("false");
        info.setActionFlag("dosave");
        info.setApproveItem(approveItem);
        if (UserManager.getInstance().mUser != null) {
            info.setUser_code(UserManager.getInstance().mUser.getUserInfo().getU_name());
        }
        info.setControlSeq(controlSeq);
        r.setOuterNetDataInfo(info);
        return r;
    }

}
