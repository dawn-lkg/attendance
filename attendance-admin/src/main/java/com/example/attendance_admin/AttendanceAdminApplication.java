package com.example.attendance_admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chenliming
 * @date 2023/4/18 10:24
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.example.attendance_framework.dao")
@ComponentScan({"com.example.attendance_framework.service","com.example.attendance_framework.dao","com.example.attendance_framework.utils"})
@ComponentScan
public class AttendanceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AttendanceAdminApplication.class,args);
    }
}
