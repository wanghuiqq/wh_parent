package com.wh.eduservice.client;

import com.wh.commonUtils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 熔错
 * @author: wanghui
 **/
@Component
public class VodFileDegradeFeignClient implements VodClient {

    //出错之后才执行
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
