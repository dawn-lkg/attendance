package com.example.attendance_wx;

import cn.hutool.cron.CronUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chenliming
 * @date 2023/4/18 10:59
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.example.attendance_framework.dao")
@ComponentScan({"com.example.attendance_framework.service","com.example.attendance_framework.dao","com.example.attendance_framework.utils","com.example.attendance_framework.config"})
@ComponentScan
@ServletComponentScan
public class AttendanceWxApplication {
    public static void main(String[] args) {
        SpringApplication.run(AttendanceWxApplication.class,args);
    }
}
