package com.idlewow.game.hub.message.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WowMessage<T extends WowMessageContent> implements Serializable {
    private WowMessageHeader header;
    private T content;

    public WowMessage() {
    }

    public WowMessage(WowMessageHeader header) {
        this.header = header;
    }

    public WowMessage(WowMessageHeader header, T content) {
        this.header = header;
        this.content = content;
    }
}
