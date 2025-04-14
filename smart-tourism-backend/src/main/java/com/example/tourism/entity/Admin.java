package com.example.tourism.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员实体类
 */
@Data
@TableName("admin_info")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID (可能不是id字段，根据截图修改)
     */
    @TableId(value = "admin_id", type = IdType.INPUT)
    private String id;

    /**
     * 用户名
     */
    @TableField("admin_username")
    private String username;

    /**
     * 密码
     */
    @TableField("admin_password")
    private String password;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 真实姓名
     */
    @TableField(exist = false)
    private String realName;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 角色
     */
    @TableField("role_id")
    private String role;

    /**
     * 头像
     */
    @TableField(exist = false)
    private String avatar;

    /**
     * 创建时间
     */
    @TableField("admin_create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(exist = false)
    private Date updateTime;

    /**
     * 最后登录时间
     */
    @TableField(exist = false)
    private Date lastLoginTime;

    /**
     * 状态（0: 禁用, 1: 正常）
     */
    @TableField(exist = false)
    private Integer status;
} 