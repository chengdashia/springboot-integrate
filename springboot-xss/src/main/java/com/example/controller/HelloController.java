package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 成大事
 * @since 2022/7/27 13:00
 */
@RestController
public class HelloController {

    @GetMapping("/test")
    public String test(String param){
        return param;
    }
}
