//package com.example.excel;
//
//import cn.afterturn.easypoi.cache.manager.POICacheManager;
//import cn.afterturn.easypoi.excel.ExcelExportUtil;
//import cn.afterturn.easypoi.excel.ExcelImportUtil;
//import cn.afterturn.easypoi.excel.ExcelXorHtmlUtil;
//import cn.afterturn.easypoi.excel.entity.ExcelToHtmlParams;
//import cn.afterturn.easypoi.excel.entity.ExportParams;
//import cn.afterturn.easypoi.excel.entity.ImportParams;
//import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
//import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
//import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
//import cn.afterturn.easypoi.word.WordExportUtil;
//import cn.afterturn.easypoi.word.parse.ParseWord07;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URLEncoder;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.NoSuchElementException;
//
//;
//
///**
// * @author 成大事
// * @since 2022/8/16 9:00
// * Excel 导入导出工具类
// */
//public class ExcelUtils {
//
//    private ExcelUtils() {
//
//    }
//
//    /**
//     * excel 导出
//     *
//     * @param list     数据列表
//     * @param fileName 导出时的excel名称
//     * @param response 写到浏览器的输出流
//     */
//    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws IOException {
//        defaultExport(list, fileName, response);
//    }
//
//    /**
//     * 默认的 excel 导出
//     *
//     * @param list     数据列表
//     * @param fileName 导出时的excel名称
//     * @param response 写到浏览器的输出流
//     */
//    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws IOException {
//        //把数据添加到excel表格中
//        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.XSSF);
//        downLoadExcel(fileName, response, workbook);
//    }
//
//    /**
//     * excel 导出
//     *
//     * @param list         数据列表
//     * @param pojoClass    pojo类型
//     * @param fileName     导出时的excel名称
//     * @param response     写到浏览器的输出流
//     * @param exportParams 导出参数（标题、sheet名称、是否创建表头，表格类型）
//     */
//    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) throws IOException {
//        //把数据添加到excel表格中
//        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
//        downLoadExcel(fileName, response, workbook);
//    }
//
//
//
//    /**
//     * excel 导出
//     *
//     * @param list         数据列表
//     * @param pojoClass    pojo类型
//     * @param fileName     导出时的excel名称
//     * @param exportParams 导出参数（标题、sheet名称、是否创建表头，表格类型）
//     * @param response     写到浏览器的输出流
//     */
//    public static void exportExcel(List<?> list, Class<?> pojoClass, String fileName, ExportParams exportParams, HttpServletResponse response) throws IOException {
//        defaultExport(list, pojoClass, fileName, response, exportParams);
//    }
//
//    /**
//     * excel 导出
//     *
//     * @param list      数据列表
//     * @param title     表格内数据标题
//     * @param sheetName sheet名称
//     * @param pojoClass pojo类型
//     * @param fileName  导出时的excel名称
//     * @param response  写到浏览器的输出流
//     */
//    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, HttpServletResponse response) throws IOException {
//        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName, ExcelType.XSSF));
//    }
//
//    /**
//     * 根据模板生成excel后导出
//     *
//     * @param templatePath 模板路径
//     * @param map          数据集合
//     * @param fileName     文件名
//     * @param response     写到浏览器的输出流
//     */
//    public static void exportExcel(TemplateExportParams templatePath, Map<String, Object> map, String fileName, HttpServletResponse response) throws IOException {
//        Workbook workbook = ExcelExportUtil.exportExcel(templatePath, map);
//        downLoadExcel(fileName, response, workbook);
//    }
//
//    /**
//     * excel 导出
//     *
//     * @param list           数据列表
//     * @param title          表格内数据标题
//     * @param sheetName      sheet名称
//     * @param pojoClass      pojo类型
//     * @param fileName       导出时的excel名称
//     * @param isCreateHeader 是否创建表头
//     * @param response       写到浏览器的输出流
//     */
//    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response) throws IOException {
//        ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.XSSF);
//        exportParams.setCreateHeadRows(isCreateHeader);
//        defaultExport(list, pojoClass, fileName, response, exportParams);
//    }
//
//
//    ///**
//    // * excel下载
//    // *
//    // * @param fileName 下载时的文件名称
//    // * @param response 写到浏览器的输出流
//    // * @param workbook excel数据
//    // */
//    //private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws IOException {
//    //    try {
//    //        response.setCharacterEncoding("UTF-8");
//    //        response.setHeader("content-Type", "application/vnd.ms-excel");
//    //        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
//    //        workbook.write(response.getOutputStream());
//    //    } catch (Exception e) {
//    //        throw new IOException(e.getMessage());
//    //    }
//    //}
//
//    /**
//     * excel下载
//     *
//     * @param fileName 下载时的文件名称
//     * @param response 写到浏览器的输出流
//     * @param workbook excel数据
//     */
//    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws IOException {
//        try {
//            response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-Type", "application/vnd.ms-excel");
//            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
//            workbook.write(response.getOutputStream());
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//    /**
//     * word下载
//     *
//     * @param fileName 下载时的文件名称
//     * @param response 写到浏览器的输出流
//     * @param doc      文档
//     */
//    private static void downLoadWord(String fileName, HttpServletResponse response, XWPFDocument doc) throws IOException {
//        try {
//            response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-Type", "application/msword");
//            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".docx", "UTF-8"));
//            doc.write(response.getOutputStream());
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//
//    /**
//     * excel 导入
//     *
//     * @param file      excel文件
//     * @param pojoClass pojo类型
//     * @param <T>       需要接收的类
//     * @return List<T>
//     */
//    public static <T> List<T> importExcel(MultipartFile file, Class<T> pojoClass) throws IOException {
//        return importExcel(file, 1, 1, pojoClass);
//    }
//
//
//    /**
//     * 导入excel
//     *
//     * @param filePath   文件路径
//     * @param titleRows  标题行
//     * @param headerRows 标题行
//     * @param pojoClass  pojo类
//     * @return {@link List}<{@link T}>
//     * @throws IOException IO异常
//     */
//    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws IOException {
//        if (StringUtils.isBlank(filePath)) {
//            return Collections.emptyList();
//        }
//        ImportParams params = new ImportParams();
//        params.setTitleRows(titleRows);
//        params.setHeadRows(headerRows);
//        params.setNeedSave(true);
//        params.setSaveUrl("");
//        try {
//            return ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
//        } catch (NoSuchElementException e) {
//            throw new IOException("模板不能为空");
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//
//    /**
//     * 导入excel
//     *
//     * @param file       上传的文件
//     * @param titleRows  表格内数据标题行
//     * @param headerRows 表头行
//     * @param pojoClass  pojo类型
//     * @return {@link List}<{@link T}>
//     * @throws IOException IO异常
//     */
//    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws IOException {
//        if (file == null) {
//            return Collections.emptyList();
//        }
//        try {
//            return importExcel(file.getInputStream(), titleRows, headerRows, pojoClass);
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//
//    /**
//     * 导入excel
//     *
//     * @param inputStream 文件输入流
//     * @param titleRows   表格内数据标题行
//     * @param headerRows  表头行
//     * @param pojoClass   pojo类型
//     * @return {@link List}<{@link T}>
//     * @throws IOException IO异常
//     */
//    public static <T> List<T> importExcel(InputStream inputStream, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws IOException {
//        if (inputStream == null) {
//            return Collections.emptyList();
//        }
//        ImportParams params = new ImportParams();
//        params.setTitleRows(titleRows);
//        params.setHeadRows(headerRows);
//        params.setSaveUrl("/excel/");
//        params.setNeedSave(true);
//        try {
//            return ExcelImportUtil.importExcel(inputStream, pojoClass, params);
//        } catch (NoSuchElementException e) {
//            throw new IOException("excel文件不能为空");
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//
//    /**
//     * excel转html预览
//     *
//     * @param filePath 文件路径
//     * @param response 写到浏览器输出流
//     * @throws IOException IO异常
//     */
//    public static void excelToHtml(String filePath, HttpServletResponse response) throws IOException {
//        ExcelToHtmlParams params = new ExcelToHtmlParams(WorkbookFactory.create(POICacheManager.getFile(filePath)), true);
//        response.getOutputStream().write(ExcelXorHtmlUtil.excelToHtml(params).getBytes());
//    }
//
//
//    /**
//     * word模板导出
//     *
//     * @param map          地图
//     * @param templatePath 模板路径
//     * @param fileName     文件名
//     * @param response     回答
//     * @throws Exception 例外
//     */
//    public static void wordTemplateExport(Map<String, Object> map, String templatePath, String fileName, HttpServletResponse response) throws Exception {
//        XWPFDocument doc = WordExportUtil.exportWord07(templatePath, map);
//        downLoadWord(fileName, response, doc);
//    }
//
//
//    /**
//     * word模板导出更多页面
//     *
//     * @param list         列表
//     * @param templatePath 模板路径
//     * @param fileName     文件名
//     * @param response     回答
//     * @throws Exception 例外
//     */
//    public static void wordTemplateExportMorePage(List<Map<String, Object>> list, String templatePath, String fileName, HttpServletResponse response) throws Exception {
//        XWPFDocument doc = new ParseWord07().parseWord(templatePath, list);
//        downLoadWord(fileName, response, doc);
//    }
//
//
//    /**
//     * excel 导入，有错误信息
//     *
//     * @param file      文件
//     * @param pojoClass pojo类
//     * @return {@link ExcelImportResult}<{@link T}>
//     * @throws IOException IO异常
//     */
//    public static <T> ExcelImportResult<T> importExcelMore(MultipartFile file, Class<T> pojoClass) throws IOException {
//        if (file == null) {
//            return null;
//        }
//        try {
//            return importExcelMore(file.getInputStream(), pojoClass);
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//
//    /**
//     * 导入excel更多
//     *
//     * @param inputStream 输入流
//     * @param pojoClass   pojo类
//     * @return {@link ExcelImportResult}<{@link T}>
//     * @throws IOException IO异常
//     */
//    private static <T> ExcelImportResult<T> importExcelMore(InputStream inputStream, Class<T> pojoClass) throws IOException {
//        if (inputStream == null) {
//            return null;
//        }
//        ImportParams params = new ImportParams();
//        //表格内数据标题行
//        params.setTitleRows(1);
//        //表头行
//        params.setHeadRows(1);
//        params.setSaveUrl("");
//        params.setNeedSave(true);
//        params.setNeedVerify(true);
//        try {
//            return ExcelImportUtil.importExcelMore(inputStream, pojoClass, params);
//        } catch (NoSuchElementException e) {
//            throw new IOException("excel文件不能为空");
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//}
