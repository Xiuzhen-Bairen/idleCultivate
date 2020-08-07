package com.idleCultivate.skill.model;

import com.idleCultivate.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class Skill extends BaseModel implements Serializable {

    private String name;
    private String type;
    private Integer level;
    private Integer value;
}

