package com.xqh.severshiro.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xqh.severshiro.entity.Model.Role;
import com.xqh.severshiro.entity.qo.RoleQo;
import com.xqh.severshiro.entity.vo.RoleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xqh
 * @since 2019-04-08
 */
public interface IRoleService extends IService<Role> {

    /**
     * 获取单个role对象
     * @param id roId
     * @return Role
     */
    RoleQo get(Long  id);

    boolean save(RoleQo roleQo);

    boolean updateById(RoleQo roleQo);

    List<RoleVo> selectList(RoleQo roleQo);

    void deleteById(Long id);

    IPage<Role> getPages(Long current, Long size, RoleQo roleQo);

    /**
     * 插入单个对象
     * @param role roId
     * @return Role
     */
    Long insertOne(RoleQo role);
}
