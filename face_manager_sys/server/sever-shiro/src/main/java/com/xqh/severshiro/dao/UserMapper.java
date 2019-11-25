package com.xqh.severshiro.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqh.severshiro.entity.Model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gjf
 * @since 2019-04-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
