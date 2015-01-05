package com.guangzhou.gov.net.parsers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guangzhou.gov.GovApplication;
import com.guangzhou.gov.manager.UserManager;
import com.guangzhou.gov.net.bean.BaseBean;
import com.guangzhou.gov.net.bean.ClientConfig;
import com.guangzhou.gov.net.bean.Division;
import com.guangzhou.gov.net.bean.Page;
import com.guangzhou.gov.net.bean.ServiceAttachment;
import com.guangzhou.gov.net.bean.ServiceCatalog;
import com.guangzhou.gov.net.bean.ServiceItem;
import com.guangzhou.gov.net.bean.ServiceOrg;
import com.guangzhou.gov.net.bean.ServiceWindow;
import com.guangzhou.gov.net.bean.Userlogin;
import com.guangzhou.gov.net.cache.JSONCache;
import com.guangzhou.gov.net.http.HttpListener.Transformer;
import com.guangzhou.gov.net.http.HttpStatus;

@SuppressWarnings("unchecked")
public class JsonTransformer implements Transformer {

    private static JsonTransformer INSTANCE;

    private JsonTransformer() {

    }

    public static JsonTransformer getInstance()
    {
        if (INSTANCE == null) {
            INSTANCE = new JsonTransformer();
        }
        return INSTANCE;
    }

