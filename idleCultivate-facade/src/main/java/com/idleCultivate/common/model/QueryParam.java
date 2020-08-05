package com.idleCultivate.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryParam implements Serializable {
    private PageParam pageParam;

    public void setPage(int pageIndex, int pageSize) {
        this.pageParam = new PageParam();
        this.pageParam.setPageIndex(pageIndex);
        this.pageParam.setPageSize(pageSize);
    }
}
