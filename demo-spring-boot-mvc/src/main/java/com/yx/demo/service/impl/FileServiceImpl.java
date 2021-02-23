package com.yx.demo.service.impl;

import com.yx.demo.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadUserHeadImg( MultipartFile file) {
        Assert.notNull(file, "文件不能为空");
        String originalFilename = file.getOriginalFilename();
        Assert.notNull(originalFilename, "源文件名为空");

        // 保存到存储设备（比如数据库，比如分布式文件系统）
        String url = "";

        return url;
    }
}