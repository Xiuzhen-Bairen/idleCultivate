package com.idleCultivate.map.service;

import com.idleCultivate.common.service.BaseService;
import com.idleCultivate.map.model.MapCoord;
import com.idleCultivate.query.model.MapCoordQueryParam;

import java.util.List;

public interface MapCoordService extends BaseService<MapCoord, MapCoordQueryParam> {
    /**
     * 根据起始地图ID查询
     *
     * @param mapId 地图ID
     * @return
     */
    List<MapCoord> listByFromMapId(String mapId);
}
