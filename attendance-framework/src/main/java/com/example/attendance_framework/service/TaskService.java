package com.example.attendance_framework.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author chenliming
 * @date 2023/5/8 13:50
 */
@Service
public interface TaskService {
    String DateToCron(Date date);
}
