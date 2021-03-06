package com.idleCultivate.query.model;

import com.idleCultivate.common.model.QueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SectQueryParam extends QueryParam {
    private String name;
    private String level;
}
