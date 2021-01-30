package com.wh.eduservice.mapper;

import com.wh.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wh.eduservice.entity.vo.CourseInfoVo;
import com.wh.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-01-18
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {


      public CoursePublishVo getPublishCourseInfo(String courseId);
}
