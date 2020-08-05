package com.idlewow.util.poi;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PoiUtil {
    public static String getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        if (cellType.equals(CellType.STRING)) {
            return cell.getStringCellValue();
        } else if (cellType.equals(CellType.NUMERIC)) {
            if (DateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                return date == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            } else {
                return new DecimalFormat("0.##").format(cell.getNumericCellValue());
            }
        } else if (cellType.equals(CellType.FORMULA)) {
            if (StringUtils.isNotBlank(cell.getStringCellValue())) {
                return cell.getStringCellValue();
            } else {
                return cell.getNumericCellValue() + "";
            }
        } else if (cellType.equals(CellType.BOOLEAN)) {
            return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
        } else {
            return "";
        }
    }
}
