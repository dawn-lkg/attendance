package com.example.attendance_framework.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author chenliming
 * @date 2023/4/26 16:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseVo {
    //课程id
    private Integer id;
    //课程名字
    private String name;
    //课程老师
    private String  teacherName;
    //学院id
    private Integer collegeId;
    //学院名字
    private String  collegeName;
    //课程开始时间
    private Date startTime;
    //课程结束时间
    private Date endTime;
    //上课地点
    private String place;
    //图片
    private String image;
    //学分
    private Integer credit;
}
