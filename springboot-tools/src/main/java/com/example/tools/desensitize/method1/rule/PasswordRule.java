package com.example.tools.desensitize.method1.rule;

/**
 * @author 成大事
 * @since 2022/7/28 22:57
 */
public class PasswordRule extends BaseRule{
    /**
     * 全部隐藏
     */
    @Override
    public String apply(String str) {
        return "******";
    }

    @Override
    void initRule() {

    }
}
