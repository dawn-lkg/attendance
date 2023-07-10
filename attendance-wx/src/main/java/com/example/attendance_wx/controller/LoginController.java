package com.example.attendance_wx.controller;

import com.example.attendance_framework.constants.SystemConstants;
import com.example.attendance_framework.entity.*;
import com.example.attendance_framework.service.StudentService;
import com.example.attendance_framework.service.TeacherService;
import com.example.attendance_framework.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenliming
 * @date 2023/4/22 22:12
 */
@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @PostMapping
    public Result login(@RequestBody User user){
        Integer role = user.getRole();
        String token=null;
        if(role== SystemConstants.ROLE_STUDENT){
             Student student= studentService.getStudentNoPassword(user.getUsername(), user.getPassword());
             if (student==null){
                 return Result.error(AppHttpCodeEnum.LOGIN_ERROR);
             }
             token=JwtTokenUtil.generateToken(student.getId().toString());
        }else if(role==SystemConstants.ROLE_TEACHER){
             Teacher teacher = teacherService.getTeacherNoPassword(user.getUsername(), user.getPassword());
             if(teacher==null){
                 return Result.error(AppHttpCodeEnum.LOGIN_ERROR);
             }
             token=JwtTokenUtil.generateToken(teacher.getId().toString());
        }
        return Result.ok(token);
    }
}
