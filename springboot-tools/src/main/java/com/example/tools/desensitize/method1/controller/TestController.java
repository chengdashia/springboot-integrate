//package com.example.tools.desensitize.method1.controller;
//
//import com.example.tools.common.result.R;
//import com.example.tools.desensitize.method1.domain.vo.TestVo;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author 成大事
// * @since 2022/7/28 23:01
// */
//@RestController
//public class TestController {
//    /**
//     * @return
//     */
//    @GetMapping(value = "/test")
//    public R<TestVo> test() {
//        TestVo testVo = new TestVo();
//        testVo.setUserName("张三");
//        testVo.setPhone("18812345678");
//        testVo.setIdCard("15030319520807064X");
//        testVo.setPassword("asdf12345678");
//        testVo.setCustomValue("sfwegewgrergergwefwefwef");
//        return R.ok(testVo);
//    }
//}
