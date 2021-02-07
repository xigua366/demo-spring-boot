package com.yx.demo.controller;

import com.yx.demo.model.request.UploadUserImageRequest;
import com.yx.demo.service.FileService;
import com.yx.demo.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("oss/test")
public class OssTestController {

    @Autowired
    private FileService fileService;

    /**
     * 上传用户头像
     *
     * 默认文件大小 1M,超过会报错
     *
     * @param file
     * @return
     */
    @PostMapping(value = "upload")
    public JsonData uploadHeaderImg(@RequestPart("file") MultipartFile file, UploadUserImageRequest uploadUserImageRequest){

        System.out.println(uploadUserImageRequest.getImageName());

        String result = fileService.uploadUserHeadImg(file);

        return result != null ? JsonData.buildSuccess(result):JsonData.buildError("上传用户头像失败");

    }
}