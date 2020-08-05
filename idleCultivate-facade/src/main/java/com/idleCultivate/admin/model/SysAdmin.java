package com.idleCultivate.admin.model;

import com.idleCultivate.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysAdmin extends BaseModel {
    private String username;
    private String password;
}
