package com.idleCultivate.user.service.impl;

import com.idleCultivate.user.manager.UserAccountManager;
import com.idleCultivate.user.model.UserAccount;
import com.idleCultivate.user.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    @Autowired
    UserAccountManager userAccountManager;

    public UserAccount register(String username, String password, String ip) {
        try {
            UserAccount userAccount = userAccountManager.findByUsername(username);
            if (userAccount != null) {
                logger.info("此用户名已被注册！");
                return null;
            }

            return userAccountManager.register(username, password, ip);
        } catch (Exception ex) {
            logger.error("用户注册失败：" + ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public UserAccount login(String username, String password) {
        try {
            UserAccount userAccount = userAccountManager.login(username, password);
            if (userAccount == null) {
                logger.info("用户名或密码错误！");
            }

            if (userAccount.getStatus() == 1) {
                logger.info("账号已冻结！");
            }
            logger.info("登录成功！");
            return userAccount;
        } catch (Exception ex) {
            logger.error("用户登录失败：" + ex.getMessage(), ex);
            return null;
        }
    }
}
