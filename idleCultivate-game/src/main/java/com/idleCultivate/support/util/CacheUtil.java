package com.idleCultivate.support.util;

import com.github.dozermapper.core.Mapper;
import com.idleCultivate.character.model.LevelExp;
import com.idleCultivate.character.service.LevelExpService;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.game.GameWorld;
import com.idleCultivate.game.hub.message.vo.map.WowMapVO;
import com.idleCultivate.map.model.WowMap;
import com.idleCultivate.map.service.WowMapService;
import com.idleCultivate.query.model.LevelExpQueryParam;
import com.idleCultivate.query.model.WowMapQueryParam;
import com.idleCultivate.util.redis.RedisCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CacheUtil {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    Mapper dozerMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private LevelExpService levelExpService;
    @Autowired
    private WowMapService wowMapService;

    public static Map<Integer, Integer> levelExpMap = new HashMap<>();

    public void initCache() {
        CommonResult commonResult = levelExpService.list(new LevelExpQueryParam());
        if (commonResult.isSuccess()) {
            List<LevelExp> list = (List<LevelExp>) commonResult.getData();
            for (LevelExp levelExp : list) {
                levelExpMap.put(levelExp.getLevel(), levelExp.getExp());
            }
        } else {
            logger.error("读取升级经验列表失败！" + commonResult.getMessage());
        }

        commonResult = wowMapService.list(new WowMapQueryParam());
        if (commonResult.isSuccess()) {
            List<WowMap> list = (List<WowMap>) commonResult.getData();
            for (WowMap wowMap : list) {
                WowMapVO wowMapVO = dozerMapper.map(wowMap, WowMapVO.class);
                GameWorld.MapCharacter.put(wowMapVO.getId(), new CopyOnWriteArrayList<>());
                redisCache.cache("idleCultivate:wowmap:" + wowMapVO.getId(), wowMapVO);
                redisCache.cache("idleCultivate:wowmap:" + wowMapVO.getName(), wowMapVO);
            }
        } else {
            logger.error("读取地图列表失败！" + commonResult.getMessage());
        }
    }

    /**
     * 获取等级所需经验
     * @param level
     * @return
     */
    public static Integer getLevelExp(Integer level) {
        return levelExpMap.get(level);
    }
}
