package com.xqh.severshiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqh.severshiro.entity.Model.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gjf
 * @since 2019-04-08
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    @Select("<script>select * from role_permission WHERE role_id in " +
            "(select role_id from user where id = #{roleId})</script>")
    List<RolePermission> selectByRoleId(Long roleId);

}
