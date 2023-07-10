package com.example.attendance_framework.service.impl;

import com.example.attendance_framework.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @author chenliming
 * @date 2023/5/8 13:52
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public String DateToCron(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month =calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        return String.format("%d %d %d %d %d ? %d", seconds, minutes , hours, day, month+1, year);
    }
}
