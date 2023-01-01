package com.dong.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.shiro.model.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 成大事
 * @since 2022/12/31 13:04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
