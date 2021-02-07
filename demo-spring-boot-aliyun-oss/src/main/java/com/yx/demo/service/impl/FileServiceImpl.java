package com.yx.demo.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;

import com.yx.demo.config.OSSConfig;
import com.yx.demo.service.FileService;
import com.yx.demo.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yangxi
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private OSSConfig ossConfig;


    @Override
    public String uploadUserHeadImg( MultipartFile file) {
        Assert.notNull(file, "文件不能为空");
        String originalFilename = file.getOriginalFilename();
        Assert.notNull(originalFilename, "源文件名为空");

        //获取相关配置
        String bucketName = ossConfig.getBucketName();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        //创建oss对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //JDK8新特性写法，构建路径
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String folder = dtf.format(ldt);
        String fileName = CommonUtil.generateUUID();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //在oss上创建文件夹test路径
        String newFileName = "user-service/user/headImg/" + folder + "/" + fileName + extension;
        try {
            PutObjectResult result = ossClient.putObject(bucketName, newFileName, file.getInputStream());
            //返回访问路径
            if (null != result) {
                // 示例: https://xigua366-b55.oss-cn-shenzhen.aliyuncs.com/user-service/headImg2021/02/07/91144a62d20e40a397f3d373c52e821e.jpg
                String imgUrl = "https://" + bucketName + "." + endpoint+"/" + newFileName;
                return imgUrl;
            }
        } catch (Exception e) {
            log.error("上传头像失败:", e);
        } finally {
            // 关闭OSS服务
            ossClient.shutdown();
        }

        return null;
    }
}