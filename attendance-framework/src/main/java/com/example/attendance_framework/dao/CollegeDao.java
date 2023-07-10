package com.example.attendance_framework.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.attendance_framework.entity.College;
import org.apache.ibatis.annotations.Mapper;

/**
 * (College)表数据库访问层
 *
 * @author 陈黎明
 * @since 2023-04-22 22:38:52
 */
@Mapper
public interface CollegeDao extends BaseMapper<College> {

}

