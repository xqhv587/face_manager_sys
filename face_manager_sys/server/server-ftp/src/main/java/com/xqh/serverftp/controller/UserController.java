package com.xqh.serverftp.controller;

import com.xqh.serverftp.service.DoUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xqh3622
 */
@Controller("user")
public class UserController {

//    @Autowired
//    private DoUploadService doUploadService;

    @RequestMapping("test")
    public void test(MultipartFile multipartFile){
        DoUploadService doUploadService = new DoUploadService();

        doUploadService.doUpload( multipartFile);
    }

    @RequestMapping("/ss")
    public String ss(){
        return "een";
    }

}
