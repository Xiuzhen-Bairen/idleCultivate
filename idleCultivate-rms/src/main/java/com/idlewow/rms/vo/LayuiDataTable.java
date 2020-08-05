package com.idlewow.rms.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LayuiDataTable<T> implements Serializable {
    private Integer code;
    private String message;
    private Integer count;
    private List<T> data;
}
