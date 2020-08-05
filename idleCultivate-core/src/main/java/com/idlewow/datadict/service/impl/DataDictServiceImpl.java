package com.idlewow.datadict.service.impl;

import com.idlewow.common.BaseServiceImpl;
import com.idlewow.common.model.CommonResult;
import com.idlewow.datadict.manager.DataDictManager;
import com.idlewow.datadict.model.DataDict;
import com.idlewow.datadict.service.DataDictService;
import com.idlewow.query.model.DataDictQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dataDictService")
public class DataDictServiceImpl extends BaseServiceImpl<DataDict, DataDictQueryParam> implements DataDictService {
    @Autowired
    DataDictManager dataDictManager;

    /**
     * 更新数据
     *
     * @param dataDict 数据对象
     * @return
     */
    @Override
    @CacheEvict(value = "dataDict", allEntries = true)
    public CommonResult update(DataDict dataDict) {
        return super.update(dataDict);
    }

    /**
     * 删除数据
     *
     * @param id 主键id
     * @return
     */
    @Override
    @CacheEvict(value = "dataDict", allEntries = true)
    public CommonResult delete(String id) {
        return super.delete(id);
    }

    /**
     * 根据ID查询
     *
     * @param id 主键id
     * @return
     */
    @Override
    @Cacheable(value = "dataDict", key = "#id", unless = "#result == null")
    public CommonResult find(String id) {
        return super.find(id);
    }

    /**
     * 根据编码查询
     *
     * @param code 编码
     * @return
     */
    @Override
    @Cacheable(value = "dataDict", key = "#code", unless = "#result == null")
    public DataDict findByCode(String code) {
        try {
            return dataDictManager.findByCode(code);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * 根据父编码查询
     *
     * @param parentCode 父编码
     * @return
     */
    @Override
    public List<DataDict> listByParentCode(String parentCode) {
        try {
            return dataDictManager.listByParentCode(parentCode);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * 根据父编码和值查询
     *
     * @param parentCode 父编码
     * @param value      值
     * @return
     */
    @Override
    @Cacheable(value = "dataDict", key = "T(String).valueOf(#parentCode).concat('-').concat(#value)", unless = "#result == null")
    public DataDict findByParentCodeAndValue(String parentCode, String value) {
        try {
            return dataDictManager.findByParentCodeAndValue(parentCode, value);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }
}
