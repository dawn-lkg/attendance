package com.example.attendance_framework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (College)表实体类
 *
 * @author 陈黎明
 * @since 2023-04-22 22:38:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class College extends Model<College> {
    //学院id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //学院名字
    @TableField("name")
    private String collegeName;
}

