package com.guangzhou.gov.view;

import android.os.Bundle;

import com.guangzhou.gov.net.http.Http;
import com.guangzhou.gov.net.http.HttpListener;
import com.guangzhou.gov.net.http.HttpStatus;
import com.guangzhou.gov.net.tools.HttpLog;

/**
 * @author chenjianping
 */
public class Tab1Activity extends AbstractBaseActivity implements HttpListener {
    Http mHttp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mOnlineAffairsView = new OnlineAffairsView(this);
        setContentView(mOnlineAffairsView);
        mHttp = new Http(this);
        mHttp.setHttpListener(this);
//         // mHttp.doListDivision("pMessage");
        // mHttp.doGetServiceOrg("pMessage", "000000027");
        // mHttp.doGetServiceOrgList("pMessage", "445302");
        // mHttp.doGetServiceItem("pMessage", "19999900112345678912440000");
        // mHttp.doGetServiceItemList("pMessage", "000000027");
        // mHttp.doGetPageServiceItem("pMessage", "440000", 1, 10);
        // mHttp.doGetServiceItemListByKeyword("pMessage", "440000", "旅游");
        // mHttp.doGetPageServiceItemByKeyword("pMessage", " 建", "440000", 1, 10);
        // mHttp.doGetServiceItemListByCatalog("pMessage", "440000", "root_gr_qtfl");
        // mHttp.doGetPageServiceItemByCatalog("pMessage", "440000", "root_gr_ztfl_qt", 1, 10);
//        mHttp.doGetServiceItemListWindow("pMessage", "14800100000316304812441224"); //json返回有问题data:{[,,,]}
//        mHttp.doGetServiceItemListAttachment("pMessage", "14800100000316304812441224"); //json返回有问题data:{[,,,]}
//        mHttp.doGetServiceItemClientConfig("pMessage", "12121"); 
//        mHttp.doGetServiceCatalogListCatalog("pMessage", "root_gr"); 
        // mHttp.doCommitDoc(data.getOuterNetDataInfo());
//         mHttp.doUserLogout("pMessage");
    }

    private OnlineAffairsView mOnlineAffairsView;

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onHttpError(HttpStatus mHs)
    {
        // TODO Auto-generated method stub
        HttpLog.d(null, "onHttpError" + mHs);

    }

    @Override
    public void onSuccess(Object parent, HttpStatus mHttpStatus)
    {
        // TODO Auto-generated method stub
        HttpLog.d(null, "onSuccess parent method" + mHttpStatus.mRequestMethod);

    }

    @Override
    public void onFail(HttpStatus mHs)
    {
        // TODO Auto-generated method stub

    }


}
