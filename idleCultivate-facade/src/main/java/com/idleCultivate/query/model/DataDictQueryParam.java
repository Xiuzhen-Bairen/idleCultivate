package com.idleCultivate.query.model;

import com.idleCultivate.common.model.QueryParam;
import lombok.Data;

@Data
public class DataDictQueryParam extends QueryParam {
    private String code;
    private String parentCode;
}
