package com.zsl.xiangqing.shiro;

import com.zsl.xiangqing.filter.CORSAuthenticationFilter;
import com.zsl.xiangqing.shiro.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * redis主机
     */
    @Value("${spring.redis.host}")
    private String host;

    /**
     * redis端口
     */
    @Value("${spring.redis.port}")
    private int port;

    /**
     * 超时
     */
    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();     // crazycake 实现
        redisManager.setHost("127.0.0.1");
        redisManager.setPort(6379);
        // 设置redis缓存时间
        redisManager.setTimeout(18000);
        return redisManager;
    }

    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean
    public RedisSessionDAO sessionDAO() {
        RedisSessionDAO sessionDAO = new RedisSessionDAO(); // crazycake 实现
        sessionDAO.setRedisManager(redisManager());
        sessionDAO.setSessionIdGenerator(sessionIdGenerator()); //  Session ID 生成器
        return sessionDAO;
    }

    @Bean
    public SimpleCookie cookie() {
        SimpleCookie cookie = new SimpleCookie("SHAREJSESSIONID"); //  cookie的name,对应的默认是 JSESSIONID
        cookie.setHttpOnly(true);
        cookie.setPath("/");        //  path为 / 用于多个系统共享JSESSIONID
        return cookie;
    }

    /**
     * 配置会话管理器
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(18000);    // 设置session超时
        sessionManager.setDeleteInvalidSessions(true);      // 删除无效session
        sessionManager.setSessionIdCookie(cookie());            // 设置JSESSIONID
        sessionManager.setSessionDAO(sessionDAO());         // 设置sessionDAO
        return sessionManager;
    }

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // 记住我cookie生效时间30天 ,单位秒
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    /**
     * 1. 配置SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(realm());
        // 记住我
        securityManager.setRememberMeManager(rememberMeManager());
        // 设置sessionManager
        securityManager.setSessionManager(sessionManager());
//        securityManager.setCacheManager(redisCacheManager()); // 配置缓存的话，退出登录的时候crazycake会报错，要求放在session里面的实体类必须有个id标识
        // 设置缓存管理器
        securityManager.setCacheManager(redisCacheManager());
        return securityManager;
    }

    /**
     * 2. 配置缓存
     */
//    @Bean
//    public CacheManager cacheManager(){
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
//        return ehCacheManager;
//    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager();   // crazycake 实现
        cacheManager.setRedisManager(redisManager());
        return cacheManager;
    }

    /**
     * 3. 配置Realm
     */
    @Bean
    public AuthorizingRealm realm() {
        UserRealm realm = new UserRealm();
        //待定
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    private HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密方式
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //加密次数 默认一次
        hashedCredentialsMatcher.setHashIterations(1);
        //存储散列后的密码是否为16进制
        //hashedCredentialsMatcher.isStoredCredentialsHexEncoded();
        //hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /**
     * 4. 配置LifecycleBeanPostProcessor，可以来自动的调用配置在Spring IOC容器中 Shiro Bean 的生命周期方法
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 5. 启用IOC容器中使用Shiro的注解，但是必须配置第四步才可以使用
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 6. 配置ShiroFilter
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        // 创建ShiroFilterFactoryBean对象
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        // 配置SecurityManager
        factoryBean.setSecurityManager(securityManager);

        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        factoryBean.setLoginUrl("/unauthorized"); // 在控制器添加一个接口，显示未登录，告诉前端
        // 登录成功后要跳转的链接
        //factoryBean.setSuccessUrl("/index");
        //未授权界面;
        //factoryBean.setUnauthorizedUrl("/403");

        // 配置权限路径
        factoryBean.setFilterChainDefinitionMap(configInterceptUrl());
        // 配置过滤器
        factoryBean.setFilters(configFilters());
        return factoryBean;
    }

    /**
     * 添加拦截的uri,配置拦截规则
     */
    private LinkedHashMap<String, String> configInterceptUrl() {
        // 用LinkedHashMap添加拦截的uri,其中authc指定需要认证的uri，anon指定排除认证的uri
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        // 静态资源
        map.put("/static/**", "anon");
        /*map.put("/css/**", "anon");
        map.put("/js/**", "anon");*/

        // 公共路径
        map.put("/user/login", "anon");
        map.put("/user/register", "anon");
        //自己的
        map.put("/user/sendSMS", "anon");
        map.put("/user/sendSMSf", "anon");
        map.put("/user/wxLogin", "anon");
        //map.put("/*", "anon");

        // 登出,项目中没有/logout路径,因为shiro是过滤器,而SpringMVC是Servlet,Shiro会先执行
        map.put("/user/logout", "logout");

        //配置不会被拦截的链接，顺序判断
        map.put("/", "anon");

        //测试
        map.put("/test", "anon");
        map.put("/vip", "authc,roles[vip]");
        map.put("/ip", "authc,roles[ip]");
        map.put("/p", "authc,roles[p]");

        //authc:所有url必须通过认证才能访问，anon:所有url都可以匿名访问
        map.put("/**", "corsAuthenticationFilter");

        // 授权
        //map.put("/user/**", "authc,roles[user]");
        //本项目没有这个，所以注释了
        //map.put("/admin/**", "authc,roles[admin]");

        // 其它任何请求都需要认证
        //map.put("/**", "authc");
        return map;
    }

    private Map<String, Filter> configFilters() {
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("corsAuthenticationFilter", new CORSAuthenticationFilter());
        return filters;
    }

    /**
     * 配置RedisTemplate，充当数据库服务
     */
    /*@Bean
    public RedisTemplate<String, Users> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Users> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Users.class));
        return redisTemplate;
    }*/

}
