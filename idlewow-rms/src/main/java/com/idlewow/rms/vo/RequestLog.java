package com.idlewow.rms.vo;

import lombok.Data;

@Data
public class RequestLog {
    private String url;
    private String ip;
    private String type;
    private String method;
    private Object[] args;
}
