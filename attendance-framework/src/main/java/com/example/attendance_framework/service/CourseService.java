package com.example.attendance_framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.attendance_framework.entity.Course;
import com.example.attendance_framework.entity.vo.CourseListVo;
import com.example.attendance_framework.entity.vo.CourseVo;

import java.util.List;

/**
 * (Course)表服务接口
 *
 * @author 陈黎明
 * @since 2023-04-22 22:39:48
 */
public interface CourseService extends IService<Course> {
    List<CourseVo> list(String query);
    List<Course> getCourseByTeacherId(Integer id);
    CourseVo getCourse(Integer id);
    boolean joinCourse(Integer stuId,Integer courseId);
    boolean cancelCourse(Integer stuId,Integer courseId);
    List<CourseVo> getStuCourse(Integer stuId);
    CourseListVo getCourseVo(Course course);
}

