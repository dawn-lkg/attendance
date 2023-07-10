package com.example.attendance_framework.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (PoliticalStatus)表实体类
 *
 * @author 陈黎明
 * @since 2023-04-26 11:33:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class PoliticalStatus extends Model<PoliticalStatus> {
    //政治面貌id
    private Integer id;
    //政治面貌名字
    @TableField("name")
    private String politicalName;
}

