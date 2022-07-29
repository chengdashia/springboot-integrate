package com.example.wxjava.controller;

import com.example.wxjava.common.result.R;
import com.example.wxjava.domain.vo.wx.WxKefuMessageVo;
import com.example.wxjava.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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


    /**
     *发送客服消息
     * @param entity
     * @return
     */
    @PostMapping("/sendKefuMessage")
    public R sendKefuMessage(@RequestBody WxKefuMessageVo entity) {
        return messageService.sendKefuMessage(entity);
    }

    ///**
    // * 发送客服消息-微信回调
    // * @param entity
    // * @return
    // */
    //@RequestMapping("/sendKefuMessageBack")
    //public String sendKefuMessageBack(WxKefuMessageBackVo entity) {
    //    return messageService.sendKefuMessageBack(entity);
    //}

    /**
     * 发送客服消息-微信回调
     * @return
     */
    @RequestMapping("/sendKefuMessageBack")
    public void sendKefuMessageBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(StringUtils.isNotBlank(request.getParameter("signature"))){
            request.getRequestURL();
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            log.info("signature: "+signature);
            log.info("timestamp: "+timestamp);
            log.info("nonce: "+nonce);
            log.info("echostr: "+echostr);
            response.getOutputStream().println(echostr);

        }
    }


}
