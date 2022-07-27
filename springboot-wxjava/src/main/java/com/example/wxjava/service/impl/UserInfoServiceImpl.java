package com.example.wxjava.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import com.example.wxjava.common.result.R;
import com.example.wxjava.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 成大事
 * @since 2022/7/27 22:48
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserInfoServiceImpl implements UserInfoService {

    private final WxMaService wxMaService;

    /**
     * 登录
     * @param code code
     * @return   WxMaJscode2SessionResult
     */
    @Override
    public R<WxMaJscode2SessionResult> login(String code) {
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            log.info(session.getSessionKey());
            log.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            return R.ok(session);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return R.error(e.toString());
        } finally {
            WxMaConfigHolder.remove();//清理ThreadLocal
        }
    }
}
