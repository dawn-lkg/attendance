package com.example.attendance_admin.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.attendance_framework.common.BaseContext;
import com.example.attendance_framework.entity.Announcements;
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
 * @since 2023-05-07 12:40:38
 */
@RestController
@RequestMapping("announce")
public class AnnouncementsController {
    /**
     * 服务对象
     */
    @Resource
    private AnnouncementsService announcementsService;
    @GetMapping("list")
    public Result list(Integer pageNum,Integer pageSize,String query){
        LambdaQueryWrapper<Announcements> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(query!=null,Announcements::getContent,query);
        Page<Announcements> page = announcementsService.page(new Page<>(pageNum, pageSize), lambdaQueryWrapper);
        return Result.ok(page);
    }
    @PostMapping
    public Result add(@RequestBody Announcements announcements){
        announcements.setAuthorId(Integer.valueOf(BaseContext.getCurrentId()));
        return Result.ok(announcementsService.save(announcements));
    }
    @GetMapping("{id}")
    public Result getById(@PathVariable("id") Integer id){
        return Result.ok(announcementsService.getById(id));
    }
    @PutMapping
    public Result update(@RequestBody Announcements announcements){
        return Result.ok(announcementsService.updateById(announcements));
    }
    @DeleteMapping("{id}")
    public Result delete(@PathVariable("id") Integer id){
        return Result.ok(announcementsService.removeById(id));
    }
}

