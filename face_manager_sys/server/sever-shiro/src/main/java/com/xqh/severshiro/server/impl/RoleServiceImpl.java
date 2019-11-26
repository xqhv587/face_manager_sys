package com.xqh.severshiro.server.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqh.severshiro.dao.RoleMapper;
import com.xqh.severshiro.entity.Model.Role;
import com.xqh.severshiro.entity.qo.RoleQo;
import com.xqh.severshiro.entity.vo.RoleVo;
import com.xqh.severshiro.server.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gjf
 * @since 2019-04-08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public RoleQo get(Long id) {
        Role role = getById(id);
        RoleQo roleQo = new RoleQo();
        BeanUtils.copyProperties(role, roleQo);
        return roleQo;
    }

    @Override
    public List<RoleVo> selectList(RoleQo roleQo) {
        QueryWrapper<Role> wrapper = getEntityWrapper(roleQo);
        List<Role> roleList =  list(wrapper);
        return roleList.stream().map(this::toVo).collect(Collectors.toList());
    }

    /**
     * 查询角色列表(分页)
     * @param current 当前页
     * @param size      大小
     * @param roleQo
     * @return 查询角色分页列表
     */
    @Override
    public IPage<Role> getPages(Long current, Long size, RoleQo roleQo) {
        QueryWrapper<Role> roleQueryWrapper = getEntityWrapper(roleQo);
        Integer count = baseMapper.selectCount(roleQueryWrapper);
        IPage<Role> roleIPage = new Page<>(current, size, count);
        return baseMapper.selectPage(roleIPage, roleQueryWrapper);
    }

    @Override
    public Long insertOne(RoleQo roleqo) {
        Role role = new Role();
        BeanUtils.copyProperties(roleqo, role);
        baseMapper.insertOne(role);
        return role.getId();
    }

    @Override
    public boolean save(RoleQo roleQo) {
        Role role = new Role();
        BeanUtils.copyProperties(roleQo, role);
        return save(role);
    }

    @Override
    public boolean updateById(RoleQo roleQo) {
        Role role = new Role();
        BeanUtils.copyProperties(roleQo, role);
        return updateById(role);
    }


    @Override
    public void deleteById(Long id) {
        removeById(id);
    }

    public RoleVo toVo(Role role){
        RoleVo roleVo = new RoleVo();
        if(role!=null){
            BeanUtils.copyProperties(role, roleVo);
        }
        return roleVo;
    }
    /**
    *  公共查询条件
    * @param roleQo
     * @return
     */
    public QueryWrapper<Role> getEntityWrapper(RoleQo roleQo){
        QueryWrapper<Role> wrapper = new QueryWrapper<Role>();
        //条件拼接
        if (StringUtils.isNotBlank(roleQo.getRoleName())){
                wrapper.eq(Role.ROLE_NAME,roleQo.getRoleName());
        }
         if (roleQo.getCreateTime()!=null){
                wrapper.eq(Role.CREATE_TIME,roleQo.getCreateTime());
         }
         if (roleQo.getUpdateTime()!=null){
                wrapper.eq(Role.UPDATE_TIME,roleQo.getUpdateTime());
         }
        return wrapper;
    }
}