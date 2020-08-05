package com.idlewow.mob.mapper;

import com.idlewow.common.BaseMapper;
import com.idlewow.mob.model.MapMob;

import java.util.List;

public interface MapMobMapper extends BaseMapper<MapMob> {
    /**
     * 根据地图ID查询列表
     *
     * @param mapId 地图ID
     * @return
     */
    List<MapMob> listByMapId(String mapId);
}
