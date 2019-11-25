package com.xqh.serverftp.service;

import com.xqh.serverftp.config.FtpProperties;
import com.xqh.serverftp.dao.UserMapper;
import com.xqh.serverftp.enums.StatusEnum;
import com.xqh.serverftp.model.User;
import com.xqh.serverftp.utils.FileNameUtil;
import com.xqh.serverftp.utils.FtpUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xqh3622
 */
public class DoUploadServiceMulil {
    @Autowired
    FtpProperties ftpProperties;
    @Autowired
    UserMapper userMapper;

    public Map<String, Object> doUpload(MultipartFile[] multipartFiles, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String headAll="";
        //  取出session中存的用户user
        User user = (User) session.getAttribute("user");
        //取出user中的id
        Integer id = user.getId();
        //根据id调用工具类生成新文件名
        String newFileName = FileNameUtil.getFileName(id);

        //对文件数组进行遍历
        for (MultipartFile multipartFile : multipartFiles) {
            //老文件名
            String oldFileName = multipartFile.getOriginalFilename();
            //截取老文件名的后缀
            String substring = oldFileName.substring(oldFileName.lastIndexOf("."));
            //将后缀放在新文件名的后面
            newFileName = newFileName + substring;
            //生成路径
            String filePath = new DateTime().toString("yyyy/MM/dd");
            //上传
            try {
                Boolean resultBoolean = FtpUtil.uploadFile(ftpProperties.getHost(), Integer.parseInt(ftpProperties.getPort()), ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newFileName, multipartFile.getInputStream());
                //判断是否上传成功
                if (resultBoolean) {
                    //上传成功
                    String head = ftpProperties.getHttpPath() + File.separator + filePath + File.separator + newFileName;
                    //将当前图片的地址加进headAll，组合成一个新字符串,每个地址中间用\隔开
                    headAll=headAll+";"+head;
                } else {
                    //如果没有上传成功
                    resultMap.put(StatusEnum.ERROR.getCodeName(), StatusEnum.ERROR.getCode());
                    resultMap.put(StatusEnum.SUCCESS.getResultName(), headAll);
                    //只要有一个没有上传成功，直接结束上传，不再继续
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       // user.setHead(headAll);
        //调用修改方法，更新数据库中的头像名
       // int i = userMapper.updateHead(user);
        //如果更新成功
        int i=5;
        if (i > 0) {
            resultMap.put(StatusEnum.SUCCESS.getCodeName(), StatusEnum.SUCCESS.getCode());
            resultMap.put(StatusEnum.SUCCESS.getResultName(), headAll);
        } else {
            //如果没有更新成功
            resultMap.put(StatusEnum.FAILED.getCodeName(), StatusEnum.FAILED.getCode());
            resultMap.put(StatusEnum.SUCCESS.getResultName(), headAll);
        }
        return resultMap;
    }
}
