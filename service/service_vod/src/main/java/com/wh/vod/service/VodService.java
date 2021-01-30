package com.wh.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: wanghui
 **/
public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    void removeMoreAlyVideo(List<String> videoIdList);

}
