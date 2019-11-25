package com.xqh.commoncore.web;

import com.alibaba.fastjson.JSONObject;

import com.xqh.commoncore.config.RedisStringUtil;
import com.xqh.commoncore.constant.CommonConstant;
import com.xqh.commoncore.constant.ServiceConstant;
import com.xqh.commoncore.model.UserInfo;
import com.xqh.commoncore.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * 基础Controller
 *
 * @author tangyi
 * @date 2018-08-23 12:01
 */
@Slf4j
public class BaseController {
    public static final String TEXT_UTF8 = "text/html;charset=UTF-8";
    public static final String JSON_UTF8 = "application/json;charset=UTF-8";
    public static final String XML_UTF8 = "application/xml;charset=UTF-8";
    protected int functionId=0;

    @Autowired
    RedisStringUtil redisUtil;

    @Value("${spring.profiles.active}")
    private String active;


    public int getFunctionId() {
        return functionId;
    }

    protected String getCurrentUserId() {
//        return "9306";


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userInfoCookie = CookieUtils.getCookieValue(request, "usertoken");
        String tokenCookieUserId = CookieUtils.getCookieValue(request, "currentloginusername");
        if (StringUtils.isEmpty(userInfoCookie) && StringUtils.isEmpty(tokenCookieUserId)) {
            return null;
        }
        if (StringUtils.equalsIgnoreCase(ServiceConstant.ENV_TEST,active) && StringUtils.isBlank(tokenCookieUserId)){
            return tokenCookieUserId;
        }else {
            String userId = null;
            if(StringUtils.isNotEmpty(userInfoCookie)) {
                byte[] userInfoToken = redisUtil.getValue(userInfoCookie);
                if (userInfoToken == null || userInfoToken.length < 1) {
                    return null;
                }
                userId = new String(userInfoToken);
            }
            if(StringUtils.isEmpty(userId) && StringUtils.isNotEmpty(tokenCookieUserId)){
                userId = tokenCookieUserId;
            }
            return userId;
        }
    }

    protected UserInfo getCurrentUserInfo(){
        if (StringUtils.equalsIgnoreCase(ServiceConstant.ENV_TEST,active)){
            String tokenCookie = getCurrentUserId();
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(tokenCookie);
            userInfo.setRealName("测试姓名");
            return userInfo;
        } else {
            String userId = "";
            String tokenCookie = getCurrentUserId();
            if (tokenCookie == null) {
                return null;
            } else {
                userId = tokenCookie;
            }
            byte[] userInfoToken = redisUtil.getValue("user_"+userId);
            if (userInfoToken == null || userInfoToken.length < 1) {
                return null;
            }
            return JSONObject.parseObject(new String(userInfoToken),UserInfo.class);
        }
    }

    private  String getString(HttpServletRequest request) {
        DataInputStream in = null;
        try {
            in = new DataInputStream(request.getInputStream());
            byte[] buf = new byte[request.getContentLength()];
            in.readFully(buf);

            return new String(buf, CommonConstant.UTF8);
        } catch (Exception e) {
            return "";
        } finally {
            if (null != in){
                try {
                    in.close();// 关闭数据流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public  String redirect(String format, Object... arguments) {
        return new StringBuffer("redirect:").append(MessageFormat.format(format, arguments)).toString();
    }
}
