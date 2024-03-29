package com.dong.shiro.config;

import com.dong.shiro.realm.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 成大事
 * @since 2022/12/31 13:27
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private ShiroRealm shiroRealm;


    /**
     * 默认web安全管理器
     * 配置SecurityManager
     * @return {@link DefaultWebSecurityManager}
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        //1、创建defaultWebSecurityManager 对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        //2、创建加密对象、设置相关属性
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //2.1采用md5加密
        matcher.setHashAlgorithmName("md5");
        //2.2迭代加密次数
        matcher.setHashIterations(3);

        //3、将加密对象存储到myRealm中
        shiroRealm.setCredentialsMatcher(matcher);

        //4、将myRealm存入defaultWebSecurityManager 对象
        defaultWebSecurityManager.setRealm(shiroRealm);

        //设置rememberme
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());

        //5、返回
        return defaultWebSecurityManager;
    }

    /**
     * shiro过滤器链定义
     *
     * @return {@link DefaultShiroFilterChainDefinition}
     */
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();

        //设置不认证可以访问的资源
        definition.addPathDefinition("/testController/userLogin","anon");
        definition.addPathDefinition("/login","anon");

        //设置需要进行登录认证的拦截范围
        definition.addPathDefinition("/**","authc");
        return definition;
    }

    /**
     * cookie 属性设置
     *
     * @return {@link SimpleCookie}
     */
    public SimpleCookie rememberMeCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //设置跨域
        //cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30*24*60*60);
        return cookie;
    }

    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("123456789".getBytes());
        return cookieRememberMeManager;
    }



}
