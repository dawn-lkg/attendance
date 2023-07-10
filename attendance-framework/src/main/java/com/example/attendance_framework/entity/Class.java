package com.example.attendance_framework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Class)表实体类
 *
 * @author 陈黎明
 * @since 2023-04-22 22:38:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class Class extends Model<Class> {
    //班级id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //班级名字
    @TableField("name")
    private String className;
    //学院id
    private String collegeId;
}

