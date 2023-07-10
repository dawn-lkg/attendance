package com.example.attendance_framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author chenliming
 * @date 2023/5/1 14:40
 */
@Configuration
public class scheduler {
    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("MyScheduler-");
        scheduler.setPoolSize(5);
        return scheduler;
    }

}
