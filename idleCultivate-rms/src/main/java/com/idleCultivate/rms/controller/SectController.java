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
        Sheet sheet = workbook.getSheet("门派");
        List<Sect> sectList = new ArrayList<>();
        // 处理当前页，循环读取每一行
        String createUser = this.currentUserName();
        for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = (XSSFRow) sheet.getRow(rowNum);
            String name = PoiUtil.getCellValue(row.getCell(1));
            String type = PoiUtil.getCellValue(row.getCell(2));
            Integer level = Integer.valueOf(PoiUtil.getCellValue(row.getCell(3)));
            Boolean alchemy = Boolean.valueOf(PoiUtil.getCellValue(row.getCell(4)));
            Boolean refiner = Boolean.valueOf(PoiUtil.getCellValue(row.getCell(4)));
            Sect sect = new Sect();
            sect.setName(name);
            sect.setType(type);
            sect.setLevel(level);
            sect.setAlchemy(alchemy);
            sect.setRefiner(refiner);
            sect.setCreateUser(createUser);
            sectList.add(sect);
        }

        fileInputStream.close();
        return sectList;
    }
}
