package com.example.wxjava.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.example.wxjava.common.result.R;
import com.example.wxjava.config.wxjava.WxMaConfiguration;
import com.example.wxjava.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/7/27 23:11
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MessageServiceImpl implements MessageService {

    private final WxMaService wxMaService;
    @Override
    public R sendMessage() {

        // 测试
        List<WxMaSubscribeMessage.MsgData> msgData = Arrays.asList(
                new WxMaSubscribeMessage.MsgData("thing1", "赵久燚是sb"),
                new WxMaSubscribeMessage.MsgData("date3", new DateTime(DateUtil.now(), DatePattern.NORM_DATETIME_FORMAT).toString())
        );
        try {
            WxMaSubscribeMessage message = WxMaSubscribeMessage.builder()
                    .toUser("oDiMW5Uov6rAYjjCH3r6jr7Nok7I")
                    .templateId("7oklMCAUD0zNAoTWikBOPSwVH2-XKC2-BJVqsUYGxgg")
                    .data(msgData)
                    .build();
            wxMaService.getMsgService().sendSubscribeMsg(message);
            return R.ok("发送成功");
        } catch (WxErrorException e) {
            log.error(e.toString());
            return R.error(e.getError().getErrorMsg());
        }
    }
}
