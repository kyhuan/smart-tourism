package com.example.tourism.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("admin_info")
public class AdminInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String adminUsername;
    private String adminPassword;
    private String adminId;
    private String email;
    private String phone;
    private LocalDateTime adminCreateTime;
    private String roleId;
    private Integer status; // 0-禁用 1-启用
}
