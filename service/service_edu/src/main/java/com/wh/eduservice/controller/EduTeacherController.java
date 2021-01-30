package com.wh.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.commonUtils.R;
import com.wh.eduservice.entity.EduTeacher;
import com.wh.eduservice.entity.vo.TeacherQuery;
import com.wh.eduservice.service.EduTeacherService;
import com.wh.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-12
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {
    //注入service
    @Autowired
    private EduTeacherService eduTeacherService;


    /**
     * 查询所有讲师
     *
     * @return
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public R findAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
//        return list;
        return R.ok().data("items", list);
    }


    /**
     * 逻辑删除讲师的方法
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("/{id}")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true)
                        @PathVariable("id") String id) {
//        return eduTeacherService.removeById(id);
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            //成功
            return R.ok();
        } else {
//            return R.error().message("删除失败");
            return R.error();
        }
    }

    /**
     * 分页查询
     *
     * @param current
     * @param limit
     * @return
     */
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("/pageTeacher/{current}/{limit}")
    //current: 当前页
    //limit: 每页记录数
    public R pageTeacher(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable Long current,
                         @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit) {


        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        try{
            int i = 10/0;
        }catch (Exception e){
            throw new GuliException(20001,"出现自定义异常处理");
        }

        //调用方法完成分页
        //调用方法时候，底层封装，把分页数据封装到pageTeacher
        eduTeacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合

        return R.ok().data("total", total).data("rows", records);

/*        //或者
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);*/
    }

    /**
     * 条件查询带有分页的方法
     *
     * @param current
     * @param limit
     * @param teacherQuery
     * @return
     */
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageTeachercondition(@PathVariable Long current,
                                  @PathVariable Long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询    mysql动态查询sql

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //判断条件值是否为空
        if (!StringUtils.isEmpty(name)) {
            //条件
            wrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现分页
        eduTeacherService.page(pageTeacher, wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
        return R.ok().data("total", total).data("rows", records);
    }

    //添加讲师的方法
    @ApiOperation(value = "新增讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //先根据id查询讲师
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("/getTeacher/{id}")
    public R findById(@ApiParam(name = "id", value = "讲师ID", required = true)
                      @PathVariable String id) {

        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher", eduTeacher);
    }

    //修改讲师
    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("/updateTeacher")
    public R updateById(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }


}

