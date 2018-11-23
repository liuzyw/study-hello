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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created on 2017-10-15
 *
 * @author liuzhaoyuan
 */
public class ExcelXlsxUtils {

    private ExcelXlsxUtils() {
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

        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);

        Cell cell = null;
        for (int i = 0; i < titles.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(titles.get(i));
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
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        return true;
    }

    public static List<List<Object>> readFromStream(InputStream inputStream) {
        if (inputStream == null) {
            return Collections.EMPTY_LIST;
        }
        XSSFWorkbook workbook = null;
        try {
            List<List<Object>> data = new ArrayList<>();
            workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i <= lastRowNum; i++) {
                XSSFRow row = sheet.getRow(i);
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
                } catch (IOException e) {
                    e.printStackTrace();
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

    private static Object getCellValue(XSSFCell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字
        cell.getCellTypeEnum();

        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }
}