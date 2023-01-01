package com.dong.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 成大事
 * @since 2022/12/31 13:38
 */
@RestController
@RequestMapping("/testController")
public class UserController {

    @GetMapping("/userLogin")
    public String userLogin(String name, String pwd, @RequestParam(defaultValue = "false") boolean remember){
        //1、获取subject 对象
        Subject subject = SecurityUtils.getSubject();
        //2、封装请求数据到token
        AuthenticationToken token = new UsernamePasswordToken(name,pwd,remember);
        //3、调用login方法进行登录认证
        try {
            subject.login(token);
            return "登录成功";
        }catch (AuthenticationException e){
            e.printStackTrace();
            System.out.println("登录失败");
            return "登录失败";
        }
    }
}
