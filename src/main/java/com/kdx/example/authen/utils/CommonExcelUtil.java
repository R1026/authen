package com.kdx.example.authen.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.kdx.example.authen.common.CommonExcelListener;
import org.apache.poi.ss.formula.functions.T;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/3/16
 */
public class CommonExcelUtil {

    public static <T> void readExcel(InputStream inputStream, Class clazz, Consumer<List<T>> consumer){
        readExcel(inputStream,clazz,0,consumer);
    }

    public static <T> void readExcel(InputStream inputStream,Class clazz,Integer sheetNo,Consumer<List<T>> consumer){
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(inputStream, clazz, new CommonExcelListener(consumer)).build();
            ReadSheet readSheet = EasyExcel.readSheet(sheetNo).build();
            excelReader.read(readSheet);
        }finally {
            if (null != excelReader)
                excelReader.finish();
        }
    }

}
