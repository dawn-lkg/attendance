package com.example.attendance_framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.attendance_framework.entity.Sc;

import java.util.List;

/**
 * (Sc)表服务接口
 *
 * @author 陈黎明
 * @since 2023-04-26 21:10:56
 */
public interface ScService extends IService<Sc> {
    boolean isJoinCourse(Integer stuId,Integer courseId);
    List<Sc> getCourseStu(Integer courseId);
}

