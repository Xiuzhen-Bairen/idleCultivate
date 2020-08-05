package com.idleCultivate.datadict.service;

import com.idleCultivate.common.service.BaseService;
import com.idleCultivate.datadict.model.DataDict;
import com.idleCultivate.query.model.DataDictQueryParam;

import java.util.List;

public interface DataDictService extends BaseService<DataDict, DataDictQueryParam> {
    /**
     * 根据编码查询
     *
     * @param code 编码
     * @return
     */
    DataDict findByCode(String code);

    /**
     * 根据父编码查询
     *
     * @param parentCode 父编码
     * @return
     */
    List<DataDict> listByParentCode(String parentCode);

    /**
     * 根据父编码和值查询
     *
     * @param parentCode 父编码
     * @param value      值
     * @return
     */
    DataDict findByParentCodeAndValue(String parentCode, String value);
}
