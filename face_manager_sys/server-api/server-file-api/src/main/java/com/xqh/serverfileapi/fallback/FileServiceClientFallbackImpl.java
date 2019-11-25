package com.xqh.serverfileapi.fallback;


import com.xqh.commoncore.model.ResponseBean;
import com.xqh.serverfileapi.FileServiceClient;
import com.xqh.serverfileapi.model.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 日志断路器实现
 *
 * @author tangyi
 * @date 2019/3/23 23:39
 */
@Slf4j
@Component
public class FileServiceClientFallbackImpl implements FileServiceClient {

    private Throwable throwable;


    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }


    @Override
    public ResponseBean<ResultVO> saveImage(String fileName, MultipartFile file) {
        log.error("feign 储存照片失败:{},{}", fileName, throwable);
        return null;
    }
}
