package com.example.attendance_wx.controller;

import com.example.attendance_framework.common.BaseContext;
import com.example.attendance_framework.constants.SystemConstants;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.entity.Teacher;
import com.example.attendance_framework.service.StudentService;
import com.example.attendance_framework.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenliming
 * @date 2023/4/26 11:12
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @GetMapping("info")
    public Result getInfo(Integer roleId){
        Object obj=null;
        Integer id= Integer.valueOf(BaseContext.getCurrentId());
        if(roleId== SystemConstants.ROLE_STUDENT){
            obj=studentService.getStudent(id);
        }else if(roleId==SystemConstants.ROLE_TEACHER){
            obj=teacherService.getById(id);
        }
        return Result.ok(obj);
    }
}
