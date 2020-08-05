package com.idleCultivate.user.service.impl;

import com.idleCultivate.common.model.CommonResult;
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

    public CommonResult register(String username, String password, String ip) {
        try {
            UserAccount userAccount = userAccountManager.findByUsername(username);
            if (userAccount != null) {
                return CommonResult.fail("此用户名已被注册！");
            }

            userAccountManager.register(username, password, ip);
            return CommonResult.success();
        } catch (Exception ex) {
            logger.error("用户注册失败：" + ex.getMessage(), ex);
            return CommonResult.fail("用户注册失败");
        }
    }

    @Override
    public CommonResult login(String username, String password) {
        try {
            UserAccount userAccount = userAccountManager.login(username, password);
            if (userAccount == null) {
                return CommonResult.fail("用户名或密码错误！");
            }

            if (userAccount.getStatus() == 1) {
                return CommonResult.fail("账号已冻结！");
            }

            return CommonResult.success("", userAccount);
        } catch (Exception ex) {
            logger.error("用户登录失败：" + ex.getMessage(), ex);
            return CommonResult.fail("用户登录失败");
        }
    }
}
