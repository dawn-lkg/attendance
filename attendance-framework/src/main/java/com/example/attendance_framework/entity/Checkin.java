package com.example.attendance_framework.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Checkin)表实体类
 *
 * @author 陈黎明
 * @since 2023-04-22 22:38:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class Checkin extends Model<Checkin> {
    //打卡id
    private Integer id;
    //学生id
    private Integer studentId;
    //课程id
    private Integer courseId;
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
    //打卡地点
    private String locationAddress;
}

