package com.example.filter;


import com.example.filter.mode2.XssAndSqlFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @author 成大事
 * @since 2022/7/27 13:11
 */
@Configuration
public class FilterConfig  {
    //@Value("${xss.enabled}")
    //private String enabled;
    //
    //@Value("${xss.excludes}")
    //private String excludes;
    //
    //@Value("${xss.urlPatterns}")
    //private String urlPatterns;
    //
    //@Bean
    //public FilterRegistrationBean xssFilterRegistration(){
    //    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    //    registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
    //    registrationBean.setFilter(new XssFilter());
    //    registrationBean.addUrlPatterns(StringUtils.split(urlPatterns,","));
    //    registrationBean.setName("XssFilter");
    //    registrationBean.setOrder(9999);
    //    Map<String,String> initParameters = new HashMap<>();
    //    initParameters.put("excludes",excludes);
    //    initParameters.put("enabled",enabled);
    //    registrationBean.setInitParameters(initParameters);
    //    return registrationBean;
    //}


    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssAndSqlFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }


}
