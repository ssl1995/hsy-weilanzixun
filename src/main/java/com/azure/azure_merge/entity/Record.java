package com.azure.azure_merge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 收藏记录表 - 用于存储用户的收藏记录信息
 * @TableName record
 */
@TableName(value ="record")
@Data
public class Record implements Serializable {
    /**
     * 记录ID - 主键 (整数型)
     */
    @TableId(type = IdType.AUTO)
    private Integer recordId;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 资源ID - 整数型
     */
    private Integer resourceId;

    /**
     * 用户ID - 整数型
     */
    private Integer userId;

    /**
     * 收藏标签ID - 关联到收藏夹表的folder_id (整数型)
     */
    private Integer folderId;

    /**
     * 收藏日期 - 日期时间类型
     */
    private LocalDateTime collectTime;

    /**
     * 收藏的内容类型 1 资讯表 2 项目表 3 国别表 4 供应商表
     */
    private Integer resourceType;

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
        Record other = (Record) that;
        return (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getResourceId() == null ? other.getResourceId() == null : this.getResourceId().equals(other.getResourceId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getFolderId() == null ? other.getFolderId() == null : this.getFolderId().equals(other.getFolderId()))
            && (this.getCollectTime() == null ? other.getCollectTime() == null : this.getCollectTime().equals(other.getCollectTime()))
            && (this.getResourceType() == null ? other.getResourceType() == null : this.getResourceType().equals(other.getResourceType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getResourceId() == null) ? 0 : getResourceId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getFolderId() == null) ? 0 : getFolderId().hashCode());
        result = prime * result + ((getCollectTime() == null) ? 0 : getCollectTime().hashCode());
        result = prime * result + ((getResourceType() == null) ? 0 : getResourceType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordId=").append(recordId);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", userId=").append(userId);
        sb.append(", folderId=").append(folderId);
        sb.append(", collectTime=").append(collectTime);
        sb.append(", resourceType=").append(resourceType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}