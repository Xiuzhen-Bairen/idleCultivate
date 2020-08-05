package com.idleCultivate.character.model;

import com.idleCultivate.common.model.BaseModel;
import com.idleCultivate.util.validation.annotation.NotBlank;
import com.idleCultivate.util.validation.annotation.Positive;
import com.idleCultivate.util.validation.annotation.Range;
import lombok.Data;

import java.io.Serializable;

/**
 * 各等级属性配置
 */
@Data
public class LevelProp extends BaseModel implements Serializable {
    @NotBlank(field = "职业")
    private String job;
    @Range(min = 1, max = 60, field = "等级")
    private Integer level;
    @Positive(field = "生命值")
    private Integer hp;
    @Positive(field = "力量")
    private Integer strength;
    @Positive(field = "敏捷")
    private Integer agility;
    @Positive(field = "耐力")
    private Integer stamina;
    @Positive(field = "智力")
    private Integer intellect;
}
