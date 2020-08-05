package com.idleCultivate.character.mapper;

import com.idleCultivate.character.model.LevelProp;
import com.idleCultivate.common.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface LevelPropMapper extends BaseMapper<LevelProp> {
    /**
     * 根据职业和等级查询
     *
     * @param job   职业
     * @param level 等级
     * @return
     */
    LevelProp findByJobAndLevel(@Param("job") String job, @Param("level") Integer level);
}

