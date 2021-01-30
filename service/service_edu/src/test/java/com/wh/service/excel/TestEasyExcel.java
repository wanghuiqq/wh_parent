package com.wh.service.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * 写操作
 * @author: wanghui
 **/
public class TestEasyExcel {

    public static void main(String[] args) {
      /*  //实现excel写的操作
        //1.设置写入文件夹地址和excel文件名称
        String filename = "F:\\write.xlsx";

        //2.调用easyexcel里面的方法实现写操作
        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());*/

        //读操作
        String filename = "F:\\write.xlsx";

        //调用
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    //创建方法返回list集合
    public static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return list;
    }
}
