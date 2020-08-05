package com.idlewow.user.service;

import com.idlewow.common.model.CommonResult;

public interface UserService {
    CommonResult register(String username, String password, String ip);

    CommonResult login(String username, String password);
}
