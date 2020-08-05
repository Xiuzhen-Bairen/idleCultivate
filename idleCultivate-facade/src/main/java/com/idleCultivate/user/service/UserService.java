package com.idleCultivate.user.service;

import com.idleCultivate.common.model.CommonResult;

public interface UserService {
    CommonResult register(String username, String password, String ip);

    CommonResult login(String username, String password);
}
