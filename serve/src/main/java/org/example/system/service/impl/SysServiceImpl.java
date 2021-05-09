package org.example.system.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.example.common.config.ApplicationConfigure;
import org.example.common.utils.jwt.JwtConfigure;
import org.example.common.utils.jwt.JwtTokenUtils;
import org.example.system.dao.jpa.SysMenuRepository;
import org.example.system.entity.SysMenu;
import org.example.system.entity.SysUser;
import org.example.system.form.UserForm;
import org.example.system.service.SysService;
import org.example.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO 系统相关服务实现类
 *
 * @author Mr.He
 * 2020/1/28 23:23
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Service
//@CacheConfig(cacheNames = "user:")
public class SysServiceImpl implements SysService {

    private static final Logger logger = LoggerFactory.getLogger(SysServiceImpl.class);
    private String aesKey;
    private String redisPrefix;
    private Long tokenCacheTime;
    private String salt;
    @Value("${isDebug}")
    private boolean isDebug;

    private final UserService userService;

    private final SysMenuRepository sysMenuRepository;

    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtConfigure jwtConfigure;

    public SysServiceImpl(ApplicationConfigure applicationConfigure,
                          UserService userService,
                          SysMenuRepository sysMenuRepository,
                          RedisTemplate<String, Object> redisTemplate,
                          JwtConfigure jwtConfigure) {
        this.userService = userService;
        this.sysMenuRepository = sysMenuRepository;
        this.redisTemplate = redisTemplate;
        this.aesKey = applicationConfigure.aesKey;
        this.redisPrefix = applicationConfigure.redisPrefix;
        this.tokenCacheTime = applicationConfigure.tokenCacheTime;
        this.salt = applicationConfigure.salt;
        this.jwtConfigure = jwtConfigure;
    }

    /**
     * Cacheable
     * * key: redis中key的值
     * * value: redis中key的前缀
     * * 例:
     * * key::value:tom
     * * userLogin::${#p0.username}
     * <p>
     * 用户登录验证
     *
     * @param form 用户信息
     * @return token
     */
    @Override
    public String loginCheck(UserForm form) {
        String username = form.getUsername();
        String password = String.valueOf(new Md5Hash(form.getPassword(), salt));
        logger.info("开始登录->用户名:[{}],密码:[{}]", username, password);
        SysUser sysUser = shiroCheckLogin(username, password);
        return JwtTokenUtils.createJWT(sysUser.getId(), sysUser.getUsername(), jwtConfigure);
    }

    /**
     * shiro认证
     *
     * @param username 用户名
     * @param password 密码
     * @return 认证成功后的用户对象
     */
    private SysUser shiroCheckLogin(String username, String password) {
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return (SysUser) subject.getPrincipal();
    }

    /**
     * 获取当前用户拥有的菜单
     *
     * @return 当前用户拥有的菜单
     */
    @Override
    public List<SysMenu> getSysMenus(SysUser user) {
        List<SysMenu> menus = sysMenuRepository.findByParentIdIsNull();
        insertChildrenMenus(menus);
        menus.forEach(System.err::println);
        return menus;
    }


    /**
     * 格式化菜单树
     *
     * @param menus 上级菜单列表(普通列表)
     * @return 加入下一级后的菜单列表
     */
    private List<SysMenu> insertChildrenMenus(List<SysMenu> menus) {
        menus.forEach(e -> {
            List<SysMenu> children = sysMenuRepository.findByParentId(e.getId());
            insertChildrenMenus(children);
            e.setChildren(children);
        });
        return menus;
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
