package com.idlewow.mob.model;

import com.idlewow.common.model.BaseModel;
import com.idlewow.util.validation.annotation.NotBlank;
import com.idlewow.util.validation.annotation.NotNull;
import com.idlewow.util.validation.annotation.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class MapMob extends BaseModel implements Serializable {
    @NotBlank(field = "怪物名称")
    private String name;
    @NotBlank(field = "地图id")
    private String mapId;
    @NotBlank(field = "地图名称")
    private String mapName;
    @NotBlank(field = "阵营")
    private String faction;
    @NotBlank(field = "怪物种类")
    private String mobClass;
    @NotBlank(field = "怪物类型")
    private String mobType;
    @Positive(field = "等级")
    private Integer level;
    @Positive(field = "生命值")
    private Integer hp;
    @Positive(field = "伤害")
    private Integer damage;
    @Positive(field = "护甲")
    private Integer armour;
}
