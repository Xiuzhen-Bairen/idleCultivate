package com.idleCultivate.rms.controller;

import com.idleCultivate.admin.model.SysAdmin;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.common.model.PageList;
import com.idleCultivate.rms.vo.LayuiDataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public abstract class BaseController {
    public static final String LoginUserKey = "loginuser";
    protected final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    @Autowired
    protected HttpSession httpSession;
    @Autowired
    protected Validator validator;

    protected SysAdmin currentUser() {
        return (SysAdmin) httpSession.getAttribute(LoginUserKey);
    }

    protected String currentUserName() {
        return this.currentUser().getUsername();
    }

    protected LayuiDataTable parseTable(PageList pageList) {
        LayuiDataTable dataTable = new LayuiDataTable();
        dataTable.setMessage("读取成功");
        dataTable.setCode(0);
        dataTable.setCount(pageList.getTotalCount());
        dataTable.setData(pageList.getRows());
        return dataTable;
    }

    protected CommonResult validate(Object object, Class... classes) {
        Set<ConstraintViolation<Object>> set = validator.validate(object, classes);
        if (set != null && set.size() > 0) {
            ConstraintViolation constraintViolation = set.iterator().next();
            return CommonResult.fail(constraintViolation.getMessage());
        }

        return CommonResult.success();
    }
}
