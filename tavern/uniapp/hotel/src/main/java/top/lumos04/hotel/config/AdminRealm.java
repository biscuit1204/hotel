package top.lumos04.hotel.config;

import top.lumos04.hotel.pojo.Admin;
import top.lumos04.hotel.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminRealm extends AuthorizingRealm {


    @Autowired
    AdminService adminService;
//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");
        //SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("admin:add");
        //拿到当前登录用户
        Subject subject = SecurityUtils.getSubject();
        Admin principal = (Admin) subject.getPrincipal();
//        获取用户权限
//        info.addStringPermission(principal.getRole());
        return info;
    }

//    登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //连接数据库
        Admin admin = adminService.getById(token.getUsername());
        if (admin==null){
            return null;
        }
        return new SimpleAuthenticationInfo(admin,admin.getPassword(),"");
    }
}
