package com.example.excel;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 成大事
 * @since 2022/8/16 20:52
 */
public class EasyExcelUtil {
    private EasyExcelUtil(){}
    /**
     * 同步无模型读（默认读取sheet0,从第2行开始读）
     * @param filePath excel文件的绝对路径
     */
    public static List<Map<Integer, String>> syncRead(String filePath){
        return EasyExcelFactory.read(filePath).sheet().doReadSync();
    }

    /**
     * 同步无模型读（自定义读取sheetX，从第2行开始读）
     * @param filePath excel文件的绝对路径
     * @param sheetNo sheet页号，从0开始
     */
    public static List<Map<Integer, String>> syncRead(String filePath, Integer sheetNo){
        return EasyExcelFactory.read(filePath).sheet(sheetNo).doReadSync();
    }

    /**
     * 同步无模型读（指定sheet和表头占的行数）
     * @param filePath 文件路径
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static List<Map<Integer, String>> syncRead(String filePath, Integer sheetNo, Integer headRowNum){
        return EasyExcelFactory.read(filePath).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    /**
     * 同步无模型读（指定sheet和表头占的行数）
     * @param inputStream  文件输入流
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static List<Map<Integer, String>> syncRead(InputStream inputStream, Integer sheetNo, Integer headRowNum){
        return EasyExcelFactory.read(inputStream).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    /**
     * 同步无模型读（指定sheet和表头占的行数）
     * @param file  文件
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static List<Map<Integer, String>> syncRead(File file, Integer sheetNo, Integer headRowNum){
        return EasyExcelFactory.read(file).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }
//====================================================无JAVA模型读取excel数据===============================================================

//====================================================将excel数据同步到JAVA模型属性里===============================================================
    /**
     * 同步按模型读（默认读取sheet0,从第2行开始读）
     * @param filePath 文件路径
     * @param clazz 模型的类类型（excel数据会按该类型转换成对象）
     */
    public static List<T> syncReadModel(String filePath, Class<T> clazz){
        return EasyExcelFactory.read(filePath).sheet().head(clazz).doReadSync();
    }

    /**
     * 同步按模型读（默认表头占一行，从第2行开始读）
     * @param filePath  文件路径
     * @param clazz 模型的类类型（excel数据会按该类型转换成对象）
     * @param sheetNo sheet页号，从0开始
     */
    public static List<T> syncReadModel(String filePath, Class<T> clazz, Integer sheetNo){
        return EasyExcelFactory.read(filePath).sheet(sheetNo).head(clazz).doReadSync();
    }

    /**
     * 同步按模型读（指定sheet和表头占的行数）
     * @param inputStream 文件输入流
     * @param clazz 模型的类类型（excel数据会按该类型转换成对象）
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static List<T> syncReadModel(InputStream inputStream, Class<T> clazz, Integer sheetNo, Integer headRowNum){
        return EasyExcelFactory.read(inputStream).sheet(sheetNo).headRowNumber(headRowNum).head(clazz).doReadSync();
    }

    /**
     * 同步按模型读（指定sheet和表头占的行数）
     * @param file 文件
     * @param clazz 模型的类类型（excel数据会按该类型转换成对象）
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static List<T> syncReadModel(File file, Class<T> clazz, Integer sheetNo, Integer headRowNum){
        return EasyExcelFactory.read(file).sheet(sheetNo).headRowNumber(headRowNum).head(clazz).doReadSync();
    }

    /**
     * 同步按模型读（指定sheet和表头占的行数）
     * @param filePath  文件路径
     * @param clazz 模型的类类型（excel数据会按该类型转换成对象）
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static List<T> syncReadModel(String filePath, Class<T> clazz, Integer sheetNo, Integer headRowNum){
        return EasyExcelFactory.read(filePath).sheet(sheetNo).headRowNumber(headRowNum).head(clazz).doReadSync();
    }

    /**
     * 异步无模型读（默认读取sheet0,从第2行开始读）
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param filePath 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static void asyncRead(String filePath, AnalysisEventListener<T> excelListener){
        EasyExcelFactory.read(filePath, excelListener).sheet().doRead();
    }

    /**
     * 异步无模型读（默认表头占一行，从第2行开始读）
     * @param filePath 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param sheetNo sheet页号，从0开始
     */
    public static void asyncRead(String filePath, AnalysisEventListener<T> excelListener, Integer sheetNo){
        EasyExcelFactory.read(filePath, excelListener).sheet(sheetNo).doRead();
    }

