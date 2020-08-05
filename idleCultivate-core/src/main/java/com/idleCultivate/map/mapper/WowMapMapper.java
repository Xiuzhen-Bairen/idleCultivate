package com.idleCultivate.map.mapper;

import com.idleCultivate.common.BaseMapper;
import com.idleCultivate.map.model.WowMap;

public interface WowMapMapper extends BaseMapper<WowMap> {
    WowMap findByName(String name);
}
