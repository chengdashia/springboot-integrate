package com.example.tools.desensitize.method1.rule;

/**
 * @author 成大事
 * @since 2022/7/28 22:55
 */
public class IdCardRule extends BaseRule{
    /**
     * 仅显示前6位和后4位
     */
    @Override
    void initRule() {
        setRule(new RuleItem()
                .setRegex("(\\d{6})\\d*(\\w{4})")
                .setFormat("$1********$2"));
    }
}
