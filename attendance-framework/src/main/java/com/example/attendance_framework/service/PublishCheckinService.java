package com.example.attendance_framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.attendance_framework.entity.PublishCheckin;
import com.example.attendance_framework.entity.vo.PublishCheckinVo;

import java.util.List;

/**
 * (PublishCheckin)表服务接口
 *
 * @author 陈黎明
 * @since 2023-04-22 22:11:32
 */
public interface PublishCheckinService extends IService<PublishCheckin> {
     PublishCheckinVo getCheckin(Integer id);
     PublishCheckinVo getNowCheckIn(Long timestamp,Integer id);
     List<PublishCheckinVo> getListCheckin(Long timestamp,List<Integer> list);
     List<PublishCheckinVo> getTeaListCheckin(Integer teaId,Integer status);
     boolean addCount(Integer id);
     boolean scheduleStatusStart(Integer publishId);
     boolean scheduleStatusEnd(Integer publishId);
}

