package com.idlewow.user.model;

import com.idlewow.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserAccount extends BaseModel implements Serializable {
    private String username;
    private String password;
    private String mail;
    private String phone;
    private String realName;
    private String idNo;
    private Integer status;
    private String remark;
    private String registerIp;
}
