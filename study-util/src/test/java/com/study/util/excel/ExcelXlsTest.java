package com.study.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Created on 2017-10-14
 *
 * @author liuzhaoyuan
 */
public class ExcelXlsTest {

    @Test
    public void writeXls() throws IOException {
        List<String> titles = new ArrayList<>();
        titles.add("idaaaaaa");
        titles.add("name");

        List<List<Object>> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Object> row = new ArrayList<>();
            row.add((i + 1) + "");
            row.add((i + 1) + "name");
            data.add(row);
        }

        File file = new File("/Users/liuzhaoyuan/Desktop/aaa.xls");

        ExcelXlsUtils.writeToStream(new FileOutputStream(file), titles, data);
    }

    @Test
    public void writeXlsx() throws IOException {
        List<String> titles = new ArrayList<>();
        titles.add("idaaaaaa");
        titles.add("name");
        titles.add("age");

        List<List<Object>> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Object> row = new ArrayList<>();
            row.add((i + 1) + "");
            row.add((i + 1) + "name");
            row.add(i+100);
            data.add(row);
        }

        File file = new File("/Users/liuzhaoyuan/Desktop/aaba.xlsx");

        ExcelXlsxUtils.writeToStream(new FileOutputStream(file), titles, data);
    }

    @Test
    public void testRead()throws Exception{
        File file = new File("/Users/liuzhaoyuan/Desktop/aaa.xls");

        System.out.println(ExcelXlsUtils.readFromStream(new FileInputStream(file)));
    }

    @Test
    public void testReads(){
        File file = new File("/Users/liuzhaoyuan/Desktop/aaa.xlsx");

        try {
            System.out.println(ExcelXlsxUtils.readFromStream(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
    }

}
