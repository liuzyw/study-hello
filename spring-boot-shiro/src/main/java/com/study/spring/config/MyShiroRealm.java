package com.study.spring.config;

import com.study.spring.entity.SysPermission;
import com.study.spring.entity.SysRole;
import com.study.spring.entity.UserInfo;
import com.study.spring.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2018-01-06
 * <p>身份校验核心类</p>
 *
 * @author liuzhaoyuan
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserInfoService userInfoService;


    /**
     * 认证信息(身份验证) Authentication 是用来验证用户身份
     *
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        // 获取用户的输入帐号
        String account = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        // 通过username从数据库中查找 User对象，如果找到，没找到.
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findUserInfoByAccount(account);
        System.out.println("----->>userInfo=" + userInfo);
        if (userInfo == null) {
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(),
            ByteSource.Util.bytes(userInfo.getCredentialsSalt()), getName());

        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();

        for (SysRole role : userInfo.getRoleList()) {
            authorizationInfo.addRole(role.getRole());
            System.out.println(role.getPermissions());

            for (SysPermission p : role.getPermissions()) {
                System.out.println(p);
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

}
