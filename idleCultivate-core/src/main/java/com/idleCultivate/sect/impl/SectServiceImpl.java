package com.idleCultivate.sect.impl;

import com.idleCultivate.common.BaseServiceImpl;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.query.model.SectQueryParam;
import com.idleCultivate.sect.model.Sect;
import com.idleCultivate.sect.service.SectService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("sectService")
public class SectServiceImpl extends BaseServiceImpl<Sect, SectQueryParam> implements SectService {
    /**
     * 更新数据
     *
     * @param sect 数据对象
     * @return
     */
    @Override
    @CachePut(value = "sect", key = "#sect.getId()")
    public CommonResult update(Sect sect) {
        return super.update(sect);
    }

    /**
     * 删除数据
     *
     * @param id 主键id
     * @return
     */
    @Override
    @CacheEvict(value = "sect", key = "#id")
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
    @Cacheable(value = "sect", key = "#id")
    public CommonResult find(String id) {
        return super.find(id);
    }
}
