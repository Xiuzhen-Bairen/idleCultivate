package com.idleCultivate.character.model;

import com.idleCultivate.common.model.BaseModel;
import com.idleCultivate.util.validation.annotation.Positive;
import com.idleCultivate.util.validation.annotation.Range;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class Item extends BaseModel implements Serializable {

    private String name;
    private String type;
    private Integer level;
    private Integer value;
    private String description;
    private Boolean pile;
    private Integer max_count;
    private Boolean sell;
}
