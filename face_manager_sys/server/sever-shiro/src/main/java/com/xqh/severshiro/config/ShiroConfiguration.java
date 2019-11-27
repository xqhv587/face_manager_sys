package com.xqh.severshiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author gjf
 * @since 2019-04-01
 */
@Configuration
public class ShiroConfiguration {

    /**
     * 将自己的验证方式加入容器
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm shiroRealm = new MyShiroRealm();
        //设置认证密码算法及迭代复杂度
        shiroRealm.setCredentialsMatcher(credentialsMatcher());
        //认证
        shiroRealm.setAuthenticationCachingEnabled(false);
        //授权
        shiroRealm.setAuthorizationCachingEnabled(false);
        return shiroRealm;
    }

    /**
     * realm的认证算法
     * @return
     */
    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("md5");
        //2次迭代
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        //设置session 由shiro管理
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登录界面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/login.html");

        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("myAccessControlFilter", new MyAccessControlFilter());
        filtersMap.put("myShiroAuthFilter", new MyShiroAuthFilter());

        shiroFilterFactoryBean.setFilters(filtersMap);

        // 设置过滤器
        Map<String,String> map = new LinkedHashMap<>(16);

        //静态资源
        map.put("/login.html", "anon");
        map.put("/component/**", "anon");
        map.put("/datas/**", "anon");
        map.put("/images/**", "anon");
        map.put("/js/**", "anon");
        map.put("/plugins/**", "anon");
        map.put("/css/**", "anon");
        map.put("/excel/**", "anon");
//        //登录开放接口
//        map.put("/user/login", "anon");
        //开放接口
        map.put("/issue/**", "anon");
        map.put("/issueType/**", "anon");
        map.put("/priority/**", "anon");
        map.put("/projectComponent/**", "anon");
        map.put("/user/**", "anon");
        map.put("/product/**", "anon");

        //管理员，需要权限
        map.put("/user/getNavList", "anon");
        //对所有用户认证 put拦截器
        map.put("/**","myAccessControlFilter");

        map.put("/**","myShiroAuthFilter");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     *  加入注解的使用，不加入这个注解不生效
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}