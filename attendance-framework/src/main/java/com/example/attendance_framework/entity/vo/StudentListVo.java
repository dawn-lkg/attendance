package com.example.attendance_framework.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author chenliming
 * @date 2023/5/2 11:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentListVo {
    //学生id
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
    private String collegeName;
    //头像
    private String avatar;
    //政治面貌英文
    private String politicalName;
    //绩点
    private Double gpa;
    //班级id
    private String className;
    //face_token
    private String faceToken;
}
