package com.azure.azure_merge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 点击记录表 - 用于存储用户的点击记录信息
 * @TableName viplogs
 */
@TableName(value ="viplogs")
@Data
public class Viplogs implements Serializable {
    /**
     * 记录ID - 主键 (整数型)
     */
    @TableId(type = IdType.AUTO)
    private Integer logId;

    /**
     * 收藏的内容类型 1 资讯表 2 项目表 3 国别表 4 供应商表
     */
    private Integer resourceType;

    /**
     * 资源ID - 整数型
     */
    private Integer resourceId;

    /**
     * 用户ID - 整数型
     */
    private Integer userId;

    /**
     * 收藏日期 - 日期时间类型
     */
    private LocalDateTime logTime;

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
        Viplogs other = (Viplogs) that;
        return (this.getLogId() == null ? other.getLogId() == null : this.getLogId().equals(other.getLogId()))
            && (this.getResourceType() == null ? other.getResourceType() == null : this.getResourceType().equals(other.getResourceType()))
            && (this.getResourceId() == null ? other.getResourceId() == null : this.getResourceId().equals(other.getResourceId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLogTime() == null ? other.getLogTime() == null : this.getLogTime().equals(other.getLogTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogId() == null) ? 0 : getLogId().hashCode());
        result = prime * result + ((getResourceType() == null) ? 0 : getResourceType().hashCode());
        result = prime * result + ((getResourceId() == null) ? 0 : getResourceId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLogTime() == null) ? 0 : getLogTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logId=").append(logId);
        sb.append(", resourceType=").append(resourceType);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", userId=").append(userId);
        sb.append(", logTime=").append(logTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}