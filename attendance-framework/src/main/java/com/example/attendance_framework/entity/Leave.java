package com.example.attendance_framework.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Leave)表实体类
 *
 * @author 陈黎明
 * @since 2023-05-05 21:27:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
@TableName("`leave`")
public class Leave extends Model<Leave> {
    //请假ID
    @TableId(type = IdType.AUTO)
    private Integer id;
    //发布的打卡ID
    private Integer publishId;
    //教师的ID
    private Integer teacherId;
    //学生ID
    private Integer studentId;
    //请假原因
    private String reason;
    //请假状态（0待审批，1已批准，2已驳回）
    private Integer status;
    //创建时间
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    }

