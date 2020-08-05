package com.idleCultivate.game.hub.message.dto.server;

import com.idleCultivate.game.hub.message.vo.map.MapInfoVO;
import lombok.Data;

@Data
public final class SLoadMapMessage extends ServerMessage {
    private MapInfoVO mapInfo;
}
