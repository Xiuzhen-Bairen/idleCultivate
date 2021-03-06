package com.idleCultivate.character.model;

import com.idleCultivate.common.model.BaseModel;
import com.idleCultivate.util.validation.annotation.Positive;
import com.idleCultivate.util.validation.annotation.Range;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class LevelExp extends BaseModel implements Serializable {
    @Range(min = 1, max = 181, field = "等级")
    private Integer level;
    @Positive(field = "经验")
    private Integer exp;
    private Integer exp_speed;
}
