package com.study.util.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Created on 2017-10-14
 *
 * @author liuzhaoyuan
 */
public class ExcelXlsUtils {

    private ExcelXlsUtils() {
    }


    public static boolean writeToStream(OutputStream outputStream, List<String> titles, List<List<Object>> data) {
        if (outputStream == null || CollectionUtils.isEmpty(titles) || CollectionUtils.isEmpty(data)) {
            return false;
        }
        if (titles.size() != data.get(0).size()) {
            return false;
        }
        for (int i = 0; i < data.size(); i++) {
            if (CollectionUtils.isEmpty(data.get(i))) {
                return false;
            }
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = null;
        for (int i = 0; i < titles.size(); i++) {
            int columnWidth = sheet.getColumnWidth(i) / 256;
            cell = row.createCell(i);
            cell.setCellValue(titles.get(i));
            if (i == 0) {
                sheet.setColumnWidth(i, (columnWidth - 2) * 256);
            } else {
                sheet.setColumnWidth(i, (columnWidth + 4) * 256);
            }
        }

        List<Object> rowData = null;
        for (int i = 0, len = data.size(); i < len; i++) {
            row = sheet.createRow(i + 1);
            rowData = data.get(i);
            for (int j = 0; j < rowData.size(); j++) {
                cell = row.createCell(j);
                cell.setCellValue(rowData.get(j).toString());
            }
        }

        try {
            workbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            return false;
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return true;
    }

    public static List<List<Object>> readFromStream(InputStream inputStream) {
        if (inputStream == null) {
            return Collections.EMPTY_LIST;
        }
        HSSFWorkbook workbook = null;
        try {
            List<List<Object>> data = new ArrayList<>();
            workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i <= lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                if (row != null) {
                    List<Object> rowData = new ArrayList<>();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        rowData.add(getCellValue(row.getCell(j)));
                    }
                    data.add(rowData);
                }

            }
            return data;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return Collections.EMPTY_LIST;
    }

    private static Object getCellValue(HSSFCell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字
        cell.getCellTypeEnum();

        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }
}
