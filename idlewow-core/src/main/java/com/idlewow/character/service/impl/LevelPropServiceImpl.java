package com.idlewow.character.service.impl;

import com.idlewow.character.manager.LevelPropManager;
import com.idlewow.character.model.LevelProp;
import com.idlewow.character.service.LevelPropService;
import com.idlewow.common.BaseServiceImpl;
import com.idlewow.common.model.CommonResult;
import com.idlewow.query.model.LevelPropQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("levelPropService")
public class LevelPropServiceImpl extends BaseServiceImpl<LevelProp, LevelPropQueryParam> implements LevelPropService {
    @Autowired
    LevelPropManager levelPropManager;

    /**
     * 更新数据
     *
     * @param levelProp 数据对象
     * @return
     */
    @Override
    @CachePut(value = "levelProp", key = "#levelProp.getId()")
    public CommonResult update(LevelProp levelProp) {
        return super.update(levelProp);
    }

    /**
     * 删除数据
     *
     * @param id 主键id
     * @return
     */
    @Override
    @CacheEvict(value = "levelProp", key = "#id")
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
    @Cacheable(value = "levelProp", key = "#id")
    public CommonResult find(String id) {
        return super.find(id);
    }

    @Override
    @Cacheable(value = "levelProp", key = "T(String).valueOf(#job).concat('-').concat(#level)", unless = "#result == null")
    public LevelProp findByJobAndLevel(String job, Integer level) {
        try {
            return levelPropManager.findByJobAndLevel(job, level);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }
}
