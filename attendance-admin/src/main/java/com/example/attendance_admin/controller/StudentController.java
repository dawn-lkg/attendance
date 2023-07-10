package com.example.attendance_admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.entity.Student;
import com.example.attendance_framework.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenliming
 * @date 2023/5/2 11:46
 */
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping("list")
    public Result list(Integer pageNum,Integer pageSize,String query){
        LambdaQueryWrapper<Student> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(query!=null,Student::getName,query);
        Page<Student> page = studentService.page(new Page<>(pageNum, pageSize), lambdaQueryWrapper);
        List<Student> records = page.getRecords();
        Map<String,Object> map=new HashMap<>();
        map.put("records",studentService.getStudentListVo(records));
        map.put("total",page.getTotal());
        return Result.ok(map);
    }
    @PostMapping
    public Result add(@RequestBody Student student){
        System.out.println(student);
        boolean save = studentService.save(student);
        return Result.ok(save);
    }
    @DeleteMapping("{id}")
    public Result delete(@PathVariable("id") Integer id){
        return Result.ok(studentService.removeById(id));
    }
    @GetMapping("{id}")
    public Result getById(@PathVariable("id") Integer id){
        return Result.ok(studentService.getById(id));
    }
    @PutMapping
    public Result update(@RequestBody Student student){
        System.out.println(student);
        return Result.ok(studentService.updateById(student));
    }
}
