package com.example.attendance_framework.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Sc)表实体类
 *
 * @author 陈黎明
 * @since 2023-04-26 21:10:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class Sc extends Model<Sc> {
    //学生id
    private Integer sutId;
    //课程id
    private Integer courseId;
    //分数
    private Integer grade;
}

