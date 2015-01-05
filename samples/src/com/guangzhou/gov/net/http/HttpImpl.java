package com.guangzhou.gov.net.http;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.guangzhou.gov.GovApplication;
import com.guangzhou.gov.manager.Object2Request;
import com.guangzhou.gov.manager.UserManager;
import com.guangzhou.gov.net.bean.ApplyAttach;
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
import com.guangzhou.gov.net.bean.request.Request;
import com.guangzhou.gov.net.http.HttpListener.HttpCallable;
import com.guangzhou.gov.net.http.HttpListener.HttpCallback;
import com.guangzhou.gov.net.parsers.JsonTransformer;
import com.guangzhou.gov.net.tools.HttpAsyncTask;
import com.guangzhou.gov.net.tools.HttpLog;
import com.guangzhou.gov.net.view.DialogManager;

/**
 * 
 * @ClassName: HttpImpl
 * @Description: http 实现
 * @author chenjianping
 * @date 2014-11-13
 * 
 */
public class HttpImpl {


    private static ExecutorService SINGLE_TASK_EXECUTOR;
    static {
        SINGLE_TASK_EXECUTOR = (ExecutorService) Executors.newFixedThreadPool(3);
    };

    private Context mContext;
    private HttpListener mListener;
    private HttpStatus mHttpStatus;

    public HttpImpl(Context context, HttpListener listener) {
        this.mContext = context;
        this.mHttpStatus = new HttpStatus();
        this.mListener = listener;
    }

