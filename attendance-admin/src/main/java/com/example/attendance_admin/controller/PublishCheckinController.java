package com.example.attendance_admin.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.attendance_framework.entity.PublishCheckin;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.entity.vo.PublishCheckinVo;
import com.example.attendance_framework.service.CheckinService;
import com.example.attendance_framework.service.PublishCheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (PublishCheckin)表控制层
 *
 * @author 陈黎明
 * @since 2023-05-07 20:23:15
 */
@RestController
@RequestMapping("publishCheckin")
public class PublishCheckinController {
    /**
     * 服务对象
     */
    @Resource
    private PublishCheckinService publishCheckinService;
    @Autowired
    CheckinService checkinService;
    @GetMapping("list")
    public Result list(Integer pageNum,Integer pageSize,Integer status,Integer teacherId,Integer courseId){
        LambdaQueryWrapper<PublishCheckin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(status!=null,PublishCheckin::getStatus,status);
        lambdaQueryWrapper.eq(teacherId!=null,PublishCheckin::getTeacherId,teacherId);
        lambdaQueryWrapper.eq(courseId!=null,PublishCheckin::getCourseId,courseId);
        Page<PublishCheckin> page = publishCheckinService.page(new Page<>(pageNum, pageSize), lambdaQueryWrapper);
        List<PublishCheckinVo> collect = page.getRecords().stream().map(item -> {
            return publishCheckinService.getCheckin(item.getId());
        }).collect(Collectors.toList());
        Map<String,Object> map=new HashMap<>();
        map.put("records",collect);
        map.put("total",page.getTotal());
        return Result.ok(map);
    }
    @DeleteMapping("{id}")
    public Result delete(@PathVariable("id") Integer id){
        boolean b = checkinService.deleteByPublishCheckinId(id);
        boolean removeById = publishCheckinService.removeById(id);
        return Result.ok(b&&removeById);
    }
}

