package com.example.attendance_framework.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author chenliming
 * @date 2023/5/4 16:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckinVo {
    //打卡id
    private Integer id;
    //打卡时间
    private Date checkinTime;
    //打卡时所在位置的纬度
    private Double latitude;
    //打卡时所在位置的经度
    private Double longitude;
    //状态
    private Integer status;
    //课程id
    private String courseName;
    //人脸图片
    private String faceImage;
}
