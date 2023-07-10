package com.example.attendance_framework.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author chenliming
 * @date 2023/5/8 19:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveVo {
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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
