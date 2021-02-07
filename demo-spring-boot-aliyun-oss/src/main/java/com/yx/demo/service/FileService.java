package com.yx.demo.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author yangxi
 */
public interface FileService {

    String uploadUserHeadImg( MultipartFile file);
}