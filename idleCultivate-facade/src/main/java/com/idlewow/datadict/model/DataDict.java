package com.idlewow.datadict.model;

import com.idlewow.common.model.BaseModel;
import com.idlewow.util.validation.annotation.NotBlank;
import com.idlewow.util.validation.annotation.NotNull;
import lombok.Data;

@Data
public class DataDict extends BaseModel {
    @NotBlank(field = "编码")
    private String code;
    private String parentCode;
    @NotNull(field = "值")
    private String value;
    private String remark;
}
