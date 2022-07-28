package com.example.tools.desensitize.method1.annotation;

import com.example.tools.desensitize.method1.rule.BaseRule;
import com.example.tools.desensitize.method1.rule.RuleItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author 成大事
 * @since 2022/7/28 22:53
 */
@Slf4j
public class MyJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {

    /**
     * 脱敏规则
     */
    private BaseRule rule;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(rule.apply(value));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        //获取对象属性上的自定义注解
        CustomSerializer customSerializer = property.getAnnotation(CustomSerializer.class);
        if (null != customSerializer) {
            try {
                //根据注解的配置信息，创建对应脱敏规则处理类
                this.rule = customSerializer.value().newInstance();
                //如果正则信息不为空，则使用注解上的正则初始化到对应的脱敏规则处理类中
                if (isNotBlank(customSerializer.pattern()) && isNotBlank(customSerializer.format())) {
                    this.rule.setRule(new RuleItem()
                            .setRegex(customSerializer.pattern())
                            .setFormat(customSerializer.format()));
                }
                return this;
            } catch (Exception e) {
                log.error("json转换处理异常", e);
            }
        }
        return prov.findValueSerializer(property.getType(), property);
    }

    private boolean isNotBlank(String str) {
        return null != str && str.trim().length() > 0;
    }
}
