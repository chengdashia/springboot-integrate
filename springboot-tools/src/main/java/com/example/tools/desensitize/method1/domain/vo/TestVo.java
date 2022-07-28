//package com.example.tools.desensitize.method1.domain.vo;
//
//import com.example.tools.desensitize.method1.annotation.CustomSerializer;
//import com.example.tools.desensitize.method1.rule.IdCardRule;
//import com.example.tools.desensitize.method1.rule.PasswordRule;
//import com.example.tools.desensitize.method1.rule.PhoneRule;
//import com.example.tools.desensitize.method1.rule.UserNameRule;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
//
//import java.io.Serializable;
//
///**
// * @author 成大事
// * @since 2022/7/28 23:00
// */
//@NoArgsConstructor
//@Data
//@Accessors(chain = true)
//public class TestVo implements Serializable {
//    private static final long serialVersionUID = 1019466745376831818L;
//
//    @CustomSerializer(UserNameRule.class)
//    private String userName;
//
//    @CustomSerializer(PhoneRule.class)
//    private String phone;
//
//    @CustomSerializer(IdCardRule.class)
//    private String idCard;
//
//    @CustomSerializer(PasswordRule.class)
//    private String password;
//
//    /**
//     * 隐藏前面10个字符
//     */
//    @CustomSerializer(pattern = "\\S{10}(\\S*)", format = "**********$1")
//    private String customValue;
//
//}
