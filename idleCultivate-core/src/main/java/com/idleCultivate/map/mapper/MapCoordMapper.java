package com.idleCultivate.map.mapper;

import com.idleCultivate.common.BaseMapper;
import com.idleCultivate.map.model.MapCoord;

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
