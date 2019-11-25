package com.xqh.serverfile.interceptor;


import com.xqh.serverfile.redis.RedisStringUtil;
import com.xqh.serverfile.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yz3702
 * @since 2018/8/14
 **/
@Component
@Slf4j
public class TransportInterceptor implements HandlerInterceptor {

    @Value("${spring.profiles.active:prod}")
    private String active;

    @Autowired
    private RedisStringUtil redisStringUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        if (StringUtils.equals(request.getRequestURI(),"/save/image") || request.getRequestURI().indexOf("photo") > 0){
            if (StringUtils.equalsIgnoreCase("test",active)){
                String tokenCookieUserId = CookieUtils.getCookieValue(request, "currentloginusername");
                if (NumberUtils.isDigits(tokenCookieUserId)){
                    return true;
                }
            } else {
                String userInfoCookie = CookieUtils.getCookieValue(request, "usertoken");
                if (StringUtils.isEmpty(userInfoCookie)) {
                    return false;
                }
                byte[] userInfoToken = redisStringUtil.getValue(userInfoCookie);
                if (userInfoToken == null || userInfoToken.length < 1) {
                    return false;
                }
                String userId  = new String(userInfoToken);
                if (StringUtils.isNotBlank(userId)){
                    return true;
                }
            }
        }
        return false;

    }

}
