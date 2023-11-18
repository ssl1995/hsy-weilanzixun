package com.azure.azure_merge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 供应商
 * @TableName supplier
 */
@TableName(value ="supplier")
@Data
public class Supplier implements Serializable {
    /**
     * 供应商id
     */
    @TableId(type = IdType.AUTO)
    private Integer supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商logo路径
     */
    private String logoPath;

    /**
     * 供应商简介
     */
    private String supplierIntroduction;

    /**
     * 供应商类型
     */
    private String supplierType;

    /**
     * 供应商地址
     */
    private String supplierAddress;

    /**
     * 供应商所属国家id
     */
    private Integer country;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 导入时间
     */
    private LocalDateTime importTime;

    /**
     * 导入管理员id
     */
    private Integer importUserid;

    /**
     * 是否删除
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
        Supplier other = (Supplier) that;
        return (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getSupplierName() == null ? other.getSupplierName() == null : this.getSupplierName().equals(other.getSupplierName()))
            && (this.getLogoPath() == null ? other.getLogoPath() == null : this.getLogoPath().equals(other.getLogoPath()))
            && (this.getSupplierIntroduction() == null ? other.getSupplierIntroduction() == null : this.getSupplierIntroduction().equals(other.getSupplierIntroduction()))
            && (this.getSupplierType() == null ? other.getSupplierType() == null : this.getSupplierType().equals(other.getSupplierType()))
            && (this.getSupplierAddress() == null ? other.getSupplierAddress() == null : this.getSupplierAddress().equals(other.getSupplierAddress()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getContactPerson() == null ? other.getContactPerson() == null : this.getContactPerson().equals(other.getContactPerson()))
            && (this.getContactNumber() == null ? other.getContactNumber() == null : this.getContactNumber().equals(other.getContactNumber()))
            && (this.getFilePath() == null ? other.getFilePath() == null : this.getFilePath().equals(other.getFilePath()))
            && (this.getImportTime() == null ? other.getImportTime() == null : this.getImportTime().equals(other.getImportTime()))
            && (this.getImportUserid() == null ? other.getImportUserid() == null : this.getImportUserid().equals(other.getImportUserid()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());
        result = prime * result + ((getLogoPath() == null) ? 0 : getLogoPath().hashCode());
        result = prime * result + ((getSupplierIntroduction() == null) ? 0 : getSupplierIntroduction().hashCode());
        result = prime * result + ((getSupplierType() == null) ? 0 : getSupplierType().hashCode());
        result = prime * result + ((getSupplierAddress() == null) ? 0 : getSupplierAddress().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getContactPerson() == null) ? 0 : getContactPerson().hashCode());
        result = prime * result + ((getContactNumber() == null) ? 0 : getContactNumber().hashCode());
        result = prime * result + ((getFilePath() == null) ? 0 : getFilePath().hashCode());
        result = prime * result + ((getImportTime() == null) ? 0 : getImportTime().hashCode());
        result = prime * result + ((getImportUserid() == null) ? 0 : getImportUserid().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", supplierId=").append(supplierId);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", logoPath=").append(logoPath);
        sb.append(", supplierIntroduction=").append(supplierIntroduction);
        sb.append(", supplierType=").append(supplierType);
        sb.append(", supplierAddress=").append(supplierAddress);
        sb.append(", country=").append(country);
        sb.append(", contactPerson=").append(contactPerson);
        sb.append(", contactNumber=").append(contactNumber);
        sb.append(", filePath=").append(filePath);
        sb.append(", importTime=").append(importTime);
        sb.append(", importUserid=").append(importUserid);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}