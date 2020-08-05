package com.idleCultivate.rms.controller;

import com.idleCultivate.character.model.LevelProp;
import com.idleCultivate.datadict.model.DataDict;
import com.idleCultivate.datadict.model.DataType;
import com.idleCultivate.datadict.service.DataDictService;
import com.idleCultivate.query.model.LevelPropQueryParam;
import com.idleCultivate.util.poi.PoiUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/manage/level_prop")
public class LevelPropController extends ExcelController<LevelProp, LevelPropQueryParam> {
    @Autowired
    DataDictService dataDictService;

    protected List<LevelProp> loadExcelData(String excelPath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("等级属性");
        List<LevelProp> levelPropList = new ArrayList<>();
        // 处理当前页，循环读取每一行
        String createUser = this.currentUserName();
        for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = (XSSFRow) sheet.getRow(rowNum);
            // todo 目前只录入战士的用于测试
            String job = PoiUtil.getCellValue(row.getCell(1));
            DataDict dataDict = dataDictService.findByParentCodeAndValue(DataType.Job.getCode(), job);
            if (dataDict.getValue().equals("战士")) {
                Integer level = Integer.valueOf(PoiUtil.getCellValue(row.getCell(2)));
                Integer hp = Integer.valueOf(PoiUtil.getCellValue(row.getCell(3)));
                Integer strength = Integer.valueOf(PoiUtil.getCellValue(row.getCell(4)));
                Integer agility = Integer.valueOf(PoiUtil.getCellValue(row.getCell(5)));
                Integer intellect = Integer.valueOf(PoiUtil.getCellValue(row.getCell(6)));
                Integer stamina = Integer.valueOf(PoiUtil.getCellValue(row.getCell(7)));
                LevelProp levelProp = new LevelProp();
                levelProp.setJob(dataDict.getCode());
                levelProp.setLevel(level);
                levelProp.setHp(hp);
                levelProp.setStrength(strength);
                levelProp.setAgility(agility);
                levelProp.setIntellect(intellect);
                levelProp.setStamina(stamina);
                levelProp.setCreateUser(createUser);
                levelPropList.add(levelProp);
            }
        }

        return levelPropList;
    }
}
