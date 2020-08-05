package com.idleCultivate.map.service.impl;

import com.idleCultivate.common.BaseServiceImpl;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.map.manager.MapCoordManager;
import com.idleCultivate.map.model.MapCoord;
import com.idleCultivate.map.service.MapCoordService;
import com.idleCultivate.query.model.MapCoordQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mapCoordService")
public class MapCoordServiceImpl extends BaseServiceImpl<MapCoord, MapCoordQueryParam> implements MapCoordService {
    @Autowired
    MapCoordManager mapCoordManager;

    /**
     * 更新数据
     *
     * @param mapCoord 数据对象
     * @return
     */
    @Override
    @CachePut(value = "mapCoord", key = "#mapCoord.getId()")
    public CommonResult update(MapCoord mapCoord) {
        return super.update(mapCoord);
    }

    /**
     * 删除数据
     *
     * @param id 主键id
     * @return
     */
    @Override
    @CacheEvict(value = "mapCoord", key = "#id")
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
    @Cacheable(value = "mapCoord", key = "#id")
    public CommonResult find(String id) {
        return super.find(id);
    }

    /**
     * 根据起始地图ID查询
     *
     * @param mapId 地图ID
     * @return
     */
    @Override
    @Cacheable(value = "mapCoordList", key = "#mapId")
    public List<MapCoord> listByFromMapId(String mapId) {
        return mapCoordManager.listByFromMapId(mapId);
    }
}
