package com.study.shiro.realm;

import com.study.shiro.permission.BitPermission;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created on 2018-01-08
 *
 * @author liuzhaoyuan
 */
public class MyShiroRealm2 extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println(" my doGetAuthorizationInfo  2");
        // 1 从 principals 获取用户登录信息 ，采用iter获取不同realm 的信息
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("role1");
//        authorizationInfo.addRole("role2");
//        authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));
//        authorizationInfo.addStringPermission("+user2+10");
//        authorizationInfo.addStringPermission("user2:*");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println(" my realm 2");
        String username = (String) token.getPrincipal();  //得到用户名
        String password = new String((char[]) token.getCredentials()); //得到密码

        if ("aaa".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }

        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }

        // 密码的比对
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        Object principal = username;
        Object credentials = "202cb962ac59075b964b07152d234b70";

        return new SimpleAuthenticationInfo(principal, credentials, getName());
    }

}
