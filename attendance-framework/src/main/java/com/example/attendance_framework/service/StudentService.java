package com.example.attendance_framework.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.attendance_framework.entity.Student;
import com.example.attendance_framework.entity.vo.StudentListVo;
import com.example.attendance_framework.entity.vo.StudentVo;

import java.util.List;

/**
 * (Student)表服务接口
 *
 * @author 陈黎明
 * @since 2023-04-22 21:28:22
 */
public interface StudentService extends IService<Student> {
    Page list(Integer pageNum, Integer pageSize, String query);
    Student getStudentNoPassword(String username,String password);
    StudentVo getStudent(Integer id);
    List<StudentListVo> getStudentListVo(List<Student> list);
}

