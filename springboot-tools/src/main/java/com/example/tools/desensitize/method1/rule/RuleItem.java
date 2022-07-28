package com.example.tools.desensitize.method1.rule;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 成大事
 * @since 2022/7/28 22:54
 */
@Data
@Accessors(chain = true)
public class RuleItem {

    /**
     * 正则
     */
    private String regex;

    /**
     * 格式化显示
     */
    private String format;
}
