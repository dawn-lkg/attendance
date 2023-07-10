package com.example.attendance_wx.controller;


import cn.hutool.cron.CronUtil;
import cn.hutool.cron.Scheduler;
import cn.hutool.cron.task.Task;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.attendance_framework.common.BaseContext;
import com.example.attendance_framework.constants.SystemConstants;
import com.example.attendance_framework.entity.*;
import com.example.attendance_framework.entity.vo.PublishCheckinVo;
import com.example.attendance_framework.service.CheckinService;
import com.example.attendance_framework.service.PublishCheckinService;
import com.example.attendance_framework.service.ScService;
import com.example.attendance_framework.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (PublishCheckin)表控制层
 *
 * @author 陈黎明
 * @since 2023-04-27 23:13:03
 */
@Slf4j
@RestController
@RequestMapping("publishCheckin")
public class PublishCheckinController{
    @Resource
    private PublishCheckinService publishCheckinService;
    @Autowired
    private CheckinService checkinService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ScService scservice;
//    @PostMapping("publish")
//    @Transactional
//    public Result add(@RequestBody PublishCheckin publishCheckin){
//        System.out.println(publishCheckin);
//        publishCheckin.setTeacherId(Integer.valueOf(BaseContext.getCurrentId()));
//        boolean save = publishCheckinService.save(publishCheckin);
//        if(!save){
//            return Result.error(AppHttpCodeEnum.SYSTEM_ERROR);
//        }
//        List<Checkin> collect = scservice.getCourseStu(publishCheckin.getCourseId()).stream().map(item -> {
//            Checkin checkin = new Checkin();
//            checkin.setCourseId(publishCheckin.getCourseId());
//            checkin.setStudentId(item.getSutId());
//            checkin.setPublishId(publishCheckin.getId());
//            checkin.setStatus(SystemConstants.NOT_CHECKED);
//            return checkin;
//        }).collect(Collectors.toList());
//        checkinService.saveBatch(collect);
//        return Result.ok(true);
//    }
    @PostMapping("publish")
    @Transactional
    public Result publish(@RequestBody PublishCheckin publishCheckin){
        publishCheckin.setTeacherId(Integer.valueOf(BaseContext.getCurrentId()));
        Date date=new Date();
        Date startTime = publishCheckin.getStartTime();
        int i = startTime.compareTo(date);
        if(i<=0){
            publishCheckin.setStatus(SystemConstants.STARED);
        }
        boolean save = publishCheckinService.save(publishCheckin);
        if(!save){
            return Result.error(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        Scheduler scheduler = new Scheduler();
        CronUtil.setMatchSecond(true);
        String start = taskService.DateToCron(publishCheckin.getStartTime());
        String end = taskService.DateToCron(publishCheckin.getEndTime());
        if(i>0){
            scheduler.schedule(start, new Task() {
                @Override
                public void execute() {
                    boolean b = publishCheckinService.scheduleStatusStart(publishCheckin.getId());
                    boolean attendance = checkinService.createAttendance(publishCheckin.getCourseId(),publishCheckin.getId());
                    if(attendance){
                        log.info("发布打卡id:{}已开始",publishCheckin.getId());
                    }
                }
            });
        }else{
            log.info("打卡已经开始！");
            boolean attendance = checkinService.createAttendance(publishCheckin.getCourseId(),publishCheckin.getId());
            if(!attendance){
                return Result.ok(AppHttpCodeEnum.SYSTEM_ERROR);
            }
        }
        scheduler.schedule(end, new Task() {
            @Override
            public void execute() {
                boolean b = publishCheckinService.scheduleStatusEnd(publishCheckin.getId());
                boolean endLate = checkinService.endLate(publishCheckin.getId());
                if(b&&endLate){
                    log.info("发布打卡id:{}已结束",publishCheckin.getId());
                }
            }
        });
        scheduler.start();
        return Result.ok(true);
    }
    @GetMapping("/student")
    public Result getStudent(Long timestamp){
        List<Integer> publishListId = checkinService.getPublishListId(Integer.valueOf(BaseContext.getCurrentId())).stream().map(
                item->item.getPublishId()).collect(Collectors.toList());
        if(publishListId.size()==0){
            return Result.ok(null);
        }
        System.out.println(publishListId);
        List<PublishCheckinVo> listCheckin = publishCheckinService.getListCheckin(timestamp, publishListId);
        System.out.println(listCheckin);
        return Result.ok(listCheckin);
    }
    @GetMapping("{id}")
    public Result getById(@PathVariable("id") Integer id){
        return Result.ok(publishCheckinService.getById(id));
    }
    @GetMapping("/teacher")
    public Result getTeacher(Integer status){
        List<PublishCheckinVo> teaListCheckin = publishCheckinService.getTeaListCheckin(Integer.valueOf(BaseContext.getCurrentId()), status);
        return Result.ok(teaListCheckin);
    }
}

