package com.idlewow.rms.controller;

import com.alibaba.fastjson.JSONObject;
import com.idlewow.admin.model.SysAdmin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/authorize")
public class AuthorizeController {
    @Autowired
    HttpSession httpSession;

    @ResponseBody
    @RequestMapping("/login")
    public Object longin(SysAdmin sysAdmin) {
        String userName = sysAdmin.getUsername();
        String password = sysAdmin.getPassword();
        if (userName.equalsIgnoreCase("admin")) {
            userName = "admin";
            password = "123456";
        }

        JSONObject jsonObject = new JSONObject();
        //验证用户名和密码不能为空
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            jsonObject.put("status", false);
            jsonObject.put("msg", "用户名和密码不能为空");
            return jsonObject.toJSONString();
        }

        httpSession.setAttribute("loginuser", sysAdmin);
        jsonObject.put("status", true);
        jsonObject.put("msg", "登录成功");
        return jsonObject;
    }
}
