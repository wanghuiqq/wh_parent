package com.wh.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: wanghui
 **/
@Data
public class CourseQuery {

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private String status;

}