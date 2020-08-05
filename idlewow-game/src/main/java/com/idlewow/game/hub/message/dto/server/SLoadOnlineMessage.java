package com.idlewow.game.hub.message.dto.server;

import com.idlewow.game.hub.message.vo.OnlineInfoVO;
import lombok.Data;

@Data
public final class SLoadOnlineMessage extends ServerMessage {
    private OnlineInfoVO onlineInfo;
}
