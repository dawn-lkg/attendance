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
 * (Admin)表实体类
 *
 * @author 陈黎明
 * @since 2023-04-30 20:16:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class Admin extends Model<Admin> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String username;
    
    private String password;
    
    private String name;
    
    private String phone;
    
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    private Date lastLogin;
    
    private Integer status;
}

