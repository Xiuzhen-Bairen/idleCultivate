package com.idleCultivate.user.service;

import com.idleCultivate.user.model.UserAccount;

public interface UserService {
    UserAccount register(String username, String password, String ip);

    UserAccount login(String username, String password);
}
