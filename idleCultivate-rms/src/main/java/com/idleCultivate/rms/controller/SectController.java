package com.idleCultivate.rms.controller;

import com.idleCultivate.query.model.SectQueryParam;
import com.idleCultivate.sect.model.Sect;
import com.idleCultivate.util.poi.PoiUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/manage/sect")
public class SectController extends ExcelController<Sect, SectQueryParam> {
    protected List<Sect> loadExcelData(String excelPath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("物品");
        List<Sect> sectList = new ArrayList<>();
        // 处理当前页，循环读取每一行
        String createUser = this.currentUserName();
        for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = (XSSFRow) sheet.getRow(rowNum);
            Integer name = Integer.valueOf(PoiUtil.getCellValue(row.getCell(1)));
            Integer type = Integer.valueOf(PoiUtil.getCellValue(row.getCell(2)));
            Integer level = Integer.valueOf(PoiUtil.getCellValue(row.getCell(3)));
            Sect sect = new Sect();
            sectList.add(sect);
        }

        fileInputStream.close();
        return sectList;
    }
}
