package com.example.dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2022-08-14 19:28:00
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    @Select("select * from user")
    @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = 1000)
    @ResultType(User.class)
    void userList(@Param(Constants.WRAPPER) QueryWrapper<User> wrapper, ResultHandler<User> handler);

}
