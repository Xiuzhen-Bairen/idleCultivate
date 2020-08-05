package com.idleCultivate.rms.controller;

import com.idleCultivate.character.model.LevelExp;
import com.idleCultivate.query.model.LevelExpQueryParam;
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
@RequestMapping("/manage/level_exp")
public class LevelExpController extends ExcelController<LevelExp, LevelExpQueryParam> {
    protected List<LevelExp> loadExcelData(String excelPath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("等级经验");
        List<LevelExp> levelExpList = new ArrayList<>();
        // 处理当前页，循环读取每一行
        String createUser = this.currentUserName();
        for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = (XSSFRow) sheet.getRow(rowNum);
            Integer level = Integer.valueOf(PoiUtil.getCellValue(row.getCell(1)));
            Integer exp = Integer.valueOf(PoiUtil.getCellValue(row.getCell(2)));
            Integer exp_speed = Integer.valueOf(PoiUtil.getCellValue(row.getCell(3)));
            LevelExp levelExp = new LevelExp();
            levelExp.setLevel(level);
            levelExp.setExp(exp);
            levelExp.setExp_speed(exp_speed);
            levelExp.setCreateUser(createUser);
            levelExpList.add(levelExp);
        }

        fileInputStream.close();
        return levelExpList;
    }
}
