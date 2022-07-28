package com.example.wxjava.service;

import com.example.wxjava.common.result.R;
import com.example.wxjava.domain.vo.wx.WxKefuMessageBackVo;
import com.example.wxjava.domain.vo.wx.WxKefuMessageVo;

/**
 * @author 成大事
 * @since 2022/7/27 23:11
 */
public interface MessageService {
    /**
     * 发送模板消息
     * @return
     */
    R sendMessage();
    /**
     * 发送客服消息
     * @param entity
     * @return
     */
    R sendKefuMessage(WxKefuMessageVo entity);

    /**
     * 发送客服消息-微信回调
     * @param entity
     * @return
     */
    String sendKefuMessageBack(WxKefuMessageBackVo entity);
}
