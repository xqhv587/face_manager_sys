package com.xqh.severshiro.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.xqh.severshiro.entity.Model.GetNavListModel;
import com.xqh.severshiro.entity.Model.User;
import com.xqh.severshiro.entity.qo.PermissionQo;
import com.xqh.severshiro.entity.qo.RolePermissionQo;
import com.xqh.severshiro.entity.qo.UserQo;
import com.xqh.severshiro.entity.vo.PermissionVo;
import com.xqh.severshiro.entity.vo.RolePermissionVo;
import com.xqh.severshiro.entity.vo.UserVo;
import com.xqh.severshiro.enums.CodeEnum;
import com.xqh.severshiro.enums.StatusEnum;
import com.xqh.severshiro.exceptions.BaseException;
import com.xqh.severshiro.server.IPermissionService;
import com.xqh.severshiro.server.IRolePermissionService;
import com.xqh.severshiro.server.IRoleService;
import com.xqh.severshiro.server.IUserService;
import com.xqh.severshiro.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xqh
 */
@RestController
@RequestMapping("/user")
@Api(tags = "1.5", description = "用户管理", value = "用户管理")
public class UserController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IPermissionService iPermissionService;
    @Autowired
    private IRolePermissionService iRolePermissionService;
//    @Autowired
//    private IRoleService roleService;

    @GetMapping("/permissionDenied")
    public JsonResult permissionDenied() {
        return JsonResult.error(CodeEnum.PERMISSION_DENIED);
    }

    @GetMapping("/requiredLogin")
    public JsonResult requiredLogin() {
        return JsonResult.error(CodeEnum.NEED_RE_LOGIN);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/login")
    public JsonResult<String> loginDo(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return JsonResult.error(CodeEnum.USERNAME_OR_PASSWORD_NOT_EMPTY);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, false);
        try {
            subject.login(token);
            //session 永不过期
            subject.getSession().setTimeout(1000 * 60 * 60 * 24);
            return JsonResult.success();
        } catch (LockedAccountException lae) {
            token.clear();
            return JsonResult.error(CodeEnum.LOCKED_ACCOUNT);
        } catch (AuthenticationException e) {
            token.clear();
            return JsonResult.error(CodeEnum.ERROR_PASSWORD);
        }
    }

    @ApiOperation(value = "用户登出", notes = "用户登出")
    @PostMapping("/logout")
    public JsonResult logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            return JsonResult.success();
        }
        return JsonResult.error(CodeEnum.FAILED);
    }

    @ApiOperation(value = "获取导航页列表", notes = "获取导航页列表")
    @PostMapping("/getNavList")
    public JsonResult<GetNavListModel> getNavList() {
        UserVo user= (UserVo) SecurityUtils.getSubject().getPrincipal();
        if(user == null){
            throw new BaseException(CodeEnum.PERMISSION_DENIED);
        }
        UserVo tbUser = iUserService.get(user.getId());
        //根据角色id查找所有的permission id 列表
        List<Long> permissionIds = iRolePermissionService.selectList(RolePermissionQo.builder()
                .roleId(tbUser.getRoleId())
                .build()).stream().map(RolePermissionVo::getPermissionId).collect(Collectors.toList());
        Set<Long> finalPermissionIds = Sets.newHashSet(permissionIds);
        //获取所有的权限列表
        List<PermissionVo> tbPermissions = iPermissionService.selectList(new PermissionQo());
        List<Long> nullArr = new ArrayList<>();
        nullArr.add(null);
        for(Long ids : permissionIds){
            List<Long> integers = tbPermissions.stream().filter(tbPermission -> tbPermission.getId().equals(ids)).map(PermissionVo::getParentId).collect(Collectors.toList());
            integers.removeAll(nullArr);
            if(CollectionUtils.isEmpty(integers)){
                continue;
            }
            finalPermissionIds.addAll(integers);
        }
        GetNavListModel getNavListResDto = new GetNavListModel();
        List<GetNavListModel.NavListBean> getNavListResDtoList = Lists.newLinkedList();
        getNavListResDto.setNavList(getNavListResDtoList);
        getNavListResDto.setUserName(tbUser.getName());

        for(Long permissionId : finalPermissionIds){
            GetNavListModel.NavListBean navListBean = new GetNavListModel.NavListBean();
            List<GetNavListModel.NavListBean.ChildrenBean> childrenBeanList = Lists.newLinkedList();
            for (PermissionVo p : tbPermissions) {
                if (permissionId.equals(p.getParentId()) && finalPermissionIds.contains(p.getId())) {
                    GetNavListModel.NavListBean.ChildrenBean childrenBean = new GetNavListModel.NavListBean.ChildrenBean();
                    BeanUtils.copyProperties(p, childrenBean);
                    childrenBeanList.add(childrenBean);
                    continue;
                }
                if (p.getId().equals(permissionId) && p.getParentId() == null) {
                    BeanUtils.copyProperties(p, navListBean);
                    getNavListResDtoList.add(navListBean);
                }
            }
            if (!CollectionUtils.isEmpty(childrenBeanList)) {
                navListBean.setChildren(childrenBeanList);
            }
        }
        return JsonResult.success(getNavListResDto);
    }


