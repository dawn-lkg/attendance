package com.example.attendance_admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.attendance_framework.entity.Admin;
import com.example.attendance_framework.entity.AppHttpCodeEnum;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.service.AdminService;
import com.example.attendance_framework.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author chenliming
 * @date 2023/4/30 20:15
 */
@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private AdminService adminService;
    @PostMapping
    @Transactional
    public Result login(@RequestBody Admin admin){
        String username=admin.getUsername();
        String password=admin.getPassword();
        if(username==null){
            return Result.error(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        if(password==null){
            return Result.error(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<Admin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername,username);
        lambdaQueryWrapper.eq(Admin::getPassword,password);
        Admin one = adminService.getOne(lambdaQueryWrapper);
        if(one==null){
            return Result.error(AppHttpCodeEnum.LOGIN_ERROR);
        }
        one.setLastLogin(new Date());
        boolean b = adminService.updateById(one);
        if(!b){
            return Result.error(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return Result.ok(JwtTokenUtil.generateToken(one.getId().toString()));
    }
}
