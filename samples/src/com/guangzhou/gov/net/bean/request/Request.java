package com.guangzhou.gov.net.bean.request;

import com.guangzhou.gov.net.bean.BaseBean;

public class Request extends BaseBean {
    private BaseBean OuterNetDataInfo;

    public BaseBean getOuterNetDataInfo()
    {
        return OuterNetDataInfo;
    }

    public void setOuterNetDataInfo(BaseBean outerNetDataInfo)
    {
        OuterNetDataInfo = outerNetDataInfo;
    }

}
