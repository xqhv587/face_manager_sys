package com.xqh.severshiro.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqh.severshiro.entity.Model.Permission;
import com.xqh.severshiro.entity.qo.PermissionQo;
import com.xqh.severshiro.entity.vo.PermissionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gjf
 * @since 2019-04-08
 */
public interface IPermissionService extends IService<Permission> {

    PermissionVo get(Long id);

    boolean save(PermissionQo permissionQo);

    boolean updateById(PermissionQo permissionQo);

    List<PermissionVo> selectList(PermissionQo permissionQo);

    List<Permission> selectByRoleId(Long roleId);

    void deleteById(Long id);

    //List<GetPermissionListModel> selectList();
}
