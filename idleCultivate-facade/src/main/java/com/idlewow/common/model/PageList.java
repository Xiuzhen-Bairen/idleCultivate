package com.idlewow.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页列表
 *
 * @param <T> 分页对象
 */
@Data
public class PageList<T> implements Serializable {
    /**
     * 当前页数
     */
    private int pageIndex;

    /**
     * 每页条数
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private int totalCount;

    /**
     * 数据列表
     */
    private List<T> rows;

    public void setPageParam(PageParam pageParam) {
        if (pageParam == null) {
            this.pageIndex = 0;
            this.pageSize = 0;
        } else {
            this.pageIndex = pageParam.getPageIndex();
            this.pageSize = pageParam.getPageSize();
        }
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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    /**
     * 总页数
     *
     * @return
     */
    public int getTotalPage() {
        return (int) Math.ceil(this.totalCount * 1.0 / this.pageSize);
    }

    /**
     * 下一页
     *
     * @return
     */
    public int getNextPage() {
        return this.pageIndex < this.getTotalPage() ? this.pageIndex + 1 : this.pageIndex;
    }

    /**
     * 上一页
     *
     * @return
     */
    public int getPrevPage() {
        return this.pageIndex > 1 ? this.pageIndex - 1 : 1;
    }

    /**
     * 是否有下一页
     *
     * @return
     */
    public boolean hasNextPage() {
        return this.pageIndex < this.getTotalPage();
    }

    /**
     * 是否有上一页
     *
     * @return
     */
    public boolean hasPrevPage() {
        return this.pageIndex > 1;
    }
}