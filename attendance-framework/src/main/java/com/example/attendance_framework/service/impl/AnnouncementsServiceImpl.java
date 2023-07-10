package com.example.attendance_framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendance_framework.dao.AnnouncementsDao;
import com.example.attendance_framework.entity.Announcements;
import com.example.attendance_framework.service.AnnouncementsService;
import org.springframework.stereotype.Service;

/**
 * (Announcements)表服务实现类
 *
 * @author 陈黎明
 * @since 2023-04-28 14:11:01
 */
@Service("announcementsService")
public class AnnouncementsServiceImpl extends ServiceImpl<AnnouncementsDao, Announcements> implements AnnouncementsService {

}

