package com.xqh.severshiro.config;

import com.xqh.severshiro.entity.vo.UserVo;
import com.xqh.severshiro.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author gjf
 * @version 1.0
 * @date 2019/5/24 13:23
 */
@Slf4j
public class MyAccessControlFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        String url = getPathWithinApplication(servletRequest);

        //获得session判断是否存在
        UserVo user = SessionUtil.getAdmin();
        if (user == null) {
            log.info("当前用户正在访问的 url => " + url);
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        saveRequest(servletRequest);
        //访问被拒绝，重定向到登录页面。
        WebUtils.issueRedirect(servletRequest, servletResponse, "/login.html");
        return false;
    }

}
