package com.idlewow.query.model;

import com.idlewow.common.model.QueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LevelPropQueryParam extends QueryParam {
    private Integer job;
    private Integer level;
}
