package com.idleCultivate.user.service;

import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.user.model.UserAccount;

public interface UserService {
    CommonResult register(String username, String password, String ip);

    UserAccount login(String username, String password);
}
