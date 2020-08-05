package com.idlewow.common;

import com.idlewow.common.model.BaseModel;
import com.idlewow.common.model.CommonResult;
import com.idlewow.common.model.PageList;
import com.idlewow.common.model.QueryParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseServiceImpl<T extends BaseModel, Q extends QueryParam> {
    public final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    public BaseManager<T> baseManager;

    /**
     * 列表查询
     *
     * @param q 查询参数
     * @return
     */
    public CommonResult list(Q q) {
        try {
            if (q.getPageParam() != null) {
                PageList<T> pageList = baseManager.pageList(q);
                return CommonResult.success("", pageList);
            } else {
                List<T> list = baseManager.list(q);
                return CommonResult.success("", list);
            }
        } catch (Exception ex) {
            logger.error("获取列表失败：" + ex.getMessage(), ex);
            return CommonResult.fail("获取列表失败：" + ex.getMessage());
        }
    }

    /**
     * 添加记录
     *
     * @param t 等级经验记录
     * @return
     */
    public CommonResult insert(T t) {
        try {
            baseManager.insert(t);
            return CommonResult.success();
        } catch (Exception ex) {
            logger.error("添加记录失败：" + ex.getMessage(), ex);
            return CommonResult.fail("添加记录失败：" + ex.getMessage());
        }
    }

    /**
     * 批量添加
     *
     * @param list 数据集合
     * @return
     */
    public CommonResult batchInsert(List<T> list) {
        try {
            baseManager.batchInsert(list);
            return CommonResult.success();
        } catch (Exception ex) {
            logger.error("批量添加失败：" + ex.getMessage(), ex);
            return CommonResult.fail("批量添加失败：" + ex.getMessage());
        }
    }

    /**
     * 更新数据
     *
     * @param t 数据对象
     * @return
     */
    public CommonResult update(T t) {
        try {
            baseManager.update(t);
            return CommonResult.success();
        } catch (Exception ex) {
            logger.error("更新失败：" + ex.getMessage(), ex);
            return CommonResult.fail("更新失败：" + ex.getMessage());
        }
    }

    /**
     * 删除数据
     *
     * @param id 主键id
     * @return
     */
    public CommonResult delete(String id) {
        try {
            baseManager.delete(id);
            return CommonResult.success();
        } catch (Exception ex) {
            logger.error("删除失败：" + ex.getMessage(), ex);
            return CommonResult.fail("删除失败：" + ex.getMessage());
        }
    }

    /**
     * 根据ID查询
     *
     * @param id 主键id
     * @return
     */
    public CommonResult find(String id) {
        try {
            T t = baseManager.find(id);
            return CommonResult.success("success", t);
        } catch (Exception ex) {
            logger.error("查询失败：" + ex.getMessage(), ex);
            return CommonResult.fail("查询失败：" + ex.getMessage());
        }
    }
}
