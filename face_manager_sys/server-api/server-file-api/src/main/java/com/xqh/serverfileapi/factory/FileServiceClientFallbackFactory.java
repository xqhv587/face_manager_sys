package com.xqh.serverfileapi.factory;


import com.xqh.serverfileapi.FileServiceClient;
import com.xqh.serverfileapi.fallback.FileServiceClientFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户断路器工厂
 *
 * @author tangyi
 * @date 2019/3/23 23:38
 */
@Component
public class FileServiceClientFallbackFactory implements FallbackFactory<FileServiceClient> {

    @Override
    public FileServiceClient create(Throwable throwable) {
        FileServiceClientFallbackImpl userServiceClientFallback = new FileServiceClientFallbackImpl();
        userServiceClientFallback.setThrowable(throwable);
        return userServiceClientFallback;
    }
}
