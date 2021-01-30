package com.wh.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wh.eduservice.entity.EduSubject;
import com.wh.eduservice.entity.excel.SubjectData;
import com.wh.eduservice.entity.subject.OneSubject;
import com.wh.eduservice.entity.subject.TwoSubject;
import com.wh.eduservice.listener.SubjectExcelListener;
import com.wh.eduservice.mapper.EduSubjectMapper;
import com.wh.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-01-15
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {

        try {
            //文件输入流
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //课程分类列表（树形）
    @Override
    public List<OneSubject> getOneTwoSubject() {

        //1.查询所有一级分类 parent_id= 0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //2.查询所有二级分类 parent_id!= 0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建list集合 用于存储最终封装数据
        List<OneSubject> finalSubject = new ArrayList<>();

        //3.封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每一个一级分类对象，获取每个一级分类对象
        //封装到要求的list集合里面 List<OneSubject> finalSubject
        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject eduSubject = oneSubjectList.get(i);
            //得到的oneSubjectList每个edusubject对象
            //多个OneSubject放到finalSubject里面
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            //把eduSubject的值复制到oneSubject
            BeanUtils.copyProperties(eduSubject,oneSubject);

            //多个oneSubject放到eduSubject
            finalSubject.add(oneSubject);


            //在一级分类循环遍历查询所有的二级分类
            //创建list集合封装到每个一级分类的二级分类
            List<TwoSubject> twoFinalSubject = new ArrayList<>();
            //遍历二级分类
            for (int j = 0; j < twoSubjectList.size(); j++) {
                //获取每个二级分类
                EduSubject tSubject = twoSubjectList.get(j);
                //判断二级分类parentid和一级分类的id是否相同
                if(tSubject.getParentId().equals(eduSubject.getId())){
                    //把tSubject的值复制到TwoSubject中，放到twoFinalSubject里
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubject.add(twoSubject);
                }
            }

            //把一级下面所有二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubject);
        }
        //4.封装二级分类
        return finalSubject;
    }
}