    /**
     * 网络是否可用
     * 
     * @return
     */
    private boolean isNetworkAvailable()
    {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) GovApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        } catch (Exception e) {
            HttpLog.e(null, "isNetworkAvailable " + e.toString());
        }
        return false;
    }


    private void invokeMethod(Object parent)
    {
        if (mHttpStatus.mStatus == HttpStatus.STATUS_NET_ERROE) {
            mListener.onHttpError(mHttpStatus);
            HttpLog.d(null, "invokeMethod HttpStatus.STATUS_NET_ERROE");
        } else {
            if (parent == null) {
                mHttpStatus.mStatus = HttpStatus.STATUS_FAIL;
                HttpLog.d(null, "invokeMethod HttpStatus.STATUS_FAIL");
                mListener.onFail(mHttpStatus);
            } else {
                mHttpStatus.mStatus = HttpStatus.STATUS_SUCCESS;
                HttpLog.d(null, "invokeMethod HttpStatus.STATUS_SUCCESS");
                mListener.onSuccess(parent, mHttpStatus);
            }

        }
    }

    /**
     * 子线程发起请求
     * 
     * @param @param method
     * @param @param pMessage
     * @param @param pHttpCallable
     * @param @param pCallback
     * @return void
     */
    private <T> void doAsync(final String method, final CharSequence pMessage, final HttpCallable<T> pHttpCallable, final HttpCallback<T> pCallback)
    {
        mHttpStatus = new HttpStatus();
        mHttpStatus.mRequestMethod = method;
        if (!isNetworkAvailable()) {
            mHttpStatus.mStatus = HttpStatus.STATUS_NET_ERROE;
            invokeMethod(mHttpStatus);
        } else {
            new HttpAsyncTask<HttpStatus, Void, T>() {
                private Dialog dialog;

                public void onPreExecute()
                {
                    if (pMessage != null) {
                        try {
                            dialog = DialogManager.getProgressMsgDialog(mContext, pMessage.toString());
                            dialog.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    super.onPreExecute();
                }

                public T doInBackground(final HttpStatus... params)
                {
                    HttpLog.d(null, "doAsync doInBackground() ");
                    try {
                        return pHttpCallable.onCall((HttpStatus) params[0]);
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                protected void onCancelled()
                {

                };

                public void onPostExecute(final T result)
                {
                    HttpLog.d(null, "doAsync onPostExecute() result = " + result);
                    try {
                        if (pCallback != null) pCallback.onCallback(result);
                    } catch (final Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (this.dialog != null) this.dialog.dismiss();
                    }
                    super.onPostExecute(result);
                }
            }.executeOnExecutor(SINGLE_TASK_EXECUTOR, mHttpStatus);
        }
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
        doAsync("doUserLogin", dialogMeg, new HttpCallable<Userlogin>() {
            @Override
            public Userlogin onCall(HttpStatus httpstatus) throws Exception
            {
                return new HttpInternal<Userlogin>(JsonTransformer.getInstance()).doPostString(mContext, UrlConfig.POST_URL_USER_Login, new Gson().toJson(Object2Request.split2LoginReq(name, pwd)),
                        httpstatus);
            }
        }, new HttpCallback<Userlogin>() {
            public void onCallback(Userlogin result)
            {
                invokeMethod(result);
            };
        });
    }

    /**
     * 
     * @Description: 用户登出接口
     * @param @param dialogMeg dialog描述（加载中...)
     * @param @param name 用户名
     * @param @param password 密码
     * @return void
     * @author chenjianping
     */
    public void doUserLogout(final String dialogMeg, final String name)
    {
        doAsync("doUserLogout", dialogMeg, new HttpCallable<Boolean>() {
            @Override
            public Boolean onCall(HttpStatus httpstatus) throws Exception
            {
                return new HttpInternal<Boolean>(JsonTransformer.getInstance()).doPostString(mContext, UrlConfig.POST_URL_USER_Logout, new Gson().toJson(Object2Request.split2LogoutReq(name)),
                        httpstatus);
            }
        }, new HttpCallback<Boolean>() {
            public void onCallback(Boolean result)
            {
                invokeMethod(result);
            };
        });
    }


    /**
     * 提交办事资料 文档部分
     * 
     * @desc 上传表单信息，获取流水号
     * @param @param dialogMeg dialog描述（加载中...)
     * @param @param approveItem
     * @param @param cust_name 企业名称
     * @param @param cust_addr 企业地址
     * @param @param company_type 企业类型
     * @param @param jbr 经办人
     * @param @param mobile 手机
     * @param @param phone 座机
     * @param @param prj_name 项目名称
     * @return @param void
     * @author chenjianping
     * @request post
     * @login 是
     */
    public void doCommitDoc(final String dialogMeg, final String approveItem, final String cust_name, final String cust_addr, final String company_type, final String jbr, final String mobile, final String phone,
            final String prj_name)
    {
        doAsync("doCommitDoc", dialogMeg, new HttpCallable<BaseBean>() {
            @Override
            public BaseBean onCall(HttpStatus httpstatus) throws Exception
            {
                return new HttpInternal<ApplyAttach>(JsonTransformer.getInstance()).doPostString(mContext,
                        UrlConfig.POST_URL_onlineApply + "?acess_token=" + UserManager.getInstance().mUser.getAcess_token(),
                        new Gson().toJson(Object2Request.split2commitDoc(approveItem, cust_name, cust_addr, company_type, jbr, mobile, phone, prj_name)), httpstatus);
            }
        }, new HttpCallback<BaseBean>() {
            @Override
            public void onCallback(BaseBean result)
            {
                invokeMethod(result);
            }
        });
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


        doAsync("doCommitFile", dialogMeg, new HttpCallable<BaseBean>() {
            @Override
            public BaseBean onCall(HttpStatus httpstatus) throws Exception
            {
                BaseBean mBean = new BaseBean();
                boolean success = true;
                // 发起上传图片
                int i = 0;
                for (String file : files) {
                    File f = new File(file.trim());
                    // File f = RWFileUtil.readLogFile();
                    if (f != null && f.canRead()) {
                        HashMap<String, Object> params = new HashMap<String, Object>();

                        Request r = Object2Request.split2commitFile(f.getName(), file, controlSeq, appove_item_stuff_id[i][1]);
                        params.put("applyAttach", new Gson().toJson(r));
                        params.put("fileContent", f);

                        if (!new HttpInternal<Boolean>(JsonTransformer.getInstance()).doPostStream(mContext,
                                UrlConfig.POST_URL_uploadApplyAttach + "?acess_token=" + UserManager.getInstance().mUser.getAcess_token(), params, httpstatus)) {
                            success = false;
                            break;
                        }
                    } else {
                        success = false;
                        break;
                    }
                    i++;
                }
                if (success) {
                    if (new HttpInternal<Boolean>(JsonTransformer.getInstance()).doPostString(mContext,
                            UrlConfig.POST_URL_onlineApply + "?acess_token=" + UserManager.getInstance().mUser.getAcess_token(), new Gson().toJson(Object2Request.split2commitConfirm(controlSeq, appove_item_stuff_id[0][0])),
                            httpstatus)) {
                        mBean.setControlSeq(controlSeq);
                    }

                }
                return mBean;
            }
        }, new HttpCallback<BaseBean>() {
            @Override
            public void onCallback(BaseBean result)
            {
                invokeMethod(result);
            }
        });
    }


    /**
     * @Description: 列出所有的行政区划
     * @param @param dialogMeg dialog描述（加载中...)
     * @return void
     * @login 是／否
     */
    public void doListDivision(final String pMessage)
    {
        doAsync("doListDivision", pMessage, new HttpCallable<List<Division>>() {
            @Override
            public List<Division> onCall(HttpStatus mHs) throws Exception
            {
                return new HttpInternal<List<Division>>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_listDivision, mHs);
            }
        }, new HttpCallback<List<Division>>() {
            public void onCallback(List<Division> result)
            {
                invokeMethod(result);
            };
        });
    }

    /**
     * 
     * @Description: 根据行政区域代码查询机构信息
     * @param @param pMessage
     * @param @param orgCode
     * @return void
     */
    public void doGetServiceOrg(final String pMessage, final String orgCode)
    {

        doAsync("doGetServiceOrg", pMessage, new HttpCallable<ServiceOrg>() {
            @Override
            public ServiceOrg onCall(HttpStatus mHs) throws Exception
            {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("orgCode", orgCode);
                return new HttpInternal<ServiceOrg>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_serviceOrg, params, mHs);
            }
        }, new HttpCallback<ServiceOrg>() {
            public void onCallback(ServiceOrg result)
            {
                invokeMethod(result);
            };
        });

    }

    /**
     * 
     * @Description: 根据行政区域代码查询机构信息
     * @param @param pMessage
     * @param @param orgCode
     * @return void
     */
    public void doGetServiceOrgList(final String pMessage, final String divisionCode)
    {

        doAsync("doGetServiceOrgList", pMessage, new HttpCallable<List<ServiceOrg>>() {
            @Override
            public List<ServiceOrg> onCall(HttpStatus mHs) throws Exception
            {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("divisionCode", divisionCode);
                return new HttpInternal<List<ServiceOrg>>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_serviceOrg_list, params, mHs);
            }
        }, new HttpCallback<List<ServiceOrg>>() {
            public void onCallback(List<ServiceOrg> result)
            {
                invokeMethod(result);
            };
        });

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
    public void doGetServiceItem(final String dialogMeg, final String serviceCode)
    {
        doAsync("doGetServiceItem", dialogMeg, new HttpCallable<ServiceItem>() {
            @Override
            public ServiceItem onCall(HttpStatus s) throws Exception
            {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("serviceCode", serviceCode);
                return new HttpInternal<ServiceItem>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_ServiceItem, params, s);
            }

        }, new HttpCallback<ServiceItem>() {
            @Override
            public void onCallback(ServiceItem result)
            {
                invokeMethod(result);
            }
        });

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
    public void doGetServiceItemList(final String dialogMeg, final String orgCode)
    {
        doAsync("doGetServiceItemList", dialogMeg, new HttpCallable<List<ServiceItem>>() {
            @Override
            public List<ServiceItem> onCall(HttpStatus s) throws Exception
            {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("orgCode", orgCode);
                return new HttpInternal<List<ServiceItem>>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_ServiceItem_list, params, s);
            }

        }, new HttpCallback<List<ServiceItem>>() {
            @Override
            public void onCallback(List<ServiceItem> result)
            {
                invokeMethod(result);
            }
        });

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
        doAsync("doGetPageServiceItem", dialogMeg, new HttpCallable<Page>() {
            @Override
            public Page onCall(HttpStatus s) throws Exception
            {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("orgCode", orgCode);
                params.put("pageNo", pageNo);
                params.put("pageSize", pageSize);
                return new HttpInternal<Page>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_ServiceItem_page, params, s);
            }

        }, new HttpCallback<Page>() {
            @Override
            public void onCallback(Page result)
            {
                invokeMethod(result);
            }
        });

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
        doAsync("doGetServiceItemListByKeyword", dialogMeg, new HttpCallable<List<ServiceItem>>() {
            @Override
            public List<ServiceItem> onCall(HttpStatus s) throws Exception
            {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("divisionCode", divisionCode);
                params.put("keyword", keyword);
                return new HttpInternal<List<ServiceItem>>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_ServiceItem_list_Bykeyword, params, s);
            }

        }, new HttpCallback<List<ServiceItem>>() {
            @Override
            public void onCallback(List<ServiceItem> result)
            {
                invokeMethod(result);
            }
        });

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
        doAsync("doGetPageServiceItemByKeyword", dialogMeg, new HttpCallable<Page>() {
            @Override
            public Page onCall(HttpStatus s) throws Exception
            {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("orgCode", orgCode);
                params.put("pageNo", pageNo);
                params.put("pageSize", pageSize);
                params.put("keyword", keyword);
                return new HttpInternal<Page>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_ServiceItem_page_Bykeyword, params, s);
            }

        }, new HttpCallback<Page>() {
            @Override
            public void onCallback(Page result)
            {
                invokeMethod(result);
            }
        });

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
        doAsync("doGetServiceItemListByCatalog", dialogMeg, new HttpCallable<List<ServiceItem>>() {
            @Override
            public List<ServiceItem> onCall(HttpStatus s) throws Exception
            {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("divisionCode", divisionCode);
                params.put("catalogCode", catalogCode);
                return new HttpInternal<List<ServiceItem>>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_ServiceItem_list_ByCatalog, params, s);
            }

        }, new HttpCallback<List<ServiceItem>>() {
            @Override
            public void onCallback(List<ServiceItem> result)
            {
                invokeMethod(result);
            }
        });

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
    public void doGetPageServiceItemByCatalog(final String dialogMeg, final String catalogCode, final String divisionCode, final int pageNo, final int pageSize)
    {
        doAsync("doGetPageServiceItemByCatalog", dialogMeg, new HttpCallable<Page>() {
            @Override
            public Page onCall(HttpStatus s) throws Exception
            {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("orgCode", divisionCode);
                params.put("pageNo", pageNo);
                params.put("pageSize", pageSize);
                params.put("catalogCode", catalogCode);
                return new HttpInternal<Page>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_ServiceItem_page_ByCatalog, params, s);
            }

        }, new HttpCallback<Page>() {
            @Override
            public void onCallback(Page result)
            {
                invokeMethod(result);
            }
        });

    }

    /**
     * 
     * @Description: 根据事项获取事项窗口列表。
     * @param @param serviceCode 事项代码
     * @request get
     * @login 否
     * @return void
     */
    public void doGetServiceItemListWindow(final String dialogMeg, final String serviceCode)
    {
        doAsync("doGetServiceItemListWindow", dialogMeg, new HttpCallable<List<ServiceWindow>>() {
            @Override
            public List<ServiceWindow> onCall(HttpStatus s) throws Exception
            {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("serviceCode", serviceCode);
                return new HttpInternal<List<ServiceWindow>>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_ServiceItem_list_Window, params, s);
            }

        }, new HttpCallback<List<ServiceWindow>>() {
            @Override
            public void onCallback(List<ServiceWindow> result)
            {
                invokeMethod(result);
            }
        });

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
        doAsync("doGetServiceItemListAttachment", dialogMeg, new HttpCallable<List<ServiceAttachment>>() {
            @Override
            public List<ServiceAttachment> onCall(HttpStatus s) throws Exception
            {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("serviceCode", serviceCode);
                return new HttpInternal<List<ServiceAttachment>>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_ServiceItem_list_Attachment, params, s);
            }

        }, new HttpCallback<List<ServiceAttachment>>() {
            @Override
            public void onCallback(List<ServiceAttachment> result)
            {
                invokeMethod(result);
            }
        });

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
        doAsync("doGetServiceItemClientConfig", dialogMeg, new HttpCallable<ClientConfig>() {
            @Override
            public ClientConfig onCall(HttpStatus s) throws Exception
            {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("serviceCode", serviceCode);
                return new HttpInternal<ClientConfig>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_ServiceItem_ClientConfig, params, s);
            }

        }, new HttpCallback<ClientConfig>() {
            @Override
            public void onCallback(ClientConfig result)
            {
                invokeMethod(result);
            }
        });

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
        doAsync("doGetServiceCatalogListCatalog", dialogMeg, new HttpCallable<List<ServiceCatalog>>() {
            @Override
            public List<ServiceCatalog> onCall(HttpStatus s) throws Exception
            {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("parentCode", parentCode);
                return new HttpInternal<List<ServiceCatalog>>(JsonTransformer.getInstance()).doGet(mContext, UrlConfig.GET_URL_serviceCatalog_list_Catalog, params, s);
            }

        }, new HttpCallback<List<ServiceCatalog>>() {
            @Override
            public void onCallback(List<ServiceCatalog> result)
            {
                invokeMethod(result);
            }
        });

    }



}
