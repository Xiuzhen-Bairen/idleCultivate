package com.idlewow.game.hub.message.vo.map;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MapInfoVO implements Serializable {
    // 地图信息
    private WowMapVO map;
    // 小地图锚点信息
    private List<MapCoordVO> mapCoords;
}
