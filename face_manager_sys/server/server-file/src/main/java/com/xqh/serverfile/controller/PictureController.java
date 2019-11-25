package com.xqh.serverfile.controller;


import com.xqh.serverfile.exception.MediaTypeException;
import com.xqh.serverfile.model.ResponseBean;
import com.xqh.serverfile.model.ResultVO;
import com.xqh.serverfile.utils.FileTypeUtil;
import com.xqh.serverfile.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@RestController
@RequestMapping("/pic")
public class PictureController {

    @Value("${video.path.redis.time}")
    private Integer redisTime;
    @Value("${default.path}")
    private String videoSaveBasePath;
    @Value("${default.video}")
    private String videoSavePath;
    @Value("${default.image}")
    private String imageSavePath;

    @PostMapping({"/save/image"})
    public ResponseBean<ResultVO> getSaveImage(@RequestParam(required = false,defaultValue = StringUtils.EMPTY) String fileName, MultipartFile file) {
        String header = Optional.ofNullable(file).map(FileUtil::getFileHeader).orElse(StringUtils.EMPTY);
        if (StringUtils.isEmpty(header)){
            throw new MediaTypeException();
        }
        boolean flag = false;
        for (String s : FileTypeUtil.FILE_TYPE_MAP.keySet()){
            if (StringUtils.containsIgnoreCase(header,s)){
                flag = true;
                break;
            }
        }
        if (!flag){
            throw new MediaTypeException();
        }
        String fileNameStr = StringUtils.isEmpty(fileName)?file.getOriginalFilename():fileName+"."+StringUtils.substringAfterLast(file.getOriginalFilename(),".");
        FileUtil.saveFile(file, this.videoSaveBasePath + this.imageSavePath, fileNameStr);
        return new ResponseBean<>(ResultVO.builder().imagePath(this.imageSavePath).fileName(fileNameStr).build());
    }
}

