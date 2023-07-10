package com.example.attendance_wx;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.attendance_framework.entity.*;
import com.example.attendance_framework.entity.Class;
import com.example.attendance_framework.entity.vo.CheckinStuVo;
import com.example.attendance_framework.entity.vo.PublishCheckinVo;
import com.example.attendance_framework.entity.vo.StudentVo;
import com.example.attendance_framework.service.*;
import com.example.attendance_framework.service.impl.FaceCheckServiceImpl;
import com.example.attendance_framework.utils.JwtTokenUtil;
import com.example.attendance_wx.controller.MyTask;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

/**
 * @author chenliming
 * @date 2023/4/23 10:54
 */
//@MapperScan("com.example.attendance_framework.dao")
//@ComponentScan({"com.example.attendance_framework.service","com.example.attendance_framework.dao","com.example.attendance_framework.utils","com.example.attendance_framework.config"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Autowired
    StudentService studentService;
    @Test
    public void Test(){
        System.out.println(studentService.getStudentNoPassword("202310101","pass1"));
    }
    @Test
    public void test1(){
        String s = JwtTokenUtil.generateToken("202310101");
        System.out.println(s);
        System.out.println(JwtTokenUtil.parseToken(s));
    }
    @Autowired
    ClassService classService;
    @Autowired
    CollegeService collegeService;
    @Autowired
    PoliticalStatusService politicalStatusService;
    @Autowired
    ScService scService;
    @Test
    public void test2(){
        Class byId = classService.getById(100);
        College byId1 = collegeService.getById(1);
        PoliticalStatus byId2 = politicalStatusService.getById(1);
        System.out.println(byId);
        System.out.println(byId1);
        System.out.println(byId2);
        StudentVo studentVo=new StudentVo();
        BeanUtils.copyProperties(byId,studentVo);
        BeanUtils.copyProperties(byId1,studentVo);
        BeanUtils.copyProperties(byId2,studentVo);
        System.out.println(studentVo);
    }
    @Test
    public void test3(){
        System.out.println(studentService.getStudent(202310101));
    }
    @Autowired
    CourseService courseService;
    @Test
    public void test4(){
        System.out.println(courseService.list("计"));
    }
    @Test
    public void test5(){
        System.out.println(courseService.getCourseByTeacherId(10001));
    }
    @Test
    public void test6(){
        System.out.println(collegeService.list());
    }
    @Test
    public void test7(){
        System.out.println(courseService.getCourse(1));
    }
    @Test
    public void test8(){
        System.out.println(scService.isJoinCourse(202310101,2));
    }
    @Test
    public void test9(){
        System.out.println(courseService.getStuCourse(202310101));
    }
    @Autowired
    private MyTask myTask;
    @Autowired
    TaskScheduler taskScheduler;
    @Test
    public void test10(){

        ScheduledFuture<?> future = taskScheduler.scheduleAtFixedRate(myTask::run, 50000);
    }
    @Autowired
    PublishCheckinService publishCheckinService;
    @Test
    public void test11(){
        System.out.println(publishCheckinService.getCheckin(1));
    }
    @Test
    public void test12(){
        LambdaQueryWrapper<PublishCheckin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.gt(PublishCheckin::getEndTime,new Date());
        lambdaQueryWrapper.lt(PublishCheckin::getStartTime,new Date());
        System.out.println(publishCheckinService.list(lambdaQueryWrapper));
    }
    @Test
    public void test13(){
        Date date = new Date(1682933011708L);
        PublishCheckinVo nowCheckIn = publishCheckinService.getNowCheckIn(1682933011708L, 1);
        System.out.println(nowCheckIn);
    }
    @Autowired
    private CheckinService checkinService;
    @Test
    public void test14(){
        Date data=new Date();
        List<Integer> publishListId = checkinService.getPublishListId(202310102).stream().map(item->{
            return item.getPublishId();
        }).collect(Collectors.toList());
        System.out.println(publishListId);
        List<PublishCheckinVo> listCheckin = publishCheckinService.getListCheckin(1682933011708L, publishListId);
        System.out.println(listCheckin);
    }
    @Autowired
    private FaceCheckServiceImpl faceCheckService;
    @Test
    public void  test15(){
        Boolean aBoolean = faceCheckService.comparePathFace("1.jpg", "2.jpg");
        System.out.println(aBoolean);
    }
    @Test
    public void  test16(){
        System.out.println(faceCheckService.compareTokenFace("884365168af37180bec4de535bc51e92", "819aa3e3344f8cdfe8da8c2af9ffecb5"));
    }
    @Test
    public void test17(){
        System.out.println(publishCheckinService.getTeaListCheckin(10001,3));
    }
    @Test
    public void test18(){
        System.out.println(checkinService.getPublishListId(202310101));
    }
    @Autowired
    private TaskService taskService;
    @Test
    public void test19(){
        System.out.println(taskService.DateToCron(new Date()));
    }
    @Test
    public void test20(){
        Date date = new Date();
        Date date1 = new Date();
        date1.setHours(15);
        int i = date1.compareTo(date);
        System.out.println(i);
    }
    @Test
    public void test21(){
        boolean b = checkinService.endLate(3);

    }
    @Test
    public void test22(){
        LambdaQueryWrapper<Checkin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Checkin::getPublishId,26);
        List<CheckinStuVo> collect = checkinService.list(lambdaQueryWrapper).stream().map(item -> {
            System.out.println(item);
            Student byId = studentService.getById(item.getStudentId());
            CheckinStuVo checkinStuVo = new CheckinStuVo();
            BeanUtils.copyProperties(item, checkinStuVo);
            checkinStuVo.setStudentName(byId.getName());
            return checkinStuVo;
        }).collect(Collectors.toList());
        System.out.println(collect);
    }
    @Test
    public void test23(){
        checkinService.getPublishListId(202310101);
    }
}
