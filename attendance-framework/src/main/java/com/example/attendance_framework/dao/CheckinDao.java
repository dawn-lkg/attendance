package com.example.attendance_framework.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.attendance_framework.entity.Checkin;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Checkin)表数据库访问层
 *
 * @author 陈黎明
 * @since 2023-04-22 22:38:20
 */
@Mapper
public interface CheckinDao extends BaseMapper<Checkin> {

}

