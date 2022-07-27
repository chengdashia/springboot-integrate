package com.example.wxjava.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.example.wxjava.common.result.R;
import com.example.wxjava.domain.dto.WxUserInfo;

/**
 * @author 成大事
 * @since 2022/7/27 22:47
 */
public interface UserInfoService {

    /**
     * 登录
     * @param code code
     * @return   WxMaJscode2SessionResult
     */
    R<WxMaJscode2SessionResult> login(String code);

    /**
     * 获取用户信息
     * @param userInfo  包含一些加密的信息
     * @return  WxMaUserInfo
     */
    R<WxMaUserInfo> getUserInfo(WxUserInfo userInfo);
}
