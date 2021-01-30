package com.wh.eduservice.service;

import com.wh.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-01-12
 */
public interface EduTeacherService extends IService<EduTeacher> {

    //查询前四条讲师
    List<EduTeacher> getTeachers();

}
