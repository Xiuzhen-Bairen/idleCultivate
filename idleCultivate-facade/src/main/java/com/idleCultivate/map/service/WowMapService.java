package com.idleCultivate.map.service;

import com.idleCultivate.common.service.BaseService;
import com.idleCultivate.map.model.WowMap;
import com.idleCultivate.query.model.WowMapQueryParam;

public interface WowMapService extends BaseService<WowMap, WowMapQueryParam> {
    WowMap findByName(String name);
}
