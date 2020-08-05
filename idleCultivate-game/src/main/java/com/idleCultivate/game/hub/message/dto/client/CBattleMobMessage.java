package com.idleCultivate.game.hub.message.dto.client;


import lombok.Data;

@Data
public final class CBattleMobMessage extends ClientMessage {
    private String mobId;
}
