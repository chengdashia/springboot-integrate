package com.example.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author 成大事
 * @since 2022/8/16 12:21
 */
public class PdfUtils {

    private static final String PDF = "E:\\test\\pdf\\农产品购销合同.pdf";
    private static final String TARGET_FILE = "E:\\test\\pdf\\2.pdf";
    /**
     * @param map 需要填充的字段
     * @param sourceFile  原文件路径
     * @param targetFile  目标文件路径
     * @throws IOException IO异常
     */
    public static void genPdf(Map<String, String> map, String sourceFile, String targetFile) throws IOException {
        sourceFile = PDF;
        File templateFile = new File(sourceFile);
        targetFile = TARGET_FILE;
        fillParam(map, FileUtils.readFileToByteArray(templateFile), targetFile);
    }


    /**
     *
     * 使用map中的参数填充pdf，map中的key和pdf表单中的field对应
     * @param fieldValueMap    字段值映射
     * @param file             文件
     * @param contractFileName 合同文件名
     */
    public static  void fillParam(Map<String, String> fieldValueMap,byte[] file, String contractFileName) {
        //输出流
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(contractFileName);
            //获取PdfReader对象,获取模板位置
            PdfReader reader = null;
            /* 将要生成的目标PDF文件名称 */
            PdfStamper stamper = null;
            BaseFont base = null;
            //取出模板中的所有字段
            AcroFields acroFields = null;
            // 获取存在resources目录下的pdf模板位置 URL
            //java.net.URL file = PdfUtils.class.getClassLoader().getResource("CONTRACT.pdf");
            try {
                reader = new PdfReader(file);
                stamper = new PdfStamper(reader, fos);
                stamper.setFormFlattening(true);
                //简体中文字体
                base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                acroFields = stamper.getAcroFields();

                for (String key : acroFields.getFields().keySet()) {
                    acroFields.setFieldProperty(key, "textfont", base, null);
                    //字体大小
                    acroFields.setFieldProperty(key, "textsize", new Float(12), null);
                }
                if (fieldValueMap != null) {
                    for (String fieldName : fieldValueMap.keySet()) {
                        if (StringUtils.isNotBlank(fieldValueMap.get(fieldName))) {
                            //获取map中key对应的Value是否为On，若是则勾选复选框
                            if (fieldValueMap.get(fieldName).equals("On") || fieldValueMap.get(fieldName) == "On") {
                                acroFields.setField(fieldName, fieldValueMap.get(fieldName),"true");
                            }else{
                                acroFields.setField(fieldName, fieldValueMap.get(fieldName));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (stamper != null) {
                    stamper.close();
                }
                if (reader != null) {
                    reader.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fos);
        }
    }

    public void addImg(String imgUrl) throws DocumentException, IOException {

        //如果图片放在resources目录下需要这么写
        // String imgUrl = new ClassPathResource("static/IMG_5809.JPG").getURL().getPath();
        //循环添加公章图片
        //获取PdfReader对象,获取模板位置
        PdfReader reader = null;
        /* 将要生成的目标PDF文件名称 */
        PdfStamper stamper = null;
        AcroFields acroFields = stamper.getAcroFields();
        //获取图片域名
        AcroFields.FieldPosition position = acroFields.getFieldPositions("key").get(0);
        //通过域名获取所在页和坐标，左下角为起点
        int pageNo  = position.page;
        Rectangle signRect = position.position;
        float x = signRect.getLeft();
        float y = signRect.getBottom();
        //读图片
        Image image = Image.getInstance("");
        //获取操作页面
        PdfContentByte under = stamper.getOverContent(pageNo);
        //根据域的大小缩放图片
        image.scaleToFit(signRect.getWidth(),signRect.getHeight());
        //添加图片
        image.setAbsolutePosition(x,y);
        under.addImage(image);
        //for(String key : imgURLMap.keySet()) {
        //    String value = imgURLMap.get(key).toString();
        //
        //    System.out.println("--"+key+"---"+value);
        //}
    }

}
