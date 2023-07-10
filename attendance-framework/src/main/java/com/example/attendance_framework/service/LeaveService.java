package com.example.attendance_framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.attendance_framework.entity.Leave;
import com.example.attendance_framework.entity.vo.LeaveListVo;

/**
 * (Leave)表服务接口
 *
 * @author 陈黎明
 * @since 2023-05-05 21:27:10
 */
public interface LeaveService extends IService<Leave> {
    LeaveListVo getLeaveListVo(Leave leave);
}

