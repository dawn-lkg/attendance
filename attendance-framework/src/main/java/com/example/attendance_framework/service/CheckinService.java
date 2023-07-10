package com.example.attendance_framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.attendance_framework.entity.Checkin;
import com.example.attendance_framework.entity.vo.CheckinListVo;
import com.example.attendance_framework.entity.vo.CheckinVo;

import java.util.List;

/**
 * (Checkin)表服务接口
 *
 * @author 陈黎明
 * @since 2023-04-22 22:38:20
 */
public interface CheckinService extends IService<Checkin> {
    List<Checkin> getPublishListId(Integer stuId);
    CheckinVo getCheckVo(Checkin checkin);
    boolean scheduleStatusLateCheckin(Integer publishId);
    CheckinListVo getCheckinList(Checkin checkin);
    boolean createAttendance(Integer courseId,Integer publishId);
    boolean endLate(Integer publishId);
    boolean deleteByPublishCheckinId(Integer publishId);
}

