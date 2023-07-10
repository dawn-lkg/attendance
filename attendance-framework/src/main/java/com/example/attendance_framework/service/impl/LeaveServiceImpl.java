package com.example.attendance_framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendance_framework.dao.LeaveDao;
import com.example.attendance_framework.entity.Leave;
import com.example.attendance_framework.entity.Student;
import com.example.attendance_framework.entity.Teacher;
import com.example.attendance_framework.entity.vo.LeaveListVo;
import com.example.attendance_framework.service.LeaveService;
import com.example.attendance_framework.service.StudentService;
import com.example.attendance_framework.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Leave)表服务实现类
 *
 * @author 陈黎明
 * @since 2023-05-05 21:27:10
 */
@Service("leaveService")
public class LeaveServiceImpl extends ServiceImpl<LeaveDao, Leave> implements LeaveService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Override
    public LeaveListVo getLeaveListVo(Leave leave) {
        LeaveListVo leaveListVo = new LeaveListVo();
        Student student = studentService.getById(leave.getStudentId());
        Teacher teacher = teacherService.getById(leave.getTeacherId());
        BeanUtils.copyProperties(leave,leaveListVo);
        leaveListVo.setStudentName(student.getName());
        leaveListVo.setTeacherName(teacher.getName());
        return leaveListVo;
    }
}

