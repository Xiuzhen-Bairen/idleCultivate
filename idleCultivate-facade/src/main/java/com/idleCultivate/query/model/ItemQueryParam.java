package com.idleCultivate.query.model;

import com.idleCultivate.common.model.QueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ItemQueryParam extends QueryParam {
    private String name;
    private String level;
    private Boolean pile;
    private Boolean sell;
}
