package org.example.fangwuzulin.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.example.fangwuzulin.entity.SysUser;
import org.example.fangwuzulin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;


@Configuration
public class ShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
    private final UserService userService;

    public ShiroRealm(UserService userService) {
        this.userService = userService;
    }

    /**
     * shiro授权调用的方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        Set<String> permissions = new HashSet<>();
        if (user != null) {
            if (user.getStatus() == 1) {
                throw new ApplicationException("用户被冻结");
            }
            if (user.isIs_admin()) {
                permissions.add("manage");
            } else {
                permissions.add("login");
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * shiro认证调用的方法,认证失败时会抛出异常
     * IncorrectCredentialsException密码错误
     * UnknownAccountException用户名错误
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        logger.debug("开始对:[{}]执行shiro认证", username);
        SysUser user = userService.findByUsername(username);
        if (null == user) {
            throw new UnknownAccountException("用户不存在");
        }
        String password = user.getPassword();
        logger.debug("用户:[{}]shiro认证结束", username);
        return new SimpleAuthenticationInfo(user, password, this.getName());
    }
}
