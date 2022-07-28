package com.example.tools.desensitize.method1.rule;

/**
 * @author 成大事
 * @since 2022/7/28 22:59
 */
public class UserNameRule extends BaseRule{
    /**
     * 仅显示最后一个汉字
     */
    @Override
    void initRule() {
        setRule(new RuleItem()
                .setRegex("\\S*(\\S)")
                .setFormat("**$1"));
    }
}
