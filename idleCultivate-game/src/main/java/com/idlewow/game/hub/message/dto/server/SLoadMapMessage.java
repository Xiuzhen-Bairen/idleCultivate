package com.idlewow.game.hub.message.dto.server;

import com.idlewow.game.hub.message.vo.map.MapInfoVO;
import lombok.Data;

@Data
public final class SLoadMapMessage extends ServerMessage {
    private MapInfoVO mapInfo;
}
