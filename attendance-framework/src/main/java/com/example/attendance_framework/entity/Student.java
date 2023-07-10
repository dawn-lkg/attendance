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
 * (Student)表实体类
 *
 * @author 陈黎明
 * @since 2023-04-22 21:28:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class Student extends Model<Student> {
    //学生id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //学生姓名
    private String name;
    //性别（0女，1男）
    private String gender;
    //年龄
    private Integer age;
    //毕业学校
    private String graduationSchool;
    //身份证号
    private String idcard;
    //出身年月
    private Date birth;
    //学院
    private Integer collegeId;
    //头像
    private String avatar;
    //政治面貌英文
    private Integer politicalStatusId;
    //绩点
    private Double gpa;
    //班级id
    private Integer classId;
    //人脸特征token
    private String  faceToken;
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

