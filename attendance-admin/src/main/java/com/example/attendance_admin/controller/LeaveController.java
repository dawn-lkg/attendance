package com.example.attendance_admin.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.attendance_framework.entity.Leave;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.entity.vo.LeaveListVo;
import com.example.attendance_framework.service.LeaveService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (Leave)表控制层
 *
 * @author 陈黎明
 * @since 2023-05-09 00:49:25
 */
@RestController
@RequestMapping("leave")
public class LeaveController{
    /**
     * 服务对象
     */
    @Resource
    private LeaveService leaveService;
    @GetMapping("list")
    public Result list(Integer pageNum,Integer pageSize){
        LambdaQueryWrapper<Leave> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        Page<Leave> page = leaveService.page(new Page<>(pageNum, pageSize), lambdaQueryWrapper);
        List<LeaveListVo> collect = page.getRecords().stream().map(item -> {
            return leaveService.getLeaveListVo(item);
        }).collect(Collectors.toList());
        Map<String,Object> map=new HashMap<>();
        map.put("records",collect);
        map.put("total",page.getTotal());
        return Result.ok(map);
    }
    @DeleteMapping("{id}")
    public Result delete(@PathVariable("id") Integer id){
        return Result.ok(leaveService.removeById(id));
    }
    @GetMapping("{id}")
    public Result getById(@PathVariable("id") Integer id){
        return Result.ok(leaveService.getById(id));
    }
    @PutMapping()
    public Result update(@RequestBody Leave leave){
        return Result.ok(leaveService.updateById(leave));
    }
}

