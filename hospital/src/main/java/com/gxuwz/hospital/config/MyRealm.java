
package com.gxuwz.hospital.config;

import com.gxuwz.hospital.entity.User;
import com.gxuwz.hospital.mapper.AssociationMapper;
import com.gxuwz.hospital.repository.UserRepository;
import com.gxuwz.hospital.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private AssociationMapper associationMapper;

    // 实现身份验证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户名和密码
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        // 根据用户名查询用户信息，例如从数据库中查询
        User user = associationMapper.findUser(username);

        // 判断用户是否存在
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }

        // 验证密码是否正确
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("密码错误");
        }

        // 构造身份验证信息
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }

    // 实现权限验证逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户的用户名
        String username = (String) principals.getPrimaryPrincipal();

        // 查询用户角色和权限信息，例如从数据库中查询
        Set<String> roles = Collections.singleton(associationMapper.findUser(username).getRole().getRoleName());
        Set<String> permissions = Collections.singleton(associationMapper.findUser(username).getRole().getRoleName());

        // 构造权限信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }
}
