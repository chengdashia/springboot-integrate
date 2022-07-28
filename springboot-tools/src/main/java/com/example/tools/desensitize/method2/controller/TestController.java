package com.example.tools.desensitize.method2.controller;

import com.example.tools.common.result.R;
import com.example.tools.desensitize.method2.domain.vo.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 成大事
 * @since 2022/7/28 23:20
 */
@RestController
public class TestController {
    @GetMapping("test")
    public R<UserInfo> test() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1004L);
        userInfo.setName("张三");
        userInfo.setEmail("1859656863@qq.com");
        userInfo.setPhone("15286535426");
        return R.ok(userInfo);
    }

}
