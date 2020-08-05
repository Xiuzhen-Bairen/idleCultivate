package com.idleCultivate.map.model;

import com.idleCultivate.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class MapCoord extends BaseModel implements Serializable {
    private String shape;
    private String coord;
    private String fromMapId;
    private String fromMapName;
    private String destMapId;
    private String destMapName;
}
