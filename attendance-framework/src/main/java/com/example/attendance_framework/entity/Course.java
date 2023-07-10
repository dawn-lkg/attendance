package com.example.attendance_framework.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Course)表实体类
 *
 * @author 陈黎明
 * @since 2023-04-22 22:39:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class Course extends Model<Course> {
    //课程id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //课程名字
    private String name;
    //课程老师
    private Integer teacherId;
    //学院id
    private Integer collegeId;
    //课程开始时间
    private Date startTime;
    //课程结束时间
    private Date endTime;
    //上课地点
    private String place;
    //上课人数
    private Integer enrollment;
    //图片
    private String image;
    //学分
    private Integer credit;
    //是否删除（0未删除，1已删除）
    @JsonIgnore
    private Integer isDeleted;
    //创建时间
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    }

