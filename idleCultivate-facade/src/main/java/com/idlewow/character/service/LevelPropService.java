package com.idlewow.character.service;

import com.idlewow.character.model.LevelProp;
import com.idlewow.common.service.BaseService;
import com.idlewow.query.model.LevelPropQueryParam;

public interface LevelPropService extends BaseService<LevelProp, LevelPropQueryParam> {
    LevelProp findByJobAndLevel(String job, Integer level);
}
