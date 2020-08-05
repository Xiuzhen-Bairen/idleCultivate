package com.idleCultivate.common;

import com.idleCultivate.common.model.QueryParam;

import java.util.List;

public interface BaseMapper<T> {
    /**
     * 添加记录
     * @param t
     */
    int insert(T t);

    /**
     * 批量添加记录
     * @param list
     * @return
     */
    int batchInsert(List<T> list);

    /**
     * 更新记录
     * @param t
     */
    int update(T t);

    /**
     * 删除记录
     * @param id
     */
    int delete(String id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    T find(String id);

    /**
     * 根据条件查询总数
     * @param queryParam
     * @return
     */
    int count(QueryParam queryParam);

    /**
     * 根据条件查询列表
     * @param queryParam
     * @return
     */
    List<T> list(QueryParam queryParam);
}
