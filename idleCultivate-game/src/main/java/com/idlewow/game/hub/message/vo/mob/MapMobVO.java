package com.idlewow.game.hub.message.vo.mob;

import com.idlewow.game.hub.message.vo.common.BaseVO;
import lombok.Data;

import java.io.Serializable;

@Data
public class MapMobVO extends BaseVO implements Serializable {
    private String name;
    private String mapId;
    private String mapName;
    private String faction;
    private String mobClass;
    private String mobType;
    private Integer level;
    private Integer hp;
    private Integer damage;
    private Integer armour;
}
