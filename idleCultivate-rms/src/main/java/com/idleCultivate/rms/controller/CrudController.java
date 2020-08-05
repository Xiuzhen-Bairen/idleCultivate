package com.idleCultivate.rms.controller;

import com.idleCultivate.common.model.BaseModel;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.common.model.PageList;
import com.idleCultivate.common.model.QueryParam;
import com.idleCultivate.common.service.BaseService;
import com.idleCultivate.util.validation.ValidateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public abstract class CrudController<T extends BaseModel, Q extends QueryParam> extends BaseController {
    private final String path = this.getClass().getAnnotation(RequestMapping.class).value()[0];

    @Autowired
    BaseService<T, Q> baseService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping("/list")
    public Object list() {
        return this.path + "/list";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object list(@RequestParam(value = "page", defaultValue = "1") int pageIndex, @RequestParam(value = "limit", defaultValue = "10") int pageSize, Q q) {
        try {
            q.setPage(pageIndex, pageSize);
            CommonResult result = baseService.list(q);
            if (result.isSuccess()) {
                PageList<T> pageList = (PageList<T>) result.getData();
                return this.parseTable(pageList);
            } else {
                request.setAttribute("errorMessage", result.getMessage());
                return "/error";
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            request.setAttribute("errorMessage", ex.getMessage());
            return "/error";
        }
    }

    @RequestMapping("/add")
    public Object add() {
        return this.path + "/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(@RequestBody T t) {
        try {
            CommonResult result = this.validate(t, ValidateGroup.Create.class);
            if (result.isSuccess()) {
                t.setCreateUser(this.currentUserName());
                result = baseService.insert(t);
            }

            return result;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return CommonResult.fail(ex.getMessage());
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public Object edit(@PathVariable String id, Model model) {
        try {
            CommonResult result = baseService.find(id);
            if (result.isSuccess()) {
                T t = (T) result.getData();
                model.addAttribute(t);
                return this.path + "/edit";
            } else {
                request.setAttribute("errorMessage", result.getMessage());
                return "/error";
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            request.setAttribute("errorMessage", ex.getMessage());
            return "/error";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public Object edit(@PathVariable String id, @RequestBody T t) {
        try {
            if (!id.equals(t.getId())) {
                return CommonResult.fail("id不一致");
            }

            CommonResult result = this.validate(t, ValidateGroup.Update.class);
            if (result.isSuccess()) {
                t.setUpdateUser(this.currentUserName());
                result = baseService.update(t);
            }

            return result;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return CommonResult.fail(ex.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Object delete(@PathVariable String id) {
        try {
            baseService.delete(id);
            return CommonResult.success();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return CommonResult.fail(ex.getMessage());
        }
    }
}
