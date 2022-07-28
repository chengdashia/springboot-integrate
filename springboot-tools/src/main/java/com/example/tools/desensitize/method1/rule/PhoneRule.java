package com.example.tools.desensitize.method1.rule;

/**
 * @author 成大事
 * @since 2022/7/28 22:57
 */
public class PhoneRule extends BaseRule{
    /**
     * 仅显示前3位和后4位
     */
    @Override
    void initRule() {
        setRule(new RuleItem()
                .setRegex("(\\d{3})\\d*(\\d{4})")
                .setFormat("$1****$2"));
    }

}
