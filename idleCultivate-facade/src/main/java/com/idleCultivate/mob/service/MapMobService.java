package com.idleCultivate.mob.service;

import com.idleCultivate.common.service.BaseService;
import com.idleCultivate.mob.model.MapMob;
import com.idleCultivate.query.model.MapMobQueryParam;

import java.util.List;

public interface MapMobService extends BaseService<MapMob, MapMobQueryParam> {
    /**
     * 根据地图ID查询列表
     *
     * @param mapId 地图ID
     * @return
     */
    List<MapMob> listByMapId(String mapId);
}
