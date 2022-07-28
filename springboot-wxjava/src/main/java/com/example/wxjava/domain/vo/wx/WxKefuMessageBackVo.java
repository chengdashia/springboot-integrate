package com.example.wxjava.domain.vo.wx;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 微信客服消息回调参数
 * @author 成大事
 * @since 2022/7/28 8:10
 */
@Data
@Accessors(chain = true)
public class WxKefuMessageBackVo {
    /**
     * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     */
    private String signature;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 随机数
     */
    private String nonce;
    /**
     * 随机字符串
     */
    private String echostr;
}
