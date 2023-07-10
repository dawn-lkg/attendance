package com.example.attendance_framework.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author chenliming
 * @date 2023/5/9 22:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveTeaVo {
    private Integer id;
    //发布的打卡ID
    private Integer publishId;

    private String studentName;
    //请假原因
    private String reason;
    //请假状态（0待审批，1已批准，2已驳回）
    private Integer status;
    //创建时间
    private Date createTime;
}
