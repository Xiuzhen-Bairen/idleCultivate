package com.idlewow.rms.controller;

import com.idlewow.common.model.BaseModel;
import com.idlewow.common.model.CommonResult;
import com.idlewow.common.model.QueryParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public abstract class ExcelController<T extends BaseModel, Q extends QueryParam> extends CrudController<T, Q> {
    @ResponseBody
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Object importExcel(HttpServletRequest request) {
        try {
            ServletContext servletContext = request.getServletContext();
            String uploadPath = servletContext.getRealPath("/upload");
            File dir = new File(uploadPath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(servletContext);
            if (multipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    MultipartFile file = multiRequest.getFile(iter.next());
                    if (file.getSize() > 0) {
                        String fileName = file.getOriginalFilename();
                        String extension = fileName.substring(fileName.lastIndexOf("."));
                        if (!extension.toLowerCase().equals(".xls") && !extension.toLowerCase().equals(".xlsx")) {
                            throw new Exception("不支持的文档格式！请上传.xls或.xlsx格式的文档！");
                        }

                        String destFileName = fileName + "_" + System.currentTimeMillis() + extension;
                        File destFile = new File(uploadPath, destFileName);
                        file.transferTo(destFile);
                        try {
                            List<T> dataList = this.loadExcelData(destFile.getPath());
                            CommonResult commonResult = this.saveExcelData(dataList);
                            if (!commonResult.isSuccess()) {
                                logger.info("批量添加失败：" + commonResult.getMessage());
                                return commonResult;
                            }
                        } finally {
                            if (destFile.exists() && !destFile.delete()) {
                                logger.error("删除上传的临时文件失败！" + destFile.getAbsolutePath());
                            }
                        }
                    }
                }
            }

            return CommonResult.success();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return CommonResult.fail();
        }
    }

    protected abstract List<T> loadExcelData(String excelPath) throws Exception;

    protected CommonResult saveExcelData(List<T> dataList) {
        return this.baseService.batchInsert(dataList);
    }
}
