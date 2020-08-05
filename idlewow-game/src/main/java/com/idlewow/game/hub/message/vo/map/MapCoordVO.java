package com.idlewow.game.hub.message.vo.map;

import com.idlewow.game.hub.message.vo.common.BaseVO;
import lombok.Data;

import java.io.Serializable;

@Data
public class MapCoordVO extends BaseVO implements Serializable {
    private String shape;
    private String coord;
    private String fromMapId;
    private String fromMapName;
    private String destMapId;
    private String destMapName;
}
