package com.idleCultivate.map.model;

import com.idleCultivate.common.model.BaseModel;
import com.idleCultivate.util.validation.annotation.NotBlank;
import com.idleCultivate.util.validation.annotation.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class WowMap extends BaseModel implements Serializable {
    @NotBlank(field = "地图名称")
    private String name;
    @NotBlank(field = "地图归属")
    private String occupy;
    @Size(field = "地图描述", max = 500)
    private String description;
}
