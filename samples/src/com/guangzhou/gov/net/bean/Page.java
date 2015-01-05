package com.guangzhou.gov.net.bean;

import java.util.ArrayList;
import java.util.List;


public class Page extends BaseBean {

    private static final long serialVersionUID = 8551163312384913617L;
    private PageContext context;
    private int index;
    private boolean hasPre;
    private boolean hasNext;
    public boolean isHasNext()
    {
        return hasNext;
    }

    public void setHasNext(boolean hasNext)
    {
        this.hasNext = hasNext;
    }

    private List<ServiceItem> items = new ArrayList<ServiceItem>();

    public PageContext getContext()
    {
        return context;
    }

    public void setContext(PageContext context)
    {
        this.context = context;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public boolean isHasPre()
    {
        return hasPre;
    }

    public void setHasPre(boolean hasPre)
    {
        this.hasPre = hasPre;
    }

    public List<ServiceItem> getItems()
    {
        return items;
    }

    public void setItems(List<ServiceItem> items)
    {
        this.items = items;
    }



}
