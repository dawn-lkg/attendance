package com.example.attendance_framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendance_framework.dao.PoliticalStatusDao;
import com.example.attendance_framework.entity.PoliticalStatus;
import com.example.attendance_framework.service.PoliticalStatusService;
import org.springframework.stereotype.Service;

/**
 * (PoliticalStatus)表服务实现类
 *
 * @author 陈黎明
 * @since 2023-04-26 11:33:05
 */
@Service("politicalStatusService")
public class PoliticalStatusServiceImpl extends ServiceImpl<PoliticalStatusDao, PoliticalStatus> implements PoliticalStatusService {

}

