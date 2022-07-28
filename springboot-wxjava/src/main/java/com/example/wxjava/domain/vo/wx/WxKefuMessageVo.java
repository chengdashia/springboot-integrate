package com.example.wxjava.domain.vo.wx;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 微信客服消息参数
 * @author 成大事
 * @since 2022/7/28 8:12
 */
@Data
@Accessors(chain = true)
public class WxKefuMessageVo {
    /**
     * 消息内容
     */
    private String content;
}
