package com.example.attendance_framework.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Announcements)表实体类
 *
 * @author 陈黎明
 * @since 2023-04-28 14:11:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class Announcements extends Model<Announcements> {
    //公告id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //公告标题
    private String title;
    //公告内容
    private String content;
    //封面图片
    private String image;
    //发布者id
    private Integer authorId;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    }

