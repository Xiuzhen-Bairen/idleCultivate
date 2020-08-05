package com.idlewow.query.model;

import com.idlewow.common.model.QueryParam;
import lombok.Data;

@Data
public class MapCoordQueryParam extends QueryParam {
    private String shape;
    private String fromMapId;
    private String destMapId;
}
