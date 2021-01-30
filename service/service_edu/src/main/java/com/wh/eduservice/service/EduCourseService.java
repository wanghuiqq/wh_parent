package com.wh.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wh.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wh.eduservice.entity.vo.CourseInfoVo;
import com.wh.eduservice.entity.vo.CoursePublishVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-01-18
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程的基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    ////根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);

    //删除课程
    void removeByCourseId(String courseId);

    //查询前八条热门课程
    List<EduCourse> getCourses();
}
