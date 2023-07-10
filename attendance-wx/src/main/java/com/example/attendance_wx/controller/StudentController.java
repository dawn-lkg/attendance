package com.example.attendance_wx.controller;

import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenliming
 * @date 2023/4/22 21:32
 */
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("list")
    public Result list(Integer pageNum,Integer pageSize,String query){
        return Result.ok(studentService.list(pageNum,pageSize,query));
    }
}
