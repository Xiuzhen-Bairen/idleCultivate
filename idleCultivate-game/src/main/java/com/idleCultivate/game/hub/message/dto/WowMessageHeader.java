package com.idleCultivate.game.hub.message.dto;

import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

@Data
public class WowMessageHeader implements Serializable {
    private String messageCode;
    private String requestTime;
    private String responseTime;
    private String version;

    public WowMessageHeader() {
        this.responseTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        this.version = "1.0";
    }

    public WowMessageHeader(String messageCode) {
        this();
        this.messageCode = messageCode;
    }
}
