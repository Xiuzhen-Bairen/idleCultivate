package com.idleCultivate.mob.service.impl;

import com.idleCultivate.common.BaseServiceImpl;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.mob.manager.MapMobManager;
import com.idleCultivate.mob.model.MapMob;
import com.idleCultivate.mob.service.MapMobService;
import com.idleCultivate.query.model.MapMobQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mapMobService")
public class MapMobServiceImpl extends BaseServiceImpl<MapMob, MapMobQueryParam> implements MapMobService {
    @Autowired
    MapMobManager mapMobManager;

    /**
     * 更新数据
     *
     * @param mapMob 数据对象
     * @return
     */
    @Override
    @CachePut(value = "mapMob", key = "#mapMob.getId()")
    public CommonResult update(MapMob mapMob) {
        return super.update(mapMob);
    }

    /**
     * 删除数据
     *
     * @param id 主键id
     * @return
     */
    @Override
    @CacheEvict(value = "mapMob", key = "#id")
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
    @Cacheable(value = "mapMob", key = "#id")
    public CommonResult find(String id) {
        return super.find(id);
    }

    /**
     * 根据地图ID查询列表
     *
     * @param mapId 地图ID
     * @return
     */
    @Override
    @Cacheable(value = "mapMobList", key = "#mapId", unless = "#reuslt==null")
    public List<MapMob> listByMapId(String mapId) {
        try {
            return mapMobManager.listByMapId(mapId);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }
}
