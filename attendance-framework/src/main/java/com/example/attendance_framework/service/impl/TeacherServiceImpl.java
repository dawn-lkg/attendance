package com.example.attendance_framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendance_framework.dao.TeacherDao;
import com.example.attendance_framework.entity.Student;
import com.example.attendance_framework.entity.Teacher;
import com.example.attendance_framework.service.TeacherService;
import org.springframework.stereotype.Service;

/**
 * (Teacher)表服务实现类
 *
 * @author 陈黎明
 * @since 2023-04-22 22:04:50
 */
@Service("teacherService")
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, Teacher> implements TeacherService {

    @Override
    public Teacher getTeacherNoPassword(String username, String password) {
        LambdaQueryWrapper<Teacher> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Teacher::getId,username);
        lambdaQueryWrapper.eq(Teacher::getPassword,password);
        return getOne(lambdaQueryWrapper);
    }
}

