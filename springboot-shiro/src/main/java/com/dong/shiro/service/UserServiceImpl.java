package com.dong.shiro.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.shiro.mapper.UserMapper;
import com.dong.shiro.model.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author 成大事
 * @since 2022/12/31 13:08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{
    @Override
    public User getUserInfoByName(String name) {
        return this.baseMapper.selectOne(new QueryWrapper<User>()
                .eq(User.NAME,name));
    }
}
