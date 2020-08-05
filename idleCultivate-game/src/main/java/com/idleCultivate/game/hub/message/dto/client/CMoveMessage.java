package com.idleCultivate.game.hub.message.dto.client;

import lombok.Data;

@Data
public final class CMoveMessage extends ClientMessage {
    private String destMapId;
}
