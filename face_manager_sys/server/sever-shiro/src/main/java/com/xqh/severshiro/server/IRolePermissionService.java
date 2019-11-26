package com.xqh.severshiro.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqh.severshiro.entity.Model.RolePermission;
import com.xqh.severshiro.entity.qo.RolePermissionQo;
import com.xqh.severshiro.entity.vo.RolePermissionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 * @author xqh
 */
public interface IRolePermissionService extends IService<RolePermission> {

    RolePermissionVo get(Long id);

    boolean save(RolePermissionQo rolePermissionQo);

    boolean updateById(RolePermissionQo rolePermissionQo);

    List<RolePermissionVo> selectList(RolePermissionQo rolePermissionQo);

    void deleteById(Long id);

    void delete(RolePermissionQo rolePermissionQo);

}
