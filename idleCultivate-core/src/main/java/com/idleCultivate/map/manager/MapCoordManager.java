package com.idleCultivate.map.manager;

import com.idleCultivate.common.BaseManager;
import com.idleCultivate.map.mapper.MapCoordMapper;
import com.idleCultivate.map.model.MapCoord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapCoordManager extends BaseManager<MapCoord> {
    @Autowired
    MapCoordMapper mapCoordMapper;

    /**
     * 根据起始地图ID查询
     *
     * @param mapId 地图ID
     * @return
     */
    public List<MapCoord> listByFromMapId(String mapId) {
        return mapCoordMapper.listByFromMapId(mapId);
    }
}
