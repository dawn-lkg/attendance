package com.example.attendance_wx.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.service.AnnouncementsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Announcements)表控制层
 *
 * @author 陈黎明
 * @since 2023-04-28 14:13:44
 */
@RestController
@RequestMapping("announcements")
public class AnnouncementsController{
    /**
     * 服务对象
     */
    @Resource
    private AnnouncementsService announcementsService;
    @GetMapping("/home")
    public Result home(){
        return Result.ok(announcementsService.list());
    }
    @GetMapping("{id}")
    public Result getById(@PathVariable Integer id){
        return Result.ok(announcementsService.getById(id));
    }
}

