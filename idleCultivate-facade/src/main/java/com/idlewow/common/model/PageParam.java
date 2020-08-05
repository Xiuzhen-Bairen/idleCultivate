package com.idlewow.common.model;

import java.io.Serializable;

/**
 * 分页参数
 */
public class PageParam implements Serializable {
    /**
     * 当前页数
     */
    private int pageIndex;

    /**
     * 每页条数
     */
    private int pageSize;

    public PageParam() {
    }

    public PageParam(int pageIndex) {
        this(pageIndex, 20);
    }

    public PageParam(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}