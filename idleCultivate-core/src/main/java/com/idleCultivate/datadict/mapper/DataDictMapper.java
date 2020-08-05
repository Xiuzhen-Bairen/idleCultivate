package com.idleCultivate.datadict.mapper;

import com.idleCultivate.common.BaseMapper;
import com.idleCultivate.datadict.model.DataDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataDictMapper extends BaseMapper<DataDict> {
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
     * @param parentCode 父编码
     * @param value 值
     * @return
     */
    DataDict findByParentCodeAndValue(@Param("parentCode") String parentCode, @Param("value") String value);
}
