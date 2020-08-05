package com.idlewow.datadict.manager;

import com.idlewow.common.BaseManager;
import com.idlewow.datadict.mapper.DataDictMapper;
import com.idlewow.datadict.model.DataDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataDictManager extends BaseManager<DataDict> {
    @Autowired
    DataDictMapper dataDictMapper;

    /**
     * 根据编码查询
     *
     * @param code 编码
     * @return
     */
    public DataDict findByCode(String code) {
        return dataDictMapper.findByCode(code);
    }

    /**
     * 根据父编码查询
     *
     * @param parentCode 父编码
     * @return
     */
    public List<DataDict> listByParentCode(String parentCode) {
        return dataDictMapper.listByParentCode(parentCode);
    }

    /**
     * 根据父编码和值查询
     *
     * @param parentCode 父编码
     * @param value      值
     * @return
     */
    public DataDict findByParentCodeAndValue(String parentCode, String value) {
        return dataDictMapper.findByParentCodeAndValue(parentCode, value);
    }
}
