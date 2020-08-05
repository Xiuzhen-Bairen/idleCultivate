package com.idlewow.common.model;

import com.idlewow.util.validation.ValidateGroup;
import com.idlewow.util.validation.annotation.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseModel implements Serializable {
    /**
     * 主键ID
     */
    @NotBlank(field = "主键id", groups = ValidateGroup.Update.class)
    private String id;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 是否删除 0-否 1-是
     */
    private Integer isDelete;
    /**
     * 版本号
     */
    private Integer version;
}
