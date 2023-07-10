package com.example.attendance_admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.attendance_framework.entity.Course;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.entity.vo.CourseListVo;
import com.example.attendance_framework.entity.vo.CourseVo;
import com.example.attendance_framework.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenliming
 * @date 2023/5/5 19:54
 */
@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping("list")
    public Result list(Integer pageNum,Integer pageSize,String query){
        LambdaQueryWrapper<Course> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(query!=null,Course::getName,query);
        Page<Course> page = courseService.page(new Page<>(pageNum, pageSize), lambdaQueryWrapper);
        List<CourseListVo> collect = page.getRecords().stream().map(item -> {
            return courseService.getCourseVo(item);
        }).collect(Collectors.toList());
        Map<String,Object> map=new HashMap<>();
        map.put("records",collect);
        map.put("total",page.getTotal());
        return Result.ok(map);
    }
    @PostMapping
    public Result add(@RequestBody Course course){
        return Result.ok(courseService.save(course));
    }
    @PutMapping
    public Result update(@RequestBody Course course){
        return Result.ok(courseService.updateById(course));
    }
    @DeleteMapping("{id}")
    public Result delete(@PathVariable("id") Integer id){
        return Result.ok(courseService.removeById(id));
    }
    @GetMapping("{id}")
    public Result getById(@PathVariable("id") Integer id){
        return Result.ok(courseService.getById(id));
    }
    @GetMapping("all")
    public Result getAll(){
        return Result.ok(courseService.list());
    }
}
