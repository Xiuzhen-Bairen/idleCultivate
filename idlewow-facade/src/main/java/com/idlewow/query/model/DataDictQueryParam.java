package com.idlewow.query.model;

import com.idlewow.common.model.QueryParam;
import lombok.Data;

@Data
public class DataDictQueryParam extends QueryParam {
    private String code;
    private String parentCode;
}
