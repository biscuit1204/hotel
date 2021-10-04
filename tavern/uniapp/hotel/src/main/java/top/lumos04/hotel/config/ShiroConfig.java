package top.lumos04.hotel.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //shiroFilterBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加内置过滤器
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login","anon");
        filterMap.put("/common/*","authc");
//        filterMap.put("/homepage.*","perms[super]");
        bean.setLoginUrl("/");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }


    //defaultWebSecurityManger
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("adminRealm") AdminRealm adminRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(adminRealm);
        return securityManager;
    }


    //创建realm对象
    @Bean
    public AdminRealm adminRealm(){
        return new AdminRealm();
    }
}
