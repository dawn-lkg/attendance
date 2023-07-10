package com.example.attendance_wx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.attendance_framework.common.BaseContext;
import com.example.attendance_framework.entity.*;
import com.example.attendance_framework.entity.vo.CourseVo;
import com.example.attendance_framework.service.CourseService;
import com.example.attendance_framework.service.ScService;
import com.example.attendance_framework.service.StudentService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenliming
 * @date 2023/4/26 16:07
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    ScService scService;
    @Autowired
    StudentService studentService;
    @GetMapping("search")
    public Result search(String query){
        List<CourseVo> list = courseService.list(query);
        return Result.ok(list);
    }
    @GetMapping("/teacher")
    public Result getCourseTeacherId(){
        Integer id = Integer.valueOf(BaseContext.getCurrentId());
        return Result.ok(courseService.getCourseByTeacherId(id));
    }
    @PostMapping("add")
    public Result addCourse(@RequestBody Course course){
        course.setTeacherId(Integer.valueOf(BaseContext.getCurrentId()));
        return Result.ok(courseService.save(course));
    }
    @GetMapping("/isJoin")
    public Result isJoin(Integer courseId){
        return Result.ok(scService.isJoinCourse(Integer.valueOf(BaseContext.getCurrentId()),courseId));
    }
    @GetMapping("/info")
    public Result getCourse(Integer id){
        return Result.ok(courseService.getCourse(id));
    }
    @PostMapping("/join")
    public Result joinCourse(@RequestBody Sc sc){
        boolean b = courseService.joinCourse(Integer.valueOf(BaseContext.getCurrentId()), sc.getCourseId());
        return Result.ok(b);
    }
    @DeleteMapping("/cancel/{id}")
    public Result cancelCourse(@PathVariable Integer id){
        System.out.println(id);
        boolean b = courseService.cancelCourse(Integer.valueOf(BaseContext.getCurrentId()), id);
        return Result.ok(b);
    }
    @GetMapping("/student")
    public Result getStudentCourse(){
        return Result.ok(courseService.getStuCourse(Integer.valueOf(BaseContext.getCurrentId())));
    }
    @GetMapping("/stuList")
    public Result getStuList(Integer id){
        LambdaQueryWrapper<Sc> scLambdaQueryWrapper=new LambdaQueryWrapper<>();
        scLambdaQueryWrapper.eq(Sc::getCourseId,id);
        List<Student> collect = scService.list(scLambdaQueryWrapper).stream().map(item -> {
            Student byId = studentService.getById(item.getSutId());
            return byId;
        }).collect(Collectors.toList());
        return Result.ok(collect);
    }
    @GetMapping("{id}")
    public Result getCourseById(@PathVariable("id") Integer id){
        return Result.ok(courseService.getById(id));
    }
    @PutMapping("update")
    public Result update(@RequestBody Course course){
        return Result.ok(courseService.updateById(course));
    }
    @DeleteMapping("{id}")
    @Transactional
    public Result delete(@PathVariable("id") Integer id){
        LambdaQueryWrapper<Sc> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Sc::getCourseId,id);
        boolean remove = scService.remove(lambdaQueryWrapper);
        if(!remove){
            return Result.error(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return Result.ok(courseService.removeById(id));
    }
}
