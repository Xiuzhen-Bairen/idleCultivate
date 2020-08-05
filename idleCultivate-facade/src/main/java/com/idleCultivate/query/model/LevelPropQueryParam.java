package com.idleCultivate.query.model;

import com.idleCultivate.common.model.QueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LevelPropQueryParam extends QueryParam {
    private Integer job;
    private Integer level;
}
