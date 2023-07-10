package com.example.attendance_admin.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.attendance_framework.entity.Checkin;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.entity.vo.CheckinListVo;
import com.example.attendance_framework.entity.vo.CheckinVo;
import com.example.attendance_framework.service.CheckinService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (Checkin)表控制层
 *
 * @author 陈黎明
 * @since 2023-05-07 20:23:28
 */
@RestController
@RequestMapping("checkin")
public class CheckinController {
    /**
     * 服务对象
     */
    @Resource
    private CheckinService checkinService;

    @GetMapping("list")
    public Result list(Integer pageNum,Integer pageSize,String query,Integer status){
        LambdaQueryWrapper<Checkin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(status!=null,Checkin::getStatus,status);
        Page<Checkin> page = checkinService.page(new Page<>(pageNum, pageSize),lambdaQueryWrapper);
        List<CheckinListVo> collect = page.getRecords().stream().map(item -> {
            return checkinService.getCheckinList(item);
        }).collect(Collectors.toList());
        Map<String,Object> map=new HashMap<>();
        map.put("records",collect);
        map.put("total",page.getTotal());
        return Result.ok(map);
    }

}

