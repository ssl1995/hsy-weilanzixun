package com.azure.azure_merge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 收藏标签-用户关联表
 * @TableName folder_user
 */
@TableName(value ="folder_user")
@Data
public class FolderUser implements Serializable {
    /**
     * 关联收藏夹的id
     */
    @TableId(type = IdType.AUTO)
    private Integer folderId;

    /**
     * 关联的vips用户id
     */
    private Integer userId;

    /**
     * 创建日期
     */
    private LocalDateTime createDate;

    /**
     * 收藏夹名称
     */
    private String folderName;

    /**
     * 1表示删除 0未删除
     */
    private Integer isDeleted;

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
        FolderUser other = (FolderUser) that;
        return (this.getFolderId() == null ? other.getFolderId() == null : this.getFolderId().equals(other.getFolderId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getFolderName() == null ? other.getFolderName() == null : this.getFolderName().equals(other.getFolderName()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFolderId() == null) ? 0 : getFolderId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getFolderName() == null) ? 0 : getFolderName().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", folderId=").append(folderId);
        sb.append(", userId=").append(userId);
        sb.append(", createDate=").append(createDate);
        sb.append(", folderName=").append(folderName);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}