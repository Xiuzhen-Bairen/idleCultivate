package com.idlewow.character.model;

import com.idlewow.common.model.BaseModel;
import com.idlewow.util.validation.annotation.Positive;
import com.idlewow.util.validation.annotation.Range;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class LevelExp extends BaseModel implements Serializable {
    @Range(min = 1, max = 59, field = "等级")
    private Integer level;
    @Positive(field = "经验")
    private Integer exp;
}
