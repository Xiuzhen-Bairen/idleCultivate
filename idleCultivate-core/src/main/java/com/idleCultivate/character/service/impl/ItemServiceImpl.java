package com.idleCultivate.character.service.impl;

import com.idleCultivate.character.model.Item;
import com.idleCultivate.character.service.ItemService;
import com.idleCultivate.common.BaseServiceImpl;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.query.model.ItemQueryParam;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("itemService")
public class ItemServiceImpl extends BaseServiceImpl<Item, ItemQueryParam> implements ItemService {
    /**
     * 更新数据
     *
     * @param item 数据对象
     * @return
     */
    @Override
    @CachePut(value = "item", key = "#item.getId()")
    public CommonResult update(Item item) {
        return super.update(item);
    }

    /**
     * 删除数据
     *
     * @param id 主键id
     * @return
     */
    @Override
    @CacheEvict(value = "item", key = "#id")
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
    @Cacheable(value = "item", key = "#id")
    public CommonResult find(String id) {
        return super.find(id);
    }
}
