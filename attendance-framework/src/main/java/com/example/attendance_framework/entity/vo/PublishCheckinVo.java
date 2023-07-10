package com.example.attendance_framework.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author chenliming
 * @date 2023/5/1 15:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishCheckinVo {
    //发布id
    private Integer id;
    //课程id
    private String courseName;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //已打卡人数
    private Integer count;
    //状态
    private Integer status;
    //纬度
    private Double latitude;
    //经度
    private Double longitude;
    //发布老师名字
    private String teacherName;
    //发布老师id
    private Integer teacherId;
}
