package com.example.attendance_framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendance_framework.dao.CollegeDao;
import com.example.attendance_framework.entity.College;
import com.example.attendance_framework.service.CollegeService;
import org.springframework.stereotype.Service;

/**
 * (College)表服务实现类
 *
 * @author 陈黎明
 * @since 2023-04-22 22:38:52
 */
@Service("collegeService")
public class CollegeServiceImpl extends ServiceImpl<CollegeDao, College> implements CollegeService {

}

