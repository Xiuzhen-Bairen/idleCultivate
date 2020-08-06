package com.idleCultivate.rms.controller;

import com.idleCultivate.character.model.Item;
import com.idleCultivate.query.model.ItemQueryParam;
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
@RequestMapping("/manage/item")
public class ItemController extends ExcelController<Item, ItemQueryParam> {
    protected List<Item> loadExcelData(String excelPath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("物品");
        List<Item> itemList = new ArrayList<>();
        // 处理当前页，循环读取每一行
        String createUser = this.currentUserName();
        for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = (XSSFRow) sheet.getRow(rowNum);
            Integer name = Integer.valueOf(PoiUtil.getCellValue(row.getCell(1)));
            Integer type = Integer.valueOf(PoiUtil.getCellValue(row.getCell(2)));
            Integer level = Integer.valueOf(PoiUtil.getCellValue(row.getCell(3)));
            Item item = new Item();
            itemList.add(item);
        }

        fileInputStream.close();
        return itemList;
    }
}
