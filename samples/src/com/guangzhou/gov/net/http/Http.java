package com.guangzhou.gov.net.http;

import java.util.ArrayList;

import android.content.Context;

import com.guangzhou.gov.manager.UserManager;
import com.guangzhou.gov.net.bean.Userlogin;


/**
 * 
 * @ClassName: Http
 * @Description: 网络请求
 * @author chenjianping
 * @date 2014-11-9
 * 
 */
public class Http {

    private Context mContext;
    private HttpListener mListener;

    public Http(Context context) {
        this.mContext = context;
    }

    public void setHttpListener(HttpListener listener)
    {
        this.mListener = listener;
    }

    /**
     * 
     * @Description: 用户登陆接口
     * @param @param dialogMeg dialog描述（加载中...)
     * @param @param name 用户名
     * @param @param password 密码
     * @return void
     * @author chenjianping
     */
    public void doUserLogin(final String dialogMeg, final String name, final String pwd)
    {
        new HttpImpl(mContext, mListener).doUserLogin(dialogMeg, name, pwd);
    }

    /**
     * 
     * @Description: 用户登陆接口
     * @param @param dialogMeg dialog描述（加载中...)
     * @param @param name 用户名
     * @param @param password 密码
     * @return void
     * @author chenjianping
     */
    public void doUserLogout(final String dialogMeg)
    {
        Userlogin u = UserManager.getInstance().mUser;
        if (u != null) {
            new HttpImpl(mContext, mListener).doUserLogout(dialogMeg, u.getUserInfo().getU_name());
        }
    }

    /**
     * 提交办事资料 文档部分
     * 
     * @desc 上传表单信息，获取流水号
     * @param @param dialogMeg dialog描述（加载中...)
     * , String approveItem
     *  @param @param cust_name 企业名称
     *  @param @param cust_addr 企业地址
     *  @param @param company_type  企业类型
     * @param @param jbr 经办人
     * @param @param mobile 手机
     * @param @param phone 座机
     * @param @param project 项目名称
     * @return @param void
     * @author chenjianping
     * @request post
     * @login 是
     */
    public void doCommitDoc(final String dialogMeg, final String approveItem, final String cust_name, final String cust_addr, final String company_type,
    		final String jbr, final String mobile, final String phone, final String project)
    {
        new HttpImpl(mContext, mListener).doCommitDoc(dialogMeg, approveItem, cust_name, cust_addr, company_type,  jbr, mobile, phone, project);
    }
    
    /**
     * 提交办事资料（文件）
     * 
     * @desc 根据流水号和资料id 上传图片
     * @param @param dialogMeg dialog描述（加载中...)
     * @param @param files (文件全路径，支持多张）
     * @param @param controlSeq 流水号
     * @param @param appove_item_stuff_id 上传材料对应的appove_item和stuff_id
     * @return @param void
     * @author chenjianping
     * @request post
     * @login 是
     * 
     *        o.setUser_code(UserManager.getInstance().mUser.getUserInfo().getU_name());
     *        o.setControlSeq("105456"); o.setStuffId("3837"); o.setFileName("log");
     * 
     */
    public void doCommitFile(final String dialogMeg, final ArrayList<String> files, final String controlSeq, final String[][] appove_item_stuff_id)
    {
        new HttpImpl(mContext, mListener).doCommitFile(dialogMeg, files, controlSeq, appove_item_stuff_id);
    }

    /**
     * @Description: 列出所有的行政区划
     * @param @param dialogMeg dialog描述（加载中...)
     * @return void
     * @login 是／否
     */
    public void doListDivision(final String pMessage)
    {
        new HttpImpl(mContext, mListener).doListDivision(pMessage);
    }

    /**
     * 
     * @Description: 根据机构编码查询机构信息
     * @param @param pMessage
     * @param @param orgCode
     * @return void
     * @login 是／否
     */
    public void doGetServiceOrg(final String pMessage, final String orgCode)
    {
        new HttpImpl(mContext, mListener).doGetServiceOrg(pMessage, orgCode);
    }

    /**
     * 
     * @Description: 根据行政区域代码查询机构信息
     * @param @param pMessage
     * @param @param divisionCode
     * @return void
     * @login 是／否
     */
    public void doGetServiceOrgList(final String pMessage, final String divisionCode)
    {
        new HttpImpl(mContext, mListener).doGetServiceOrgList(pMessage, divisionCode);
    }


    /**
     * @param @param dialogMeg dialog描述（加载中...)
     * @Description: 根据服务事项编码获取服务事项
     * @return void
     * @author chenjianping
     * @request get
     * @login 否
     * 
     */
    public void doGetServiceItem(final String dialogMeg, String serviceCode)
    {
        new HttpImpl(mContext, mListener).doGetServiceItem(dialogMeg, serviceCode);
    }

