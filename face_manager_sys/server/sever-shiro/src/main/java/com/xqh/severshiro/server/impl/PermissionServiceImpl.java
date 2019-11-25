package com.xqh.severshiro.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqh.severshiro.dao.PermissionMapper;
import com.xqh.severshiro.entity.Model.Permission;
import com.xqh.severshiro.entity.qo.PermissionQo;
import com.xqh.severshiro.entity.vo.PermissionVo;
import com.xqh.severshiro.server.IPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public PermissionVo get(Long id) {
        Permission permission = getById(id);
        return toVo(permission);
    }

    @Override
    public List<PermissionVo> selectList(PermissionQo permissionQo) {
        QueryWrapper<Permission> wrapper = getEntityWrapper(permissionQo);
        List<Permission> permissionList =  list(wrapper);
        return permissionList.stream().map(this::toVo).collect(Collectors.toList());
    }

    @Override
    public List<Permission> selectByRoleId(Long roleId) {

        return baseMapper.selectByRoleId(roleId);
    }


    @Override
    public boolean save(PermissionQo permissionQo) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionQo, permission);
        return save(permission);
    }

    @Override
    public boolean updateById(PermissionQo permissionQo) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionQo, permission);
        return updateById(permission);
    }

    @Override
    public void deleteById(Long id) {
        removeById(id);
    }

//    @Override
//    public List<GetPermissionListModel> selectList() {
//        return baseMapper.selectAllList();
//    }

    public PermissionVo toVo(Permission permission){
        PermissionVo permissionVo = new PermissionVo();
        if(permission!=null){
            BeanUtils.copyProperties(permission, permissionVo);
        }
        return permissionVo;
    }
    /**
    *  公共查询条件
    * @param permissionQo
     * @return
     */
    public QueryWrapper<Permission> getEntityWrapper(PermissionQo permissionQo){
        QueryWrapper<Permission> wrapper = new QueryWrapper<Permission>();
        //条件拼接
        if (StringUtils.isNotBlank(permissionQo.getTitle())){
                wrapper.eq(Permission.TITLE,permissionQo.getTitle());
        }
        if (StringUtils.isNotBlank(permissionQo.getIcon())){
                wrapper.eq(Permission.ICON,permissionQo.getIcon());
        }
        if (StringUtils.isNotBlank(permissionQo.getSpread())){
                wrapper.eq(Permission.SPREAD,permissionQo.getSpread());
        }
        if (StringUtils.isNotBlank(permissionQo.getHref())){
                wrapper.eq(Permission.HREF,permissionQo.getHref());
        }
         if (permissionQo.getParentId()!=null){
                wrapper.eq(Permission.PARENT_ID,permissionQo.getParentId());
         }
         if (permissionQo.getSort()!=null){
                wrapper.eq(Permission.SORT,permissionQo.getSort());
         }
         if (permissionQo.getCreateTime()!=null){
                wrapper.eq(Permission.CREATE_TIME,permissionQo.getCreateTime());
         }
         if (permissionQo.getUpdateTime()!=null){
                wrapper.eq(Permission.UPDATE_TIME,permissionQo.getUpdateTime());
         }
        return wrapper;
    }
}