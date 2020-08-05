package com.idleCultivate.map.service.impl;

import com.idleCultivate.common.BaseServiceImpl;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.map.manager.WowMapManager;
import com.idleCultivate.map.model.WowMap;
import com.idleCultivate.map.service.WowMapService;
import com.idleCultivate.query.model.WowMapQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("wowMapService")
public class WowMapServiceImpl extends BaseServiceImpl<WowMap, WowMapQueryParam> implements WowMapService {
    @Autowired
    WowMapManager wowMapManager;

    /**
     * 更新数据
     *
     * @param wowMap 数据对象
     * @return
     */
    @Override
    @CacheEvict(value = "wowMap", key = "#id", allEntries = true)
    public CommonResult update(WowMap wowMap) {
        return super.update(wowMap);
    }

    /**
     * 删除数据
     *
     * @param id 主键id
     * @return
     */
    @Override
    @CacheEvict(value = "wowMap", key = "#id", allEntries = true)
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
    @Cacheable(value = "wowMap", key = "#id")
    public CommonResult find(String id) {
        return super.find(id);
    }

    /**
     * 根据地图名称查询
     *
     * @param name 地图名称
     * @return
     */
    @Override
    @Cacheable(value = "wowMap", key = "#name")
    public WowMap findByName(String name) {
        WowMap wowMap = wowMapManager.findByName(name);
        return wowMap;
    }
}
