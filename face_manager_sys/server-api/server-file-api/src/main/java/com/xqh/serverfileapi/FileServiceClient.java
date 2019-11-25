package com.xqh.serverfileapi;


import com.xqh.commoncore.model.ResponseBean;
import com.xqh.commonfeign.config.CustomFeignConfig;
import com.xqh.serverfileapi.factory.FileServiceClientFallbackFactory;
import com.xqh.serverfileapi.model.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 *
 * @author ye
 * @date 2018-12-30 23:21
 */
@FeignClient(name="file-service",url = "${pic.server}",configuration = CustomFeignConfig.class, fallbackFactory = FileServiceClientFallbackFactory.class)
public interface FileServiceClient {

    /**
     * 通过关键词获取在线课堂列表
     * @param fileName
     * @return
     */
    @PostMapping(value = "/pic/save/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseBean<ResultVO> saveImage(@RequestParam(value = "fileName", required = false) String fileName, @RequestPart("file") MultipartFile file);

}
