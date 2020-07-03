package com.easy.ui.config;

/*
 * shiro配置类
 */

import com.easy.ui.shiro.Myrealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    //自定义 realm\
    @Bean
    public Myrealm myrealm() {
        Myrealm myrealm = new Myrealm();
        return myrealm;
    }

    /**
     * SecurityManager 安全 管理器
     */
    @Bean("securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myrealm());
        return defaultWebSecurityManager;
    }


    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/");
        shiroFilterFactoryBean.setSuccessUrl("index");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //登出
        map.put("/logout", "logout");
        //认证
        map.put("/*.js", "anon");
        map.put("/layui/**", "anon");
        map.put("/lib/**", "anon");
        map.put("/modules/**", "anon");
        map.put("/**", "authc");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
}
