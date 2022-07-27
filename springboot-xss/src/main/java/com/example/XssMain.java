package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author 成大事
 * @since 2022/7/27 12:59
 */
@ServletComponentScan
@SpringBootApplication
public class XssMain {
    public static void main(String[] args) {
        SpringApplication.run(XssMain.class,args);
    }
}
