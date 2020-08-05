package com.idlewow.map.model;

import com.idlewow.common.model.BaseModel;
import com.idlewow.util.validation.annotation.NotBlank;
import com.idlewow.util.validation.annotation.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class WowMap extends BaseModel implements Serializable {
    @NotBlank(field = "地图名称")
    private String name;
    @NotBlank(field = "领土归属")
    private String occupy;
    @Size(field = "地图描述", max = 500)
    private String description;
}
