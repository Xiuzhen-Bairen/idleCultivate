package com.idlewow.mob.manager;

import com.idlewow.common.BaseManager;
import com.idlewow.mob.mapper.MapMobMapper;
import com.idlewow.mob.model.MapMob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapMobManager extends BaseManager<MapMob> {
    @Autowired
    MapMobMapper mapMobMapper;

    /**
     * 根据地图ID查询列表
     *
     * @param mapId 地图ID
     * @return
     */
    public List<MapMob> listByMapId(String mapId) {
        return mapMobMapper.listByMapId(mapId);
    }
}