package com.idlewow.game.hub.message.dto.server;

import lombok.Data;

@Data
public final class SChatMessage extends ServerMessage {
    private String sendId;
    private String sendName;
    private String recvId;
    private String recvName;
    private String message;
    private String channel;
}
