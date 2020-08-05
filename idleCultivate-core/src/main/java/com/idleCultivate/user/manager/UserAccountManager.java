package com.idleCultivate.user.manager;

import com.idleCultivate.user.mapper.UserAccountMapper;
import com.idleCultivate.user.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAccountManager {
    @Autowired
    UserAccountMapper userAccountMapper;

    public UserAccount findByUsername(String username) {
        return userAccountMapper.findByUsername(username);
    }

    public void register(String username, String password, String ip) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        userAccount.setRegisterIp(ip);
        userAccount.setCreateUser("idleCultivate");
        int effected = userAccountMapper.register(userAccount);
        if (effected == 0) {
            throw new RuntimeException("sql effected 0 rows");
        }
    }

    public UserAccount login(String username, String password) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        return userAccountMapper.login(userAccount);
    }
}
