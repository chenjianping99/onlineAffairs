package com.guangzhou.gov.net.http;

/**
 * 
 * @ClassName: UrlConfig
 * @author chenjianping
 * @date 2014-11-9
 * 
 */
public interface UrlConfig {
    static final String API_ROOT = "http://210.76.67.116:8001/gdbsService";
    //static final String API_ROOT = "http://esss.nat123.net/gdbsService";
    
    public static final String POST_URL_USER_Login = API_ROOT + "/user/login"; // 登录

    public static final String POST_URL_USER_Logout = API_ROOT + "/user/logout"; // 登出

    public static final String POST_URL_onlineApply = API_ROOT + "/work/onlineApply"; // 提交表单

    public static final String POST_URL_uploadApplyAttach = API_ROOT + "/work/uploadApplyAttach"; // 上传表单附件

    public static final String GET_URL_listDivision = API_ROOT + "/division /listDivision"; // 列出所有的行政区划

    public static final String GET_URL_serviceOrg = API_ROOT + "/serviceOrg/getServiceOrg"; // 根据机构编码查询机构信息

    public static final String GET_URL_serviceOrg_list = API_ROOT + "/serviceOrg/listOrg"; // 根据行政区域代码查询机构信息
    
    public static final String GET_URL_ServiceItem = API_ROOT + "/serviceItem/getServiceItem"; // 根据服务事项编码获取服务事项
    
    public static final String GET_URL_ServiceItem_list = API_ROOT + "/serviceItem/listServiceItem"; // 根据服务机构编码查询服务机构事项列表。

    public static final String GET_URL_ServiceItem_page = API_ROOT + "/serviceItem/pageServiceItem"; // 根据服务机构编码分页查询服务机构事项列表。

    public static final String GET_URL_ServiceItem_list_Bykeyword = API_ROOT + "/serviceItem/listServiceItemBykeyword"; // 根据行政区划和关键字查询服务机构事项列表。

    public static final String GET_URL_ServiceItem_page_Bykeyword = API_ROOT + "/serviceItem/pageServiceItemByKeyword"; // 根据行政区划和关键字分页查询服务机构事项列表。

    public static final String GET_URL_ServiceItem_list_ByCatalog = API_ROOT + "/serviceItem/listServiceItemByCatalog"; // 根据行政区划和事项分类列出事项列表

    public static final String GET_URL_ServiceItem_page_ByCatalog = API_ROOT + "/serviceItem/pageServiceItemByCatalog"; // 根据行政区划和事项分类列出事项列表
    
    public static final String GET_URL_ServiceItem_list_Window = API_ROOT + "/serviceItem/listServiceItemWindow"; // 根据事项获取事项窗口列表。

    public static final String GET_URL_ServiceItem_list_Attachment = API_ROOT + "/serviceItem/listServiceItemAttachment"; // 根据事项获取事项附件
    
    public static final String GET_URL_ServiceItem_ClientConfig = API_ROOT + "/serviceItem/getServiceItemClientConfig"; // 根据事项代码获取客户端事项配置。
  
    public static final String GET_URL_serviceCatalog_list_Catalog = API_ROOT + "/serviceCatalog/listItemCatalog"; // 根据父级分类编码列出子级事项分类列表

}
