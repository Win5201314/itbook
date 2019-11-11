package com.zsl.boss.demo.config;

import com.zsl.boss.demo.entity.Role;
import com.zsl.boss.demo.entity.Users;
import com.zsl.boss.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm 继承AuthorizingRealm 重写  AuthorizationInfo（授权） 和  AuthenticationInfo（认证）
 * 参考博文 https://www.cnblogs.com/zhouguanglin/archive/2018/02/27/8477807.html
 * https://jinnianshilongnian.iteye.com/blog/2029717
 * 讲的很全面的博文
 * https://www.w3cschool.cn/shiro/andc1if0.html
 */
@Configuration
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 提供用户信息，返回权限信息
     * @param principals 身份
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------------------->授权认证：");
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        //对应的手机号
        String userName = (String) principals.getPrimaryPrincipal();
        //通过手机号查找对应数据的主键
        int userId = userService.selectIdByPhone(userName);
        //通过用户主键查找对应的角色集合
        Set<Role> roleIdSet = userService.findRoleIdByUid(userId);
        Set<String> roleSet = new HashSet<>();
        Set<Integer> permissionIdSet = new HashSet<>();
        for (Role roleInfo : roleIdSet) {
            int roleId = roleInfo.getId();
            roleSet.add(userService.findRoleByRoleId(roleId));
            //将拥有角色的所有权限放进Set里面，也就是求Set集合的并集
            permissionIdSet.addAll(userService.findPermissionIdByRoleId(roleId));
        }
        Set<String> permissionSet = new HashSet<>();
        for (int permissionId : permissionIdSet) {
            String permissionName = userService.findPermissionById(permissionId).getPermissionName();
            permissionSet.add(permissionName);
        }
        // 将角色名称组成的Set提供给授权info
        authorizationInfo.setRoles(roleSet);
        // 将权限名称组成的Set提供给info
        authorizationInfo.setStringPermissions(permissionSet);

        return authorizationInfo;
    }

    /**
     * 提供帐户信息，返回认证信息
     * @param authenticationToken token
     * @return 认证信息
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("---------------------------->登陆验证:");
        String phone = (String) authenticationToken.getPrincipal();
        //我们这里用的手机号作为用户名，具备唯一性
        Users user = userService.selectByPhone(phone);
        if(user == null) {
            //用户不存在就抛出异常
            throw new UnknownAccountException();
        }
        if(user.isLocked()) {
            //用户被锁定就抛异常
            throw new  LockedAccountException();
        }
        //密码可以通过SimpleHash加密，然后保存进数据库。
        //此处是获取数据库内的账号、密码、盐值(salt = phone + salt)，保存到登陆信息info中
       return new SimpleAuthenticationInfo(
               user.getPhone(),
               user.getPassword(),
               ByteSource.Util.bytes(user.getCredentialsSalt()),
               getName());                   //realm name
    }

    @PostConstruct
    public void initCredentialsMatcher() {
        //该句作用是重写shiro的密码验证，让shiro用我自己的验证
        setCredentialsMatcher(new CredentialsMatcher());
    }
}
