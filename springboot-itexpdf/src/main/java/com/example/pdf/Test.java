package com.example.pdf;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 成大事
 * @since 2022/8/16 12:15
 */
public class Test {
    public static void main(String[] args) throws IOException {

        PdfData pdfData = new PdfData()
                .setPartA("张三").setPartB("李四")
                .setType("苹果").setVarieties("红富士")
                .setNums("10.2")
                .setPrice("20.1").setTotalPrice("2032.1")
                .setTotal("贰佰伍拾壹块六毛一")
                .setPartAAddress("河南省洛阳市洛龙区佃庄镇佃庄村")
                .setPartBAddress("贵阳市黔西南自治区长顺县党务村11组")
                .setPartAPhone("17337995232")
                .setPartBPhone("17337995232")
                .setYear(String.valueOf(LocalDateTimeUtil.now().getYear()))
                .setMonth(String.valueOf(LocalDateTimeUtil.now().getMonth()))
                .setDay(String.valueOf(LocalDateTimeUtil.now().getHour()));
        Map<String, String> map = convertBeanToMap(pdfData);


        PdfUtils.genPdf(map,null,null);

        //XEasyPdfDocument pdfDocument = new XEasyPdfDocument("E:\\test\\pdf\\农产品购销合同.pdf");
        //pdfDocument.formFiller().fill(map);
        //pdfDocument.close();

    }

    /**
     * 将对象转成map
     * @param object  对象
     * @return         map
     */
    public static Map<String, String> convertBeanToMap(Object object)
    {
        if(object == null){
            return null;
        }
        Map<String, String> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(object);
                    map.put(key, String.valueOf(value));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
