package com.idlewow.mob.service;

import com.idlewow.common.service.BaseService;
import com.idlewow.mob.model.MapMob;
import com.idlewow.query.model.MapMobQueryParam;

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
