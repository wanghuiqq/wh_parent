package com.wh.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.wh.oss.service.OssService;
import com.wh.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @author: wanghui
 **/
@Service
public class OssServiceImpl implements OssService {


    /**
     * 文件上传到oss
     *
     * @param file
     * @return
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以北京为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.END_POINT;
        // 工具类获取值
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件输入流。
            InputStream inputStream = file.getInputStream();

            //获取文件名称
            String filename = file.getOriginalFilename();

            //1.在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //yu4dsads01.jpg
            filename = uuid + filename;

            //2.把文件按日期分类  2020/12/25/01.jpg
            //获取当前日期 2020/12/25
            String datePath = new DateTime().toString("yyyy/MM/dd");

            //拼接  2020/12/25/01.jpg
            filename = datePath + "/" + filename;

            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数 上传到oss文件路径和文件名称  /aa/bb/1.jpg
            //第三个参数 上传文件输入流
            ossClient.putObject(bucketName, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //https://edu-1010153.oss-cn-beijing.aliyuncs.com/1.jpg
            String url = "https://" + bucketName + "." + endpoint + "/" + filename;
            return url;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
