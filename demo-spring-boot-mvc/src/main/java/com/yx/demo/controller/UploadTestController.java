package com.yx.demo.controller;

import com.yx.demo.exception.BizCodeEnum;
import com.yx.demo.service.FileService;
import com.yx.demo.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("/upload/test")
public class UploadTestController {

    private static final Logger log = LoggerFactory.getLogger(UploadTestController.class);

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
    public JsonData uploadHeaderImg(@RequestPart("file") MultipartFile file, @RequestParam(value = "image_name", required = false) String imageName) {

        log.info("imageName:" + imageName);
        String result = fileService.uploadUserHeadImg(file);
        return result != null?JsonData.buildSuccess(result):JsonData.buildError(BizCodeEnum.FILE_UPLOAD_USER_IMG_FAIL);
    }

}