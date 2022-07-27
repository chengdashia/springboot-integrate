package com.example.wxjava.controller;

import com.example.wxjava.common.result.R;
import com.example.wxjava.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 成大事
 * @since 2022/7/27 23:11
 */
@Slf4j
@RestController
@RequestMapping("/wx/msg")
@AllArgsConstructor
public class WxMessageController {

    private final MessageService messageService;


    @PostMapping("/sendMessage")
    public R sendMsg() {
        return messageService.sendMessage();
    }

}
