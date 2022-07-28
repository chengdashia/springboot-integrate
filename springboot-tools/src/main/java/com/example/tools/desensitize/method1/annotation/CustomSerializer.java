package com.example.tools.desensitize.method1.annotation;

import com.example.tools.desensitize.method1.rule.BaseRule;
import com.example.tools.desensitize.method1.rule.DefaultRule;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.*;

/**
 * @author 成大事
 * @since 2022/7/28 22:52
 */
@JacksonAnnotationsInside
@JsonSerialize(using = MyJsonSerializer.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CustomSerializer {

    /**
     * 脱敏规则处理类
     * @return
     */
    Class<? extends BaseRule> value() default DefaultRule.class;

    /**
     * 正则，pattern和format必需同时有值。如果都有值时，优先使用正则进行规则替换
     * @return
     */
    String pattern() default "";

    String format() default "";

}
