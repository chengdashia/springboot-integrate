package com.example.tools.desensitize.method2.domain.vo;

import com.example.tools.desensitize.method2.annotation.Desensitize;
import com.example.tools.desensitize.method2.annotation.DesensitizeRuleEnums;
import lombok.Data;

/**
 * @author 成大事
 * @since 2022/7/28 23:20
 */
@Data
public class UserInfo {

    // 用户id
    private Long id;

    // 姓名
    @Desensitize(rule = DesensitizeRuleEnums.CHINESE_NAME)
    private String name;

    // 邮箱
    @Desensitize(rule = DesensitizeRuleEnums.EMAIL)
    private String email;

    // 电话
    @Desensitize(rule = DesensitizeRuleEnums.MOBILE_PHONE)
    private String phone;
}

