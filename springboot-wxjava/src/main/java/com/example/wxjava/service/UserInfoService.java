package com.example.wxjava.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.example.wxjava.common.result.R;

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
}
