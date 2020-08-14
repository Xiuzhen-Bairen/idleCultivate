package com.idleCultivate.rms.controller;

import com.idleCultivate.query.model.MonsterQueryParam;
import com.idleCultivate.monster.model.Monster;
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
@RequestMapping("/manage/monster")
public class MonsterController extends ExcelController<Monster, MonsterQueryParam> {
    protected List<Monster> loadExcelData(String excelPath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("怪物");
        List<Monster> monsterList = new ArrayList<>();
        // 处理当前页，循环读取每一行
        String createUser = this.currentUserName();
        for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = (XSSFRow) sheet.getRow(rowNum);
            Integer mapId = Integer.valueOf(PoiUtil.getCellValue(row.getCell(1)));
//            Integer mapName = Integer.valueOf(PoiUtil.getCellValue(row.getCell(2)));
            String name = PoiUtil.getCellValue(row.getCell(2));
            Integer level = Integer.valueOf(PoiUtil.getCellValue(row.getCell(3)));
            Integer race = Integer.valueOf(PoiUtil.getCellValue(row.getCell(4)));
            Integer job = Integer.valueOf(PoiUtil.getCellValue(row.getCell(5)));
            Integer att = Integer.valueOf(PoiUtil.getCellValue(row.getCell(6)));
            Integer def = Integer.valueOf(PoiUtil.getCellValue(row.getCell(7)));
            Integer hp = Integer.valueOf(PoiUtil.getCellValue(row.getCell(8)));
            Integer crit = Integer.valueOf(PoiUtil.getCellValue(row.getCell(9)));
            Integer dodge = Integer.valueOf(PoiUtil.getCellValue(row.getCell(10)));
            Integer coordinate = Integer.valueOf(PoiUtil.getCellValue(row.getCell(10)));
//            Boolean is_hide = Boolean.valueOf(PoiUtil.getCellValue(row.getCell(11)));
//            String extra_info = PoiUtil.getCellValue(row.getCell(12));
            Monster monster = new Monster();
            monster.setMapId(mapId);
//            monster.setMapName(mapName);
            monster.setName(name);
            monster.setLevel(level);
            monster.setRace(race);
            monster.setJob(job);
            monster.setAtt(att);
            monster.setDef(def);
            monster.setHp(hp);
            monster.setCrit(crit);
            monster.setDodge(dodge);
            monster.setCoordinate(coordinate);
//            monster.setIs_hide(is_hide);
//            monster.setExtra_info(extra_info);
            monster.setCreateUser(createUser);
            monsterList.add(monster);
        }

        fileInputStream.close();
        return monsterList;
    }
}
