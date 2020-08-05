package com.idleCultivate.query.model;

import com.idleCultivate.common.model.QueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MapMobQueryParam extends QueryParam {
    private String name;
    private String mapId;
    private String mapName;
    private Integer faction;
    private Integer mobClass;
    private Integer mobType;
    private Integer levelStart;
    private Integer levelEnd;
}
