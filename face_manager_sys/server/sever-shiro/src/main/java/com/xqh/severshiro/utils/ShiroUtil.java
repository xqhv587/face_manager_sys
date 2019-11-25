package com.xqh.severshiro.utils;

import com.xqh.severshiro.config.MyShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.util.ByteSource;

/**
 * @author gjf
 * @date 2019/01/29 16:48
 */
public class ShiroUtil {

    public static void clearAuth(){
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm)rsm.getRealms().iterator().next();
        realm.clearCachedAuthorization();
    }


    /**
     * 添加user的密码加密方法
     */
    public static String  sysMd5(String username,String password) {
        //加密方式
        String hashAlgorithmName = "MD5";
        //以账号作为盐值
        ByteSource salt = ByteSource.Util.bytes(username);
        //加密1024次
        int hashIterations = 1024;
        SimpleHash hash = new SimpleHash(hashAlgorithmName, password, salt, hashIterations);
        return hash.toString();
    }
}



