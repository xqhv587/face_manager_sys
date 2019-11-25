package com.xqh.severshiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqh.severshiro.entity.Model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("select * from permission where id in (select permission_id from role_permission where role_id=#{roleId})")
    List<Permission> selectByRoleId(Long roleId);

    @Select("select * from permission where parent_id in (select permission_id from role_permission where role_id=#{roleId})")
    List<Permission> selectByParentId(Long roleId);

//    @Select("select * from permission where id not in (select parent_id from permission where parent_id is not null)")
//    List<GetPermissionListModel> selectAllList();
}
