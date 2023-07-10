package com.example.attendance_framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendance_framework.dao.AdminDao;
import com.example.attendance_framework.entity.Admin;
import com.example.attendance_framework.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * (Admin)表服务实现类
 *
 * @author 陈黎明
 * @since 2023-04-30 20:16:17
 */
@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

}

