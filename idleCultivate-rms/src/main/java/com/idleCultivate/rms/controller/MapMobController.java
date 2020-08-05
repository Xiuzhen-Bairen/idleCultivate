package com.idleCultivate.rms.controller;

import com.idleCultivate.datadict.model.DataDict;
import com.idleCultivate.datadict.model.DataType;
import com.idleCultivate.datadict.service.DataDictService;
import com.idleCultivate.map.model.WowMap;
import com.idleCultivate.map.service.WowMapService;
import com.idleCultivate.mob.model.MapMob;
import com.idleCultivate.query.model.MapMobQueryParam;
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
@RequestMapping("/manage/map_mob")
public class MapMobController extends ExcelController<MapMob, MapMobQueryParam> {
    @Autowired
    WowMapService wowMapService;
    @Autowired
    DataDictService dataDictService;

    protected List<MapMob> loadExcelData(String excelPath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("怪物");
        List<MapMob> mapMobList = new ArrayList<>();
        // 处理当前页，循环读取每一行
        String createUser = this.currentUserName();
        for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = (XSSFRow) sheet.getRow(rowNum);
            String mapName = PoiUtil.getCellValue(row.getCell(1));
            String mobName = PoiUtil.getCellValue(row.getCell(2));
            String faction = PoiUtil.getCellValue(row.getCell(3));
            String mobClass = PoiUtil.getCellValue(row.getCell(4));
            String mobType = PoiUtil.getCellValue(row.getCell(5));
            Integer level = Integer.valueOf(PoiUtil.getCellValue(row.getCell(6)));
            Integer hp = Integer.valueOf(PoiUtil.getCellValue(row.getCell(7)));
            Integer damage = Integer.valueOf(PoiUtil.getCellValue(row.getCell(8)));
            Integer armour = Integer.valueOf(PoiUtil.getCellValue(row.getCell(9)));
            WowMap wowMap = wowMapService.findByName(mapName);
            MapMob mapMob = new MapMob();
            mapMob.setMapId(wowMap.getId());
            mapMob.setMapName(mapName);
            mapMob.setName(mobName);
            DataDict dataDict = dataDictService.findByParentCodeAndValue(DataType.Faction.getCode(), faction);
            if (dataDict == null) {
                System.out.println("null faction:" + faction);
            }
            mapMob.setFaction(dataDict.getCode());
            dataDict = dataDictService.findByParentCodeAndValue(DataType.MobClass.getCode(), mobClass);
            if (dataDict == null) {
                System.out.println("null mobClass:" + mobClass);
            }
            mapMob.setMobClass(dataDict.getCode());
            dataDict = dataDictService.findByParentCodeAndValue(DataType.MobType.getCode(), mobType);
            mapMob.setMobType(dataDict.getCode());
            mapMob.setLevel(level);
            mapMob.setHp(hp);
            mapMob.setDamage(damage);
            mapMob.setArmour(armour);
            mapMob.setCreateUser(createUser);
            mapMobList.add(mapMob);
        }

        fileInputStream.close();
        return mapMobList;
    }
}
