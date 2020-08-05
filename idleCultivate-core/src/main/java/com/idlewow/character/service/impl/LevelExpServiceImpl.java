package com.idlewow.character.service.impl;

import com.idlewow.character.model.LevelExp;
import com.idlewow.character.service.LevelExpService;
import com.idlewow.common.BaseServiceImpl;
import com.idlewow.common.model.CommonResult;
import com.idlewow.query.model.LevelExpQueryParam;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("levelExpService")
public class LevelExpServiceImpl extends BaseServiceImpl<LevelExp, LevelExpQueryParam> implements LevelExpService {
    /**
     * 更新数据
     *
     * @param levelExp 数据对象
     * @return
     */
    @Override
    @CachePut(value = "levelExp", key = "#levelExp.getId()")
    public CommonResult update(LevelExp levelExp) {
        return super.update(levelExp);
    }

    /**
     * 删除数据
     *
     * @param id 主键id
     * @return
     */
    @Override
    @CacheEvict(value = "levelExp", key = "#id")
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
    @Cacheable(value = "levelExp", key = "#id")
    public CommonResult find(String id) {
        return super.find(id);
    }
}
