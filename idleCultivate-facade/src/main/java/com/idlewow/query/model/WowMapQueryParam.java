package com.idlewow.query.model;

import com.idlewow.common.model.QueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WowMapQueryParam extends QueryParam {
    private String name;
}
