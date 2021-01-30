package com.wh.eduservice.service;

import com.wh.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wh.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-01-15
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getOneTwoSubject();

}
