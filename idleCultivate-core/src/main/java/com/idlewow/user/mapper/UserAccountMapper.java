package com.idlewow.user.mapper;

import com.idlewow.user.model.UserAccount;

public interface UserAccountMapper {
    int register(UserAccount userAccount);

    UserAccount login(UserAccount userAccount);

    UserAccount findByUsername(String username);
}
