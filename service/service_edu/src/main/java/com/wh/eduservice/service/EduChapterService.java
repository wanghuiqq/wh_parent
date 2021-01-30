package com.wh.eduservice.service;

import com.wh.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wh.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-01-18
 */
public interface EduChapterService extends IService<EduChapter> {

    //课程大纲列表，根据课程id查询
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    //删除章节
    boolean deleteChapterId(String chapterId);

    //根据课程id删除章节
    void removeChapterByCourseId(String courseId);
}
