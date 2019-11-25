package com.xqh.severshiro.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqh.severshiro.dao.UserMapper;
import com.xqh.severshiro.entity.Model.User;
import com.xqh.severshiro.entity.qo.UserQo;
import com.xqh.severshiro.entity.vo.UserVo;
import com.xqh.severshiro.server.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserVo get(Long id) {
        User user = getById(id);
        return toVo(user);
    }

    @Override
    public List<UserVo> selectList(UserQo userQo) {
        QueryWrapper<User> wrapper = getEntityWrapper(userQo);
        List<User> userList =  list(wrapper);
        return userList.stream().map(this::toVo).collect(Collectors.toList());
    }

    @Override
    public IPage<User> getPages(Long current, Long size, UserQo userQo) {
        QueryWrapper<User> userQueryWrapper = getEntityWrapper(userQo);
        Integer count = baseMapper.selectCount(userQueryWrapper);
        IPage<User> userIPage = new Page<>(current, size, count);
        return baseMapper.selectPage(userIPage, userQueryWrapper);
    }

    @Override
    public boolean save(UserQo userQo) {
        User user = new User();
        BeanUtils.copyProperties(userQo, user);
        return save(user);
    }

    @Override
    public boolean updateById(UserQo userQo) {
        User user = new User();
        BeanUtils.copyProperties(userQo, user);
        return updateById(user);
    }

    @Override
    public void deleteById(Long id) {
        removeById(id);
    }

    public UserVo toVo(User user){
        UserVo userVo = new UserVo();
        if(user!=null){
            BeanUtils.copyProperties(user, userVo);
        }
        return userVo;
    }

    /**
    *  公共查询条件
    * @param userQo
     * @return
     */
    public QueryWrapper<User> getEntityWrapper(UserQo userQo){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        //条件拼接
        if (StringUtils.isNotBlank(userQo.getName())){
            wrapper.eq(User.NAME,userQo.getName());
        }
        if (StringUtils.isNotBlank(userQo.getPassword())){
            wrapper.eq(User.PASSWORD,userQo.getPassword());
        }
         if (userQo.getRoleId()!=null){
             wrapper.eq(User.ROLE_ID,userQo.getRoleId());
         }


//         if(userQo.getStatus() != null){
//             wrapper.eq(User.STATUS, userQo.getStatus());
//         }
        return wrapper;
    }
}