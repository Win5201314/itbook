package com.zsl.xiangqing.shiro.realm;

import com.zsl.xiangqing.entity.Users;
import com.zsl.xiangqing.exception.user.*;
import com.zsl.xiangqing.shiro.ShiroUtils;
import com.zsl.xiangqing.shiro.service.LoginService;
import com.zsl.xiangqing.shiro.service.PermissionService;
import com.zsl.xiangqing.shiro.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
//用户 角色 权限表的设计参考博文：https://blog.csdn.net/larger5/article/details/79838212
public class UserRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    private LoginService loginService;
    private PermissionService permissionService;
    private RoleService roleService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前验证主体的对象
        Users user = ShiroUtils.getUser();
        // 角色列表
        Set<String> roles;
        // 权限列表
        Set<String> permissions;
        // 授权对象信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取用户对应的角色信息
        roles = roleService.selectRoleKeys(user.getId());
        // 获取用户对应的权限信息
        permissions = permissionService.selectPermsByUserId(user.getId());
        // 角色加入AuthorizationInfo认证对象
        info.setRoles(roles);
        // 权限加入AuthorizationInfo认证对象
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }

        Users user = null;
        try {
            user = loginService.login(username, password);
        } catch (CaptchaException e) {
            throw new AuthenticationException(e.getMessage(), e);
        } catch (UserNotExistsException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (UserPasswordNotMatchException e) {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        } catch (UserPasswordRetryLimitExceedException e) {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (UserBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (RoleBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        //这里密码必须密文,否则报错,这里的username作为盐值
        password = ShiroUtils.Md5HashPassword(username, password);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        //设置盐值
        info.setCredentialsSalt(ByteSource.Util.bytes(username));
        return info;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

}
