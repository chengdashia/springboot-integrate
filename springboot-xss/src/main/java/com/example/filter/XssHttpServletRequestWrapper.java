package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 成大事
 * @since 2022/7/27 13:04
 */
@Slf4j
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        String strHeader = super.getHeader(name);
        if(StringUtils.isEmpty(strHeader)){
            return strHeader;

        }
//        return Jsoup.clean(super.getHeader(name),Whitelist.relaxed());
        return HtmlUtils.htmlEscape(strHeader,"UTF-8");

    }

    @Override
    public String getParameter(String name) {
        String strParameter = super.getParameter(name);
        if(StringUtils.isEmpty(strParameter)){
            return strParameter;
        }
        return HtmlUtils.htmlEscape(strParameter,"UTF-8");
    }


    @Override
    public String[] getParameterValues(String name) {

        String[] values = super.getParameterValues(name);
        if(values==null || name.toLowerCase().endsWith("content")){
            return values;
        }
        int length = values.length;
        String[] escapseValues = new String[length];
        for(int i = 0;i<length;i++){
            //过滤一切可能的xss攻击字符串
            escapseValues[i] = HtmlUtils.htmlEscape(values[i],"UTF-8");
            if(!StringUtils.equals(escapseValues[i],values[i])){
                log.info("xss字符串过滤前："+values[i]+"\r\n"+"过滤后："+escapseValues[i]);
            }
        }
        return escapseValues;
    }
    /**
     * 重写getInputStream方法
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        log.debug("参数去空格处理");

        //非json类型，直接返回
        if (!super.getHeader(HttpHeaders.CONTENT_TYPE).equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
            return super.getInputStream();
        }
        //从输入流中取出body串, 如果为空，直接返回
        String reqBodyStr = IOUtils.toString(super.getInputStream(), "utf-8");
        if (StringUtils.isEmpty(reqBodyStr)) {
            return super.getInputStream();
        }
        log.debug("转化前reqest body:{}", reqBodyStr);

        //reqBodyStr转为Map对象
        Map<String, Object> paramMap = new ObjectMapper().readValue(reqBodyStr, new TypeReference<HashMap<String, Object>>() {
        });
        //去首尾空格
        Map<String,Object> trimedMap = recursiveTrim(paramMap);
        log.debug("转化后reqest body：" + JSON.toJSONString(trimedMap));

        //重新构造一个输入流对象
        byte[] bytes = JSON.toJSONString(trimedMap).getBytes("utf-8");
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        return new MyServletInputStream(bis);
    }

    /**
     * 只处理String, List, Map
     *
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String,Object> recursiveTrim(Map<String, Object> param) {
        for (String key : param.keySet()) {

            Object vo = param.get(key);
            if (null == vo) {
                //key对应的值为空
                continue;
            }
            if (vo instanceof String) {
                if (key.toLowerCase().endsWith("desc")||key.toLowerCase().endsWith("content")||key.toLowerCase().endsWith("introduce")){
                    continue;
                }
                //key对应的值为String类型, 过滤后重新放入map
                param.put(key, HtmlUtils.htmlEscape((String) vo,"UTF-8"));
            } else if (vo instanceof Map) {
                param.put(key, recursiveTrim((Map<String,Object>) vo));
            } else if (vo instanceof List) {
                //key对应的值为List类型
                List<Object> alist = (List<Object>) vo;
                for (int i = 0; i < alist.size(); i++) {
                    //遍历list
                    Object vol = alist.get(i);
                    if (vol instanceof String) {
                        //list里的元素为String, 过滤
                        alist.set(i, HtmlUtils.htmlEscape((String) vol,"UTF-8"));
                    } else if (vol instanceof Map) {
                        //list里的元素为Map, 递归处理
                        alist.set(i, recursiveTrim((Map<String,Object>) vol));
                    }
                }
                param.put(key, vo);
            }
        }
        return param;

    }


    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
