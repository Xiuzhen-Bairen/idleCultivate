package com.idleCultivate.datadict.model;

import com.idleCultivate.common.model.BaseModel;
import com.idleCultivate.util.validation.annotation.NotBlank;
import com.idleCultivate.util.validation.annotation.NotNull;
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
