package cn.crabapples.tuole.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO shiro配置类
 *
 * @author Mr.He
 * 2020/3/5 0:57
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Configuration
public class ShiroConfigure {
    ShiroRealm shiroRealm;

    public ShiroConfigure(ShiroRealm shiroRealm) {
        this.shiroRealm = shiroRealm;
    }

    /**
     * 创建securityManager类注入的Bean
     * @return DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(ShiroRealm shiroRealm){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(shiroRealm);
        return webSecurityManager;
    }

    /**
     * 创建shiro过滤器工厂添加到shiroManger中(用于拦截url)
     * @return shiro过滤器工厂
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
        shiroFilterFactory.setSecurityManager(securityManager);
        Map<String,String> filterMap = new LinkedHashMap<>();
        /*
         * shiro过滤器配置
         *  authc需要认证的url
         *  anon不需要认证的url
         *  perms需要授权的url
         */
        filterMap.put("/manage/loginCheck","anon");
//        filterMap.put("/manage/**","authc");
        filterMap.put("/api/**","anon");

        filterMap.put("/js/**","anon");
        filterMap.put("/css/**","anon");
        filterMap.put("/X-admin/css/**","anon");
        filterMap.put("/X-admin/js/**","anon");
        filterMap.put("/X-admin/images/**","anon");
        filterMap.put("/X-admin/fonts/**","anon");
        filterMap.put("/X-admin/lib/**","anon");
        filterMap.put("/view/**","anon");

//        filterMap.put("/index","perms[]");

        shiroFilterFactory.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactory.setLoginUrl("/manage/login");
        /*
         * ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑
         * 认证失败跳转的页面
         *
         * 授权失败跳转的页面
         * ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
         */
        shiroFilterFactory.setUnauthorizedUrl("/manage/login");
        return shiroFilterFactory;
    }


    /*
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    /**
     *  开启Shiro的注解验证过滤功能(如@RequiresRoles,@RequiresPermissions)
     *  需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启shiro对SpringAop注解支持
     * @param securityManager shiro安全管理器
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
