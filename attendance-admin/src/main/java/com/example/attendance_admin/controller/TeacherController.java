package com.example.attendance_admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.entity.Teacher;
import com.example.attendance_framework.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenliming
 * @date 2023/5/5 19:39
 */
@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @GetMapping("list")
    public Result list(Integer pageNum,Integer pageSize,String query){
        LambdaQueryWrapper<Teacher> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(query!=null,Teacher::getName,query);
        Page<Teacher> page = teacherService.page(new Page<>(pageNum, pageSize), lambdaQueryWrapper);
        return Result.ok(page);
    }
    @GetMapping("{id}")
    public Result getById(@PathVariable("id") Integer id){
        return Result.ok(teacherService.getById(id));
    }
    @PostMapping
    public Result add(@RequestBody Teacher teacher){
        return Result.ok(teacherService.save(teacher));
    }
    @DeleteMapping("{id}")
    public Result delete(@PathVariable("id") Integer id){
        return Result.ok(teacherService.removeById(id));
    }
    @PutMapping
    public Result update(@RequestBody Teacher teacher){
        return Result.ok(teacherService.updateById(teacher));
    }
    @GetMapping("all")
    public Result getAll(){
        return Result.ok(teacherService.list());
    }
}
