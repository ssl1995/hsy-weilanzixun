package com.azure.azure_merge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 行业表
 * @TableName industry
 */
@TableName(value ="industry")
@Data
public class Industry implements Serializable {
    /**
     * 行业id
     */
    @TableId(type = IdType.AUTO)
    private Integer industryId;

    /**
     * 行业名
     */
    private String industryName;

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
        Industry other = (Industry) that;
        return (this.getIndustryId() == null ? other.getIndustryId() == null : this.getIndustryId().equals(other.getIndustryId()))
            && (this.getIndustryName() == null ? other.getIndustryName() == null : this.getIndustryName().equals(other.getIndustryName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIndustryId() == null) ? 0 : getIndustryId().hashCode());
        result = prime * result + ((getIndustryName() == null) ? 0 : getIndustryName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", industryId=").append(industryId);
        sb.append(", industryName=").append(industryName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}