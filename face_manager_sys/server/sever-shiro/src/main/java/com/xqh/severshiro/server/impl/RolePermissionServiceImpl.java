package com.xqh.severshiro.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqh.severshiro.dao.RolePermissionMapper;
import com.xqh.severshiro.entity.Model.RolePermission;
import com.xqh.severshiro.entity.qo.RolePermissionQo;
import com.xqh.severshiro.entity.vo.RolePermissionVo;
import com.xqh.severshiro.server.IRolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xqh
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

    @Override
    public RolePermissionVo get(Long id) {
        RolePermission rolePermission = getById(id);
        return toVo(rolePermission);
    }

    @Override
    public List<RolePermissionVo> selectList(RolePermissionQo rolePermissionQo) {
        QueryWrapper<RolePermission> wrapper = getEntityWrapper(rolePermissionQo);
        List<RolePermission> rolePermissionList =  list(wrapper);
        return rolePermissionList.stream().map(this::toVo).collect(Collectors.toList());
    }

    @Override
    public boolean save(RolePermissionQo rolePermissionQo) {
        RolePermission rolePermission = new RolePermission();
        BeanUtils.copyProperties(rolePermissionQo, rolePermission);
        return save(rolePermission);
    }

    @Override
    public boolean updateById(RolePermissionQo rolePermissionQo) {
        RolePermission rolePermission = new RolePermission();
        BeanUtils.copyProperties(rolePermissionQo, rolePermission);
        return updateById(rolePermission);
    }

    @Override
    public void deleteById(Long id) {
        removeById(id);
    }

    @Override
    public void delete(RolePermissionQo rolePermissionQo){

        baseMapper.delete(getEntityWrapper(rolePermissionQo));
    }

    public RolePermissionVo toVo(RolePermission rolePermission){
        RolePermissionVo rolePermissionVo = new RolePermissionVo();
        if(rolePermission!=null){
            BeanUtils.copyProperties(rolePermission, rolePermissionVo);
        }
        return rolePermissionVo;
    }
    /**
    *  公共查询条件
    * @param rolePermissionQo
     * @return
     */
    public QueryWrapper<RolePermission> getEntityWrapper(RolePermissionQo rolePermissionQo){
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<RolePermission>();
        //条件拼接
         if (rolePermissionQo.getRoleId()!=null){
                wrapper.eq(RolePermission.ROLE_ID,rolePermissionQo.getRoleId());
         }
         if (rolePermissionQo.getPermissionId()!=null){
                wrapper.eq(RolePermission.PERMISSION_ID,rolePermissionQo.getPermissionId());
         }
         if (rolePermissionQo.getCreateTime()!=null){
                wrapper.eq(RolePermission.CREATE_TIME,rolePermissionQo.getCreateTime());
         }
         if (rolePermissionQo.getUpdateTime()!=null){
                wrapper.eq(RolePermission.UPDATE_TIME,rolePermissionQo.getUpdateTime());
         }
        return wrapper;
    }
}