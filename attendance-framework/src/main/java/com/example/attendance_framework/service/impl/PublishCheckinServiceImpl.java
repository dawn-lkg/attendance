package com.example.attendance_framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendance_framework.constants.SystemConstants;
import com.example.attendance_framework.dao.PublishCheckinDao;
import com.example.attendance_framework.entity.Checkin;
import com.example.attendance_framework.entity.Course;
import com.example.attendance_framework.entity.PublishCheckin;
import com.example.attendance_framework.entity.Teacher;
import com.example.attendance_framework.entity.vo.PublishCheckinVo;
import com.example.attendance_framework.service.CourseService;
import com.example.attendance_framework.service.PublishCheckinService;
import com.example.attendance_framework.service.ScService;
import com.example.attendance_framework.service.TeacherService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (PublishCheckin)表服务实现类
 *
 * @author 陈黎明
 * @since 2023-04-22 22:11:32
 */
@Service("publishCheckinService")
public class PublishCheckinServiceImpl extends ServiceImpl<PublishCheckinDao, PublishCheckin> implements PublishCheckinService {
    @Autowired
    CourseService courseService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    ScService scService;
    @Override
    public PublishCheckinVo getCheckin(Integer id) {
        PublishCheckin one = getById(id);
        return getPublishCheckinVo(one);
    }

    @Override
    public PublishCheckinVo getNowCheckIn(Long timestamp, Integer id) {
        Date date = new Date(timestamp);
        LambdaQueryWrapper<PublishCheckin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.gt(PublishCheckin::getEndTime,date);
        lambdaQueryWrapper.lt(PublishCheckin::getStartTime,date);
        lambdaQueryWrapper.eq(PublishCheckin::getId,id);
        PublishCheckin one = getOne(lambdaQueryWrapper);
        return getPublishCheckinVo(one);
    }

    @Override
    public List<PublishCheckinVo> getListCheckin(Long timestamp, List<Integer> list) {
        Date date = new Date(timestamp);
        LambdaQueryWrapper<PublishCheckin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(PublishCheckin::getId,list);
//        lambdaQueryWrapper.gt(PublishCheckin::getEndTime,date);
//        lambdaQueryWrapper.lt(PublishCheckin::getStartTime,date);
        lambdaQueryWrapper.eq(PublishCheckin::getStatus,SystemConstants.STARED);
        List<PublishCheckin> publishCheckins = list(lambdaQueryWrapper);
        if(publishCheckins==null) return null;
        List<PublishCheckinVo> collect = publishCheckins.stream().map(item -> {
            return getPublishCheckinVo(item);
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<PublishCheckinVo> getTeaListCheckin(Integer teaId,Integer status) {
        LambdaQueryWrapper<PublishCheckin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(status==0){
            lambdaQueryWrapper.gt(PublishCheckin::getEndTime,new Date());
            lambdaQueryWrapper.lt(PublishCheckin::getStartTime,new Date());
        }else if(status==1){
            lambdaQueryWrapper.gt(PublishCheckin::getStartTime,new Date());
        }else if(status==2){
            lambdaQueryWrapper.lt(PublishCheckin::getEndTime,new Date());
        }
        lambdaQueryWrapper.eq(PublishCheckin::getTeacherId,teaId);
        List<PublishCheckinVo> list = list(lambdaQueryWrapper).stream().map(item->{
            return getPublishCheckinVo(item);
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public boolean addCount(Integer id) {
        PublishCheckin byId = getById(id);
        byId.setCount(byId.getCount()+1);
        return updateById(byId);
    }


    @Override
    public boolean scheduleStatusStart(Integer publishId) {
        PublishCheckin publishCheckin=new PublishCheckin();
        publishCheckin.setId(publishId);
        publishCheckin.setStatus(SystemConstants.STARED);
        return updateById(publishCheckin);
    }

    @Override
    public boolean scheduleStatusEnd(Integer publishId) {
        PublishCheckin publishCheckin=new PublishCheckin();
        publishCheckin.setId(publishId);
        publishCheckin.setStatus(SystemConstants.END);
        return updateById(publishCheckin);
    }
    @NotNull
    private PublishCheckinVo getPublishCheckinVo(PublishCheckin one) {
        PublishCheckinVo publishCheckinVo=new PublishCheckinVo();
        BeanUtils.copyProperties(one,publishCheckinVo);
        Course course = courseService.getById(one.getCourseId());
        publishCheckinVo.setCourseName(course.getName());
        Teacher byId = teacherService.getById(one.getTeacherId());
        publishCheckinVo.setTeacherName(byId.getName());
        return publishCheckinVo;
    }
}

