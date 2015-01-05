package com.guangzhou.gov.net.bean;

public class PageContext extends BaseBean {

    private static final long serialVersionUID = -6640134076611572601L;
    private int total;
    private int pageCount;
    private int pageSize;

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }



}
