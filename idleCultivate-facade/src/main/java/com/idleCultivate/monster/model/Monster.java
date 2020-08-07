package com.idleCultivate.monster.model;

import com.idleCultivate.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class Monster extends BaseModel implements Serializable {
    private Integer mapId;
    private Integer mapName;
    private String name;
    private Integer level;
    private Integer race;
    private Integer job;
    private Integer att;
    private Integer def;
    private Integer hp;
    private Integer crit;
    private Integer dodge;
    private Integer coordinate;
    private Boolean is_hide;
    private String extra_info;
}

