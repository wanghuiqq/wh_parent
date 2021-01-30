package com.wh.oss.controller;

import com.wh.commonUtils.R;
import com.wh.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * @author: wanghui
 **/
@Api(description="阿里云文件管理")
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin //跨域
public class OssController {


    @Autowired
    private OssService ossService;

    @ApiOperation(value = "文件上传")
    @PostMapping
    public R uploadOss(MultipartFile file){
        //获取上传文件 MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);

        return R.ok().data("url",url);
    }
}
