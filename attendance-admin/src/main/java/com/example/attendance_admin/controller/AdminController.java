package com.example.attendance_admin.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.attendance_framework.entity.Admin;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.service.AdminService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Admin)表控制层
 *
 * @author 陈黎明
 * @since 2023-05-07 17:03:48
 */
@RestController
@RequestMapping("admin")
public class AdminController{
    @Resource
    private AdminService adminService;
    @GetMapping("list")
    public Result list(Integer pageNum,Integer pageSize,String query){
        LambdaQueryWrapper<Admin> lambdaQueryWrapper=new LambdaQueryWrapper<Admin>();
        lambdaQueryWrapper.like(query!=null,Admin::getName,query);
        Page<Admin> page = adminService.page(new Page<>(pageNum, pageSize), lambdaQueryWrapper);
        return Result.ok(page);
    }
    @GetMapping("{id}")
    public Result getById(@PathVariable("id") Integer id){
        return Result.ok(adminService.getById(id));
    }
    @PutMapping
    public Result update(@RequestBody Admin admin){
        if(admin.getPassword()!=null){
            admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        }
        return Result.ok(adminService.updateById(admin));
    }
    @PostMapping
    public Result add(@RequestBody Admin admin){
        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        return Result.ok(adminService.save(admin));
    }
}

