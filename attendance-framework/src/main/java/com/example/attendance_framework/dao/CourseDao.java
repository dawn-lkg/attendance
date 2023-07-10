package com.example.attendance_framework.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.attendance_framework.entity.Course;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Course)表数据库访问层
 *
 * @author 陈黎明
 * @since 2023-04-22 22:39:48
 */
@Mapper
public interface CourseDao extends BaseMapper<Course> {

}

