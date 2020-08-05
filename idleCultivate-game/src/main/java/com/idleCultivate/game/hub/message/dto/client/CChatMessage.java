package com.idleCultivate.game.hub.message.dto.client;

import lombok.Data;

@Data
public final class CChatMessage extends ClientMessage {
    private String recvId;
    private String recvName;
    private String message;
    private String channel;
}
