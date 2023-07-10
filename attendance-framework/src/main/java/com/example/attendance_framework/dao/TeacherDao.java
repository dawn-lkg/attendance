package com.example.attendance_framework.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.attendance_framework.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Teacher)表数据库访问层
 *
 * @author 陈黎明
 * @since 2023-04-22 22:04:50
 */
@Mapper
public interface TeacherDao extends BaseMapper<Teacher> {

}

