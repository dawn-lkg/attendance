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
 * (Teacher)表实体类
 *
 * @author 陈黎明
 * @since 2023-04-22 22:06:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class Teacher extends Model<Teacher> {
    //教师号
    @TableId(type = IdType.AUTO)
    private Integer id;
    //教师名字
    private String name;
    //性别
    private Integer gender;
    //学历
    private String degree;
    //职位
    private String position;
    //生日
    private Date birth;
    //身份证号
    private String idcard;
    //手机号
    private String phone;
    //头像
    private String avatar;
    //密码
    @JsonIgnore
    private String password;
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

