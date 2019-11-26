package com.xqh.severshiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqh.severshiro.entity.Model.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xqh
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Insert("INSERT INTO role(role_name) VALUES(#{role.roleName})")
    @Options(useGeneratedKeys=true, keyProperty="id",keyColumn="id")
    int insertOne(@Param("role") Role role);
}
