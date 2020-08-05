package com.idleCultivate.rms.controller;

import com.idleCultivate.datadict.model.DataDict;
import com.idleCultivate.datadict.model.DataType;
import com.idleCultivate.datadict.service.DataDictService;
import com.idleCultivate.map.model.WowMap;
import com.idleCultivate.query.model.WowMapQueryParam;
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
@RequestMapping("/manage/map")
public class MapController extends ExcelController<WowMap, WowMapQueryParam> {
    @Autowired
    DataDictService dataDictService;

    protected List<WowMap> loadExcelData(String excelPath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("地图");
        List<WowMap> wowMapList = new ArrayList<>();
        // 处理当前页，循环读取每一行
        String createUser = this.currentUserName();
        for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = (XSSFRow) sheet.getRow(rowNum);
            String name = PoiUtil.getCellValue(row.getCell(2));
            DataDict dataDict = dataDictService.findByParentCodeAndValue(DataType.Occupy.getCode(), PoiUtil.getCellValue(row.getCell(5)));
            String occupy = dataDict.getCode();
            WowMap wowMap = new WowMap();
            wowMap.setName(name);
            wowMap.setOccupy(occupy);
            wowMap.setDescription("");
            wowMap.setCreateUser(createUser);
            wowMapList.add(wowMap);
        }

        fileInputStream.close();
        return wowMapList;
    }
}

