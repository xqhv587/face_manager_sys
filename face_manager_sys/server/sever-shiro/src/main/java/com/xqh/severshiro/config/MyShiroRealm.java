package com.xqh.severshiro.config;

import com.xqh.severshiro.dao.PermissionMapper;
import com.xqh.severshiro.entity.Model.Permission;
import com.xqh.severshiro.entity.qo.UserQo;
import com.xqh.severshiro.entity.vo.UserVo;
import com.xqh.severshiro.server.IUserService;
import com.xqh.severshiro.utils.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现AuthorizingRealm接口用户用户认证
 * @author xqh
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private IUserService iUserService;

    @Resource
    private PermissionMapper permissionMapper;


    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        if (StringUtils.isEmpty(username)) {
            throw new AccountException("用户名为空");
        }

        // 从数据库获取对应用户名密码的用户
        UserQo tbUser = new UserQo();
        tbUser.setName(username);
        //tbUser.setStatus(StatusEnum.NORMAL.getStatus());
        UserVo selectEntity = iUserService.selectList(tbUser).get(0);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                selectEntity,
                selectEntity.getPassword(),
                ByteSource.Util.bytes(username),
                //realm name
                getName()
        );
        // 当验证都通过后，把用户信息放在session里
        SessionUtil.setAdmin(selectEntity);
        SessionUtil.setAdminId(selectEntity.getId());
        return authenticationInfo;
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserVo user = (UserVo) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        Long roleId = user.getRoleId();
        List<Permission> tbPermissions = permissionMapper.selectByRoleId(roleId);
        List<Permission> tbPermissions2 = permissionMapper.selectByParentId(roleId);
        if(!CollectionUtils.isEmpty(tbPermissions2)){
            tbPermissions.addAll(tbPermissions2);
        }
        info.setStringPermissions(tbPermissions.stream().map(Permission::getTitle).collect(Collectors.toSet()));
        return info;
    }

    public void clearCachedAuthorization(){
        //清空权限缓存
        clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}