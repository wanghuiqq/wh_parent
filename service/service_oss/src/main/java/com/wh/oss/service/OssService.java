package com.wh.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: wanghui
 **/
public interface OssService {

    //文件上传至阿里云
    String uploadFileAvatar(MultipartFile file);
}
