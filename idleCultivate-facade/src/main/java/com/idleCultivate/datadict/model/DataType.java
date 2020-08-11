package com.idleCultivate.datadict.model;

import java.io.Serializable;

public enum DataType implements Serializable {
    //for character
    Zone("10100", "区服"),
    //for map
    Occupy("10200", "地图归属"),
    //for monster
    Race("10300", "种族"),
    Job("10310", "职业"),
    //for item
    ItemType("10400", "物品类型"),
    //for sect
    SectType("10500", "门派类型"),
    //for skill
    SkillType("10600", "功法类型");

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
