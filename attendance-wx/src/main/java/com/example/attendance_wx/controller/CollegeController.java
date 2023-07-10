package com.example.attendance_wx.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2023-04-28 20:18:28
 */
@RestController
@RequestMapping("college")
public class CollegeController{
    /**
     * 服务对象
     */
    @Resource
    private CollegeService collegeService;
    @GetMapping("all")
    public Result list(){
        return Result.ok(collegeService.list());
    }
}

