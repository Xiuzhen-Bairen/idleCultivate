package com.idleCultivate.character.service;

import com.idleCultivate.character.model.LevelProp;
import com.idleCultivate.common.service.BaseService;
import com.idleCultivate.query.model.LevelPropQueryParam;

public interface LevelPropService extends BaseService<LevelProp, LevelPropQueryParam> {
    LevelProp findByJobAndLevel(String job, Integer level);
}
