package com.example.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/8/16 21:41
 */
public class EasyExcelWriterFactory {
    private int sheetNo = 0;
    private ExcelWriter excelWriter = null;

    public EasyExcelWriterFactory(OutputStream outputStream) {
        excelWriter = EasyExcelFactory.write(outputStream).build();
    }

    public EasyExcelWriterFactory(File file) {
        excelWriter = EasyExcelFactory.write(file).build();
    }

    public EasyExcelWriterFactory(String filePath) {
        excelWriter = EasyExcelFactory.write(filePath).build();
    }

    /**
     * 链式模板表头写入
     * @param headClazz 表头格式
     * @param data 数据 List<ExcelModel> 或者List<List<Object>>
     */
    public EasyExcelWriterFactory writeModel(Class<T> headClazz, List<T> data, String sheetName){
        excelWriter.write(data, EasyExcelFactory.writerSheet(this.sheetNo++, sheetName).head(headClazz).build());
        return this;
    }

    /**
     * 链式自定义表头写入
     *
     * @param data      数据 List<ExcelModel> 或者List<List<Object>>
     * @param head      头
     * @param sheetName 工作表名称
     * @return {@link EasyExcelWriterFactory}
     */
    public EasyExcelWriterFactory write(List<List<String>> head, List<T> data, String sheetName){
        excelWriter.write(data, EasyExcelFactory.writerSheet(this.sheetNo++, sheetName).head(head).build());
        return this;
    }

    /**
     * 使用此类结束后，一定要关闭流
     */
    public void finish() {
        excelWriter.finish();
    }

}
