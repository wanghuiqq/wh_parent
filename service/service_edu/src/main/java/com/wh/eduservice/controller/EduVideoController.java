package com.wh.eduservice.controller;


import com.wh.commonUtils.R;
import com.wh.eduservice.client.VodClient;
import com.wh.eduservice.entity.EduVideo;
import com.wh.eduservice.service.EduVideoService;
import com.wh.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-18
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/video")
public class EduVideoController {


    @Autowired
    private EduVideoService videoService;

    //注入vodclient
    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }

    //修改小节
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }

    //删除小节  同时把阿里云视频删除
    @DeleteMapping("/{id}")
    public R deleteVideo(@PathVariable String id){
        //根据小节id获取视频id，然后在调用方法实现视频的删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();

        //判断小节里面是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)){
            //根据视频id，远程调用视频实现删除
            R result = vodClient.removeAlyVideo(videoSourceId);
            if(result.getCode() ==20001){
                throw new GuliException(20001,"删除视频失败，熔断器");
            }
        }
        videoService.removeById(id);
        return R.ok();
    }
}

