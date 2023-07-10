package com.example.attendance_wx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.attendance_framework.common.BaseContext;
import com.example.attendance_framework.constants.SystemConstants;
import com.example.attendance_framework.entity.AppHttpCodeEnum;
import com.example.attendance_framework.entity.Checkin;
import com.example.attendance_framework.entity.Leave;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.entity.vo.LeaveListVo;
import com.example.attendance_framework.service.CheckinService;
import com.example.attendance_framework.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenliming
 * @date 2023/5/8 16:21
 */
@RestController
@RequestMapping("leave")
public class LeaveController {
    @Autowired
    LeaveService leaveService;
    @Autowired
    CheckinService checkinService;
    @GetMapping
    public Result getTeaLeaveList(){
        LambdaQueryWrapper<Leave> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Leave::getTeacherId,Integer.valueOf(BaseContext.getCurrentId()));
        lambdaQueryWrapper.eq(Leave::getStatus, SystemConstants.NOT_EXAMINE);
        List<Leave> list = leaveService.list(lambdaQueryWrapper);
        return Result.ok(list);
    }
    @PostMapping("add")
    public Result stuLeave(@RequestBody Leave leave){
        System.out.println(leave);
        leave.setStudentId(Integer.valueOf(BaseContext.getCurrentId()));
        leave.setStatus(SystemConstants.NOT_EXAMINE);
        boolean save = leaveService.save(leave);
        return Result.ok(save);
    }
    @GetMapping("stu")
    public Result getStu(){
        LambdaQueryWrapper<Leave> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Leave::getStudentId,Integer.valueOf(BaseContext.getCurrentId()));
        lambdaQueryWrapper.orderByDesc(Leave::getCreateTime);
        List<Leave> list = leaveService.list(lambdaQueryWrapper);
        return Result.ok(list);
    }
    @DeleteMapping("{id}")
    public Result delete(@PathVariable("id") Integer id){
        return Result.ok(leaveService.removeById(id));
    }
    @GetMapping("tea")
    public Result getTeaLeave(){
        LambdaQueryWrapper<Leave> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Leave::getTeacherId,BaseContext.getCurrentId());
        lambdaQueryWrapper.orderByDesc(Leave::getCreateTime);
        List<LeaveListVo> collect = leaveService.list(lambdaQueryWrapper).stream().map(item -> {
            return leaveService.getLeaveListVo(item);
        }).collect(Collectors.toList());
        return Result.ok(collect);
    }
    @PutMapping("/reject")
    @Transactional
    public Result rejectLeave(@RequestBody Leave leave){
        leave.setStatus(SystemConstants.NOT_PASS);
        boolean b = leaveService.updateById(leave);
        return Result.ok(b);
    }
    @PutMapping("/agree")
    public Result agreeLeave(@RequestBody Leave leave){
        leave.setStatus(SystemConstants.PASS);
        boolean b = leaveService.updateById(leave);
        if(!b){
            return Result.error(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        Leave byId = leaveService.getById(leave.getId());
        LambdaQueryWrapper<Checkin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Checkin::getPublishId,byId.getPublishId());
        lambdaQueryWrapper.eq(Checkin::getStudentId,byId.getStudentId());
        Checkin checkin = new Checkin();
        checkin.setStatus(SystemConstants.LEAVE);
        boolean update = checkinService.update(checkin, lambdaQueryWrapper);
        return Result.ok(update);
    }
}
