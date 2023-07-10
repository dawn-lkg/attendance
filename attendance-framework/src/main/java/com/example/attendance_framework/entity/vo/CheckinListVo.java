package com.example.attendance_framework.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author chenliming
 * @date 2023/5/7 22:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckinListVo {
    //打卡id
    private Integer id;
    //学生id
    private Integer studentId;
    //学生姓名
    private String studentName;
    //课程id
    private Integer courseId;
    //课程名称
    private String courseName;
    //发布打卡id
    private Integer publishId;
    //打卡时间
    private Date checkinTime;
    //脸部特征
    private String faceToken;
    //人脸图片
    private String faceImage;
    //打卡时所在位置的纬度
    private Double latitude;
    //打卡时所在位置的经度
    private Double longitude;
    //状态
    private Integer status;
}
