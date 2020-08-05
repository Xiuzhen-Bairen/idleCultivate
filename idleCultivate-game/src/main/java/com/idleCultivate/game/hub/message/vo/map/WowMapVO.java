package com.idleCultivate.game.hub.message.vo.map;

import com.idleCultivate.game.hub.message.vo.common.BaseVO;
import lombok.Data;

import java.io.Serializable;

@Data
public class WowMapVO extends BaseVO implements Serializable {
    private String name;
    private String occupy;
    private String description;
}
