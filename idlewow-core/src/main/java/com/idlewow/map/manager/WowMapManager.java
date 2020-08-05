package com.idlewow.map.manager;

import com.idlewow.common.BaseManager;
import com.idlewow.map.mapper.WowMapMapper;
import com.idlewow.map.model.WowMap;
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

