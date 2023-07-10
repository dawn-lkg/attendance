package com.example.attendance_framework.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.attendance_framework.entity.Class;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Class)表数据库访问层
 *
 * @author 陈黎明
 * @since 2023-04-22 22:38:39
 */
@Mapper
public interface ClassDao extends BaseMapper<Class> {

}

