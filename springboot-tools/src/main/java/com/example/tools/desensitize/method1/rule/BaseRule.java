package com.example.tools.desensitize.method1.rule;

import lombok.Data;

import java.util.function.Function;

/**
 * @author 成大事
 * @since 2022/7/28 22:54
 */
@Data
public abstract class BaseRule implements Function<String, String> {

    /**
     * 脱敏规则对象
     */
    private RuleItem rule;

    public String apply(String str) {
        if (null == str) {
            return null;
        }
        //初始化脱敏规则
        initRule();
        if (null == rule || null == rule.getRegex() || null == rule.getFormat()) {
            return str;
        }
        //正则替换
        return str.replaceAll(rule.getRegex(), rule.getFormat());
    }

    abstract void initRule();

}
