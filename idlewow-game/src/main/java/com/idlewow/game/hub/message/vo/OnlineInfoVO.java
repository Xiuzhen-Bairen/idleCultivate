package com.idlewow.game.hub.message.vo;

import com.idlewow.game.hub.message.vo.character.CharacterVO;
import com.idlewow.game.hub.message.vo.mob.MapMobVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OnlineInfoVO implements Serializable {
    // 地图ID
    private String mapId;
    // 在线怪物列表
    private List<MapMobVO> mapMobList;
    // 在线玩家列表
    private List<CharacterVO> mapCharacterList;
}
