package com.example.attendance_wx.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTask {
    private static Integer count=0;

     // 默认执行时间，可以在运行时修改
    public void run() {
        count++;
        System.out.println("执行"+count+"定时任务...");
    }

}
