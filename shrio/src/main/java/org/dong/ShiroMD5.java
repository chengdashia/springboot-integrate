package org.dong;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author 成大事
 * @since 2022/12/30 15:00
 */
public class ShiroMD5 {
    public static void main(String[] args) {
        //密码明文
        String pwd = "z3";
        //使用MD5加密
        Md5Hash md5Hash = new Md5Hash(pwd);
        System.out.println("md5加密： "+md5Hash);

        //带盐的md5加密，盐就是在密码明文后拼接新字符串，然后再进行加密
        Md5Hash salt = new Md5Hash(pwd, "salt");
        System.out.println("md5加密，带盐： "+salt);

        //为了保证安全，避免被破解还可以多次迭代加密，保证数据安全
        Md5Hash salt1 = new Md5Hash(pwd, "salt", 3);
        System.out.println("多次加盐迭代加密：  "+salt1);

        //使用父类进行加密
        SimpleHash simpleHash = new SimpleHash("MD5",pwd,"salt",3);
        System.out.println("父类带盐多次加密:   "+simpleHash);
    }
}
