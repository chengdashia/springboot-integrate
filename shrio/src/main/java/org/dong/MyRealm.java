package org.dong;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @author 成大事
 * @since 2022/12/30 15:23
 */
public class MyRealm extends AuthenticatingRealm {
    //自定义登录认证方法，shiro的login方法底层会调用该类的认证方法进行认证
    //需要配置自定义的realm 生效，在ini文件可以配置，在springboot中配置
    //该方法只是获取进行对比的信息，认证逻辑还是按照shrio底层认证逻辑完成
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1，获取身份信息
        String principal = authenticationToken.getPrincipal().toString();
        //2，获取凭证信息
        String password = new String((char[]) authenticationToken.getCredentials());
        System.out.println("认证用户信息：  "+principal + " --- "+password);

        //3,获取数据库中的存储的用户信息
        if(principal.equals("zhangsan")){
            //3.1数据库中存储的加盐3次迭代的密码
            String pwdInfo = "7174f64b13022acd3c56e2781e098a5f";
            //3.4创建封装校验逻辑对象，封装数据返回
            AuthenticationInfo info = new SimpleAuthenticationInfo(authenticationToken.getPrincipal(),
                    pwdInfo,
                    ByteSource.Util.bytes("salt"),
                    authenticationToken.getPrincipal().toString());

            return info;
        }
        //4、创建封装校验逻辑对象，封装数据返回

        return null;


    }
}
