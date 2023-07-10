package com.example.attendance_admin.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.attendance_framework.entity.College;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.service.CollegeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (College)表控制层
 *
 * @author 陈黎明
 * @since 2023-04-27 23:42:17
 */
@RestController
@RequestMapping("college")
public class CollegeController{
    /**
     * 服务对象
     */
    @Resource
    private CollegeService collegeService;
    @GetMapping("list")
    public Result list(Integer pageNum,Integer pageSize,String query){
        System.out.println(pageNum+":"+pageSize);
        LambdaQueryWrapper<College> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(query!=null,College::getCollegeName,query);
        Page<College> page = collegeService.page(new Page<>(pageNum, pageSize), lambdaQueryWrapper);
        return Result.ok(page);
    }
    @GetMapping("{id}")
    public Result getById(@PathVariable("id") Integer id){
        return Result.ok(collegeService.getById(id));
    }
    @DeleteMapping("{id}")
    public Result delete(@PathVariable("id") Integer id){
        return Result.ok(collegeService.removeById(id));
    }
    @PostMapping
    public Result add(@RequestBody College college){
        return Result.ok(collegeService.save(college));
    }
    @PutMapping
    public Result update(@RequestBody College college){
        return Result.ok(collegeService.updateById(college));
    }
    @GetMapping("all")
    public Result getAll(){
        return Result.ok(collegeService.list());
    }
}

