package com.gxuwz.hospital.config;

import com.gxuwz.hospital.config.MyRealm;
import com.gxuwz.hospital.repository.UserRepository;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        // 自定义Realm，用于处理身份验证和权限验证
        return new MyRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置登录页面和登录成功后跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");

        // 配置URL的权限规则
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/register","anon");//不需要登录就可以访问的页面
        filterChainDefinitionMap.put("/index","anon");//不需要登录就可以访问的页面
        filterChainDefinitionMap.put("/toAdminLogin","anon");//不需要登录就可以访问的页面
        filterChainDefinitionMap.put("/adminLogin","anon");//不需要登录就可以访问的页面

        filterChainDefinitionMap.put("/**", "authc"); // 需要登录才能访问
        filterChainDefinitionMap.put("/*/**", "authc"); // 需要登录才能访问

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

}
