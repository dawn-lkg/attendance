package com.example.attendance_admin;

import com.example.attendance_framework.service.CheckinService;
import com.example.attendance_framework.service.CollegeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

/**
 * @author chenliming
 * @date 2023/4/30 20:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Autowired
    CollegeService collegeService;
    @Test
    public void test(){
        System.out.println(collegeService.list());
    }
    @Test
    public void test1(){
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }
    CheckinService checkinService;
    @Test
    public void test2(){}
}
