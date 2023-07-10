package com.example.attendance_framework.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (PublishCheckin)表实体类
 *
 * @author 陈黎明
 * @since 2023-04-22 22:11:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class PublishCheckin extends Model<PublishCheckin> {
    //发布id
    private Integer id;
    //课程id
    private Integer courseId;

    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //纬度
    private Double latitude;
    //经度
    private Double longitude;

    //打卡人数
    private Integer count;
    //发布老师id
    private Integer teacherId;
    //状态
    private Integer status;
    //范围
    private Integer radius;
    //发布时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}