    /**
     * 异步无模型读（指定sheet和表头占的行数）
     * @param inputStream  文件输入流
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static void asyncRead(InputStream inputStream, AnalysisEventListener<T> excelListener, Integer sheetNo, Integer headRowNum){
        EasyExcelFactory.read(inputStream, excelListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    /**
     * 异步无模型读（指定sheet和表头占的行数）
     * @param file 文件
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static void asyncRead(File file, AnalysisEventListener<T> excelListener, Integer sheetNo, Integer headRowNum){
        EasyExcelFactory.read(file, excelListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    /**
     * 异步无模型读（指定sheet和表头占的行数）
     * @param filePath  文件路径
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static void asyncRead(String filePath, AnalysisEventListener<T> excelListener, Integer sheetNo, Integer headRowNum){
        EasyExcelFactory.read(filePath, excelListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    /**
     * 异步按模型读取（默认读取sheet0,从第2行开始读）
     * @param filePath  文件路径
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param clazz 模型的类类型（excel数据会按该类型转换成对象）
     */
    public static void asyncReadModel(String filePath, AnalysisEventListener<T> excelListener, Class<T> clazz){
        EasyExcelFactory.read(filePath, clazz, excelListener).sheet().doRead();
    }

    /**
     * 异步按模型读取（默认表头占一行，从第2行开始读）
     * @param filePath  文件路径
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param clazz 模型的类类型（excel数据会按该类型转换成对象）
     * @param sheetNo  sheet页号，从0开始
     */
    public static void asyncReadModel(String filePath, AnalysisEventListener<T> excelListener, Class<T> clazz, Integer sheetNo){
        EasyExcelFactory.read(filePath, clazz, excelListener).sheet(sheetNo).doRead();
    }

    /**
     * 异步按模型读取
     * @param inputStream  文件输入流
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param clazz 模型的类类型（excel数据会按该类型转换成对象）
     * @param sheetNo  sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static void asyncReadModel(InputStream inputStream, AnalysisEventListener<T> excelListener, Class<T> clazz, Integer sheetNo, Integer headRowNum){
        EasyExcelFactory.read(inputStream, clazz, excelListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    /**
     * 异步按模型读取
     * @param file  文件
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param clazz 模型的类类型（excel数据会按该类型转换成对象）
     * @param sheetNo  sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static void asyncReadModel(File file, AnalysisEventListener<T> excelListener, Class<T> clazz, Integer sheetNo, Integer headRowNum){
        EasyExcelFactory.read(file, clazz, excelListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    /**
     * 异步按模型读取
     * @param filePath 文件路径
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param clazz 模型的类类型（excel数据会按该类型转换成对象）
     * @param sheetNo  sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static void asyncReadModel(String filePath, AnalysisEventListener<T> excelListener, Class<T> clazz, Integer sheetNo, Integer headRowNum){
        EasyExcelFactory.read(filePath, clazz, excelListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    /**
     * 无模板写文件
     * @param filePath  文件路径
     * @param head 表头数据
     * @param data 表内容数据
     */
    public static void write(String filePath, List<List<String>> head, List<T> data){
        EasyExcelFactory.write(filePath).head(head).sheet().doWrite(data);
    }

