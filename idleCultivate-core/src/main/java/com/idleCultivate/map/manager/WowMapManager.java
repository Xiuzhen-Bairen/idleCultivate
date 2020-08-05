package com.idleCultivate.map.manager;

import com.idleCultivate.common.BaseManager;
import com.idleCultivate.map.mapper.WowMapMapper;
import com.idleCultivate.map.model.WowMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WowMapManager extends BaseManager<WowMap> {
    @Autowired
    WowMapMapper wowMapMapper;

    public WowMap findByName(String name) {
        return wowMapMapper.findByName(name);
    }
}

