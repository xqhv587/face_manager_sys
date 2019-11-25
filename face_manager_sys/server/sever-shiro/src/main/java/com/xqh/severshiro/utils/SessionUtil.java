package com.xqh.severshiro.utils;

import com.xqh.severshiro.entity.vo.UserVo;
import org.apache.shiro.SecurityUtils;

/**
 * @author gjf
 * @date 2019/2/20 14:28
 * @version 1.0
 */
public class SessionUtil {
    private final static String SESSION_ADMIN = "userSession";
    private final static String SESSION_ADMIN_ID = "userSessionId";

    public static void setAdmin(UserVo sysAdmin){
        SecurityUtils.getSubject().getSession().setAttribute(SESSION_ADMIN, sysAdmin);
    }

    public static UserVo getAdmin(){
        return  (UserVo) SecurityUtils.getSubject().getSession().getAttribute(SESSION_ADMIN);
    }

    public static void setAdminId(Long sysAdminId){
        SecurityUtils.getSubject().getSession().setAttribute(SESSION_ADMIN_ID, sysAdminId);
    }
    public static Long getAdminId(){
        return  (Long) SecurityUtils.getSubject().getSession().getAttribute(SESSION_ADMIN_ID);
    }

}