    /**
     * 响应给浏览器的excel文件
     * @param response servlet响应对象
     * @param fileName 设置文件明
     * @param list  数据列表
     * @param clazz 响应类
     */
    public static<T> void writerExcel(HttpServletResponse response, String fileName, List<T> list, Class<T> clazz) throws IOException {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("UTF-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyExcel没有关系
            String encode = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + encode + ".xlsx");
            EasyExcelFactory.write(response.getOutputStream(), clazz)
                    //设置不自动关闭流
                    .autoCloseStream(Boolean.FALSE)
                    .sheet(fileName)
                    .doWrite(list);
        } catch (Exception e) {
            //重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
        }
    }


    /**
     * 无模板写文件
     * @param filePath 文件路径
     * @param head 表头数据
     * @param data 表内容数据
     * @param sheetNo sheet页号，从0开始
     * @param sheetName sheet名称
     */
    public static void write(String filePath, List<List<String>> head, List<List<Object>> data, Integer sheetNo, String sheetName){
        EasyExcelFactory.write(filePath).head(head).sheet(sheetNo, sheetName).doWrite(data);
    }


    /**
     * 根据excel模板文件写入文件
     *
     * @param filePath         文件路径
     * @param templateFileName 模板文件名
     * @param headClazz        pojo类
     * @param data             数据
     */
    public static void writeTemplate(String filePath, String templateFileName, Class<T> headClazz, List<T> data){
        EasyExcelFactory.write(filePath, headClazz).withTemplate(templateFileName).sheet().doWrite(data);
    }


    /**
     * 根据excel模板文件写入文件
     *
     * @param filePath         文件路径
     * @param templateFileName 模板文件名
     * @param data             数据
     */
    public static void writeTemplate(String filePath, String templateFileName, List<T> data){
        EasyExcelFactory.write(filePath).withTemplate(templateFileName).sheet().doWrite(data);
    }


    /**
     * 按模板写文件
     *
     * @param filePath  模板文件路径
     * @param headClazz 表头模板
     * @param data      数据
     */
    public static void write(String filePath, Class<T> headClazz, List<T> data){
        EasyExcelFactory.write(filePath, headClazz).sheet().doWrite(data);
    }

    /**
     * 按模板写文件
     * @param filePath  模板文件路径
     * @param headClazz 表头模板
     * @param data 数据
     * @param sheetNo sheet页号，从0开始
     * @param sheetName sheet名称
     */
    public static void write(String filePath, Class<T> headClazz, List<T> data, Integer sheetNo, String sheetName){
        EasyExcelFactory.write(filePath, headClazz).sheet(sheetNo, sheetName).doWrite(data);
    }

    /**
     * 按模板写文件
     * @param filePath 模板文件路径
     * @param headClazz 表头模板
     * @param data 数据
     * @param writeHandler 自定义的处理器，比如设置table样式，设置超链接、单元格下拉框等等功能都可以通过这个实现（需要注册多个则自己通过链式去调用）
     * @param sheetNo sheet页号，从0开始
     * @param sheetName sheet名称
     */
    public static void write(String filePath, Class<T> headClazz, List<T> data, WriteHandler writeHandler, Integer sheetNo, String sheetName){
        EasyExcelFactory.write(filePath, headClazz).registerWriteHandler(writeHandler).sheet(sheetNo, sheetName).doWrite(data);
    }

    /**
     * 按模板写文件（包含某些字段）
     * @param filePath 文件路径
     * @param headClazz 表头模板
     * @param data 数据
     * @param includeCols 包含字段集合，根据字段名称显示
     * @param sheetNo sheet页号，从0开始
     * @param sheetName sheet名称
     */
    public static void writeInclude(String filePath, Class<T> headClazz, List<T> data, Set<String> includeCols, Integer sheetNo, String sheetName){
        EasyExcelFactory.write(filePath, headClazz).includeColumnFiledNames(includeCols).sheet(sheetNo, sheetName).doWrite(data);
    }

    /**
     * 按模板写文件（排除某些字段）
     * @param filePath 文件路径
     * @param headClazz 表头模板
     * @param data 数据
     * @param excludeCols 过滤排除的字段，根据字段名称过滤
     * @param sheetNo sheet页号，从0开始
     * @param sheetName sheet名称
     */
    public static void writeExclude(String filePath, Class<T> headClazz, List<T> data, Set<String> excludeCols, Integer sheetNo, String sheetName){
        EasyExcelFactory.write(filePath, headClazz).excludeColumnFiledNames(excludeCols).sheet(sheetNo, sheetName).doWrite(data);
    }

    /**
     * 多个sheet页的数据链式写入
     * ExcelUtil.writeWithSheets(outputStream)
     *                 .writeModel(ExcelModel.class, excelModelList, "sheetName1")
     *                 .write(headData, data,"sheetName2")
     *                 .finish();
     * @param outputStream 文件输入流
     */
    public static EasyExcelWriterFactory writeWithSheets(OutputStream outputStream){
        return new EasyExcelWriterFactory(outputStream);
    }

    /**
     * 多个sheet页的数据链式写入
     * ExcelUtil.writeWithSheets(file)
     *                 .writeModel(ExcelModel.class, excelModelList, "sheetName1")
     *                 .write(headData, data,"sheetName2")
     *                 .finish();
     * @param file 文件
     */
    public static EasyExcelWriterFactory writeWithSheets(File file){
        return new EasyExcelWriterFactory(file);
    }

    /**
     * 多个sheet页的数据链式写入
     * ExcelUtil.writeWithSheets(filePath)
     *                 .writeModel(ExcelModel.class, excelModelList, "sheetName1")
     *                 .write(headData, data,"sheetName2")
     *                 .finish();
     * @param filePath  文件路径
     */
    public static EasyExcelWriterFactory writeWithSheets(String filePath){
        return new EasyExcelWriterFactory(filePath);
    }

    /**
     * 多个sheet页的数据链式写入（失败了会返回一个有部分数据的Excel）
     * ExcelUtil.writeWithSheets(response, exportFileName)
     *                 .writeModel(ExcelModel.class, excelModelList, "sheetName1")
     *                 .write(headData, data,"sheetName2")
     *                 .finish();
     * @param response 浏览器输出流
     * @param exportFileName 导出的文件名称
     */
    public static <T> void writeWithSheetsWeb(HttpServletResponse response,String exportFileName,Class<T> clazz,List<T> list) throws IOException {
        String time = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
        //添加响应头信息
        response.setHeader("Content-disposition", "attachment; filename=" + exportFileName+"-"+time+".xlsx");
        //设置类型
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        //设置头
        response.setHeader("Pragma", "No-cache");
        //设置头
        response.setHeader("Cache-Control", "no-cache");
        //设置日期头
        response.setDateHeader("Expires", 0);
        List<List<T>> partition = ListUtil.partition(list, 10000);
        ////必须放到循环外，否则会刷新流
        //ExcelWriter excelWriter= EasyExcelFactory.write(response.getOutputStream()).build();
        //for (int i = 0; i < partition.size(); i++) {
        //    WriteSheet writeSheet = EasyExcelUtil.writerSheet(i, "合同" + (i + 1))
        //            .head(clazz)
        //            .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
        //            .build();
        //    excelWriter.write(partition,writeSheet);
        //}
        //excelWriter.finish();

        // 这里 指定文件
        ExcelWriter excelWriter = EasyExcelFactory.write(response.getOutputStream(), clazz)
                .autoCloseStream(Boolean.FALSE)
                .build();
        // 去调用写入实际使用时根据数据库分页的总的页数来。这里最终会写到sheet里面
        for (int i = 0; i < partition.size(); i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(i, "用户" + i).build();
            excelWriter.write(partition.get(i), writeSheet);
        }
        //刷新流
        excelWriter.finish();
        excelWriter.close();


    }

    public static void downloadExcel(HttpServletResponse response,String fileName,Class<?> clazz,List<T> list){
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("UTF-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyExcel没有关系
            String encode = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + encode + ".xlsx");
            EasyExcelFactory.write(response.getOutputStream(), clazz)
                    //设置不自动关闭流
                    .autoCloseStream(Boolean.FALSE)
                    .sheet(fileName)
                    .doWrite(list);
        } catch (Exception e) {
            //重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
        }
    }

    /**
     * 多个sheet页的数据链式写入（失败了会返回一个有部分数据的Excel）
     * ExcelUtil.writeWithSheets(response, exportFileName)
     *                 .writeModel(ExcelModel.class, excelModelList, "sheetName1")
     *                 .write(headData, data,"sheetName2")
     *                 .finish();
     * @param response 浏览器输出流
     * @param exportFileName 导出的文件名称
     */
    public static EasyExcelWriterFactory writeWithSheetsDownload(HttpServletResponse response, String exportFileName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        String fileName = URLEncoder.encode(exportFileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        return new EasyExcelWriterFactory(response.getOutputStream());
    }

}
