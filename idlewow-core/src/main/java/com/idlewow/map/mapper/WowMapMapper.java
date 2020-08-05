package com.idlewow.map.mapper;

import com.idlewow.common.BaseMapper;
import com.idlewow.map.model.WowMap;

public interface WowMapMapper extends BaseMapper<WowMap> {
    WowMap findByName(String name);
}
