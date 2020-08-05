package com.idlewow.rms.controller;

import com.idlewow.datadict.model.DataDict;
import com.idlewow.query.model.DataDictQueryParam;
import com.idlewow.rms.support.util.DataDictUtil;
import com.idlewow.util.poi.PoiUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/manage/data_dict")
public class DataDictController extends ExcelController<DataDict, DataDictQueryParam> {
    protected List<DataDict> loadExcelData(String excelPath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("数据字典");
        List<DataDict> dataDictList = new ArrayList<>();
        // 处理当前页，循环读取每一行
        String createUser = this.currentUserName();
        for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = (XSSFRow) sheet.getRow(rowNum);
            String code = PoiUtil.getCellValue(row.getCell(1));
            String parentCode = PoiUtil.getCellValue(row.getCell(2));
            String value = PoiUtil.getCellValue(row.getCell(3));
            String remark = PoiUtil.getCellValue(row.getCell(4));

            DataDict dataDict = new DataDict();
            dataDict.setCode(code);
            dataDict.setParentCode(parentCode);
            dataDict.setValue(value);
            dataDict.setRemark(remark);
            dataDict.setCreateUser(createUser);
            dataDictList.add(dataDict);
        }

        fileInputStream.close();
        return dataDictList;
    }

    @ResponseBody
    @RequestMapping("/configMap")
    public Object loadConfigMap() {
        return DataDictUtil.ConfigMap;
    }
}
