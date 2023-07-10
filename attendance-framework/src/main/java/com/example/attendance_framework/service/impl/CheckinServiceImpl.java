package com.example.attendance_framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendance_framework.constants.SystemConstants;
import com.example.attendance_framework.dao.CheckinDao;
import com.example.attendance_framework.entity.Checkin;
import com.example.attendance_framework.entity.Course;
import com.example.attendance_framework.entity.Student;
import com.example.attendance_framework.entity.vo.CheckinListVo;
import com.example.attendance_framework.entity.vo.CheckinVo;
import com.example.attendance_framework.entity.vo.PublishCheckinVo;
import com.example.attendance_framework.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * (Checkin)表服务实现类
 *
 * @author 陈黎明
 * @since 2023-04-22 22:38:20
 */
@Service("checkinService")
public class CheckinServiceImpl extends ServiceImpl<CheckinDao, Checkin> implements CheckinService {
    @Autowired
    private PublishCheckinService publishCheckinService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ScService scService;
    @Override
    public List<Checkin> getPublishListId(Integer stuId) {
        LambdaQueryWrapper<Checkin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Checkin::getStudentId,stuId);
        lambdaQueryWrapper.eq(Checkin::getStatus,SystemConstants.NOT_CHECKED);
        List<Checkin> list = list(lambdaQueryWrapper);
        System.out.println(list);
        return list;
    }
    @Override
    public CheckinVo getCheckVo(Checkin checkin){
        CheckinVo checkinVo=new CheckinVo();
        PublishCheckinVo publishCheckinVo = publishCheckinService.getCheckin(checkin.getPublishId());
        BeanUtils.copyProperties(publishCheckinVo,checkinVo);
        BeanUtils.copyProperties(checkin,checkinVo);
        return checkinVo;
    }
    @Override
    public boolean scheduleStatusLateCheckin(Integer publishId) {
        LambdaQueryWrapper<Checkin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Checkin::getPublishId,publishId);
        lambdaQueryWrapper.eq(Checkin::getStatus,SystemConstants.NOT_CHECKED);
        list().stream().forEach(item->{
            item.setStatus(SystemConstants.LATE);
            updateById(item);
        });
        return true;
    }

    @Override
    public CheckinListVo getCheckinList(Checkin checkin) {
        Student student = studentService.getById(checkin.getStudentId());
        Course course = courseService.getById(checkin.getCourseId());
        CheckinListVo checkinListVo = new CheckinListVo();
        BeanUtils.copyProperties(checkin,checkinListVo);
        checkinListVo.setCourseName(course.getName());
        checkinListVo.setStudentName(student.getName());
        return checkinListVo;
    }
    @Override
    public boolean createAttendance(Integer courseId,Integer publishId) {
        List<Checkin> collect = scService.getCourseStu(courseId).stream().map(item -> {
            Checkin checkin = new Checkin();
            checkin.setCourseId(courseId);
            checkin.setStudentId(item.getSutId());
            checkin.setPublishId(publishId);
            checkin.setStatus(SystemConstants.NOT_CHECKED);
            return checkin;
        }).collect(Collectors.toList());
        boolean b = saveBatch(collect);
        return b;
    }

    @Override
    public boolean endLate(Integer publishId) {
        LambdaQueryWrapper<Checkin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Checkin::getPublishId,publishId);
        lambdaQueryWrapper.eq(Checkin::getStatus,SystemConstants.NOT_CHECKED);
        Checkin checkin = new Checkin();
        checkin.setStatus(SystemConstants.LATE);
        boolean update = update(checkin, lambdaQueryWrapper);
        return update;
    }
    @Override
    public boolean deleteByPublishCheckinId(Integer publishId) {
        LambdaQueryWrapper<Checkin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Checkin::getPublishId,publishId);
        boolean remove = remove(lambdaQueryWrapper);
        return remove;
    }

}

