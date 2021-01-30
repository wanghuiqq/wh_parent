package com.wh.eduservice.controller;


import com.wh.commonUtils.R;
import com.wh.eduservice.entity.EduSubject;
import com.wh.eduservice.entity.subject.OneSubject;
import com.wh.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 课程科目
 * 课程分类
 * @author testjava
 * @since 2021-01-15
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取上传文件过来，把文件内容读取出来
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){
        //上传过来excel文件
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    //课程分类列表（树形）
    @GetMapping("/getAllSubject")
    public R getAllSubject(){
        //list泛型集合是一级分类
        List<OneSubject> list = subjectService.getOneTwoSubject();
        return R.ok().data("list",list);
    }
}

