package com.example.tourism.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("user_info")
public class UserInfo {
    @TableId(value = "portal_user_name", type = IdType.INPUT)
    private String portalUserName;
    
    private String portalPassword;
    private String memberId;
    private String email;
    private String phone;
    private LocalDateTime portalCreateTime;
    
    @TableField(exist = false)
    private Integer status; // 用户状态,非数据库字段
    
    // 以下是非数据库字段
    @TableField(exist = false)
    private String avatar;  
    
    @TableField(exist = false)
    private String nickname;
}
