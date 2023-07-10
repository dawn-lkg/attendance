package com.example.attendance_framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendance_framework.dao.CourseDao;
import com.example.attendance_framework.entity.College;
import com.example.attendance_framework.entity.Course;
import com.example.attendance_framework.entity.Sc;
import com.example.attendance_framework.entity.Teacher;
import com.example.attendance_framework.entity.vo.CourseListVo;
import com.example.attendance_framework.entity.vo.CourseVo;
import com.example.attendance_framework.service.CollegeService;
import com.example.attendance_framework.service.CourseService;
import com.example.attendance_framework.service.ScService;
import com.example.attendance_framework.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * (Course)表服务实现类
 *
 * @author 陈黎明
 * @since 2023-04-22 22:39:48
 */
@Service("courseService")
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course> implements CourseService {
    @Autowired
    TeacherService teacherService;
    @Autowired
    CollegeService collegeService;
    @Autowired
    ScService scService;
    @Override
    public List<CourseVo> list(String query) {
        System.out.println(query);
        LambdaQueryWrapper<Course> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(query!=null,Course::getName,query);
        List<CourseVo> collect = list(lambdaQueryWrapper).stream().map(item -> {
            CourseVo courseVo = new CourseVo();
            BeanUtils.copyProperties(item, courseVo);
            Teacher byId = teacherService.getById(item.getTeacherId());
            courseVo.setTeacherName(byId.getName());
            return courseVo;
        }).collect(Collectors.toList());
        return collect;
    }
    @Override
    public List<Course> getCourseByTeacherId(Integer id) {
        LambdaQueryWrapper<Course> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Course::getTeacherId,id);
        return list(lambdaQueryWrapper);
    }

    @Override
    public CourseVo getCourse(Integer id) {
        Course byId = getById(id);
        Teacher teacher = teacherService.getById(byId.getTeacherId());
        CourseVo courseVo=new CourseVo();
        BeanUtils.copyProperties(byId,courseVo);
        courseVo.setTeacherName(teacher.getName());
        return courseVo;
    }

    @Override
    @Transactional
    public boolean joinCourse(Integer stuId, Integer courseId) {
        Sc sc=new Sc();
        sc.setCourseId(courseId);
        sc.setSutId(stuId);
        boolean save = scService.save(sc);
        if(!save){
            return false;
        }
        Course course = getById(courseId);
        course.setEnrollment(course.getEnrollment()+1);
        boolean b = updateById(course);
        if(!b){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean cancelCourse(Integer stuId, Integer courseId) {
        LambdaQueryWrapper<Sc> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Sc::getCourseId,courseId);
        lambdaQueryWrapper.eq(Sc::getSutId,stuId);
        boolean remove = scService.remove(lambdaQueryWrapper);
        if(!remove){
            return false;
        }
        Course course = getById(courseId);
        course.setEnrollment(course.getEnrollment()-1);
        boolean b = updateById(course);
        if(!b){
            return false;
        }
        return true;
    }

    @Override
    public List<CourseVo> getStuCourse(Integer stuId) {
        LambdaQueryWrapper<Sc> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Sc::getSutId,stuId);
        List<CourseVo> collect = scService.list(lambdaQueryWrapper).stream().map(item -> {
            CourseVo course = getCourse(item.getCourseId());
            return course;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public CourseListVo getCourseVo(Course course) {
        Teacher byId = teacherService.getById(course.getTeacherId());
        College college = collegeService.getById(course.getCollegeId());
        CourseListVo courseVo=new CourseListVo();
        courseVo.setTeacherName(byId.getName());
        courseVo.setCollegeName(college.getCollegeName());
        BeanUtils.copyProperties(course,courseVo);
        return courseVo;
    }

}

