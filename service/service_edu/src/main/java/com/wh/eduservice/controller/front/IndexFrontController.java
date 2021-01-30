package com.wh.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wh.commonUtils.R;
import com.wh.eduservice.entity.EduCourse;
import com.wh.eduservice.entity.EduTeacher;
import com.wh.eduservice.service.EduCourseService;
import com.wh.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: wanghui
 **/
@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    //查询前八条热门课程,查询前四条讲师
/*    @GetMapping("/index")
    public R index(){
        //查询前八条热门课程
        QueryWrapper<EduCourse> wrapperCourse = new QueryWrapper<>();
        wrapperCourse.orderByDesc("id");
        wrapperCourse.last("limit 8");
        List<EduCourse> eduList = courseService.list(wrapperCourse);

        //查询前四条讲师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);
        return R.ok().data("eduList",eduList).data("teacherList",teacherList);
    }*/

    @GetMapping("/index")
    public R index(){
        //查询前八条热门课程
        List<EduCourse> eduList = courseService.getCourses();
        //查询前四条讲师

        List<EduTeacher> teacherList = teacherService.getTeachers();
        return R.ok().data("eduList",eduList).data("teacherList",teacherList);
    }
}
