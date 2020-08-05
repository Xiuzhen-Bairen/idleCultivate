package com.idleCultivate.common.service;

import com.idleCultivate.common.model.BaseModel;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.common.model.QueryParam;

import java.util.List;

public interface BaseService<T extends BaseModel, Q extends QueryParam> {
    /**
     * 添加记录
     *
     * @param t
     */
    CommonResult insert(T t);

    /**
     * 批量添加记录
     *
     * @param list
     * @return
     */
    CommonResult batchInsert(List<T> list);

    /**
     * 更新记录
     *
     * @param t
     */
    CommonResult update(T t);

    /**
     * 删除记录
     *
     * @param id
     */
    CommonResult delete(String id);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    CommonResult find(String id);

    /**
     * 根据条件查询列表
     *
     * @param q
     * @return
     */
    CommonResult list(Q q);
}
