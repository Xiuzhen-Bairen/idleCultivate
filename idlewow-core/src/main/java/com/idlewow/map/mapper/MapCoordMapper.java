package com.idlewow.map.mapper;

import com.idlewow.common.BaseMapper;
import com.idlewow.map.model.MapCoord;

import java.util.List;

public interface MapCoordMapper extends BaseMapper<MapCoord> {
    /**
     * 根据起始地图ID查询
     *
     * @param mapId 地图ID
     * @return
     */
    List<MapCoord> listByFromMapId(String mapId);
}
