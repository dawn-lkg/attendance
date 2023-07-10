package com.example.attendance_framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.attendance_framework.entity.Teacher;

/**
 * (Teacher)表服务接口
 *
 * @author 陈黎明
 * @since 2023-04-22 22:04:50
 */
public interface TeacherService extends IService<Teacher> {
    Teacher getTeacherNoPassword(String username,String password);
}

