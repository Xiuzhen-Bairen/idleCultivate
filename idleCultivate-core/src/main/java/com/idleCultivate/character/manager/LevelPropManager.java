package com.idleCultivate.character.manager;

import com.idleCultivate.character.mapper.LevelPropMapper;
import com.idleCultivate.character.model.LevelProp;
import com.idleCultivate.common.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LevelPropManager extends BaseManager<LevelProp> {
    @Autowired
    LevelPropMapper levelPropMapper;

    /**
     * 根据职业和等级查询
     *
     * @param job   职业
     * @param level 等级
     * @return
     */
    public LevelProp findByJobAndLevel(String job, Integer level) {
        return levelPropMapper.findByJobAndLevel(job, level);
    }
}