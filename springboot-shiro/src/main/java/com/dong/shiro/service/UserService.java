package com.dong.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.shiro.model.domain.User;

/**
 * @author 成大事
 * @since 2022/12/31 13:04
 */
public interface UserService extends IService<User> {


    /**
     * 按名称获取用户信息
     *
     * @param name 名称
     * @return {@link User}
     */
    User getUserInfoByName(String name);
}
