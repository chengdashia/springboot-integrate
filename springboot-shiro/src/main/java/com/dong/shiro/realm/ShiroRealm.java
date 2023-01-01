package com.dong.shiro.realm;

import com.dong.shiro.model.domain.User;
import com.dong.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 成大事
 * @since 2022/12/31 13:20
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 获取授权信息
     * 自定义授权方法
     * @param principalCollection 本金收款
     * @return {@link AuthorizationInfo}
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 获取身份验证信息
     * 自定义登录认证方法
     * @param authenticationToken 认证令牌
     * @return {@link AuthenticationInfo}
     * @throws AuthenticationException 身份验证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1、获取用户身份信息
        String name = authenticationToken.getPrincipal().toString();
        //2、调用业务层获取用户信息（数据库）
        User user = userService.getUserInfoByName(name);
        //3、非空判断，将数据封装返回
        if(user != null){
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    //认证信息
                    authenticationToken.getPrincipal(),
                    //数据库的密码
                    user.getPwd(),
                    //加盐信息
                    ByteSource.Util.bytes("salt"),
                    //获取字符信息toString
                    authenticationToken.getPrincipal().toString()
            );
            return info;
        }
        return null;
    }
}
