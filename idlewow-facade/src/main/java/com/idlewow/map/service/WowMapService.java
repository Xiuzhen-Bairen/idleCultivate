package com.idlewow.map.service;

import com.idlewow.common.service.BaseService;
import com.idlewow.map.model.WowMap;
import com.idlewow.query.model.WowMapQueryParam;

public interface WowMapService extends BaseService<WowMap, WowMapQueryParam> {
    WowMap findByName(String name);
}
