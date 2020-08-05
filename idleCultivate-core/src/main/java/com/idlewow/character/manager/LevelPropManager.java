package com.idlewow.character.manager;

import com.idlewow.character.mapper.LevelPropMapper;
import com.idlewow.character.model.LevelProp;
import com.idlewow.common.BaseManager;
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