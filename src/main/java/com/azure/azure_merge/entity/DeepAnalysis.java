package com.azure.azure_merge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 深度分析
 * @TableName deep_analysis
 */
@TableName(value ="deep_analysis")
@Data
public class DeepAnalysis implements Serializable {
    /**
     * 深度分析id
     */
    @TableId(type = IdType.AUTO)
    private Integer deepAnalysisId;

    /**
     * 深度分析标题
     */
    private String deepAnalysisTitle;

    /**
     * 深度分析类别
     */
    private String deepAnalysisCategory;

    /**
     * 资讯管理员ID
     */
    private Integer adminId;

    /**
     * 上传时间
     */
    private LocalDateTime deepAnalysisEditTime;

    /**
     * 分析路径
     */
    private String analysisPath;

    /**
     * 是否收费(1表示收费，0表示免费)
     */
    private Integer isCharging;

    /**
     * 是否删除 (1表示未删除，0表示已删除)
     */
    private Integer isDeleted;

    /**
     * 价格
     */
    private Double deepAnalysisPrice;

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
        DeepAnalysis other = (DeepAnalysis) that;
        return (this.getDeepAnalysisId() == null ? other.getDeepAnalysisId() == null : this.getDeepAnalysisId().equals(other.getDeepAnalysisId()))
            && (this.getDeepAnalysisTitle() == null ? other.getDeepAnalysisTitle() == null : this.getDeepAnalysisTitle().equals(other.getDeepAnalysisTitle()))
            && (this.getDeepAnalysisCategory() == null ? other.getDeepAnalysisCategory() == null : this.getDeepAnalysisCategory().equals(other.getDeepAnalysisCategory()))
            && (this.getAdminId() == null ? other.getAdminId() == null : this.getAdminId().equals(other.getAdminId()))
            && (this.getDeepAnalysisEditTime() == null ? other.getDeepAnalysisEditTime() == null : this.getDeepAnalysisEditTime().equals(other.getDeepAnalysisEditTime()))
            && (this.getAnalysisPath() == null ? other.getAnalysisPath() == null : this.getAnalysisPath().equals(other.getAnalysisPath()))
            && (this.getIsCharging() == null ? other.getIsCharging() == null : this.getIsCharging().equals(other.getIsCharging()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getDeepAnalysisPrice() == null ? other.getDeepAnalysisPrice() == null : this.getDeepAnalysisPrice().equals(other.getDeepAnalysisPrice()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDeepAnalysisId() == null) ? 0 : getDeepAnalysisId().hashCode());
        result = prime * result + ((getDeepAnalysisTitle() == null) ? 0 : getDeepAnalysisTitle().hashCode());
        result = prime * result + ((getDeepAnalysisCategory() == null) ? 0 : getDeepAnalysisCategory().hashCode());
        result = prime * result + ((getAdminId() == null) ? 0 : getAdminId().hashCode());
        result = prime * result + ((getDeepAnalysisEditTime() == null) ? 0 : getDeepAnalysisEditTime().hashCode());
        result = prime * result + ((getAnalysisPath() == null) ? 0 : getAnalysisPath().hashCode());
        result = prime * result + ((getIsCharging() == null) ? 0 : getIsCharging().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getDeepAnalysisPrice() == null) ? 0 : getDeepAnalysisPrice().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deepAnalysisId=").append(deepAnalysisId);
        sb.append(", deepAnalysisTitle=").append(deepAnalysisTitle);
        sb.append(", deepAnalysisCategory=").append(deepAnalysisCategory);
        sb.append(", adminId=").append(adminId);
        sb.append(", deepAnalysisEditTime=").append(deepAnalysisEditTime);
        sb.append(", analysisPath=").append(analysisPath);
        sb.append(", isCharging=").append(isCharging);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", deepAnalysisPrice=").append(deepAnalysisPrice);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}