    @Override
    public <T> T onTransformer(HttpJsonResponse json, HttpStatus httpStatus)
    {
        try {
            if (json != null) {
                if (json.responstSuccess()) {
                    if ("doUserLogin".equals(httpStatus.mRequestMethod)) {
                        return doUserLogin(json);
                    } else if ("doUserLogout".equals(httpStatus.mRequestMethod)) {
                        return doUserLogout(json);
                    } else if ("doCommitDoc".equals(httpStatus.mRequestMethod)) {
                        return doCommitDoc(json);
                    } else if ("doCommitFile".equals(httpStatus.mRequestMethod)) {
                        return doCommitFile(json);
                    } else if ("doListDivision".equals(httpStatus.mRequestMethod)) {
                        return doListDivision(json);
                    } else if ("doGetServiceOrg".equals(httpStatus.mRequestMethod)) {
                        return doGetServiceOrg(json);
                    } else if ("doGetServiceOrgList".equals(httpStatus.mRequestMethod)) {
                        return doGetServiceOrgList(json);
                    } else if ("doGetServiceItem".equals(httpStatus.mRequestMethod)) {
                        return doGetServiceItem(json);
                    } else if ("doGetServiceItemList".equals(httpStatus.mRequestMethod)) {
                        return doGetServiceItemList(json);
                    } else if ("doGetPageServiceItem".equals(httpStatus.mRequestMethod)) {
                        return doGetPageServiceItem(json);
                    } else if ("doGetServiceItemListByKeyword".equals(httpStatus.mRequestMethod)) {
                        return doGetServiceItemListByKeyword(json);
                    } else if ("doGetPageServiceItemByKeyword".equals(httpStatus.mRequestMethod)) {
                        return doGetPageServiceItemByKeyword(json);
                    } else if ("doGetServiceItemListByCatalog".equals(httpStatus.mRequestMethod)) {
                        return doGetServiceItemListByCatalog(json);
                    } else if ("doGetPageServiceItemByCatalog".equals(httpStatus.mRequestMethod)) {
                        return doGetPageServiceItemByCatalog(json);
                    } else if ("doGetServiceItemListWindow".equals(httpStatus.mRequestMethod)) {
                        return doGetServiceItemListWindow(json);
                    } else if ("doGetServiceItemListAttachment".equals(httpStatus.mRequestMethod)) {
                        return doGetServiceItemListAttachment(json);
                    } else if ("doGetServiceItemClientConfig".equals(httpStatus.mRequestMethod)) {
                        return doGetServiceItemClientConfig(json);
                    } else if ("doGetServiceCatalogListCatalog".equals(httpStatus.mRequestMethod)) {
                        return doGetServiceCatalogListCatalog(json);
                    }
                } else {
                    httpStatus.setError(json);
                    if (httpStatus.error_code.equals("100011")) {
                        JSONCache.removCache(GovApplication.getInstance(), JSONCache.KEY_USER_LOGIN_INFO);
                        UserManager.getInstance().init();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * 
     * @Description: 解析用户登陆
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doUserLogin(HttpJsonResponse json)
    {
        Userlogin u = new Gson().fromJson(json.getBodys(), Userlogin.class);
        if (u != null) {
            JSONCache.saveCache(GovApplication.getInstance(), JSONCache.KEY_USER_LOGIN_INFO, json.getBodys().toString());
            UserManager.getInstance().init();
        }
        return (T) u;
    }

    /**
     * 
     * @Description: 解析用户登出
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doUserLogout(HttpJsonResponse json)
    {
        JSONCache.removCache(GovApplication.getInstance(), JSONCache.KEY_USER_LOGIN_INFO);
        UserManager.getInstance().init();
        return (T) new Boolean(json.responstSuccess());
    }

    /**
     * 
     * @Description: 解析用户提交表单
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doCommitDoc(HttpJsonResponse json)
    {
        String controlSeq = json.getBodyString();
        BaseBean bean = new BaseBean();
        bean.setControlSeq(controlSeq);
        return (T) bean;
    }

    /**
     * 
     * @Description: 解析用户提交文件
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doCommitFile(HttpJsonResponse json)
    {
        return (T) new Boolean(json.responstSuccess());
    }

    /**
     * 
     * @Description: 列出所有行政机构
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doListDivision(HttpJsonResponse json)
    {
        return new Gson().fromJson(json.getBodyArray(), new TypeToken<List<Division>>() {}.getType());
    }

    /**
     * 
     * @Description: 根据行政区域代码查询机构信息
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doGetServiceOrg(HttpJsonResponse json)
    {
        return (T) new Gson().fromJson(json.getBodys(), ServiceOrg.class);
    }

    /**
     * 
     * @Description: 根据行政区域代码查询机构信息
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doGetServiceOrgList(HttpJsonResponse json)
    {
        return (T) new Gson().fromJson(json.getBodyArray(), new TypeToken<List<ServiceOrg>>() {}.getType());
    }

    /**
     * 
     * @Description: 根据服务事项编码获取服务事项。
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doGetServiceItem(HttpJsonResponse json)
    {
        return (T) new Gson().fromJson(json.getBodys(), ServiceItem.class);
    }

    /**
     * 
     * @Description: 根据服务事项编码获取服务事项。
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doGetServiceItemList(HttpJsonResponse json)
    {
        return (T) new Gson().fromJson(json.getBodyArray(), new TypeToken<List<ServiceItem>>() {}.getType());
    }

    /**
     * 
     * @Description: 根据服务机构编码分页查询服务机构事项列表。
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doGetPageServiceItem(HttpJsonResponse json)
    {
        return (T) new Gson().fromJson(json.getBodys(), new TypeToken<Page>() {}.getType());
    }

    /**
     * 
     * @Description: 根据行政区划和关键字查询服务机构事项列表。
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doGetServiceItemListByKeyword(HttpJsonResponse json)
    {
        return (T) new Gson().fromJson(json.getBodyArray(), new TypeToken<List<ServiceItem>>() {}.getType());
    }

    /**
     * 
     * @Description: 根据服务机构编码和关键字分页查询服务机构事项列表。
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doGetPageServiceItemByKeyword(HttpJsonResponse json)
    {
        return (T) new Gson().fromJson(json.getBodys(), new TypeToken<Page>() {}.getType());
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
    public <T> T doGetServiceItemListByCatalog(HttpJsonResponse json)
    {
        return (T) new Gson().fromJson(json.getBodyArray(), new TypeToken<List<ServiceItem>>() {}.getType());
    }


    /**
     * 
     * @Description: 根据服务机构编码和关键字分页查询服务机构事项列表。
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doGetPageServiceItemByCatalog(HttpJsonResponse json)
    {
        return (T) new Gson().fromJson(json.getBodys(), new TypeToken<Page>() {}.getType());
    }

    /**
     * 
     * @Description: 根据事项获取事项窗口列表。
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doGetServiceItemListWindow(HttpJsonResponse json)
    {
        return (T) new Gson().fromJson(json.getBodyArray(), new TypeToken<List<ServiceWindow>>() {}.getType());
    }

    /**
     * 
     * @Description: 根据事项获取事项附件。
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doGetServiceItemListAttachment(HttpJsonResponse json)
    {
        return (T) new Gson().fromJson(json.getBodyArray(), new TypeToken<List<ServiceAttachment>>() {}.getType());
    }

    /**
     * 
     * @Description: 根据事项代码获取客户端事项配置
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doGetServiceItemClientConfig(HttpJsonResponse json)
    {
        return (T) new Gson().fromJson(json.getBodys(), new TypeToken<ClientConfig>() {}.getType());
    }

    /**
     * 
     * @Description: 根据父级分类编码列出子级事项分类列表
     * @param @param json
     * @param @return
     * @return T
     */
    private <T> T doGetServiceCatalogListCatalog(HttpJsonResponse json)
    {
        return (T) new Gson().fromJson(json.getBodyArray(), new TypeToken<List<ServiceCatalog>>() {}.getType());
    }



}
