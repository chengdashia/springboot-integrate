package com.example.wxjava.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.example.wxjava.common.result.R;
import com.example.wxjava.domain.vo.wx.WxKefuMessageBackVo;
import com.example.wxjava.domain.vo.wx.WxKefuMessageVo;
import com.example.wxjava.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //String userId = StpUtil.getLoginIdAsString();
        // 测试
        List<WxMaSubscribeMessage.MsgData> msgData = Arrays.asList(
                new WxMaSubscribeMessage.MsgData("thing1", "嘿嘿"),
                new WxMaSubscribeMessage.MsgData("date3", new DateTime(DateUtil.now(), DatePattern.NORM_DATETIME_FORMAT).toString())
        );
        try {
            WxMaSubscribeMessage message = WxMaSubscribeMessage.builder()
                    .toUser("oDiMW5WNZLBmK1vPsqE4QbawW0Ng")
                    //.toUser(userId)
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

    @Override
    public R sendKefuMessage(WxKefuMessageVo entity) {
        try {
            String openId = "oDiMW5Uov6rAYjjCH3r6jr7Nok7I";
            WxMaKefuMessage message = WxMaKefuMessage.newTextBuilder()
                    .toUser(openId)
                    .content(entity.getContent())
                    .build();
            wxMaService.getMsgService().sendKefuMsg(message);
            return R.ok("发送成功");
        } catch (WxErrorException e) {
            log.error(e.toString());
            return R.error(e.getError().getErrorMsg());
        }
    }

    @Override
    public String sendKefuMessageBack(WxKefuMessageBackVo entity) {
        log.info("微信客服回调：{}", entity);
        return entity.getEchostr();
    }
}
