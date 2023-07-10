package com.example.attendance_framework.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.attendance_framework.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Student)表数据库访问层
 *
 * @author 陈黎明
 * @since 2023-04-22 21:28:21
 */
@Mapper
public interface StudentDao extends BaseMapper<Student> {

}

