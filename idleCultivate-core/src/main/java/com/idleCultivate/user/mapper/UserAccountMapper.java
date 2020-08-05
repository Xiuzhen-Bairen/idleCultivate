package com.idleCultivate.user.mapper;

import com.idleCultivate.user.model.UserAccount;

public interface UserAccountMapper {
    int register(UserAccount userAccount);

    UserAccount login(UserAccount userAccount);

    UserAccount findByUsername(String username);
}
