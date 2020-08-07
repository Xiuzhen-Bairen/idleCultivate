package com.idleCultivate.skill.service.impl;

import com.idleCultivate.skill.model.Skill;
import com.idleCultivate.skill.service.SkillService;
import com.idleCultivate.common.BaseServiceImpl;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.query.model.SkillQueryParam;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("skillService")
public class SkillServiceImpl extends BaseServiceImpl<Skill, SkillQueryParam> implements SkillService {
    /**
     * 更新数据
     *
     * @param skill 数据对象
     * @return
     */
    @Override
    @CachePut(value = "skill", key = "#skill.getId()")
    public CommonResult update(Skill skill) {
        return super.update(skill);
    }

    /**
     * 删除数据
     *
     * @param id 主键id
     * @return
     */
    @Override
    @CacheEvict(value = "skill", key = "#id")
    public CommonResult delete(String id) {
        return super.delete(id);
    }

    /**
     * 根据ID查询
     *
     * @param id 主键id
     * @return
     */
    @Override
    @Cacheable(value = "skill", key = "#id")
    public CommonResult find(String id) {
        return super.find(id);
    }
}
