package com.idleCultivate.monster.impl;

import com.idleCultivate.common.BaseServiceImpl;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.query.model.MonsterQueryParam;
import com.idleCultivate.monster.model.Monster;
import com.idleCultivate.monster.service.MonsterService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("monsterService")
public class MonsterServiceImpl extends BaseServiceImpl<Monster, MonsterQueryParam> implements MonsterService {
    /**
     * 更新数据
     *
     * @param monster 数据对象
     * @return
     */
    @Override
    @CachePut(value = "monster", key = "#monster.getId()")
    public CommonResult update(Monster monster) {
        return super.update(monster);
    }

    /**
     * 删除数据
     *
     * @param id 主键id
     * @return
     */
    @Override
    @CacheEvict(value = "monster", key = "#id")
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
    @Cacheable(value = "monster", key = "#id")
    public CommonResult find(String id) {
        return super.find(id);
    }
}
