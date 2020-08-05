package com.idlewow.map.service;

import com.idlewow.common.service.BaseService;
import com.idlewow.map.model.MapCoord;
import com.idlewow.query.model.MapCoordQueryParam;

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
