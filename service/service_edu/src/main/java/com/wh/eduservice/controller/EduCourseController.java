package com.wh.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.commonUtils.R;
import com.wh.eduservice.entity.EduCourse;
import com.wh.eduservice.entity.EduTeacher;
import com.wh.eduservice.entity.vo.CourseInfoVo;
import com.wh.eduservice.entity.vo.CoursePublishVo;
import com.wh.eduservice.entity.vo.CourseQuery;
import com.wh.eduservice.entity.vo.TeacherQuery;
import com.wh.eduservice.service.EduCourseService;
import com.wh.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程表
 * @author testjava
 * @since 2021-01-18
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    //课程列表基本实现
    //带有分页的条件查询

    //查询所有课程
    @GetMapping("/getCourseList")
    public R getCourseList(){

        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list",list);
    }

    //分页查询课程
    @ApiOperation(value = "分页课程列表")
    @GetMapping("/pageCourse/{current}/{limit}")
    //current: 当前页
    //limit: 每页记录数
    public R pageTeacher(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable Long current,
                         @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit) {
           Page<EduCourse> pageCourse = new Page<>(current, limit);

        try{
            int i = 10/0;
        }catch (Exception e){
            throw new GuliException(20001,"出现自定义异常处理");
        }

        //调用方法完成分页
        //调用方法时候，底层封装，把分页数据封装到pageTeacher
        courseService.page(pageCourse, null);

        long total = pageCourse.getTotal();//总记录数
        List<EduCourse> records = pageCourse.getRecords();//数据list集合

        return R.ok().data("total", total).data("rows", records);
    }
    @ApiOperation(value = "多条件组合查询带分页效果的课程列表")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageCoursecondition(@PathVariable Long current,
                                  @PathVariable Long limit,
                                  @RequestBody(required = false) CourseQuery courseQuery) {
        Page<EduCourse> pageCourse = new Page<>(current, limit);
        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //多条件组合查询    mysql动态查询sql

        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        //判断条件值是否为空
        if(!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }

        //按照添加时间降序来排序记录
        wrapper.orderByDesc("gmt_create");

        courseService.page(pageCourse,wrapper);

        long total = pageCourse.getTotal();
        List<EduCourse> records = pageCourse.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    //添加课程基本的方法
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加课程之后id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }


    //根据课程id查询课程的基本信息
    @GetMapping("/getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    //根据课程id查询课程确认信息
    @GetMapping("/getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }


    //课程最终发布
    //修改课程状态
    @PostMapping("/publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();
    }


    //课程删除
    @DeleteMapping("/{courseId}")
    public R deleteCourseId(@PathVariable String courseId){
        courseService.removeByCourseId(courseId);
        return R.ok();
    }
}

