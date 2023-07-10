package com.example.attendance_framework.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.attendance_framework.entity.PublishCheckin;
import org.apache.ibatis.annotations.Mapper;

/**
 * (PublishCheckin)表数据库访问层
 *
 * @author 陈黎明
 * @since 2023-04-22 22:11:32
 */
@Mapper
public interface PublishCheckinDao extends BaseMapper<PublishCheckin> {

}