    /**
     * @param @param dialogMeg dialog描述（加载中...)
     * @Description: 根据服务机构编码查询服务机构事项列表。
     * @return void
     * @author chenjianping
     * @request get
     * @login 否
     * 
     */
    public void doGetServiceItemList(final String dialogMeg, String orgCode)
    {
        new HttpImpl(mContext, mListener).doGetServiceItemList(dialogMeg, orgCode);
    }

    /**
     * 
     * @Description: 根据服务机构编码分页查询服务机构事项列表。
     * @param @param orgCode
     * @param @param pageNo
     * @param @param pageSize
     * @request get
     * @login 否
     * @return void
     */
    public void doGetPageServiceItem(final String dialogMeg, final String orgCode, final int pageNo, final int pageSize)
    {
        new HttpImpl(mContext, mListener).doGetPageServiceItem(dialogMeg, orgCode, pageNo, pageSize);
    }

    /**
     * 
     * @Description: 根据行政区划和关键字查询服务机构事项列表。
     * @param @param divisionCode
     * @param @param keyword
     * @request get
     * @login 否
     * @return void
     */
    public void doGetServiceItemListByKeyword(final String dialogMeg, final String divisionCode, final String keyword)
    {
        new HttpImpl(mContext, mListener).doGetServiceItemListByKeyword(dialogMeg, divisionCode, keyword);
    }

    /**
     * 
     * @Description: 根据服务机构编码分页查询服务机构事项列表。
     * @param @param orgCode
     * @param @param pageNo
     * @param @param pageSize
     * @param @param keyword
     * @request get
     * @login 否
     * @return void
     */
    public void doGetPageServiceItemByKeyword(final String dialogMeg, final String keyword, final String orgCode, final int pageNo, final int pageSize)
    {
        new HttpImpl(mContext, mListener).doGetPageServiceItemByKeyword(dialogMeg, keyword, orgCode, pageNo, pageSize);
    }

    /**
     * 
     * @Description: 根据行政区划和事项分类列出事项列表
     * @param @param divisionCode
     * @param @param keyword
     * @request get
     * @login 否
     * @return void
     */
    public void doGetServiceItemListByCatalog(final String dialogMeg, final String divisionCode, final String catalogCode)
    {
        new HttpImpl(mContext, mListener).doGetServiceItemListByCatalog(dialogMeg, divisionCode, catalogCode);
    }

    /**
     * 
     * @Description: 根据服务机构编码he分类代码分页查询服务机构事项列表。
     * @param @param divisionCode
     * @param @param catalogCode
     * @param @param pageNo
     * @param @param pageSize
     * @request get
     * @login 否
     * @return void
     */
    public void doGetPageServiceItemByCatalog(final String dialogMeg, final String divisionCode, final String catalogCode, final int pageNo, final int pageSize)
    {
        new HttpImpl(mContext, mListener).doGetPageServiceItemByCatalog(dialogMeg, catalogCode, divisionCode, pageNo, pageSize);
    }

    /**
     * 
     * @Description: 根据事项获取事项窗口列表。
     * @param @param divisionCode 事项代码
     * @request get
     * @login 否
     * @return void
     */
    public void doGetServiceItemListWindow(final String dialogMeg, final String serviceCode)
    {
        new HttpImpl(mContext, mListener).doGetServiceItemListWindow(dialogMeg, serviceCode);
    }

    /**
     * 
     * @Description: 根据事项获取事项附件。
     * @param @param divisionCode 事项代码
     * @request get
     * @login 否
     * @return void
     */
    public void doGetServiceItemListAttachment(final String dialogMeg, final String serviceCode)
    {
        new HttpImpl(mContext, mListener).doGetServiceItemListAttachment(dialogMeg, serviceCode);
    }

    /**
     * 
     * @Description: 根据事项代码获取客户端事项配置
     * @param @param divisionCode 事项代码
     * @request get
     * @login 否
     * @return void
     */
    public void doGetServiceItemClientConfig(final String dialogMeg, final String serviceCode)
    {
        new HttpImpl(mContext, mListener).doGetServiceItemClientConfig(dialogMeg, serviceCode);
    }

    /**
     * 
     * @Description: 根据父级分类编码列出子级事项分类列表
     * @param @param parentCode 事项代码
     * @request get
     * @login 否
     * @return void
     */
    public void doGetServiceCatalogListCatalog(final String dialogMeg, final String parentCode)
    {
        new HttpImpl(mContext, mListener).doGetServiceCatalogListCatalog(dialogMeg, parentCode);
    }

}
