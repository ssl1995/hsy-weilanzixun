package com.azure.azure_merge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 国家概况
 * @TableName country_detail
 */
@TableName(value ="country_detail")
@Data
public class CountryDetail implements Serializable {
    /**
     * 国家id
     */
    @TableId(type = IdType.AUTO)
    private Integer countryId;

    /**
     * 国家名称
     */
    private String countryName;

    /**
     * 政治体制
     */
    private String countryPolitics;

    /**
     * 执政党
     */
    private String rulingParty;

    /**
     * 主要在野党
     */
    private String oppositionParty;

    /**
     * 经济体量
     */
    private String economicVolume;

    /**
     * 对外关系
     */
    private String externalRelation;

    /**
     * 所属区域
     */
    private Integer regionId;

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
        CountryDetail other = (CountryDetail) that;
        return (this.getCountryId() == null ? other.getCountryId() == null : this.getCountryId().equals(other.getCountryId()))
            && (this.getCountryName() == null ? other.getCountryName() == null : this.getCountryName().equals(other.getCountryName()))
            && (this.getCountryPolitics() == null ? other.getCountryPolitics() == null : this.getCountryPolitics().equals(other.getCountryPolitics()))
            && (this.getRulingParty() == null ? other.getRulingParty() == null : this.getRulingParty().equals(other.getRulingParty()))
            && (this.getOppositionParty() == null ? other.getOppositionParty() == null : this.getOppositionParty().equals(other.getOppositionParty()))
            && (this.getEconomicVolume() == null ? other.getEconomicVolume() == null : this.getEconomicVolume().equals(other.getEconomicVolume()))
            && (this.getExternalRelation() == null ? other.getExternalRelation() == null : this.getExternalRelation().equals(other.getExternalRelation()))
            && (this.getRegionId() == null ? other.getRegionId() == null : this.getRegionId().equals(other.getRegionId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCountryId() == null) ? 0 : getCountryId().hashCode());
        result = prime * result + ((getCountryName() == null) ? 0 : getCountryName().hashCode());
        result = prime * result + ((getCountryPolitics() == null) ? 0 : getCountryPolitics().hashCode());
        result = prime * result + ((getRulingParty() == null) ? 0 : getRulingParty().hashCode());
        result = prime * result + ((getOppositionParty() == null) ? 0 : getOppositionParty().hashCode());
        result = prime * result + ((getEconomicVolume() == null) ? 0 : getEconomicVolume().hashCode());
        result = prime * result + ((getExternalRelation() == null) ? 0 : getExternalRelation().hashCode());
        result = prime * result + ((getRegionId() == null) ? 0 : getRegionId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", countryId=").append(countryId);
        sb.append(", countryName=").append(countryName);
        sb.append(", countryPolitics=").append(countryPolitics);
        sb.append(", rulingParty=").append(rulingParty);
        sb.append(", oppositionParty=").append(oppositionParty);
        sb.append(", economicVolume=").append(economicVolume);
        sb.append(", externalRelation=").append(externalRelation);
        sb.append(", regionId=").append(regionId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}