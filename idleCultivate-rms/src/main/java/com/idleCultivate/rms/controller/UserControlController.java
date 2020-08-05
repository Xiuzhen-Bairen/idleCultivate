package com.idleCultivate.rms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userControl")
public class UserControlController {
    @RequestMapping("/chooseMap")
    public Object chooseMap() {
        return "/userControl/chooseMap";
    }
}