//    @RequiresPermissions("系统管理")
//    @PostMapping(value = "/getUserList")
//    @ApiOperation(value = "管理员列表", notes = "管理员列表")
//    public JsonResult getAdminList(
//            @ApiParam(value = "请求页码", required = true)  @RequestParam(name = "page", required = false,	defaultValue="1") Long pageIndex ,
//            @ApiParam(value = "请求页码大小", required = true)  @RequestParam(name = "length", required = false,	defaultValue="15")  Long pageSize,
//            @ApiParam(value = "要搜索的管理员名称") String keyword) {
//        IPage<User> result = iUserService.getPages(pageIndex, pageSize, UserQo
//                .builder().name(keyword)
//                .status(StatusEnum.NORMAL.getStatus())
//                .build());
//
//        List<UserListVo> userListVos = result.getRecords().stream().map(user -> {
//            UserListVo userListVo = new UserListVo();
//            BeanUtils.copyProperties(user, userListVo);
//            userListVo.setCreateTime(user.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//            userListVo.setRoleName(roleService.get(user.getRoleId()).getRoleName());
//            return userListVo;
//        }).collect(Collectors.toList());
//
//        PageModel<UserListVo> packagePageModel = new PageModel<>();
//        packagePageModel.setData(userListVos);
//        packagePageModel.setPageSize(pageSize);
//        packagePageModel.setPageNum(pageIndex);
//        packagePageModel.setTotalSize(result.getTotal());
//        packagePageModel.setTotalPage(result.getPages());
//        return JsonResult.success(packagePageModel);
//    }

    /**
    * 保存
    * @param userQo 传递的实体
    * @return 0 失败  1 成功
    */
//    @ApiOperation(value = "添加用户", notes = "添加用户")
//    @PostMapping("/addUser")
//    public JsonResult addUser(@Valid UserQo userQo) {
//        String adminName = userQo.getUsername().trim();
//        String adminPassWord = userQo.getPassword().trim();
//
//        if (userQo.getId() != null && userQo.getId() > 0) {
//            UserVo sysAdmin = iUserService.get(userQo.getId());
//            adminPassWord = sysAdmin.getPassword();
//            sysAdmin.setPassword(ShiroUtil.sysMd5(adminName, adminPassWord));
//            sysAdmin.setNickName(userQo.getNickName());
//            sysAdmin.setRoleId(userQo.getRoleId());
//            sysAdmin.setUsername(adminName);
//            //修改
//            iUserService.updateById(userQo);
//        }else{
//            userQo.setStatus(StatusEnum.NORMAL.getStatus());
//            userQo.setPassword(ShiroUtil.sysMd5(adminName, adminPassWord));
//            //增加
//            iUserService.save(userQo);
//        }
//        return JsonResult.success();
//    }

    /**
    * 根据id删除对象
    * @param id  实体ID
    * @return 0 失败  1 成功
    */
//    @PostMapping("/delete")
//    public JsonResult userDelete(Integer id){
//        if(id == 1){
//            //管理员不能删除
//            return JsonResult.error(CodeEnum.ADMIN_NOT_DELETE);
//        }
//        iUserService.updateById(UserQo.builder().id(id)
//                .status(StatusEnum.DELETE.getStatus())
//                .build());
//        return JsonResult.success();
//    }

//    @ApiOperation(value = "修改密码", notes = "修改密码")
//    @PostMapping("/changePassword")
//    public JsonResult changePassword(@Valid ChangePasswordQo changePasswordQo) {
//        UserVo userVo = iUserService.get(changePasswordQo.getId());
//        String userName = userVo.getUsername();
//       boolean result = iUserService.updateById(UserQo.builder().id(changePasswordQo.getId())
//                .password(ShiroUtil.sysMd5(userName, changePasswordQo.getPass()))
//                .build());
//        AssertUtil.isTrue(!result, CodeEnum.UPDATE_FAILED);
//        return JsonResult.success();
//    }



}