package com.idlewow.datadict.model;

import java.io.Serializable;

public enum DataType implements Serializable {
    Occupy("10100", "领土归属"),
    Faction("10110", "阵营"),
    Race("10200", "种族"),
    Job("10250", "职业"),
    MobType("10300", "怪物类型"),
    MobClass("10310", "怪物种类");

    private String code;
    private String value;

    DataType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
