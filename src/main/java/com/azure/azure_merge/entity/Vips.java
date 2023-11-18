package com.azure.azure_merge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * vips用户表
 * @TableName vips
 */
@TableName(value ="vips")
@Data
public class Vips implements Serializable {
    /**
     * 用户ID (主键)
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 创建用户的管理员id
     */
    private Integer createuserId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码 (可以使用加密方法存储)
     */
    private String userPassword;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 单位
     */
    private String companyName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 账户状态 
     */
    private Integer vipsStatus;

    /**
     * 创建日期
     */
    private Date regDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Vips other = (Vips) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreateuserId() == null ? other.getCreateuserId() == null : this.getCreateuserId().equals(other.getCreateuserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserPassword() == null ? other.getUserPassword() == null : this.getUserPassword().equals(other.getUserPassword()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getVipsStatus() == null ? other.getVipsStatus() == null : this.getVipsStatus().equals(other.getVipsStatus()))
            && (this.getRegDate() == null ? other.getRegDate() == null : this.getRegDate().equals(other.getRegDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreateuserId() == null) ? 0 : getCreateuserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserPassword() == null) ? 0 : getUserPassword().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getVipsStatus() == null) ? 0 : getVipsStatus().hashCode());
        result = prime * result + ((getRegDate() == null) ? 0 : getRegDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", createuserId=").append(createuserId);
        sb.append(", userName=").append(userName);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", companyName=").append(companyName);
        sb.append(", email=").append(email);
        sb.append(", vipsStatus=").append(vipsStatus);
        sb.append(", regDate=").append(regDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}