package com.idlewow.game.hub.message.dto.server;

import lombok.Data;

import java.util.Map;

@Data
public final class SLoadCacheMessage extends ServerMessage {
    private Map<String, Integer> levelExpMap;
}
