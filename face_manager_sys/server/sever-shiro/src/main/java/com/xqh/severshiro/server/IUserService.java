package com.xqh.severshiro.server;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xqh.severshiro.entity.Model.User;
import com.xqh.severshiro.entity.qo.UserQo;
import com.xqh.severshiro.entity.vo.UserVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gjf
 * @since 2019-04-08
 */
public interface IUserService extends IService<User> {

    UserVo get(Long id);

    boolean save(UserQo userQo);

    boolean updateById(UserQo userQo);

    List<UserVo> selectList(UserQo userQo);

    IPage<User> getPages(Long current, Long size, UserQo userQo);

    void deleteById(Long id);
}
