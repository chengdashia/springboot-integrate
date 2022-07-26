package com.example.wxjava.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.example.wxjava.common.result.R;
import com.example.wxjava.domain.dto.WxUserInfo;
import com.example.wxjava.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 成大事
 * @since 2022/7/27 22:44
 */
@Slf4j
@RestController
@RequestMapping("/wx/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WxUserInfoController {
    private final WxMaService wxMaService;

    private final UserInfoService userInfoService;

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public R<WxMaJscode2SessionResult> login(@RequestParam("code") String code) {
        return userInfoService.login(code);
    }


    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @PostMapping("/getUserInfo")
    public R<WxMaUserInfo> getUserInfo(@RequestBody WxUserInfo userInfo) {
        return userInfoService.getUserInfo(userInfo);
    }


}

